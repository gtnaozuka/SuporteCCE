<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Suporte CCE - Login</title>
        <link href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.servletContext.contextPath}/css/suporte-cce.css" rel="stylesheet">
    </head>
    <body class="body-uel">
        <%@include file="/view/include/navbarIndex.jsp" %>

        <div class="container-fluid">
            <div class="jumbotron">
                <h1 class="text-center jumbotron-header-uel">Suporte CCE</h1>
                <c:if test="${not empty sucesso}">
                    <div class="alert alert-success alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <strong><c:out value="${sucesso}"/></strong>
                    </div>
                </c:if>
                <c:if test="${not empty erro}">
                    <div class="alert alert-danger alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <strong><c:out value="${erro}"/></strong>
                    </div>
                </c:if>
                <form class="form-horizontal form-uel" role="form" action="${pageContext.servletContext.contextPath}/login" method="POST">
                    <div class="form-group">
                        <input type="text" class="form-control" id="inputMatriculaChapa" placeholder="Matrícula/Chapa funcional" name="matricula_chapa" required="true" maxlength="20">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" id="inputSenha" placeholder="Senha" name="senha" required="true" maxlength="20">
                        <div class="text-right" style="margin-bottom: 0">
                            <a class="btn link-uel" href="#">Esqueceu a senha?</a>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="text-center">
                            <button type="submit" class="btn btn-uel">Login</button>
                            <a class="btn link-uel" href="${pageContext.servletContext.contextPath}/pessoa/create">Cadastrar</a>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="${pageContext.servletContext.contextPath}/js/bootstrap.min.js" ></script>
    </body>
</html>
