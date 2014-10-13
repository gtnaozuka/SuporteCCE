<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--<c:if test="${empty sessionScope.pessoa}">
    <c:redirect context="${pageContext.servletContext.contextPath}" url="/"/>
</c:if>--%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Suporte CCE - Edição/Exclusão de técnicos</title>
        <link href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.servletContext.contextPath}/css/suporte-cce.css" rel="stylesheet">
    </head>
    <body class="body-uel">
        <%--<%@include file="/view/include/deleteAccount.jsp" %>
        <%@include file="/view/include/navbar.jsp"%>--%>

        <div class="container-fluid">
            <div class="jumbotron">
                <h2 class="text-center">Edição/Exclusão de técnicos</h2>
                <table class="table table-striped table-hover table-uel">
                    <thead>
                        <tr>
                            <th class="text-center">Chapa funcional</th>
                            <th class="text-center">Nome</th>
                            <th class="text-center">Email</th>
                            <th class="text-center" colspan='2'>Ação</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="t" items="${tecnicosList}">
                            <tr>
                                <td><c:out value="${t.matriculaChapa}"/></td>
                                <td><c:out value="${t.nome}"/></td>
                                <td><c:out value="${t.email}"/></td>
                                <td>
                                    <a href="#">
                                        <button type="button" class="btn btn-default btn-small">
                                            <span class="glyphicon glyphicon-edit"></span>
                                        </button>
                                    </a>
                                </td>
                                <td>
                                    <a class="tecnicos" data-toggle="modal" data-href="#">
                                        <button type="button" class="btn btn-default btn-small">
                                            <span class="glyphicon glyphicon-trash"></span>
                                        </button>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="${pageContext.servletContext.contextPath}/js/bootstrap.min.js" ></script>
    </body>
</html>
