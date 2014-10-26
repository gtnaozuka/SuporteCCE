package controller;

import com.google.gson.Gson;
import dao.PessoaDAO;
import dao.RequisicaoDAO;
import entity.Email;
import entity.Pessoa;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
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
    "/pessoa/delete"
})
public class PessoaController extends HttpServlet {

    public static final int USUARIO = 1;
    public static final int TECNICO = 2;
    public static final int ADMINISTRADOR = 3;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession(false);
        switch (request.getServletPath()) {
            case "":
                if (session == null || session.getAttribute("pessoa") == null) {
                    dispatcher = request.getRequestDispatcher("/index.jsp");
                    dispatcher.forward(request, response);
                } else {
                    Pessoa p = (Pessoa) session.getAttribute("pessoa");
                    RequisicaoDAO requisicaoDAO = new RequisicaoDAO();
                    PessoaDAO pessoaDAO = new PessoaDAO();
                    if (p.getTipo() == USUARIO) {
                        request.setAttribute("pendentesList", requisicaoDAO.listByStateAndUser(RequisicaoController.PENDENTE, p.getId()));
                        request.setAttribute("execucaoList", requisicaoDAO.listByStateAndUser(RequisicaoController.EXECUCAO, p.getId()));
                        request.setAttribute("esperaList", requisicaoDAO.listByStateAndUser(RequisicaoController.ESPERA, p.getId()));
                        request.setAttribute("concluidosList", requisicaoDAO.listByStateAndUser(RequisicaoController.CONCLUIDO, p.getId()));
                        dispatcher = request.getRequestDispatcher("/view/usuario/welcome.jsp");
                        dispatcher.forward(request, response);
                    } else if (p.getTipo() == TECNICO) {
                        request.setAttribute("pendentesList", requisicaoDAO.listByState(RequisicaoController.PENDENTE));
                        request.setAttribute("execucaoList", requisicaoDAO.listByState(RequisicaoController.EXECUCAO));
                        request.setAttribute("esperaList", requisicaoDAO.listByState(RequisicaoController.ESPERA));
                        request.setAttribute("concluidosList", requisicaoDAO.listByState(RequisicaoController.CONCLUIDO));
                        dispatcher = request.getRequestDispatcher("/view/tecnico/welcome.jsp");
                        dispatcher.forward(request, response);
                    } else if (p.getTipo() == ADMINISTRADOR) {
                        request.setAttribute("tecnicosList", pessoaDAO.listByType(TECNICO));
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
                PessoaDAO pessoaDAO = new PessoaDAO();
                Pessoa p = pessoaDAO.read(Integer.parseInt(request.getParameter("tecnico_id")));
                pessoaDAO.delete(p);
                request.setAttribute("sucesso", "Exclusão efetuada com sucesso!");
                dispatcher = request.getRequestDispatcher("/view/tecnico/index.jsp");
                dispatcher.forward(request, response);
                break;
            case "/tecnico/adm_update":
                pessoaDAO = new PessoaDAO();
                p = pessoaDAO.read(Integer.parseInt(request.getParameter("tecnico_id")));

                Gson gson = new Gson();
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
        PessoaDAO pessoaDAO = new PessoaDAO();
        RequisicaoDAO requisicaoDAO = new RequisicaoDAO();
        switch (request.getServletPath()) {
            case "":
                Pessoa p = new Pessoa();
                p.setMatriculaChapa(request.getParameter("matricula_chapa"));
                p.setSenha(request.getParameter("senha"));

                try {
                    p = pessoaDAO.authenticate(p);
                    setSessionPerson(request, p);

                    if (p.getTipo() == USUARIO) {
                        request.setAttribute("pendentesList", requisicaoDAO.listByStateAndUser(RequisicaoController.PENDENTE, p.getId()));
                        request.setAttribute("execucaoList", requisicaoDAO.listByStateAndUser(RequisicaoController.EXECUCAO, p.getId()));
                        request.setAttribute("esperaList", requisicaoDAO.listByStateAndUser(RequisicaoController.ESPERA, p.getId()));
                        request.setAttribute("concluidosList", requisicaoDAO.listByStateAndUser(RequisicaoController.CONCLUIDO, p.getId()));
                        dispatcher = request.getRequestDispatcher("/view/usuario/welcome.jsp");
                        dispatcher.forward(request, response);
                    } else if (p.getTipo() == TECNICO) {
                        request.setAttribute("pendentesList", requisicaoDAO.listByState(RequisicaoController.PENDENTE));
                        request.setAttribute("execucaoList", requisicaoDAO.listByState(RequisicaoController.EXECUCAO));
                        request.setAttribute("esperaList", requisicaoDAO.listByState(RequisicaoController.ESPERA));
                        request.setAttribute("concluidosList", requisicaoDAO.listByState(RequisicaoController.CONCLUIDO));
                        dispatcher = request.getRequestDispatcher("/view/tecnico/welcome.jsp");
                        dispatcher.forward(request, response);
                    } else if (p.getTipo() == ADMINISTRADOR) {
                        request.setAttribute("tecnicosList", pessoaDAO.listByType(TECNICO));
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
                p = pessoaDAO.read(Integer.parseInt(request.getParameter("id")));
                p.setMatriculaChapa(request.getParameter("chapa"));
                p.setSenha(request.getParameter("senha"));
                p.setNome(request.getParameter("nome"));
                p.setDepartamento(request.getParameter("departamento"));
                p.setEmail(request.getParameter("email"));

                if (pessoaDAO.save(p, false) != null) {
                    request.setAttribute("sucesso", "Alterações efetuadas com sucesso!");
                    dispatcher = request.getRequestDispatcher("/view/tecnico/index.jsp");
                    dispatcher.forward(request, response);
                } else {
                    request.setAttribute("erro", "Erro ao salvar.");
                    dispatcher = request.getRequestDispatcher("/view/tecnico/index.jsp");
                    dispatcher.forward(request, response);
                }
                break;
            case "/pessoa/forgot_password":
                
                String email = request.getParameter("email");
                PessoaDAO pdao = new PessoaDAO();
                Pessoa pessoa = pdao.readByEmail(email);
                if(pessoa != null) {
                    //Se o email existe
                    String newPassword = resetPassword();
                    pessoa.setSenha(newPassword);
                    pdao.save(pessoa, false); //altera senha
                    
                    /* -------------------------------------------------------------------- */
                    /* Envio de email */
                    
                        Email email1 = new Email();
                        email1.setSubject("Alteração de senha");
                        email1.setTo(email);
                        String conteudo = "Você esqueceu sua senha e requisitou uma nova. Acesse o sistema utilizando a combinação:\n"
                                + newPassword + "\n\n Atenção! Mensagem gerada automaticamente. Não é necessária resposta.";
                        email1.setContent(conteudo);
                        Email.sendEmail(email1);
                    
                    /* -------------------------------------------------------------------- */
                    
                    request.setAttribute("sucesso", "Email enviado com sucesso!");
                    dispatcher = request.getRequestDispatcher("/forgot_password.jsp");
                } else {
                    //Se o email nao existe
                    request.setAttribute("erro", "E-mail fornecido não encontrado na base.");
                    dispatcher = request.getRequestDispatcher("/forgot_password.jsp");
                }
                break;
            case "/pessoa/update_password":
                p = getSessionPerson(request);
                p.setSenha(request.getParameter("senha_atual"));

                try {
                    pessoaDAO.verifyPassword(p);
                    if (request.getParameter("nova_senha").equals(request.getParameter("confirm_senha"))) {
                        p.setSenha(request.getParameter("nova_senha"));
                        p = pessoaDAO.save(p, false);
                        if (p != null) {
                            setSessionPerson(request, p);
                            request.setAttribute("sucesso", "Senha alterada com sucesso!");
                            dispatcher = request.getRequestDispatcher("/view/update_password.jsp");
                            dispatcher.forward(request, response);
                        } else {
                            request.setAttribute("erro", "Erro ao salvar.");
                            dispatcher = request.getRequestDispatcher("/view/update_password.jsp");
                            dispatcher.forward(request, response);
                        }
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

                p = pessoaDAO.save(p, false);
                if (p != null) {
                    setSessionPerson(request, p);
                    dispatcher = request.getRequestDispatcher("/view/usuario/welcome.jsp");
                    dispatcher.forward(request, response);
                } else {
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

                p = pessoaDAO.save(p, true);
                if (p != null) {
                    setSessionPerson(request, p);
                    request.setAttribute("sucesso", "Alterações efetuadas com sucesso!");
                    dispatcher = request.getRequestDispatcher("/view/usuario/update.jsp");
                    dispatcher.forward(request, response);
                } else {
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

                p = pessoaDAO.save(p, false);
                if (p != null) {
                    request.setAttribute("sucesso", "Cadastro efetuado com sucesso!");
                    dispatcher = request.getRequestDispatcher("/view/tecnico/create.jsp");
                    dispatcher.forward(request, response);
                } else {
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

                p = pessoaDAO.save(p, true);
                if (p != null) {
                    setSessionPerson(request, p);
                    request.setAttribute("sucesso", "Alterações efetuadas com sucesso!");
                    dispatcher = request.getRequestDispatcher("/view/tecnico/update.jsp");
                    dispatcher.forward(request, response);
                } else {
                    request.setAttribute("erro", "Erro ao salvar.");
                    dispatcher = request.getRequestDispatcher("/view/tecnico/update.jsp");
                    dispatcher.forward(request, response);
                }
                break;
            case "/pessoa/delete":
                p = getSessionPerson(request);
                p.setSenha(request.getParameter("senha_atual"));

                try {
                    pessoaDAO.verifyPassword(p);
                    pessoaDAO.delete(p);

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

    private void setSessionPerson(HttpServletRequest request, Pessoa pessoa) {
        HttpSession session = request.getSession();
        session.setAttribute("pessoa", pessoa);
    }

    private String resetPassword() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(64, random).toString(32);
    }

}
