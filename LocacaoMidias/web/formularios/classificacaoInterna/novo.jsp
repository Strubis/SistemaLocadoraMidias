<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Nova Classificação Interna</title>
        <link rel="stylesheet" href="${cp}/css/estilos.css" />
    </head>
    <body>
        <h1>Nova Classificação Interna</h1>
        
        <form method="POST" action="${cp}/processaClassificacaoInterna">
            <input name="acao" type="hidden" value="inserir" />
            
            <table class="tabelaInserir">
                <tr>
                    <td>Descrição:</td>
                    <td>
                        <input name="descricao" type="text" size="30" maxlength="45" required />
                    </td>
                </tr>
                
                <tr>
                    <td>Valor do Aluguel:</td>
                    <td>
                        <input name="valorAluguel" size="8" type="number" 
                               placeholder="R$ 9,99" step="0.01" min="0.1" required />
                    </td>
                </tr>
                
                <tr>
                    <td>
                        <a href="${cp}/formularios/classificacaoInterna/listagem.jsp">
                            Voltar
                        </a>
                    </td>
                    <td>
                        <input class="btn-redondo" type="submit" value="Salvar" />
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
