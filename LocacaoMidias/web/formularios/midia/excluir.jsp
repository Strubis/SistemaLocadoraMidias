<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Excluir Mídia</title>
        <link rel="stylesheet" href="${cp}/css/estilos.css" />
    </head>
    <body>
        <h1>Excluir Mídia</h1>
        
        <form method="POST" action="${cp}/processaMidia">
            <input name="acao" type="hidden" value="excluir"/>
            <input name="id" type="hidden" value="${requestScope.midia.id}"/>
            
            <table class="tabelaListagem">
                <tr>
                    <td>Título:</td>
                    <td>${midia.titulo}</td>
                </tr>
                
                <tr>
                    <td>Ano de Lançamento:</td>
                    <td>${midia.anoLancamento}</td>
                </tr>
                        
                <tr>
                    <td>Código de Barras:</td>
                    <td>${midia.codigoBarras}</td>
                </tr>       
                        
                <tr>
                    <td>Duração (min):</td>
                    <td>${midia.duracaoMin}</td>
                </tr>        
                        
                <tr>
                    <td>Ator Principal:</td>
                    <td>${midia.idAtorAtrizPrincipal.nome} ${midia.idAtorAtrizPrincipal.sobrenome}</td>
                </tr>
                        
                <tr>
                    <td>Ator Coadjuvante:</td>
                    <td>${midia.idAtorAtrizCoadjuvante.nome} ${midia.idAtorAtrizCoadjuvante.sobrenome}</td>
                </tr>
                        
                <tr>
                    <td>Gênero:</td>
                    <td>${midia.idGenero.descricao}</td>
                </tr>
                        
                <tr>
                    <td>Classificação Etária:</td>
                    <td>${midia.idClassEtaria.descricao}</td>
                </tr>
                        
                <tr>
                    <td>Tipo:</td>
                    <td>${midia.idTipo.descricao}</td>
                </tr>
                
                <tr>
                    <td>Classificação Interna:</td>
                    <td>
                        ${midia.idClassInterna.descricao} - R$ 
                        <fmt:formatNumber
                            pattern="#.##"
                            minIntegerDigits="1"
                            minFractionDigits="2"
                            maxFractionDigits="2">
                          ${midia.idClassInterna.valorAluguel}
                        </fmt:formatNumber>
                    </td>
                </tr>
                
                <tr>
                    <td>
                        <a href="${cp}/formularios/midia/listagem.jsp">
                            Voltar
                        </a>
                    </td>
                    <td>
                        <input class="btn-redondo" type="submit" value="Excluir"/>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
