<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Nova Mídia</title>
        <link rel="stylesheet" href="${cp}/css/estilos.css" />
    </head>
    <body>
        <h1>Nova Mídia</h1>
        
        <form method="POST" action="${cp}/processaMidia">
            <input name="acao" type="hidden" value="inserir"/>
            
            <table class="tabelaInserir">
                <tr>
                    <td>Título:</td>
                    <td>
                        <input name="titulo"
                               type="text"
                               size="30"
                               maxlength="100"
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
                               required/>
                    </td>
                </tr>
                <tr>
                    <td>Duração em Minutos:</td>
                    <td>
                        <input name="durMin"
                               type="text"
                               size="5"
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
                                <option value="${aap.id}">
                                    ${aap.nome} ${aap.sobrenome}
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Ator Coadjuvante:</td>
                    <td>
                        <select name="idAac" required>
                            <c:forEach items="${servicosAap.todos}" var="aac">
                                <option value="${aac.id}">
                                    ${aac.nome} ${aac.sobrenome}
                                </option>
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
                                <option value="${gen.id}">
                                    ${gen.descricao}
                                </option>
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
                                <option value="${ce.id}">
                                    ${ce.descricao}
                                </option>
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
                                <option value="${tipo.id}">
                                    ${tipo.descricao}
                                </option>
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
                        <input class="btn-redondo" type="submit" value="Salvar"/>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
