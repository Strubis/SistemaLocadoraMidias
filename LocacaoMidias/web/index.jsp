<%-- 
    Document   : index
    Created on : 27 de out. de 2021, 21:04:20
    Author     : EmersonPC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="${cp}/css/estilos.css"/>

        <title>Locadora de Mídias</title>
    </head>
    <body>
        <h1>Locadora de Mídias</h1>

        <p>
            <a href="${cp}/formularios/cidades/listagem.jsp">
                Cidades
            </a>
        </p>
        
        <p>
            <a href="${cp}/formularios/estados/listagem.jsp">
                Estados
            </a>
        </p>
        
        <p>
            <a href="${cp}/formularios/clientes/listagem.jsp">
                Clientes
            </a>
        </p>
        
        <p>
            <a href="${cp}/formularios/ator/listagem.jsp">
                Atores
            </a>
        </p>
        
        <p>
            <a href="${cp}/formularios/genero/listagem.jsp">
                Gêneros
            </a>
        </p>
        
        <p>
            <a href="${cp}/formularios/classificacaoEtaria/listagem.jsp">
                Classificações Etárias
            </a>
        </p>
        
        <p>
            <a href="${cp}/formularios/classificacaoInterna/listagem.jsp">
                Classificações Internas
            </a>
        </p>
        
        <p>
            <a href="${cp}/formularios/exemplar/listagem.jsp">
                Exemplares
            </a>
        </p>
        
        <p>
            <a href="${cp}/formularios/tipo/listagem.jsp">
                Tipos
            </a>
        </p>
        
        <p>
            <a href="${cp}/formularios/midia/listagem.jsp">
                Mídias
            </a>
        </p>

        <!-- Teste para animação
        <div id="animacao">
            <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="white" 
             class="bi bi-film" viewBox="0 0 16 16">
                <path d="M0 1a1 1 0 0 1 1-1h14a1 1 0 0 1 1 1v14a1 1 0 0 1-1 1H1a1 1 0 0 1-1-1V1zm4 0v6h8V1H4zm8 8H4v6h8V9zM1 1v2h2V1H1zm2 3H1v2h2V4zM1 7v2h2V7H1zm2 3H1v2h2v-2zm-2 3v2h2v-2H1zM15 1h-2v2h2V1zm-2 3v2h2V4h-2zm2 3h-2v2h2V7zm-2 3v2h2v-2h-2zm2 3h-2v2h2v-2z"/>
            </svg>
        </div> -->

    </body>
</html>
