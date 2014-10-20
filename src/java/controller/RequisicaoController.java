package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "RequisicaoController", urlPatterns = {"/RequisicaoController"})
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
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        switch (request.getServletPath()) {
            case "/requisicao/create":
                request.getParameter("tipo");
                request.getParameter("local");
                request.getParameter("fuel");
                request.getParameter("descricao");
			//Valida os roles.

                //Se a validacao deu certo
                request.setAttribute("sucesso", "Requisição enviada com sucesso!");
                dispatcher = request.getRequestDispatcher("/view/requisicao/create.jsp");

                //Se a validacao deu errado
                request.setAttribute("erro", "Erro ao enviar a requsição");
                dispatcher = request.getRequestDispatcher("/view/requisicao/create.jsp");
                break;
            case "/requisicao/accept":
                //Modal nao criado ainda...
                break;
        }
    }
}
