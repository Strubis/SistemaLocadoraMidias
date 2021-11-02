<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}" />
<c:set var="prefixo" value="processaExemplar?acao=preparar" />
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="${cp}/css/estilos.css"/>
        <title>Exemplares</title>
    </head>
    <body>
        <h1>Exemplares</h1>
        
        <p>
            <a href="${cp}/formularios/exemplar/novo.jsp">
                Novo Exemplar
            </a>
        </p>
        
        <table class="tabelaListagem">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Disponível</th>
                    <th>Título</th>
                    <th>Alterar</th>
                    <th>Excluir</th>
                </tr>
            </thead>
            
            <tbody>
                <jsp:useBean id="servicos" 
                             class="locacaomidias.servicos.ExemplarServices" />
                <c:forEach items="${servicos.todos}" var="exemplar">
                    <tr>
                        <td>${exemplar.codigoInterno}</td>
                        <td>
                            <c:choose>
                                <c:when test="${exemplar.disponivel}">
                                    Sim
                                </c:when>
                                <c:otherwise>
                                    Não
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${exemplar.midia.titulo}</td>
                        <td>
                            <a href="${cp}/${prefixo}Alteracao&id=${exemplar.codigoInterno}">
                                Alterar
                            </a>
                        </td>
                        <td>
                            <a href="${cp}/${prefixo}Exclusao&id=${exemplar.codigoInterno}">
                                Excluir
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <p>
            <a href="${cp}/formularios/exemplar/novo.jsp">
                Novo Exemplar
            </a>
        </p>
        <p>
            <a href="${cp}/index.jsp">
                Tela Principal
            </a>
        </p>
    </body>
</html>
