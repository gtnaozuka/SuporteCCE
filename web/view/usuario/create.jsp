<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Suporte CCE - Cadastro de usuário</title>
        <link href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.servletContext.contextPath}/css/suporte-cce.css" rel="stylesheet">
    </head>
    <body class="body-uel">
        <%@include file="/view/include/navbarIndex.jsp" %>

        <div class="container-fluid">
            <div class="jumbotron">
                <h2 class="text-center jumbotron-header-uel">Cadastro de usuário</h2>
                <c:if test="${not empty erro}">
                    <div class="alert alert-danger alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <strong><c:out value="${erro}"/></strong>
                    </div>
                </c:if>
                <form class="form-uel" role="form" action="${pageContext.servletContext.contextPath}/pessoa/create" method="POST">
                    <div class="form-group">
                        <label for="inputMatricula">Matrícula</label>
                        <input type="text" class="form-control" id="inputMatricula" placeholder="Insira seu número de matrícula" name="matricula" required="true" maxlength="20" pattern="[0-9]{6,20}">
                        <span class="help-block text-right">Mínimo 6 dígitos.</span>
                    </div>
                    <div class="form-group">
                        <label for="inputSenha">Senha</label>
                        <input type="password" class="form-control" id="inputSenha" placeholder="Insira sua senha" name="senha" required="true" maxlength="20" pattern=".{6,20}">
                        <span class="help-block text-right">Mínimo 6 caracteres.</span>
                    </div>
                    <div class="form-group">
                        <label for="inputNome">Nome</label>
                        <input type="text" class="form-control" id="inputNome" placeholder="Insira seu nome" name="nome" required="true" maxlength="20">
                    </div>
                    <div class="form-group">
                        <label for="inputDepartamento">Departamento</label>
                        <input type="text" class="form-control" id="inputDepartamento" placeholder="Insira seu departamento" name="departamento" required="true" maxlength="20">
                    </div>
                    <div class="form-group">
                        <label for="inputEmail">E-mail</label>
                        <input type="text" class="form-control" id="inputEmail" placeholder="Insira seu e-mail" name="email" required="true" maxlength="20">
                    </div>
                    <div class="form-group">
                        <div class="text-center">
                            <input type="submit" class="btn btn-uel" value="Cadastrar">
                            <a class="btn link-uel" href="${pageContext.servletContext.contextPath}/" role="button">Voltar</a>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="${pageContext.servletContext.contextPath}/js/bootstrap.min.js" ></script>
    </body>
</html>
