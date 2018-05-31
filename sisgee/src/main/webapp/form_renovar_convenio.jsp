<%-- 
    Document   : form_renovar_convenio
    Created on : 24/05/2018, 19:15:33
    Author     : vinicius
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
    <html>
        <head>
            <%@include file="import_head.jspf"%>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title><fmt:message key="br.cefetrj.sisgee.form_renovar_convenio.msg_titulo" /></title>
        </head>
        <body>
            <%@include file="import_navbar.jspf"%>
            <div class="container">
                <c:if test="${ not empty msg }">
                    <div class="alert alert-warning" role="alert">
                        ${ msg }
                    </div>
                </c:if>

                <p class="tituloForm">
                <h5>
                    <fmt:message key = "br.cefetrj.sisgee.form_renovar_convenio.msg_titulo"/>
                </h5>

                <form action=BuscaConvenioServlet method="post" name="renovarConvenio">
                    <fieldset class="form-group">
                        <div class="form-group col-md-6">
                            <label for="numConvenio"><fmt:message key = "br.cefetrj.sisgee.form_renovar_convenio.msg_numConvenio"/></label>
                            <input type="text" class="form-control ${ not empty numConvenio ? 'is-invalid': 'is-valid' }" id="numConvenio" name="numConvenio" value="${ param.numConvenio }">
                            <c:if test="${ not empty numConvenio }">
                                <div class="invalid-feedback">${ numConvenio }</div>
                            </c:if>
                        </div>

                        <div class="form-group col-md-6">
                            <label for="nomeConveniado"><fmt:message key = "br.cefetrj.sisgee.form_renovar_convenio.msg_nome_conveniado"/></label>
                            <div class="input-group">   
                                <input type="text" class="form-control ${ not empty nomeConveniadoMsg ? 'is-invalid': 'is-valid' }" id="nomeConveniado" name="nomeConveniado" maxlength="100" value="${ param.nomeConveniado }">

                                <span class="input-group-btn">
                                    <button type="submit" class="btn btn-primary"><fmt:message key = "br.cefetrj.sisgee.form_renovar_convenio.msg_buscar"/></button>
                                </span>
                                <c:if test="${ not empty nomeConveniado }">
                                    <div class="invalid-feedback">${ nomeConveniado }</div>
                                </c:if>
                            </div>
                        </div>


                        <div class=" isBusca Busca" ${ empty param.isAgenteIntegracao ? "style='display:none'" : param.isAgenteIntegracao == "nao" ? "style='display:none'" : "" }>        
                        <button id="btnListarConvenios" type="button" onclick="document.forms['dadosAluno'].submit()" class="btn btn-secondary" disabled="true"><fmt:message key = "br.cefetrj.sisgee.form_renovar_convenio.renovar"/></button>        
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th scope="col"><fmt:message key = "br.cefetrj.sisgee.form_renovar_convenio.tabela_selecao"/></th>
                                    <th scope="col"><fmt:message key = "br.cefetrj.sisgee.form_renovar_convenio.msg_numConvenio"/></th>
                                    <th scope="col"><fmt:message key = "br.cefetrj.sisgee.form_renovar_convenio.msg_nome_conveniado"/></th>
                                    <th scope="col"><fmt:message key = "br.cefetrj.sisgee.form_renovar_convenio.tabela_cpf_cnpj"/></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${listagem}" var="i">
                                    <tr>
                                        <th <label class="radio-inline"><input type="radio" ></label></th>
                                        <td>i.numConvenio</td>
                                        <td>i.nomeCoveniado</td>
                                        <td>i.cpf_cnpj</td>
                                    </tr>
                                </c:forEach>
                        </table>
                        </div>
                        <div align='center'>
                            <button type="button" class="btn btn-secondary" onclick="javascript:location.href = 'index.jsp'"><fmt:message key = "br.cefetrj.sisgee.form_renovar_convenio.msg_cancelar"/></button>
                        </div>
                    </fieldset>
                </form>
                <%@include file="import_footer.jspf"%>
                <%@include file="import_finalbodyscripts.jspf"%>
        </body>
