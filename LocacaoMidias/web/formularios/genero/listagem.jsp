<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}" />
<c:set var="prefixo" value="processaGenero?acao=preparar"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gêneros</title>
        <link rel="stylesheet" href="${cp}/css/estilos.css" />
    </head>
    <body>
        <h1 class="title">Gêneros</h1>

        <p>
            <a href="${cp}/formularios/genero/novo.jsp">
                Novo Gênero
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
                    class="locacaomidias.servicos.GeneroServices" />
                <c:forEach items="${servicos.todos}" var="genero">
                    <tr>
                        <td>${genero.id}</td>
                        <td>${genero.descricao}</td>
                        <td>
                            <a href="${cp}/${prefixo}Alteracao&id=${genero.id}">
                                Alterar
                            </a>
                        </td>
                        <td>
                            <a href="${cp}/${prefixo}Exclusao&id=${genero.id}">
                                Excluir
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
                
        <p>
            <a href="${cp}/formularios/genero/novo.jsp">
                Novo Gênero
            </a>
        </p>
        
        <p><a href="${cp}/index.jsp">Tela Principal</a></p>
    </body>
</html>
