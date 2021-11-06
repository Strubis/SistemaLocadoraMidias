<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="${cp}/css/estilos.css"/>
        <title>Alterar Exemplar</title>
    </head>
    <body>
        <h1>Alterar Exemplar</h1>

        <form method="POST" action="${cp}/processaExemplar">
            <input type="hidden" name="acao" value="alterar" />
            <input type="hidden" name="id" value="${requestScope.exemplar.codigoInterno}" />

            <table class="tabelaInserir">
                <tr>
                    <td>Mídia: </td>
                    <td>
                        <jsp:useBean id="servicos" 
                                     class="locacaomidias.servicos.MidiaServices" />
                        <select name="idMidia" required>
                            <c:forEach items="${servicos.todos}" var="midia">
                                <c:choose>
                                    <c:when test="${requestScope.exemplar.midia.id eq midia.id}">
                                        <option value="${midia.id}" selected>
                                            ${midia.titulo}
                                        </option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${midia.id}">
                                            ${midia.titulo}
                                        </option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="${cp}/formularios/exemplar/listagem.jsp">
                            Voltar
                        </a>
                    </td>
                    <td>
                        <input class="btn-redondo" type="submit" value="Alterar"/>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
