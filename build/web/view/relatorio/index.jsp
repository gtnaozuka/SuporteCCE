<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${empty sessionScope.pessoa}">
    <c:redirect context="${pageContext.servletContext.contextPath}" url="/"/>
</c:if>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Suporte CCE - Geração de relatórios</title>
        <link href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.servletContext.contextPath}/css/suporte-cce.css" rel="stylesheet">
    </head>
    <body class="body-uel">
        <%@include file="/view/include/deleteAccount.jsp" %>
        <%@include file="/view/include/navbarTecnico.jsp" %>

        <div class="container-fluid">
            <div class="jumbotron">
                <h2 class="text-center jumbotron-header-uel">Geração de relatórios</h2>
                <c:if test="${not empty erro}">
                    <div class="alert alert-danger alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <strong><c:out value="${erro}"/></strong>
                    </div>
                </c:if>
                <form class="form-uel" role="form" action="${pageContext.servletContext.contextPath}/relatorio" method="POST">
                    <div class="form-group">
                        <label for="inputTecnico">Técnico</label>
                        <div class="input-group inputGroupTecnico">
                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">Selecione um técnico <span class="caret"></span></button>
                            <ul class="dropdown-menu">
                                <c:forEach var="t" items="${tecnicosList}">
                                    <li data-id="${t.id}"><a href="#"><c:out value="${t.nome}"/></a></li>
                                </c:forEach>
                            </ul>
                            <input type="hidden" class="form-control category" id="inputTecnico" name="tecnico" required="true">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputDataInicio">Data de início</label>
                        <input type="date" class="form-control" id="inputDataInicio" placeholder="Insira a data de início" name="data_inicio" required="true">
                    </div>
                    <div class="form-group">
                        <label for="inputDataTermino">Data de término</label>
                        <input type="date" class="form-control" id="inputDataTermino" placeholder="Insira a data de término" name="data_termino" required="true">
                    </div>
                    <div class="form-group">
                        <div class="text-center">
                            <input type="submit" class="btn btn-uel" value="Gerar relatório">
                            <a class="btn link-uel" href="${pageContext.servletContext.contextPath}/" role="button">Voltar</a>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="${pageContext.servletContext.contextPath}/js/bootstrap.min.js" ></script>
        <script src="${pageContext.servletContext.contextPath}/js/suporte-cce.js"></script>
    </body>
</html>
