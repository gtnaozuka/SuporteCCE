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
        <title>Suporte CCE</title>
        <link href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.servletContext.contextPath}/css/suporte-cce.css" rel="stylesheet">
    </head>
    <body class="body-uel">
        <%@include file="/view/include/deleteAccount.jsp" %>        
        <%@include file="/view/include/navbarUsuario.jsp"%>

        <div class="container-fluid">
            <div class="jumbotron">
                <h2 class="text-center">Requisições</h2>
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
                <ul class="nav nav-tabs nav-uel" role="tablist" id="tabs">
                    <li id="tabPendentes" class='active'><a href="#Pendentes" role="tab" data-toggle="tab">Pendentes</a></li>
                    <li id="tabExecucao"><a href="#Execucao" role="tab" data-toggle="tab">Em execução</a></li>
                    <li id="tabEspera"><a href="#Espera" role="tab" data-toggle="tab">Em espera</a></li>
                    <li id="tabConcluido"><a href="#Concluido" role="tab" data-toggle="tab">Concluído</a></li>
                </ul>
                <div class="tab-content">
                    <div class="tab-pane fade in active" id="Pendentes">
                        <table class="table table-striped table-hover table-uel">
                            <thead>
                                <tr>
                                    <th class="text-center">Tipo</th>
                                    <th class="text-center">Local</th>
                                    <th class="text-center">Descrição</th>
                                    <th class="text-center">Data</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="r" items="${pendentesList}">
                                    <tr>
                                        <td><c:out value="${r.tipo}"/></td>
                                        <td><c:out value="${r.localizacao}"/></td>
                                        <td><c:out value="${r.descricao}"/></td>
                                        <td><c:out value="${r.dataCriacao}"/></td>
                                        <td>
                                            <a class="btn_requisicaoDelete" data-toggle="modal" data-href="${pageContext.servletContext.contextPath}/requisicao/delete?requisicao_id=${r.id}">
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
                    <div class="tab-pane fade" id="Execucao">
                        <table class="table table-striped table-hover table-uel">
                            <thead>
                                <tr>
                                    <th class="text-center">Tipo</th>
                                    <th class="text-center">Local</th>
                                    <th class="text-center">Descrição</th>
                                    <th class="text-center">Data</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="r" items="${execucaoList}">
                                    <tr>
                                        <td><c:out value="${r.tipo}"/></td>
                                        <td><c:out value="${r.localizacao}"/></td>
                                        <td><c:out value="${r.descricao}"/></td>
                                        <td><c:out value="${r.dataCriacao}"/></td>
                                        <td>
                                            <a class="btn_requisicaoDelete" data-toggle="modal" data-href="${pageContext.servletContext.contextPath}/requisicao/delete?requisicao_id=${r.id}">
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
                    <div class="tab-pane fade" id="Espera">
                        <table class="table table-striped table-hover table-uel">
                            <thead>
                                <tr>
                                    <th class="text-center">Tipo</th>
                                    <th class="text-center">Local</th>
                                    <th class="text-center">Descrição</th>
                                    <th class="text-center">Data</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="r" items="${esperaList}">
                                    <tr>
                                        <td><c:out value="${r.tipo}"/></td>
                                        <td><c:out value="${r.localizacao}"/></td>
                                        <td><c:out value="${r.descricao}"/></td>
                                        <td><c:out value="${r.dataCriacao}"/></td>
                                        <td>
                                            <a class="btn_requisicaoDelete" data-toggle="modal" data-href="${pageContext.servletContext.contextPath}/requisicao/delete?requisicao_id=${r.id}">
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
                    <div class="tab-pane fade" id="Concluído">
                        <table class="table table-striped table-hover table-uel">
                            <thead>
                                <tr>
                                    <th class="text-center">Tipo</th>
                                    <th class="text-center">Local</th>
                                    <th class="text-center">Descrição</th>
                                    <th class="text-center">Data</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="r" items="${concluidosList}">
                                    <tr>
                                        <td><c:out value="${r.tipo}"/></td>
                                        <td><c:out value="${r.localizacao}"/></td>
                                        <td><c:out value="${r.descricao}"/></td>
                                        <td><c:out value="${r.dataCriacao}"/></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="${pageContext.servletContext.contextPath}/js/bootstrap.min.js" ></script>
    </body>
</html>
