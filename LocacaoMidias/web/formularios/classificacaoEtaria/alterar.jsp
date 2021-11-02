<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alterar Classiicação Etária</title>
        <link rel="stylesheet" href="${cp}/css/estilos.css" />
    </head>
    <body>
        <h1 class="title">Alterar Classiicação Etária</h1>
        
        <form method="POST" action="${cp}/processaClassificacaoEtaria">
            <input name="acao" type="hidden" value="alterar" />
            <input name="id" type="hidden" value="${requestScope.classEtaria.id}" />

            <table class="tabelaInserir">
                <tr>
                    <td>Descrição:</td>
                    <td>
                        <input name="descricao" type="text" size="30" maxlength="30"
                               value="${requestScope.classEtaria.descricao}"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="${cp}/formularios/classificacaoEtaria/listagem.jsp">
                            Voltar
                        </a>
                    </td>
                    <td>
                        <input class="btn-redondo" type="submit" value="Alterar" />
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
