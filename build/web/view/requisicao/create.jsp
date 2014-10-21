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
        <title>Suporte CCE - Criação de requisição</title>
        <link href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.servletContext.contextPath}/css/suporte-cce.css" rel="stylesheet">
    </head>
    <body class="body-uel">
        <%--<%@include file="/view/include/deleteAccount.jsp" %>
        <%@include file="/view/include/navbar.jsp" %>--%>

        <div class="container-fluid">
            <div class="jumbotron">
                <h2 class="text-center jumbotron-header-uel">Criação de requisição</h2>
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
                <form class="form-uel" role="form" action="${pageContext.servletContext.contextPath}/requisicao/create" method="POST">
                    <div class="control-group">
                        <label for="inputTipo" class="control-label">Tipo de requisição</label>
                        <div class="controls col-sm-offset-1">
                            <label class="radio">
                                <input type="radio" name="tipo" id="inputTipo" value="suporte" checked>
                                Suporte
                            </label>
                            <label class="radio">
                                <input type="radio" name="tipo" id="inputTipo" value="manutencao">
                                Manutenção
                            </label>
                            <label class="radio">
                                <input type="radio" name="tipo" id="inputTipo" value="outros">
                                Outros
                            </label>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="inputLocal" class="control-label">Local</label>
                        <div class="controls col-sm-offset-1">
                            <label class="radio">
                                <input type="radio" name="local" id="inputLocal" value="computacao" checked>
                                Departamento de Computação
                            </label>
                            <label class="radio">
                                <input type="radio" name="local" id="inputLocal" value="estatistica">
                                Departamento de Estatística
                            </label>
                            <label class="radio">
                                <input type="radio" name="local" id="inputLocal" value="fisica">
                                Departamento de Física
                            </label>
                            <label class="radio">
                                <input type="radio" name="local" id="inputLocal" value="geografia">
                                Departamento de Geografia
                            </label>
                            <label class="radio">
                                <input type="radio" name="local" id="inputLocal" value="quimica">
                                Departamento de Química
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputFuel">FUEL (opcional)</label>
                        <input type="text" class="form-control" id="inputFuel" placeholder="Insira o FUEL do equipamento (opcional)" name="fuel" maxlength="20" pattern="[0-9]{6,20}">
                        <span class="help-block text-right">Mínimo 6 dígitos.</span>
                    </div>
                    <div class="form-group">
                        <label for="inputDescricao">Descrição</label>
                        <textarea class="form-control" id="inputDescricao" placeholder="Insira a descrição do problema" name="descricao" rows="3" style="resize: none" required="true"></textarea>
                    </div>
                    <div class="form-group">
                        <div class="text-center">
                            <input type="submit" class="btn btn-uel" value="Enviar">
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
