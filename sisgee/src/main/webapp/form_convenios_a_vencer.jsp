<%-- 
    Document   : convenios_a_vencer
    Created on : 24/05/2018, 19:35:32
    Author     : vinicius
--%>

<!DOCTYPE html>
<%@page import="br.cefetrj.sisgee.view.utils.ConvenioUtils"%>
<html lang="en">
    <head>
        <%@include file="import_head.jspf"%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="br.cefetrj.sisgee.form_convenios_a_vencer.msg_titulo" /></title>
    </head>
    <body>
        <%@include file="import_navbar.jspf"%>
        <div class="container">
            <h4><fmt:message key="br.cefetrj.sisgee.form_convenios_a_vencer.msg_titulo" /></h4>
            <c:choose>
                <c:when test="${not empty ListaConveniosAVencer}">
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered">
                            <thead>
                                <tr class="table-active">
                                    <th><fmt:message key="br.cefetrj.sisgee.form_convenios_a_vencer.vigencia" /></th>
                                    <th><fmt:message key="br.cefetrj.sisgee.form_convenios_a_vencer.convenio" /></th>
                                    <th><fmt:message key="br.cefetrj.sisgee.form_convenios_a_vencer.nomeConveniado" /></th>
                                    <th><fmt:message key="br.cefetrj.sisgee.form_convenios_a_vencer.cpf_cnpj" /></th>
                                    <th><fmt:message key="br.cefetrj.sisgee.form_convenios_a_vencer.email" /></th>
                                    <th><fmt:message key="br.cefetrj.sisgee.form_convenios_a_vencer.telefone" /></th>
                                    <th><fmt:message key="br.cefetrj.sisgee.form_convenios_a_vencer.contato" /></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${ListaConveniosAVencer}" var="conv">
                                    <tr>
                                        <td>${ConvenioUtils.getVigencia(conv.dataAssinatura)}</td>
                                        <td>${ConvenioUtils.getNumeroConvenioFormatado(conv.numeroConvenio)}</td>
                                        <td>${conv.nomeConveniado}</td>
                                        <td>${ConvenioUtils.getIdConveniadoFormatado(conv.cpf_cnpj)}</td>    
                                        <td>${conv.email}</td>
                                        <td>${ConvenioUtils.getNumeroTelefoneFormatado(conv.telefone)}</td>
                                        <td>${conv.pessoaContato}</td>

                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>    
                    <div align='center'>
                        <button type="button" class="btn btn-primary" onclick="javascript:window.open('ConveniosVencerServlet?imprimir=sim')"><fmt:message key = "br.cefetrj.sisgee.relatorio.relatorio_consolidado.bt_imprimir"/></button>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="alert alert-warning" role="alert">
                            <h2>${ msg }</h2>
                    </div>
                </c:otherwise>
            </c:choose>
            <div class="text-center">
                <button type="button" class="btn btn-secondary mx-auto" onclick="javascript:location.href='index.jsp'"><fmt:message key = "br.cefetrj.sisgee.form_convenios_a_vencer.msg_cancelar"/></button>
            </div>
        </div>
                
        <%@include file="import_footer.jspf"%>
	<%@include file="import_finalbodyscripts.jspf"%>
    </body>
</html>
