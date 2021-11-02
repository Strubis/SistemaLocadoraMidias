<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="cp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Excluir Classificação Etária</title>
        <link rel="stylesheet" href="${cp}/css/estilos.css" />
    </head>
    <body>
        <h1 class="title">Excluir Classificação Etária</h1>
        
        <form method="POST" action="${cp}/processaClassificacaoEtaria">
            <input name="acao" type="hidden" value="excluir" />
            <input name="id" type="hidden" value="${requestScope.classEtaria.id}" />
            
            <table class="tabelaListagem">
                <tr>
                    <td>Descrição:</td>
                    <td>${requestScope.classEtaria.descricao}</td>
                </tr>
                <tr>
                    <td>
                        <a href="${cp}/formularios/classificacaoEtaria/listagem.jsp">
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
