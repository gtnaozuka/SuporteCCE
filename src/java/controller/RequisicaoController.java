package controller;

import com.google.gson.Gson;
import dao.RequisicaoDAO;
import entity.Pessoa;
import entity.Requisicao;
import java.io.IOException;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "RequisicaoController", urlPatterns = {
    "/requisicao/create",
    "/requisicao/accept",
    "/requisicao/delete"})
public class RequisicaoController extends HttpServlet {

    public static final Integer PENDENTE = 1;
    public static final Integer EXECUCAO = 2;
    public static final Integer ESPERA = 3;
    public static final Integer CONCLUIDO = 4;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        switch (request.getServletPath()) {
            case "/requisicao/create":
                dispatcher = request.getRequestDispatcher("/view/requisicao/create.jsp");
                dispatcher.forward(request, response);
                break;
            case "/requisicao/delete":
                RequisicaoDAO rdao = new RequisicaoDAO();
                Requisicao r = rdao.read(Integer.parseInt(request.getParameter("requisicao_id")));
                Pessoa p = PessoaController.getSessionPerson(request);
                if (p.equals(r.getUsuarioId())) {
                    rdao.delete(r);
                    request.setAttribute("sucesso", "Exclusão efetuada com sucesso!");
                    dispatcher = request.getRequestDispatcher("/view/usuario/welcome.jsp");
                    dispatcher.forward(request, response);
                } else {
                    request.setAttribute("erro", "Falha ao apagar requisição!");
                    dispatcher = request.getRequestDispatcher("/view/usuario/welcome.jsp");
                    dispatcher.forward(request, response);
                }
                break;
            case "/requisicao/accept":
                rdao = new RequisicaoDAO();
                r = rdao.read(Integer.parseInt(request.getParameter("requisicao_id")));

                Gson gson = new Gson();
                response.getWriter().write(gson.toJson(r));
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        RequisicaoDAO rdao = new RequisicaoDAO();
        switch (request.getServletPath()) {
            case "/requisicao/create":
                Requisicao r = new Requisicao();
                r.setUsuarioId(PessoaController.getSessionPerson(request));
                r.setTipo(request.getParameter("tipo"));
                r.setLocalizacao(request.getParameter("local"));
                r.setFuel(Integer.parseInt(request.getParameter("fuel")));
                r.setDescricao(request.getParameter("descricao"));
                r.setEstado(PENDENTE);
                r.setDataCriacao(new Date()); //hora atual

                if (rdao.save(r) != null) {
                    request.setAttribute("sucesso", "Requisição enviada com sucesso!");
                    dispatcher = request.getRequestDispatcher("/view/requisicao/create.jsp");
                    dispatcher.forward(request, response);
                } else {
                    request.setAttribute("erro", "Erro ao enviar a requisição");
                    dispatcher = request.getRequestDispatcher("/view/requisicao/create.jsp");
                    dispatcher.forward(request, response);
                }

                break;
            case "/requisicao/accept":
                r = rdao.read(Integer.parseInt(request.getParameter("id")));
                r.setPrioridade(Integer.parseInt(request.getParameter("prioridade")));
                r.setObservacao(request.getParameter("observacao"));
                r.setTecnicoId(PessoaController.getSessionPerson(request));
                r.setEstado(EXECUCAO);

                if (rdao.save(r) != null) {

                } else {

                }

                //mensagem de resposta
                break;
        }
    }
}
