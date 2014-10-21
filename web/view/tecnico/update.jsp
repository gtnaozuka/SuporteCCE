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
        <title>Suporte CCE - Alteração de perfil</title>
        <link href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.servletContext.contextPath}/css/suporte-cce.css" rel="stylesheet">
    </head>
    <body class="body-uel">
        <%@include file="/view/include/deleteAccount.jsp" %>        
        <%@include file="/view/include/navbarTecnico.jsp"%>

        <div class="container-fluid">
            <div class="jumbotron">
                <h2 class="text-center jumbotron-header-uel">Alteração de perfil</h2>
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
                <form class="form-uel" role="form" action="${pageContext.servletContext.contextPath}/tecnico/update" method="POST">
                    <div class="form-group">
                        <label for="inputChapa">Chapa funcional</label>
                        <input type="text" class="form-control" id="inputChapa" placeholder="Insira seu número de chapa funcional" name="chapa" required="true" maxlength="20" pattern="[0-9]{6,20}" value="${sessionScope.pessoa.matricula_chapa}">
                        <span class="help-block text-right">Mínimo 6 dígitos.</span>
                    </div>
                    <div class="form-group">
                        <label for="inputSenha">Senha: <a class="btn btn-uel btn-xs" href="${pageContext.servletContext.contextPath}/pessoa/update_password">Alterar senha</a></label>
                    </div>
                    <div class="form-group">
                        <label for="inputNome">Nome</label>
                        <input type="text" class="form-control" id="inputNome" placeholder="Insira seu nome" name="nome" required="true" maxlength="20" value="${sessionScope.pessoa.nome}">
                    </div>
                    <div class="form-group">
                        <label for="inputDepartamento">Departamento (opcional)</label>
                        <input type="text" class="form-control" id="inputDepartamento" placeholder="Insira seu departamento (opcional)" name="departamento" required="true" maxlength="20" value="${sessionScope.pessoa.departamento}">
                    </div>
                    <div class="form-group">
                        <label for="inputEmail">E-mail</label>
                        <input type="text" class="form-control" id="inputEmail" placeholder="Insira seu e-mail" name="email" required="true" maxlength="20" value="${sessionScope.pessoa.email}">
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
