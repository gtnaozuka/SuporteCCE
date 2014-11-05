package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.RequisicaoDAO;
import entity.Email;
import entity.Pessoa;
import entity.Requisicao;
import java.io.IOException;
import java.util.Date;
import javax.persistence.PersistenceException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "RequisicaoController", urlPatterns = {
    "/requisicao/create",
    "/requisicao/accept",
    "/requisicao/delete",
    "/requisicao/update"})
public class RequisicaoController extends HttpServlet {

    public static final Integer PENDENTE = 1;
    public static final Integer EXECUCAO = 2;
    public static final Integer ESPERA = 3;
    public static final Integer CONCLUIDO = 4;
    public static final Integer BAIXA = 1;
    public static final Integer MEDIA = 2;
    public static final Integer ALTA = 3;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        RequisicaoDAO rDAO = new RequisicaoDAO();
        switch (request.getServletPath()) {
            case "/requisicao/create":
                dispatcher = request.getRequestDispatcher("/view/requisicao/create.jsp");
                dispatcher.forward(request, response);
                break;
            case "/requisicao/delete":
            case "/requisicao/accept":
            case "/requisicao/update":
                Requisicao r = rDAO.read(Integer.parseInt(request.getParameter("requisicao_id")));

                Gson gson = new GsonBuilder().setPrettyPrinting().setDateFormat("yyyy-MM-dd HH:mm:ss.SSS").excludeFieldsWithoutExposeAnnotation().create();
                response.setCharacterEncoding("utf-8");
                response.getWriter().write(gson.toJson(r));
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        RequisicaoDAO rDAO = new RequisicaoDAO();
        switch (request.getServletPath()) {
            case "/requisicao/create":
                Requisicao r = new Requisicao();
                r.setUsuarioId(PessoaController.getSessionPerson(request));
                r.setTipo(request.getParameter("tipo"));
                r.setLocalizacao(request.getParameter("local"));
                if (!request.getParameter("fuel").equals("")) {
                    r.setFuel(Integer.parseInt(request.getParameter("fuel")));
                }
                r.setDescricao(request.getParameter("descricao"));
                r.setEstado(PENDENTE);
                r.setDataCriacao(new Date()); //hora atual

                try {
                    rDAO.save(r);
                    request.setAttribute("sucesso", "Requisição enviada com sucesso!");
                    dispatcher = request.getRequestDispatcher("/view/requisicao/create.jsp");
                    dispatcher.forward(request, response);
                } catch (PersistenceException ex) {
                    request.setAttribute("erro", "Erro ao enviar a requisição");
                    dispatcher = request.getRequestDispatcher("/view/requisicao/create.jsp");
                    dispatcher.forward(request, response);
                }
                break;
            case "/requisicao/delete":
                r = rDAO.read(Integer.parseInt(request.getParameter("id")));
                Pessoa p = PessoaController.getSessionPerson(request);
                if (p.equals(r.getUsuarioId())) {
                    Pessoa tecnicoResponsavel = r.getTecnicoId();
                    rDAO.delete(r);
                    /* -------------------------------------------------------------------- */
                    /* Envio de e */
                    if (tecnicoResponsavel != null) {
                        Email e = new Email();
                        e.setSubject("Cancelamento de requisição");
                        e.setTo(tecnicoResponsavel.getEmail());
                        String conteudo = "A requisição " + "\"" + r.getDescricao() + "\", feita por " + r.getUsuarioId().getNome()
                                + " foi cancelada pelo usuário.\n\nAtenção: e-mail gerado automaticamente, não é necessária resposta.";
                        e.setContent(conteudo);
                        Email.sendEmail(e);
                    }
                    /* -------------------------------------------------------------------- */
                    request.setAttribute("sucesso", "Exclusão efetuada com sucesso!");
                    request.setAttribute("pendentesList", rDAO.listByStateAndUser(RequisicaoController.PENDENTE, p));
                    request.setAttribute("execucaoList", rDAO.listByStateAndUser(RequisicaoController.EXECUCAO, p));
                    request.setAttribute("esperaList", rDAO.listByStateAndUser(RequisicaoController.ESPERA, p));
                    request.setAttribute("concluidosList", rDAO.listByStateAndUser(RequisicaoController.CONCLUIDO, p));
                    dispatcher = request.getRequestDispatcher("/view/usuario/welcome.jsp");
                    dispatcher.forward(request, response);
                } else {
                    request.setAttribute("erro", "Falha ao apagar requisição!");
                    request.setAttribute("pendentesList", rDAO.listByStateAndUser(RequisicaoController.PENDENTE, p));
                    request.setAttribute("execucaoList", rDAO.listByStateAndUser(RequisicaoController.EXECUCAO, p));
                    request.setAttribute("esperaList", rDAO.listByStateAndUser(RequisicaoController.ESPERA, p));
                    request.setAttribute("concluidosList", rDAO.listByStateAndUser(RequisicaoController.CONCLUIDO, p));
                    dispatcher = request.getRequestDispatcher("/view/usuario/welcome.jsp");
                    dispatcher.forward(request, response);
                }
                break;
            case "/requisicao/accept":
                r = rDAO.read(Integer.parseInt(request.getParameter("id")));
                r.setPrioridade(Integer.parseInt(request.getParameter("prioridade")));
                r.setObservacao(request.getParameter("observacao"));
                r.setTecnicoId(PessoaController.getSessionPerson(request));
                r.setEstado(EXECUCAO);

                try {
                    rDAO.save(r);

                    /* -------------------------------------------------------------------- */
                    /* Envio de e */
                    Email e = new Email();
                    e.setSubject("Requisição analisada e aceita.");
                    e.setTo(r.getUsuarioId().getEmail());
                    String conteudo = "Sua requisição " + "\"" + r.getDescricao() + "\", foi aceita pelo técnico " + r.getTecnicoId().getNome()
                            + ". Aguarde pelo contato do profissional para a resolução do problema/dificuldade em questão.\n\nSuporte CCE UEL.\n\n"
                            + "Atenção: esse e-mail é gerado automaticamente, não é necessária resposta.";
                    e.setContent(conteudo);
                    Email.sendEmail(e);

                    /* -------------------------------------------------------------------- */
                    request.setAttribute("sucesso", "Requisição aceita com sucesso!");
                    request.setAttribute("pendentesList", rDAO.listByState(RequisicaoController.PENDENTE));
                    request.setAttribute("execucaoList", rDAO.listByState(RequisicaoController.EXECUCAO));
                    request.setAttribute("esperaList", rDAO.listByState(RequisicaoController.ESPERA));
                    request.setAttribute("concluidosList", rDAO.listByState(RequisicaoController.CONCLUIDO));
                    dispatcher = request.getRequestDispatcher("/view/tecnico/welcome.jsp");
                    dispatcher.forward(request, response);
                } catch (PersistenceException ex) {
                    request.setAttribute("erro", "Erro ao salvar.");
                    request.setAttribute("pendentesList", rDAO.listByState(RequisicaoController.PENDENTE));
                    request.setAttribute("execucaoList", rDAO.listByState(RequisicaoController.EXECUCAO));
                    request.setAttribute("esperaList", rDAO.listByState(RequisicaoController.ESPERA));
                    request.setAttribute("concluidosList", rDAO.listByState(RequisicaoController.CONCLUIDO));
                    dispatcher = request.getRequestDispatcher("/view/tecnico/welcome.jsp");
                    dispatcher.forward(request, response);
                }
                break;
            case "/requisicao/update":
                r = rDAO.read(Integer.parseInt(request.getParameter("id")));
                r.setPrioridade(Integer.parseInt(request.getParameter("prioridade")));
                r.setObservacao(request.getParameter("observacao"));
                r.setEstado(Integer.parseInt(request.getParameter("estado")));

                try {
                    rDAO.save(r);

                    /* -------------------------------------------------------------------- */
                    /* Envio de e */
                    /*Email e = new Email();
                    e.setSubject("Requisição analisada e aceita.");
                    e.setTo(r.getUsuarioId().getEmail());
                    String conteudo = "Sua requisição " + "\"" + r.getDescricao() + "\", foi aceita pelo técnico " + r.getTecnicoId().getNome()
                            + ". Aguarde pelo contato do profissional para a resolução do problema/dificuldade em questão.\n\nSuporte CCE UEL.\n\n"
                            + "Atenção: esse e-mail é gerado automaticamente, não é necessária resposta.";
                    e.setContent(conteudo);
                    Email.sendEmail(e);*/

                    /* -------------------------------------------------------------------- */
                    request.setAttribute("sucesso", "Requisição salva com sucesso!");
                    request.setAttribute("pendentesList", rDAO.listByState(RequisicaoController.PENDENTE));
                    request.setAttribute("execucaoList", rDAO.listByState(RequisicaoController.EXECUCAO));
                    request.setAttribute("esperaList", rDAO.listByState(RequisicaoController.ESPERA));
                    request.setAttribute("concluidosList", rDAO.listByState(RequisicaoController.CONCLUIDO));
                    dispatcher = request.getRequestDispatcher("/view/tecnico/welcome.jsp");
                    dispatcher.forward(request, response);
                } catch (PersistenceException ex) {
                    request.setAttribute("erro", "Erro ao salvar.");
                    request.setAttribute("pendentesList", rDAO.listByState(RequisicaoController.PENDENTE));
                    request.setAttribute("execucaoList", rDAO.listByState(RequisicaoController.EXECUCAO));
                    request.setAttribute("esperaList", rDAO.listByState(RequisicaoController.ESPERA));
                    request.setAttribute("concluidosList", rDAO.listByState(RequisicaoController.CONCLUIDO));
                    dispatcher = request.getRequestDispatcher("/view/tecnico/welcome.jsp");
                    dispatcher.forward(request, response);
                }
                break;
        }
    }
}
