<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="${cp}/css/estilos.css"/>
        <title>Excluir Exemplar</title>
    </head>
    <body>
        <h1>Excluir Exemplar</h1>
        
        <form method="POST" action="${cp}/processaExemplar">
            <input type="hidden" name="acao" value="excluir" />
            <input type="hidden" name="id" value="${requestScope.exemplar.codigoInterno}" />
            
            <table class="tabelaListagem">
            <tr>
                <td>Disponível:</td>
                <td>
                    <c:choose>
                        <c:when test="${requestScope.exemplar.disponivel}">
                            Sim
                        </c:when>
                        <c:otherwise>
                            Não
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <td>Mídia:</td>
                <td>${requestScope.exemplar.midia.titulo}</td>
            </tr>
            <tr>
                <td><a href="${cp}/formularios/exemplar/listagem.jsp">Voltar</a></td>
                <td><input class="btn-redondo" type="submit" value="Excluir"/></td>
            </tr>
        </table>
        </form>
    </body>
</html>
