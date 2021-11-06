<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Alterar Mídia</title>
        <link rel="stylesheet" href="${cp}/css/estilos.css" />
    </head>
    <body>
        <h1>Alterar Mídia</h1>
        
        <form method="POST" action="${cp}/processaMidia">
            <input name="acao" type="hidden" value="alterar"/>
            <input name="id" type="hidden" value="${requestScope.midia.id}"/>
            
            <table class="tabelaInserir">
                <tr>
                    <td>Título:</td>
                    <td>
                        <input name="titulo"
                               type="text"
                               size="30"
                               maxlength="100"
                               value="${requestScope.midia.titulo}"
                               required/>
                    </td>
                </tr>
                <tr>
                    <td>Ano de Lançamento:</td>
                    <td>
                        <input name="anoLancamento"
                               type="text"
                               size="10"
                               maxlength="100"
                               value="${requestScope.midia.anoLancamento}"
                               required/>
                    </td>
                </tr>
                <tr>
                    <td>Código de Barras:</td>
                    <td>
                        <input name="codBarras"
                               type="text"
                               size="13"
                               pattern="\d{13}"
                               placeholder="9999999999999"
                               value="${requestScope.midia.codigoBarras}"
                               required/>
                    </td>
                </tr>
                <tr>
                    <td>Duração em Minutos:</td>
                    <td>
                        <input name="durMin"
                               type="text"
                               size="5"
                               value="${requestScope.midia.duracaoMin}"
                               required/>
                    </td>
                </tr>
                <tr>
                    <td>Ator Principal:</td>
                    <td>
                        <jsp:useBean
                            id="servicosAap" 
                            class="locacaomidias.servicos.AtorAtrizServices" />
                        <select name="idAap" required>
                            <c:forEach items="${servicosAap.todos}" var="aap">
                                <c:choose>
                                    <c:when test="${requestScope.midia.idAtorAtrizPrincipal.id eq aap.id}">
                                        <option value="${aap.id}" selected>
                                            ${aap.nome} ${aap.sobrenome}
                                        </option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${aap.id}">
                                            ${aap.nome} ${aap.sobrenome}
                                        </option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Ator Coadjuvante:</td>
                    <td>
                        <select name="idAac" required>
                            <c:forEach items="${servicosAap.todos}" var="aac">
                                <c:choose>
                                    <c:when test="${requestScope.midia.idAtorAtrizCoadjuvante.id eq aac.id}">
                                        <option value="${aac.id}" selected>
                                            ${aac.nome} ${aac.sobrenome}
                                        </option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${aac.id}">
                                            ${aac.nome} ${aac.sobrenome}
                                        </option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Gênero:</td>
                    <td>
                        <jsp:useBean 
                            id="servicosGen" 
                            class="locacaomidias.servicos.GeneroServices"/>
                        <select name="idGen" required>
                            <c:forEach items="${servicosGen.todos}" var="gen">
                                <c:choose>
                                    <c:when test="${requestScope.midia.idGenero.id eq gen.id}">
                                        <option value="${gen.id}" selected>
                                            ${gen.descricao}
                                        </option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${gen.id}">
                                            ${gen.descricao}
                                        </option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Classificação Etária:</td>
                    <td>
                        <jsp:useBean 
                            id="servicosCe" 
                            class="locacaomidias.servicos.ClassificacaoEtariaServices"/>
                        <select name="idCe" required>
                            <c:forEach items="${servicosCe.todos}" var="ce">
                                <c:choose>
                                    <c:when test="${requestScope.midia.idClassEtaria.id eq ce.id}">
                                        <option value="${ce.id}" selected>
                                            ${ce.descricao}
                                        </option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${ce.id}">
                                            ${ce.descricao}
                                        </option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Tipo:</td>
                    <td>
                        <jsp:useBean 
                            id="servicosTipo" 
                            class="locacaomidias.servicos.TipoServices"/>
                        <select name="idTipo" required>
                            <c:forEach items="${servicosTipo.todos}" var="tipo">
                                <c:choose>
                                    <c:when test="${requestScope.midia.idTipo.id eq tipo.id}">
                                        <option value="${tipo.id}" selected>
                                            ${tipo.descricao}
                                        </option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${tipo.id}">
                                            ${tipo.descricao}
                                        </option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Classificação Interna:</td>
                    <td>
                        <jsp:useBean 
                            id="servicosCi" 
                            class="locacaomidias.servicos.ClassificacaoInternaServices"/>
                        <select name="idCi" required>
                            <c:forEach items="${servicosCi.todos}" var="ci">
                                <c:choose>
                                    <c:when test="${requestScope.midia.idClassInterna.id eq ci.id}">
                                        <option value="${ci.id}" selected>
                                            ${ci.descricao} - R$ 
                                            <fmt:formatNumber
                                                pattern="#.##"
                                                minIntegerDigits="1"
                                                minFractionDigits="2"
                                                maxFractionDigits="2">
                                              ${ci.valorAluguel}
                                            </fmt:formatNumber>
                                        </option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${ci.id}">
                                            ${ci.descricao} - R$ 
                                            <fmt:formatNumber
                                                pattern="#.##"
                                                minIntegerDigits="1"
                                                minFractionDigits="2"
                                                maxFractionDigits="2">
                                              ${ci.valorAluguel}
                                            </fmt:formatNumber>
                                        </option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="${cp}/formularios/midia/listagem.jsp">
                            Voltar
                        </a>
                    </td>
                    <td>
                        <input class="btn-redondo" type="submit" value="Alterar"/>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
