<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="cp" value="${pageContext.request.contextPath}" />
<c:set var="prefixo" value="processaClassificacaoInterna?acao=preparar" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Classificações Internas</title>
        <link rel="stylesheet" href="${cp}/css/estilos.css" />
    </head>
    <body>
        <h1>Classificações Internas</h1>
        
        <p>
            <a href="${cp}/formularios/classificacaoInterna/novo.jsp">
                Nova Classificação Interna
            </a>
        </p>
        
        <table class="tabelaListagem">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Descrição</th>
                    <th>Valor do Aluguel</th>
                    <th>Alterar</th>
                    <th>Excluir</th>
                </tr>
            </thead>
            
            <tbody>
                <jsp:useBean 
                    id="servicos" 
                    class="locacaomidias.servicos.ClassificacaoInternaServices"/>
                
                <c:forEach items="${servicos.todos}" var="ci">
                    <tr>
                        <td>${ci.id}</td>
                        <td>${ci.descricao}</td>
                        <td>${ci.valorAluguel}</td>
                        <td>
                            <a href="${cp}/${prefixo}Alteracao&id=${ci.id}">
                                Alterar
                            </a>
                        </td>
                        <td>
                            <a href="${cp}/${prefixo}Exclusao&id=${ci.id}">
                                Excluir
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                
            </tbody>
        </table>
                
        <p>
            <a href="${cp}/formularios/classificacaoInterna/novo.jsp">
                Nova Classificação Interna
            </a>
        </p>
                
        <p>
            <a href="${cp}/index.jsp">
                Tela Principal
            </a>
        </p>
    </body>
</html>
