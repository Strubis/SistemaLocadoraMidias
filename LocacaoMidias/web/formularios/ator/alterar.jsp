<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Atualizar Dados do Ator</title>
        <link rel="stylesheet" href="${cp}/css/estilos.css" />
    </head>
    <body>
        <h1 class="title">Atualizar Dados do Ator</h1>
        
        <form method="POST" action="${cp}/processaAtorAtriz">
            <input name="acao" type="hidden" value="alterar" />
            <input name="id" type="hidden" value="${requestScope.ator.id}" />

            <table class="tabelaInserir">
                <tr>
                    <td>Nome:</td>
                    <td>
                        <input name="nome" type="text" size="25" maxlength="45"
                               value="${requestScope.ator.nome}"/>
                    </td>
                </tr>
                <tr>
                    <td>Sobrenome:</td>
                    <td>
                        <input name="sobrenome" type="text" size="25" maxlength="45"
                               value="${requestScope.ator.sobrenome}"/>
                    </td>
                </tr>
                <tr>
                    <td>Data de estreia:</td>
                    <td>
                        <input name="dataEstreia" type="date"
                               value="${requestScope.ator.dataEstreia}"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="${cp}/formularios/ator/listagem.jsp">
                            Voltar
                        </a>
                    </td>
                    <td>
                        <input class="btn-redondo" type="submit" value="Alterar" />
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
