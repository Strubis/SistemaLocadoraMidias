<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}" />
<c:set var="prefixo" value="processaClassificacaoEtaria?acao=preparar"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Classificações Etárias</title>
        <link rel="stylesheet" href="${cp}/css/estilos.css" />
    </head>
    <body>
        <h1 class="title">Classificações Etárias</h1>

        <p>
            <a href="${cp}/formularios/classificacaoEtaria/novo.jsp">
                Nova Classificação Etária
            </a>
        </p>

        <table class="tabelaListagem">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Descrição</th>
                    <th>Alterar</th>
                    <th>Excluir</th>
                </tr>
            </thead>

            <tbody>
                <jsp:useBean 
                    id="servicos" 
                    class="locacaomidias.servicos.ClassificacaoEtariaServices" />
                <c:forEach items="${servicos.todos}" var="classEtaria">
                    <tr>
                        <td>${classEtaria.id}</td>
                        <td>${classEtaria.descricao}</td>
                        <td>
                            <a href="${cp}/${prefixo}Alteracao&id=${classEtaria.id}">
                                Alterar
                            </a>
                        </td>
                        <td>
                            <a href="${cp}/${prefixo}Exclusao&id=${classEtaria.id}">
                                Excluir
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
                
        <p>
            <a href="${cp}/formularios/classificacaoEtaria/novo.jsp">
                Nova Classificação Etária
            </a>
        </p>
        
        <p><a href="${cp}/index.jsp">Tela Principal</a></p>
    </body>
</html>
