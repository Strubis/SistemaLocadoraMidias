<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="cp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Excluir Classificação Interna</title>
        <link rel="stylesheet" href="${cp}/css/estilos.css" />
    </head>
    <body>
        <h1>Excluir Classificação Interna</h1>
        
        <form method="POST" action="${cp}/processaClassificacaoInterna">
            <input type="hidden" name="acao" value="excluir" />
            <input type="hidden" name="id" value="${requestScope.classInterna.id}" />
            
            <table class="tabelaListagem">
                <tr>
                    <td>Descricao:</td>
                    <td>${requestScope.classInterna.descricao}</td>
                </tr>
                
                <tr>
                    <td>Valor do Aluguel:</td>
                    <td>${requestScope.classInterna.valorAluguel}</td>
                </tr>
                
                <tr>
                    <td>
                        <a href="${cp}/formularios/classificacaoInterna/listagem.jsp">
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
