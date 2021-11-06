<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cp" value="${pageContext.request.contextPath}" />
<c:set var="prefixo" value="processaMidia?acao=preparar"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Mídias</title>
        <link rel="stylesheet" href="${cp}/css/estilos.css" />
    </head>
    <body>
        <h1>Mídias</h1>
        
        <p>
            <a href="${cp}/formularios/midia/novo.jsp">
                Nova Mídia
            </a>
        </p>
        
        <table class="tabelaListagem">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Título</th>
                    <th>Ano de Lançamento</th>
                    <th>Código de Barras</th>
                    <th>Duração (min)</th>
                    <th>Ator Principal</th>
                    <th>Ator Coadjuvante</th>
                    <th>Gênero</th>
                    <th>Classificação Etária</th>
                    <th>Tipo</th>
                    <th>Classificação Interna</th>
                    <th>Alterar</th>
                    <th>Excluir</th>
                </tr>
            </thead>
            
            <tbody>
                <jsp:useBean 
                    id="servicos" 
                    class="locacaomidias.servicos.MidiaServices"/>
                <c:forEach items="${servicos.todos}" var="midia">
                    <tr>
                        <td>${midia.id}</td>
                        <td>${midia.titulo}</td>
                        <td>${midia.anoLancamento}</td>
                        <td>${midia.codigoBarras}</td>
                        <td>${midia.duracaoMin}</td>
                        <td>${midia.idAtorAtrizPrincipal.nome} ${midia.idAtorAtrizPrincipal.sobrenome}</td>
                        <td>${midia.idAtorAtrizCoadjuvante.nome} ${midia.idAtorAtrizCoadjuvante.sobrenome}</td>
                        <td>${midia.idGenero.descricao}</td>
                        <td>${midia.idClassEtaria.descricao}</td>
                        <td>${midia.idTipo.descricao}</td>
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
                        <td>
                            <a href="${cp}/${prefixo}Alteracao&id=${midia.id}">
                              Alterar
                            </a>
                        </td>
                        <td>
                            <a href="${cp}/${prefixo}Exclusao&id=${midia.id}">
                              Excluir
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <p>
            <a href="${cp}/formularios/midia/novo.jsp">
                Nova Mídia
            </a>
        </p>
        
        <p><a href="${cp}/index.jsp">Tela Principal</a></p>
    </body>
</html>
