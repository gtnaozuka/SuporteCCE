package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.RequisicaoDAO;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
                /*PessoaDAO pdao = new PessoaDAO();
                 List<Pessoa> tecnicosList = pdao.listByType(PessoaController.TECNICO);
                 request.setAttribute("tecnicosList", tecnicosList);*/
                dispatcher = request.getRequestDispatcher("/view/relatorio/index.jsp");
                dispatcher.forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequisicaoDAO rDAO = new RequisicaoDAO();
        switch (request.getServletPath()) {
            case "/relatorio":
                String grafico = request.getParameter("grafico");
                String data_inicio = request.getParameter("data_inicio");
                String data_termino = request.getParameter("data_termino");
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    /*if (tecnico_id.equals("")) { //definir o que deve ser retornado
                     rDAO.listByTime(df.parse(request.getParameter("data_inicio")), df.parse(request.getParameter("data_termino")));
                     } else {
                     rDAO.listByTechnicalAndTime(pDAO.read(Integer.parseInt(tecnico_id)), df.parse(request.getParameter("data_inicio")), df.parse(request.getParameter("data_termino")));
                     }*/
                    Package pack = new Package();
                    pack.title = "Gráfico de requisições " + grafico.toLowerCase();
                    pack.subtitle = "Entre " + data_inicio + " e " + data_termino;

                    List<Object[]> objects = rDAO.listByChartAndTime(grafico, df.parse(data_inicio), df.parse(data_termino));
                    switch (grafico) {
                        case "Por fuel":
                            for (Object[] o : objects) {
                                o[0] = o[0].toString();
                            }
                            break;
                        case "Por estado":
                            for (Object[] o : objects) {
                                if (o[0].equals(RequisicaoController.PENDENTE)) {
                                    o[0] = "Pendente";
                                } else if (o[0].equals(RequisicaoController.EXECUCAO)) {
                                    o[0] = "Em execução";
                                } else if (o[0].equals(RequisicaoController.ESPERA)) {
                                    o[0] = "Em espera";
                                } else {
                                    o[0] = "Concluído";
                                }
                            }
                            break;
                        case "Por prioridade":
                            for (Object[] o : objects) {
                                if (o[0].equals(RequisicaoController.BAIXA)) {
                                    o[0] = "Baixa";
                                } else if (o[0].equals(RequisicaoController.MEDIA)) {
                                    o[0] = "Média";
                                } else {
                                    o[0] = "Alta";
                                }
                            }
                            break;
                    }
                    pack.data = objects;

                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    response.setCharacterEncoding("utf-8");
                    response.getWriter().write(gson.toJson(pack));
                } catch (ParseException ex) {
                    Logger.getLogger(RelatorioController.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        }
    }

    private static class Package {

        public String title;
        public String subtitle;
        public List<Object[]> data;
    }
}
