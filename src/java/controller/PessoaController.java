package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.PessoaDAO;
import dao.RequisicaoDAO;
import entity.Email;
import entity.Pessoa;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "PessoaController", urlPatterns = {
    "",
    "/pessoa/forgot_password",
    "/pessoa/update_password",
    "/usuario/create",
    "/usuario/update",
    "/tecnico/create",
    "/tecnico/update",
    "/tecnico/adm_delete",
    "/tecnico/adm_update",
    "/pessoa/delete",
    "/logout"
})
public class PessoaController extends HttpServlet {

    public static final int USUARIO = 1;
    public static final int TECNICO = 2;
    public static final int ADMINISTRADOR = 3;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession(false);
        PessoaDAO pDAO = new PessoaDAO();
        RequisicaoDAO rDAO = new RequisicaoDAO();
        switch (request.getServletPath()) {
            case "":
                if (session == null || session.getAttribute("pessoa") == null) {
                    dispatcher = request.getRequestDispatcher("/index.jsp");
                    dispatcher.forward(request, response);
                } else {
                    Pessoa p = (Pessoa) session.getAttribute("pessoa");
                    if (p.getTipo() == USUARIO) {
                        request.setAttribute("pendentesList", rDAO.listByStateAndUser(RequisicaoController.PENDENTE, p));
                        request.setAttribute("execucaoList", rDAO.listByStateAndUser(RequisicaoController.EXECUCAO, p));
                        request.setAttribute("esperaList", rDAO.listByStateAndUser(RequisicaoController.ESPERA, p));
                        request.setAttribute("concluidosList", rDAO.listByStateAndUser(RequisicaoController.CONCLUIDO, p));
                        dispatcher = request.getRequestDispatcher("/view/usuario/welcome.jsp");
                        dispatcher.forward(request, response);
                    } else if (p.getTipo() == TECNICO) {
                        request.setAttribute("pendentesList", rDAO.listByState(RequisicaoController.PENDENTE));
                        request.setAttribute("execucaoList", rDAO.listByState(RequisicaoController.EXECUCAO));
                        request.setAttribute("esperaList", rDAO.listByState(RequisicaoController.ESPERA));
                        request.setAttribute("concluidosList", rDAO.listByState(RequisicaoController.CONCLUIDO));
                        dispatcher = request.getRequestDispatcher("/view/tecnico/welcome.jsp");
                        dispatcher.forward(request, response);
                    } else if (p.getTipo() == ADMINISTRADOR) {
                        request.setAttribute("tecnicosList", pDAO.listByType(TECNICO));
                        dispatcher = request.getRequestDispatcher("/view/tecnico/index.jsp");
                        dispatcher.forward(request, response);
                    }
                }
                break;
            case "/pessoa/forgot_password":
                dispatcher = request.getRequestDispatcher("/forgot_password.jsp");
                dispatcher.forward(request, response);
                break;
            case "/pessoa/update_password":
                dispatcher = request.getRequestDispatcher("/view/update_password.jsp");
                dispatcher.forward(request, response);
                break;
            case "/usuario/create":
                dispatcher = request.getRequestDispatcher("/view/usuario/create.jsp");
                dispatcher.forward(request, response);
                break;
            case "/usuario/update":
                dispatcher = request.getRequestDispatcher("/view/usuario/update.jsp");
                dispatcher.forward(request, response);
                break;
            case "/tecnico/create":
                dispatcher = request.getRequestDispatcher("/view/tecnico/create.jsp");
                dispatcher.forward(request, response);
                break;
            case "/tecnico/update":
                dispatcher = request.getRequestDispatcher("/view/tecnico/update.jsp");
                dispatcher.forward(request, response);
                break;
            case "/tecnico/adm_delete":
                Pessoa p = pDAO.read(Integer.parseInt(request.getParameter("tecnico_id")));
                pDAO.delete(p);
                request.setAttribute("sucesso", "Exclusão efetuada com sucesso!");
                request.setAttribute("tecnicosList", pDAO.listByType(TECNICO));
                dispatcher = request.getRequestDispatcher("/view/tecnico/index.jsp");
                dispatcher.forward(request, response);
                break;
            case "/tecnico/adm_update":
                p = pDAO.read(Integer.parseInt(request.getParameter("tecnico_id")));

                Gson gson = new GsonBuilder().setPrettyPrinting().setDateFormat("yyyy-MM-dd HH:mm:ss.SSS").excludeFieldsWithoutExposeAnnotation().create();
                response.setCharacterEncoding("utf-8");
                response.getWriter().write(gson.toJson(p));
                break;
            case "/logout":
                setSessionPerson(request, null);
                dispatcher = request.getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        PessoaDAO pDAO = new PessoaDAO();
        RequisicaoDAO rDAO = new RequisicaoDAO();
        switch (request.getServletPath()) {
            case "":
                Pessoa p = new Pessoa();
                p.setMatriculaChapa(request.getParameter("matricula_chapa"));
                p.setSenha(request.getParameter("senha"));

                try {
                    p = pDAO.authenticate(p);
                    setSessionPerson(request, p);

                    if (p.getTipo() == USUARIO) {
                        request.setAttribute("pendentesList", rDAO.listByStateAndUser(RequisicaoController.PENDENTE, p));
                        request.setAttribute("execucaoList", rDAO.listByStateAndUser(RequisicaoController.EXECUCAO, p));
                        request.setAttribute("esperaList", rDAO.listByStateAndUser(RequisicaoController.ESPERA, p));
                        request.setAttribute("concluidosList", rDAO.listByStateAndUser(RequisicaoController.CONCLUIDO, p));
                        dispatcher = request.getRequestDispatcher("/view/usuario/welcome.jsp");
                        dispatcher.forward(request, response);
                    } else if (p.getTipo() == TECNICO) {
                        request.setAttribute("pendentesList", rDAO.listByState(RequisicaoController.PENDENTE));
                        request.setAttribute("execucaoList", rDAO.listByState(RequisicaoController.EXECUCAO));
                        request.setAttribute("esperaList", rDAO.listByState(RequisicaoController.ESPERA));
                        request.setAttribute("concluidosList", rDAO.listByState(RequisicaoController.CONCLUIDO));
                        dispatcher = request.getRequestDispatcher("/view/tecnico/welcome.jsp");
                        dispatcher.forward(request, response);
                    } else if (p.getTipo() == ADMINISTRADOR) {
                        request.setAttribute("tecnicosList", pDAO.listByType(TECNICO));
                        dispatcher = request.getRequestDispatcher("/view/tecnico/index.jsp");
                        dispatcher.forward(request, response);
                    }
                } catch (SecurityException ex) {
                    request.setAttribute("erro", ex.getMessage());
                    dispatcher = request.getRequestDispatcher("/index.jsp");
                    dispatcher.forward(request, response);
                }
                break;
            case "/tecnico/adm_update":
                p = pDAO.read(Integer.parseInt(request.getParameter("id")));
                p.setMatriculaChapa(request.getParameter("chapa"));
                p.setSenha(request.getParameter("senha"));
                p.setNome(request.getParameter("nome"));
                p.setDepartamento(request.getParameter("departamento"));
                p.setEmail(request.getParameter("email"));

                try {
                    pDAO.save(p, false);
                    request.setAttribute("sucesso", "Alterações efetuadas com sucesso!");
                    request.setAttribute("tecnicosList", pDAO.listByType(TECNICO));
                    dispatcher = request.getRequestDispatcher("/view/tecnico/index.jsp");
                    dispatcher.forward(request, response);
                } catch (PersistenceException ex) {
                    request.setAttribute("erro", "Erro ao salvar.");
                    request.setAttribute("tecnicosList", pDAO.listByType(TECNICO));
                    dispatcher = request.getRequestDispatcher("/view/tecnico/index.jsp");
                    dispatcher.forward(request, response);
                }
                break;
            case "/pessoa/forgot_password":
                String email = request.getParameter("email");
                try {
                    p = pDAO.readByEmail(email);
                    String newPassword = resetPassword();
                    p.setSenha(newPassword);
                    pDAO.save(p, false); //altera senha

                    /* -------------------------------------------------------------------- */
                    /* Envio de email */
                    Email e = new Email();
                    e.setSubject("Alteração de senha");
                    e.setTo(email);
                    String conteudo = "Você esqueceu sua senha e requisitou uma nova. Acesse o sistema utilizando a combinação:\n"
                            + newPassword + "\n\n Atenção! Mensagem gerada automaticamente. Não é necessária resposta.";
                    e.setContent(conteudo);
                    Email.sendEmail(e);

                    /* -------------------------------------------------------------------- */
                    request.setAttribute("sucesso", "Email enviado com sucesso!");
                    dispatcher = request.getRequestDispatcher("/forgot_password.jsp");
                    dispatcher.forward(request, response);
                } catch (NoResultException ex) {
                    request.setAttribute("erro", "E-mail fornecido não encontrado na base.");
                    dispatcher = request.getRequestDispatcher("/forgot_password.jsp");
                    dispatcher.forward(request, response);
                }
                break;
            case "/pessoa/update_password":
                p = getSessionPerson(request);
                p.setSenha(request.getParameter("senha_atual"));

                try {
                    pDAO.verifyPassword(p);
                    if (request.getParameter("nova_senha").equals(request.getParameter("confirm_senha"))) {
                        p.setSenha(request.getParameter("nova_senha"));
                        setSessionPerson(request, pDAO.save(p, false));
                        request.setAttribute("sucesso", "Senha alterada com sucesso!");
                        dispatcher = request.getRequestDispatcher("/view/update_password.jsp");
                        dispatcher.forward(request, response);
                    } else {
                        request.setAttribute("erro", "Nova senha e confirmação de senha não coincidem.");
                        dispatcher = request.getRequestDispatcher("/view/update_password.jsp");
                        dispatcher.forward(request, response);
                    }
                } catch (SecurityException ex) {
                    request.setAttribute("erro", ex.getMessage());
                    dispatcher = request.getRequestDispatcher("/view/update_password.jsp");
                    dispatcher.forward(request, response);
                }
                break;
            case "/usuario/create":
                p = new Pessoa();
                p.setMatriculaChapa(request.getParameter("matricula"));
                p.setSenha(request.getParameter("senha"));
                p.setNome(request.getParameter("nome"));
                p.setDepartamento(request.getParameter("departamento"));
                p.setEmail(request.getParameter("email"));
                p.setTipo(USUARIO);

                try {
                    setSessionPerson(request, pDAO.save(p, false));
                    dispatcher = request.getRequestDispatcher("/view/usuario/welcome.jsp");
                    dispatcher.forward(request, response);
                } catch (PersistenceException ex) {
                    request.setAttribute("erro", "Erro ao salvar.");
                    dispatcher = request.getRequestDispatcher("/view/usuario/create.jsp");
                    dispatcher.forward(request, response);
                }
                break;
            case "/usuario/update":
                p = getSessionPerson(request);
                p.setMatriculaChapa(request.getParameter("matricula"));
                p.setNome(request.getParameter("nome"));
                p.setDepartamento(request.getParameter("departamento"));
                p.setEmail(request.getParameter("email"));

                try {
                    setSessionPerson(request, pDAO.save(p, true));
                    request.setAttribute("sucesso", "Alterações efetuadas com sucesso!");
                    dispatcher = request.getRequestDispatcher("/view/usuario/update.jsp");
                    dispatcher.forward(request, response);
                } catch (PersistenceException ex) {
                    request.setAttribute("erro", "Erro ao salvar.");
                    dispatcher = request.getRequestDispatcher("/view/usuario/update.jsp");
                    dispatcher.forward(request, response);
                }
                break;
            case "/tecnico/create":
                p = new Pessoa();
                p.setMatriculaChapa(request.getParameter("chapa"));
                p.setSenha(request.getParameter("senha"));
                p.setNome(request.getParameter("nome"));
                p.setDepartamento(request.getParameter("departamento"));
                p.setEmail(request.getParameter("email"));
                p.setTipo(TECNICO);

                try {
                    pDAO.save(p, false);
                    request.setAttribute("sucesso", "Cadastro efetuado com sucesso!");
                    dispatcher = request.getRequestDispatcher("/view/tecnico/create.jsp");
                    dispatcher.forward(request, response);
                } catch (PersistenceException ex) {
                    request.setAttribute("erro", "Erro ao salvar.");
                    dispatcher = request.getRequestDispatcher("/view/tecnico/create.jsp");
                    dispatcher.forward(request, response);
                }
                break;
            case "/tecnico/update":
                p = getSessionPerson(request);
                p.setMatriculaChapa(request.getParameter("chapa"));
                p.setNome(request.getParameter("nome"));
                p.setDepartamento(request.getParameter("departamento"));
                p.setEmail(request.getParameter("email"));

                try {
                    setSessionPerson(request, pDAO.save(p, true));
                    request.setAttribute("sucesso", "Alterações efetuadas com sucesso!");
                    dispatcher = request.getRequestDispatcher("/view/tecnico/update.jsp");
                    dispatcher.forward(request, response);
                } catch (PersistenceException ex) {
                    request.setAttribute("erro", "Erro ao salvar.");
                    dispatcher = request.getRequestDispatcher("/view/tecnico/update.jsp");
                    dispatcher.forward(request, response);
                }
                break;
            case "/pessoa/delete":
                p = getSessionPerson(request);
                p.setSenha(request.getParameter("senha_atual"));

                try {
                    pDAO.verifyPassword(p);
                    pDAO.delete(p);

                    setSessionPerson(request, null);
                    request.setAttribute("sucesso", "Exclusão efetuada com sucesso!");
                    dispatcher = request.getRequestDispatcher("/index.jsp");
                    dispatcher.forward(request, response);
                } catch (SecurityException ex) {
                    //Pensar depois...
                }
                break;
        }
    }

    public static Pessoa getSessionPerson(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return (Pessoa) session.getAttribute("pessoa");
    }

    private void setSessionPerson(HttpServletRequest request, Pessoa p) {
        HttpSession session = request.getSession();
        session.setAttribute("pessoa", p);
    }

    private String resetPassword() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(64, random).toString(32);
    }

}
