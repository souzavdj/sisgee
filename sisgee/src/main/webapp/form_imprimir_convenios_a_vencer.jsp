<%-- 
    Document   : form_imprimir_convenios_a_vencer
    Created on : 16/06/2018, 16:58:54
    Author     : vinicius
--%>

<!DOCTYPE html>
<%@page import="br.cefetrj.sisgee.view.utils.ConvenioUtils"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="import_head.jspf"%>
        <title><fmt:message key="br.cefetrj.sisgee.form_imprimir_convenios_a_vencer.titulo"/></title>
    </head>
    <body>
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
                            <c:choose>
                                <c:when test="${conv.isPessoaJuridica eq true }">
                                    <td>${ConvenioUtils.getCnpjEmpresaFormatado(conv.cpf_cnpj)}</td>
                                </c:when>
                                <c:otherwise>
                                    <td >${ConvenioUtils.getCpfFormatado(conv.cpf_cnpj)}</td>
                                </c:otherwise>    
                            </c:choose>    
                            <td>${conv.email}</td>
                            <td>${ConvenioUtils.getNumeroTelefoneFormatado(conv.telefone)}</td>
                            <td>${conv.pessoaContato}</td>

                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <%--<div align='center'>
            <button type="button" class="btn btn-primary" onclick="javascript:window.close()"><fmt:message key = "br.cefetrj.sisgee.resources.form.fechar"/></button>
        </div>--%>
    </body>
</html>
