<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Novo Tipo</title>
        <link rel="stylesheet" href="${cp}/css/estilos.css" />
    </head>
    <body>
        <h1>Novo Tipo</h1>
        
        <form method="POST" action="${cp}/processaTipos">
            <input type="hidden" name="acao" value="inserr" />
            
            <table class="tabelaInserir">
                <tr>
                    <td>Descrição:</td>
                    <td>
                        <input type="text" name="descricao" size="30" maxlength="45" required />
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="${cp}/formularios/tipo/listagem.jsp">Voltar</a>
                    </td>
                    <td>
                        <input class="btn-redondo" type="submit" value="Salvar" />
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
