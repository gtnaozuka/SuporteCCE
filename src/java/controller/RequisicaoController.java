package controller;

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
import javax.servlet.http.HttpSession;

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
                break;
            case "/requisicao/delete":
                //Modal nao criado ainda...

                RequisicaoDAO rdao = new RequisicaoDAO();
                Requisicao requisicao = rdao.read(Integer.parseInt(request.getParameter("id_requisicao")));
                HttpSession session = request.getSession(false);
                Pessoa pessoa = (Pessoa) session.getAttribute("pessoa");
                if (pessoa.equals(requisicao.getUsuarioId())) {
                    rdao.delete(requisicao);
                    response.getOutputStream().println("Requisição apagada com sucesso.");
                } else {
                    response.getOutputStream().println("Falha ao apagar requisição.");
                }
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        switch (request.getServletPath()) {
            case "/requisicao/create":
                Requisicao requisicao = new Requisicao();
                requisicao.setTipo(request.getParameter("tipo"));
                requisicao.setEstado(PENDENTE); //padrao como pendente?
                requisicao.setLocalizacao(request.getParameter("local"));
                if (request.getParameter("local") != null) {
                    requisicao.setFuel(Integer.parseInt(request.getParameter("fuel")));
                }
                requisicao.setDescricao(request.getParameter("descricao"));
                requisicao.setDataCriacao(new Date()); //hora atual

                HttpSession session = request.getSession(false);

                RequisicaoDAO rdao = new RequisicaoDAO();

                if (rdao.save(requisicao) != null) {
                    //Se a validacao deu certo
                    request.setAttribute("sucesso", "Requisição enviada com sucesso!");
                    dispatcher = request.getRequestDispatcher("/view/requisicao/create.jsp");

                } else {
                    //Se a validacao deu errado
                    request.setAttribute("erro", "Erro ao enviar a requisição");
                    dispatcher = request.getRequestDispatcher("/view/requisicao/create.jsp");

                }

                break;
            case "/requisicao/accept":
                //Modal nao criado ainda...
                RequisicaoDAO rdao2 = new RequisicaoDAO();
                Requisicao r = new Requisicao();
                HttpSession session1 = request.getSession(false);
                Pessoa p = (Pessoa) session1.getAttribute("pessoa");
                r = rdao2.read(Integer.parseInt(request.getParameter("requisicao_id")));
                r.setObservacao(request.getParameter("observacao"));
                r.setTecnicoId(p);
                r.setPrioridade(Integer.parseInt(request.getParameter("prioridade")));
                r.setEstado(EXECUCAO);

                if(rdao2.save(r) != null) {
                    
                } else {
                    
                }

                //mensagem de resposta
                break;
        }
    }
}
