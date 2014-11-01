package controller;

import dao.PessoaDAO;
import dao.RequisicaoDAO;
import entity.Pessoa;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                String tecnico_id = request.getParameter("tecnico");
                DateFormat df = DateFormat.getDateInstance();

                PessoaDAO pDAO = new PessoaDAO();
                RequisicaoDAO rDAO = new RequisicaoDAO();
                try {
                    if (tecnico_id.equals("")) { //definir o que deve ser retornado
                        rDAO.listByTime(df.parse(request.getParameter("data_inicio")), df.parse(request.getParameter("data_termino")));
                    } else {
                        rDAO.listByTechnicalAndTime(pDAO.read(Integer.parseInt(tecnico_id)), df.parse(request.getParameter("data_inicio")), df.parse(request.getParameter("data_termino")));
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(RelatorioController.class.getName()).log(Level.SEVERE, null, ex);
                }

                //Gerar relatorio
                //Pensar depois... Provavelmente enviar os dados gráficos via json.
                break;
        }
    }
}
