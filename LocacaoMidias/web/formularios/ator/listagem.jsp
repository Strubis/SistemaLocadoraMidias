<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<c:set var="prefixo" value="processaAtorAtriz?acao=preparar" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Atores</title>
        <link rel="stylesheet" href="${cp}/css/estilos.css">
    </head>
    <body>
        <h1 class="title">Atores</h1>
        
        <p>
            <a href="${cp}/formularios/ator/novo.jsp">Novo Ator</a>
        </p>
        
        <table class="tabelaListagem">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Nome</th>
                    <th>Sobrenome</th>
                    <th>Data de Estreia</th>
                    <th>Alterar</th>
                    <th>Excluir</th>
                </tr>
            </thead>
            <tbody>
                <jsp:useBean 
                    id="servicos"
                    class="locacaomidias.servicos.AtorAtrizServices"/>
                <c:forEach items="${servicos.todos}" var="ator">
                    <tr>
                        <td>${ator.id}</td>
                        <td>${ator.nome}</td>
                        <td>${ator.sobrenome}</td>
                        <td>
                            <fmt:formatDate 
                                pattern="dd/MM/yyyy" 
                                value="${ator.dataEstreia}" />
                        </td>
                        <td>
                            <a href="${cp}/${prefixo}Alteracao&id=${ator.id}">
                                Alterar
                            </a>
                        </td>
                        <td>
                            <a href="${cp}/${prefixo}Exclusao&id=${ator.id}">
                                Excluir
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
                
        <p>
            <a href="${cp}/formularios/ator/novo.jsp">Novo Ator</a>
        </p>
        
        <p>
            <a href="${cp}/index.jsp">Tela Principal</a>
        </p>
    </body>
</html>
