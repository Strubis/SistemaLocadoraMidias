<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="${cp}/css/estilos.css" />
        <title>Nova Locação</title>
        
        <script src="${cp}/js/libs/jquery/jquery.min.js"></script>
        <script src="${cp}/js/libs/decimal.js/decimal.min.js"></script>
        <script src="${cp}/js/formularios/locacao/novo.js"></script>
    </head>
    <body>
        <h1>Nova Locação</h1>

        <form id="novaLocacao" method="POST" action="${cp}/processaLocacao">

            <input name="acao" type="hidden" value="inserir"/>

            <div id="divItensLocacao">
                <table class="tabelaInserir">
                    <tr>
                        <td>
                            <jsp:useBean 
                                id="servicosC" 
                                scope="page" 
                                class="locacaomidias.servicos.ClienteServices"/>

                            Cliente:
                            <select id="selectCliente" name="idCliente" required>
                                <c:forEach items="${servicosC.todos}" var="cliente">
                                    <option value="${cliente.id}">
                                        ${cliente.nome} ${cliente.sobrenome}
                                    </option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <jsp:useBean 
                                id="servicosEx" 
                                scope="page" 
                                class="locacaomidias.servicos.ExemplarServices"/>
                            Exemplar:
                            <select id="selectExemplar">
                                <c:forEach items="${servicosEx.todos}" var="exemplar">
                                    <c:choose>
                                        <c:when test="${exemplar.disponivel}">
                                            <fmt:formatNumber
                                                pattern="#.##"
                                                minIntegerDigits="1"
                                                minFractionDigits="2"
                                                maxFractionDigits="2"
                                                var="valorAluguel"
                                                scope="page"
                                                value="${exemplar.midia.idClassInterna.valorAluguel}"/>

                                            <option value="${exemplar.codigoInterno}"
                                                    data-valor="${valorAluguel}"
                                                    data-titulo="${exemplar.midia.titulo}">
                                                ${exemplar.midia.titulo}
                                                - R$ ${valorAluguel}
                                            </option>
                                        </c:when>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Data da Devolução:
                            <input id="dataDevolucao" name="dataFim"
                                   type="date"
                                   size="8"
                                   placeholder="dd/mm/yyyy"
                                   min="2021-11-12"
                                   required />
                        </td>
                        <td class="btnsItensLocacao">
                            <p>
                                <input id="btnInserir" type="button" value="&#x2795;">
                                <input id="btnRemover" type="button" value="&#x2796;">
                                <input id="btnLimpar" type="button" value="&#x274C;">
                            </p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Itens da Venda:
                            <br>
                            <select id="selectItensLocacao" size="10" multiple>
                            </select>
                            <br>
                            <div>
                                <div id="divTotal">Total: R$ 0,00</div>
                            </div>
                        </td>
                        <td>
                            <input class="btn-redondo" type="submit" value="Salvar"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p>
                                <a href="${cp}/formularios/locacao/listagem.jsp">
                                    Voltar
                                </a>
                            </p>
                        </td>
                    </tr>
                </table>
            </div>
        </form>

    </body>
</html>
