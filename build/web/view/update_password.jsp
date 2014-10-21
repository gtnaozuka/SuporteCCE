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
        <title>Suporte CCE - Alteração de senha</title>
        <link href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.servletContext.contextPath}/css/suporte-cce.css" rel="stylesheet">
    </head>
    <body class="body-uel">
        <c:choose>
            <c:when test="${sessionScope.pessoa.tipo == 1}">
                <%@include file="/view/include/deleteAccount.jsp" %>        
                <%@include file="/view/include/navbarUsuario.jsp"%>
            </c:when>
            <c:when test="${sessionScope.pessoa.tipo == 2}">
                <%@include file="/view/include/deleteAccount.jsp" %>        
                <%@include file="/view/include/navbarTecnico.jsp"%>
            </c:when>
            <c:when test="${sessionScope.pessoa.tipo == 3}">      
                <%@include file="/view/include/navbarAdmin.jsp"%>
            </c:when>
        </c:choose>

        <div class="container-fluid">
            <div class="jumbotron">
                <h2 class="text-center jumbotron-header-uel">Alteração de senha</h2>
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
                <form class="form-uel" role="form" action="${pageContext.servletContext.contextPath}/pessoa/update_password" method="POST">
                    <div class="form-group">
                        <label for="inputSenhaAtual">Senha atual</label>
                        <input type="password" class="form-control" id="inputSenhaAtual" placeholder="Insira sua senha atual" name="senha_atual" required="true" maxlength="20">
                    </div>
                    <div class="form-group">
                        <label for="inputNovaSenha">Nova senha</label>
                        <input type="password" class="form-control" id="inputNovaSenha" placeholder="Insira sua nova senha" name="nova_senha" required="true" maxlength="20" pattern=".{6,20}">
                        <span class="help-block text-right">Mínimo 6 caracteres.</span>
                    </div>
                    <div class="form-group">
                        <label for="inputConfirmSenha">Confirmação de senha</label>
                        <input type="password" class="form-control" id="inputConfirmSenha" placeholder="Confirme sua nova senha" name="confirm_senha" required="true" maxlength="20">
                    </div>
                    <div class="form-group">
                        <div class="text-center">
                            <button type="submit" class="btn btn-uel">Salvar</button>
                            <a class="btn link-uel" href="${pageContext.servletContext.contextPath}/" role="button">Cancelar</a>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="${pageContext.servletContext.contextPath}/js/bootstrap.min.js" ></script>
    </body>
</html>
