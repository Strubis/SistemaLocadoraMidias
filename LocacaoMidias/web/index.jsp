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
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" 
              crossorigin="anonymous" />
        
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        
        <title>Locadora de Mídias</title>
    </head>
    <body class="bg-dark">
        <h1>Locadora de Mídias</h1>
        
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto" style="margin-left: auto; margin-right: auto">
                <li class="nav-item dropdown">
                  <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Pessoas
                  </a>
                  <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="${cp}/formularios/clientes/listagem.jsp">Cadastro de Clientes</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="${cp}/formularios/ator/listagem.jsp">Cadastro de Atores</a>
                  </div>
                </li>
                <li class="nav-item dropdown">
                  <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Localidades
                  </a>
                  <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="${cp}/formularios/cidades/listagem.jsp">Cadastro de Cidades</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="${cp}/formularios/estados/listagem.jsp">Cadastro de Estados</a>
                  </div>
                </li>
                <li class="nav-item dropdown">
                  <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Mídias
                  </a>
                  <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="${cp}/formularios/midia/listagem.jsp">Cadastro de Mídias</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="${cp}/formularios/tipo/listagem.jsp">Tipos de Mídia</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="${cp}/formularios/genero/listagem.jsp">Gêneros</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="${cp}/formularios/classificacaoEtaria/listagem.jsp">Classificações Etárias</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="${cp}/formularios/classificacaoInterna/listagem.jsp">Classificações Internas</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="${cp}/formularios/exemplar/listagem.jsp">Cadastro de Exemplar</a>
                  </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${cp}/formularios/locacao/listagem.jsp">Locações</a>
                </li>
              </ul>
            </div>
        </nav>
                
        <div class="container">
            <div id="demo" class="carousel slide" data-ride="carousel">
                <ul class="carousel-indicators">
                  <li data-target="#demo" data-slide-to="0" class="active"></li>
                  <li data-target="#demo" data-slide-to="1"></li>
                  <li data-target="#demo" data-slide-to="2"></li>
                </ul>
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img src="https://cdn.pixabay.com/photo/2016/01/29/03/11/movies-1167319_960_720.jpg" alt="Cadastro de Pessoas" width="1100" height="500">
                        <div class="carousel-caption">
                            <h3>Cadastro de Pessoas</h3>
                            <p>Cadastre seu cliente ou um ator/atriz preferido!</p>
                        </div>   
                    </div>
                    
                    <div class="carousel-item">
                        <img src="https://cdn.pixabay.com/photo/2016/09/14/08/18/film-1668918_960_720.jpg" alt="Chicago" width="1100" height="500">
                        <div class="carousel-caption">
                            <h3>Cadastro de Mídia</h3>
                            <p>Aqui você pode cadastrar aquela mídia preferida, além de conseguir locar depois!</p>
                        </div>   
                    </div>
                    
                    <div class="carousel-item">
                        <img src="https://cdn.pixabay.com/photo/2016/11/13/12/52/kuala-lumpur-1820944_960_720.jpg" alt="New York" width="1100" height="500">
                        <div class="carousel-caption">
                            <h3>Cadastro de Localidades</h3>
                            <p>Cadastre sua região ou a de alguém próximo!</p>
                        </div>   
                    </div>
                </div>
                
                <a class="carousel-control-prev" href="#demo" data-slide="prev">
                  <span class="carousel-control-prev-icon"></span>
                </a>
                <a class="carousel-control-next" href="#demo" data-slide="next">
                  <span class="carousel-control-next-icon"></span>
                </a>
            </div>
        </div>
                
        <!-- Teste para animação
        <div class="col-md-6" id="animacao">
            <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="white" 
                 class="bi bi-film" viewBox="0 0 16 16">
                <path d="M0 1a1 1 0 0 1 1-1h14a1 1 0 0 1 1 1v14a1 1 0 0 1-1 1H1a1 1 0 0 1-1-1V1zm4 0v6h8V1H4zm8 8H4v6h8V9zM1 1v2h2V1H1zm2 3H1v2h2V4zM1 7v2h2V7H1zm2 3H1v2h2v-2zm-2 3v2h2v-2H1zM15 1h-2v2h2V1zm-2 3v2h2V4h-2zm2 3h-2v2h2V7zm-2 3v2h2v-2h-2zm2 3h-2v2h2v-2z"/>
            </svg>
            <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="white" 
                 class="bi bi-disc" viewBox="0 0 16 16">
                <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                <path d="M10 8a2 2 0 1 1-4 0 2 2 0 0 1 4 0zM8 4a4 4 0 0 0-4 4 .5.5 0 0 1-1 0 5 5 0 0 1 5-5 .5.5 0 0 1 0 1zm4.5 3.5a.5.5 0 0 1 .5.5 5 5 0 0 1-5 5 .5.5 0 0 1 0-1 4 4 0 0 0 4-4 .5.5 0 0 1 .5-.5z"/>
            </svg>
        </div>
        -->
        
        <footer class="text-center bg-dark fixed-bottom" >
            <!-- Grid container -->
            <div class="container p-1"></div>
            <!-- Grid container -->

            <!-- Copyright -->
            <div class="text-center p-3 text-white">
              © 2021 Copyright:
              <a class="text-white" href="https://github.com/Strubis">Emerson Lima</a>
            </div>
            <!-- Copyright -->
        </footer>
    </body>
</html>
