package controller;

import entity.Pessoa;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "PessoaController", urlPatterns = {
                                    //"/",
                                    "/pessoa/forgot_password",
                                    "/pessoa/update_password",
                                    "/usuario/create",
                                    "/usuario/update",
                                    "/tecnico/create",
                                    "/tecnico/update",
                                    "/tecnico/adm_delete",
                                    "/tecnico/adm_update",
                                    "/pessoa/delete"})
public class PessoaController extends HttpServlet {

    private static final int USUARIO = 1;
    private static final int TECNICO = 2;
    private static final int ADMINISTRADOR = 3;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        HttpSession session = request.getSession(false);
        switch (request.getServletPath()) {
            case "/":
                if (request.getSession(false) == null) {
                    dispatcher = request.getRequestDispatcher("/index.jsp");
                } else {
                    Pessoa p = (Pessoa) session.getAttribute("pessoa");
                    if (p.getTipo() == USUARIO) {
//                        request.setAttribute("pendentesList", pendentesList);
//                        request.setAttribute("execucaoList", execucaoList);
//                        request.setAttribute("esperaList", esperaList);
//                        request.setAttribute("concluidosList", concluidoList);
                        dispatcher = request.getRequestDispatcher("/view/usuario/welcome.jsp");
                    } else if (p.getTipo() == TECNICO) {
//                        request.setAttribute("pendentesList", pendentesList);
//                        request.setAttribute("execucaoList", execucaoList);
//                        request.setAttribute("esperaList", esperaList);
//                        request.setAttribute("concluidosList", concluidoList);
                        dispatcher = request.getRequestDispatcher("/view/tecnico/welcome.jsp");
                    } else if (p.getTipo() == ADMINISTRADOR) {
//                        request.setAttribute("tecnicosList", tecnicosList);
                        dispatcher = request.getRequestDispatcher("/view/tecnico/index.jsp");
                    }
                }
                break;
            case "/pessoa/forgot_password":
                dispatcher = request.getRequestDispatcher("/forgot_password.jsp");
                break;
            case "/pessoa/update_password":
                dispatcher = request.getRequestDispatcher("/view/update_password.jsp");
                break;
            case "/usuario/create":
                dispatcher = request.getRequestDispatcher("/view/usuario/create.jsp");
                break;
            case "/usuario/update":
                dispatcher = request.getRequestDispatcher("/view/usuario/update.jsp");
                break;
            case "/tecnico/create":
                dispatcher = request.getRequestDispatcher("/view/tecnico/create.jsp");
                break;
            case "/tecnico/update":
                dispatcher = request.getRequestDispatcher("/view/tecnico/update.jsp");
                break;
            case "/tecnico/adm_delete":
                //Modal nao criado ainda...
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        switch (request.getServletPath()) {
            case "/":
                request.getParameter("matricula_chapa");
                request.getParameter("senha");
			//Valida os roles.

                //Se a validacao deu certo
//                Pessoa p = //bagulho do dao
                Pessoa p = new Pessoa();
                if (p.getTipo() == USUARIO) {
//                    request.setAttribute("pendentesList", pendentesList);
//                    request.setAttribute("execucaoList", execucaoList);
//                    request.setAttribute("esperaList", esperaList);
//                    request.setAttribute("concluidosList", concluidoList);
                    dispatcher = request.getRequestDispatcher("/view/usuario/welcome.jsp");
                } else if (p.getTipo() == TECNICO) {
//                    request.setAttribute("pendentesList", pendentesList);
//                    request.setAttribute("execucaoList", execucaoList);
//                    request.setAttribute("esperaList", esperaList);
//                    request.setAttribute("concluidosList", concluidoList);
                    dispatcher = request.getRequestDispatcher("/view/tecnico/welcome.jsp");
                } else if (p.getTipo() == ADMINISTRADOR) {
//                    request.setAttribute("tecnicosList", tecnicosList);
                    dispatcher = request.getRequestDispatcher("/view/tecnico/index.jsp");
                }

                //Se a validacao deu errado
//                request.setAttribute("erro", SQLException.getMessage());
                dispatcher = request.getRequestDispatcher("/index.jsp");
                break;
            case "/tecnico/adm_update":
                //Modal nao criado ainda...
                break;
            case "/pessoa/forgot_password":
                request.getParameter("email");
			//Valida os roles.

                //Se o email existe
                request.setAttribute("sucesso", "Email enviado com sucesso!");
                dispatcher = request.getRequestDispatcher("/forgot_password.jsp");

                //Se o email nao existe
//                request.setAttribute("erro", SQLException.getMessage());
                dispatcher = request.getRequestDispatcher("/forgot_password.jsp");
                break;
            case "/pessoa/update_password":
                request.getParameter("senha_atual");
                request.getParameter("nova_senha");
                request.getParameter("confirm_senha");
			//Valida os roles.

                //Se a validacao deu certo.
                request.setAttribute("sucesso", "Senha alterada com sucesso!");
                dispatcher = request.getRequestDispatcher("/view/update_password.jsp");

                //Se a vlidacao deu errado.
//                request.setAttribute("erro", SQLException.getMessage());
                dispatcher = request.getRequestDispatcher("/view/update_password.jsp");
                break;
            case "/usuario/create":
                request.getParameter("matricula");
                request.getParameter("senha");
                request.getParameter("nome");
                request.getParameter("departamento");
                request.getParameter("email");
			//Valida os roles.

                //Se a validacao deu certo
                dispatcher = request.getRequestDispatcher("/view/usuario/welcome.jsp");

                //Se a validacao deu errado
//                request.setAttribute("erro", SQLException.getMessage());
                dispatcher = request.getRequestDispatcher("/view/usuario/create.jsp");
                break;
            case "/usuario/update":
                request.getParameter("matricula");
                request.getParameter("nome");
                request.getParameter("departamento");
                request.getParameter("email");
			//Valida os roles.

                //Se a validacao deu certo
                request.setAttribute("sucesso", "Alterações efetuadas com sucesso!");
                dispatcher = request.getRequestDispatcher("/view/usuario/update.jsp");

                //Se a validacao deu errado
//                request.setAttribute("erro", SQLException.getMessage());
                dispatcher = request.getRequestDispatcher("/view/usuario/update.jsp");
                break;
            case "/tecnico/create":
                request.getParameter("chapa");
                request.getParameter("senha");
                request.getParameter("nome");
                request.getParameter("departamento");
                request.getParameter("email");
			//Valida os roles.

                //Se a validacao deu certo.
                request.setAttribute("sucesso", "Cadastro efetuado com sucesso!");
                dispatcher = request.getRequestDispatcher("/view/tecnico/create.jsp");

                //Se a validacao deu errado
//                request.setAttribute("erro", SQLException.getMessage());
                dispatcher = request.getRequestDispatcher("/view/tecnico/create.jsp");
                break;
            case "/tecnico/update":
                request.getParameter("chapa");
                request.getParameter("nome");
                request.getParameter("departamento");
                request.getParameter("email");
			//Valida os roles.

                //Se a validacao deu certo.
                request.setAttribute("sucesso", "Alterações efetuadas com sucesso!");
                dispatcher = request.getRequestDispatcher("/view/tecnico/update.jsp");

                //Se a validacao deu errado
//                request.setAttribute("erro", SQLException.getMessage());
                dispatcher = request.getRequestDispatcher("/view/tecnico/update.jsp");
                break;
            case "/pessoa/delete":
                request.getParameter("senha_atual");
			//Valida os roles.

                //Se a validacao deu certo
                request.setAttribute("sucesso", "Exclusão efetuada com sucesso!");
                dispatcher = request.getRequestDispatcher("/index.jsp");

			//Se a validacao deu errado
                //Pensar depois...
                break;
        }
    }
}
