package controller;

import dao.PessoaDAO;
import entity.Pessoa;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "RelatorioController", urlPatterns = {"/relatorio"})
public class RelatorioController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        switch (request.getServletPath()) {
            case "/relatorio":
                PessoaDAO pdao = new PessoaDAO();
                List<Pessoa> tecnicosList = pdao.listByType(PessoaController.TECNICO);
                request.setAttribute("tecnicosList", tecnicosList);
                dispatcher = request.getRequestDispatcher("/view/relatorio/index.jsp");
                dispatcher.forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch (request.getServletPath()) {
            case "/relatorio":
                /*request.getParameter("tecnico");
                 request.getParameter("data_inicio");
                 request.getParameter("data_termino");
                 //Gerar relatorio
                 //Pensar depois... Provavelmente enviar os dados gráficos via json.*/
                break;
        }
    }
}
