<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Excluir Tipo</title>
        <link rel="stylesheet" href="${cp}/css/estilos.css" />
    </head>
    <body>
        <h1>Excluir Tipo</h1>
        
        <form method="POST" action="${cp}/processaTipos">
            <input name="acao" type="hidden" value="excluir" />
            <input name="id" type="hidden" value="${requestScope.tipo.id}" />
            
            <table class="tabelaListagem">
                <tr>
                    <td>Descrição:</td>
                    <td>${requestScope.tipo.descricao}</td>
                </tr>
                <tr>
                    <td>
                        <a href="${cp}/formularios/tipo/listagem.jsp">
                            Voltar
                        </a>
                    </td>
                    <td>
                        <input class="btn-redondo" type="submit" value="Excluir" />
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
