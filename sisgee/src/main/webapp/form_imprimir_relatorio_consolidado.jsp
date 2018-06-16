<%-- 
    Document   : form_imprimir_relatorio_consolidado
    Created on : 16/06/2018, 01:23:55
    Author     : vinicius
--%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="import_head.jspf"%>
        <title><fmt:message key="br.cefetrj.sisgee.relatorio.form_imprimir_relatorio_consolidado_titulo"/></title>
    </head>
    <body>
        <c:forEach items="${ relatorio }" var="relatorio">
                <table class="table table-hover" style="width: 100%; margin-top: 20px;">
                    <thead>
                        <tr>
                            <th>${ relatorio.nomeCurso }</th>
                            <th>Total</th>
                        </tr>
                    </thead>
                    <tbody>		
                        <tr>
                            <td><fmt:message key="br.cefetrj.sisgee.relatorio.relatorio_consolidado.tabela_resultado.qnt_termoestagio"/></td>
                            <td>${ relatorio.qntTermoEstagio }</td>
                        </tr>
                        <tr>
                            <td><fmt:message key="br.cefetrj.sisgee.relatorio.relatorio_consolidado.tabela_resultado.qnt_termoaditivo"/></td>
                            <td>${ relatorio.qntTermoAditivo }</td>
                        </tr>
                        <tr>
                            <td><fmt:message key="br.cefetrj.sisgee.relatorio.relatorio_consolidado.tabela_resultado.qnt_rescisao" /></td>
                            <td>${ relatorio.qntRescReg }</td>
                        </tr>
                    </tbody>
                </table>
        </c:forEach>
        <%--<div align='center'>
            <button type="button" class="btn btn-primary" onclick="javascript:window.close()"><fmt:message key = "br.cefetrj.sisgee.resources.form.fechar"/></button>
        </div>--%>    
    </body>
</html>
