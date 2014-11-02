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
        <title>Suporte CCE - Cadastro de técnico</title>
        <link href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.servletContext.contextPath}/css/suporte-cce.css" rel="stylesheet">
    </head>
    <body class="body-uel">
        <%@include file="/view/include/navbarAdmin.jsp" %>

        <div class="container-fluid">
            <div class="jumbotron">
                <h2 class="text-center jumbotron-header-uel">Cadastro de técnico</h2>
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
                <form class="form-uel" role="form" action="${pageContext.servletContext.contextPath}/tecnico/create" method="POST">
                    <div class="form-group">
                        <label for="inputChapa">Chapa funcional</label>
                        <input type="text" class="form-control" id="inputChapa" placeholder="Insira seu número de chapa funcional" name="chapa" required="true" maxlength="20" pattern="[0-9]{6,20}">
                        <span class="help-block text-right">Mínimo 6 dígitos.</span>
                    </div>
                    <div class="form-group">
                        <label for="inputSenha">Senha</label>
                        <input type="password" class="form-control" id="inputSenha" placeholder="Insira sua senha" name="senha" required="true" maxlength="20" pattern=".{6,20}">
                        <span class="help-block text-right">Mínimo 6 caracteres.</span>
                    </div>
                    <div class="form-group">
                        <label for="inputNome">Nome</label>
                        <input type="text" class="form-control" id="inputNome" placeholder="Insira seu nome" name="nome" required="true" maxlength="40">
                    </div>
                    <div class="form-group">
                        <label for="inputLocal">Departamento (opcional)</label>
                        <div class="input-group inputGroupLocal">
                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">Selecione um departamento (opcional) <span class="caret"></span></button>
                            <ul class="dropdown-menu">
                                <li><a href="#">Departamento de Computação</a></li>
                                <li><a href="#">Departamento de Estatística</a></li>
                                <li><a href="#">Departamento de Física</a></li>
                                <li><a href="#">Departamento de Geografia</a></li>
                                <li><a href="#">Departamento de Química</a></li>
                            </ul>
                            <input type="hidden" class="form-control category" id="inputLocal" name="departamento">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputEmail">E-mail</label>
                        <input type="text" class="form-control" id="inputEmail" placeholder="Insira seu e-mail" name="email" required="true" maxlength="40">
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
        <script src="${pageContext.servletContext.contextPath}/js/suporte-cce.js" ></script>
    </body>
</html>
