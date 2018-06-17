<%-- 
    Document   : form_renovar_convenio
    Created on : 24/05/2018, 19:15:33
    Author     : vinicius
--%>
<%@page import="br.cefetrj.sisgee.view.utils.ConvenioUtils" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="import_head.jspf"%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="br.cefetrj.sisgee.form_renovar_infos.msg_titulo" /></title>
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
                <fmt:message key = "br.cefetrj.sisgee.form_renovar_infos.msg_titulo"/>
            </h5>
            <fieldset class="form-group">
            <form action=ValidaBuscaConvenioServlet method="post" name="renovarConvenio">
                <div class="form-group">
                    <div class="form-group col-md-3">
                        <label for="numConvenio"><fmt:message key = "br.cefetrj.sisgee.form_renovar_convenio.msg_numConvenio"/></label>
                        <input type="text" class="form-control ${ not empty numConvenioMsg or not empty campoMsg ? 'is-invalid': 'is-valid' }" id="numConvenio" name="numConvenio" maxlength="6" value="${ param.numConvenio }">
                        <c:if test="${ not empty campoMsg }">
                            <div class="invalid-feedback">${ campoMsg }</div>
                        </c:if>
                        <c:if test="${ not empty numConvenioMsg }">
                            <div class="invalid-feedback">${ numConvenioMsg }</div>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <label class="form-group col-md-9" for="nomeConveniado"><fmt:message key = "br.cefetrj.sisgee.form_renovar_convenio.msg_nome_conveniado"/>
                    
                        <div class="form-row"> 
                            <div class="form-group col-md-5">
                                <input type="text" class="form-control ${ not empty nomeConveniadoMsg or not empty campoMsg ? 'is-invalid': 'is-valid' }" id="nomeConveniado" name="nomeConveniado" maxlength="100" value="${ param.nomeConveniado }" placeholder="${ param.nomeConveniado }">                           
                                <c:if test="${ not empty campoMsg }">
                                    <div class="invalid-feedback">${ campoMsg }</div>
                                </c:if>
                                <c:if test="${ not empty nomeConveniadoMsg }">
                                    <div class="invalid-feedback">${ nomeConveniadoMsg }</div>
                                </c:if>
                                
                            </div>
                            <div class="form-group">
                                <button type="submit" class="form-control btn btn-primary"><fmt:message key = "br.cefetrj.sisgee.form_renovar_convenio.msg_buscar"/></button>
                            </div>
                        </div>
                    </div>
                </div>
                    </form>
                    
                    <form action=InfoConvenioServlet method="post" name="info">
                    <c:if test="${not empty listaBusca or con ne null}">
                        <button id="btnRenovarConvenio" type="submit" class="btn btn-primary"><fmt:message key = "br.cefetrj.sisgee.form_renovar_convenio.renovar"/></button>
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
                                <c:if test="${not empty listaBusca}">
                                <c:forEach items="${listaBusca}" var="i">
                                    <tr>
                                        <th <label class="radio-inline selecionadoChk"><input type="radio" name="convenioSelecionado" id="convenioSelecionado" value="${i.cpf_cnpj}"></label></th>
                                        <td>${ConvenioUtils.getNumeroConvenioFormatado(i.numeroConvenio)}</td>
                                        <td>${i.nomeConveniado}</td>
                                        <td>${ConvenioUtils.getIdConveniadoFormatado(i.cpf_cnpj)}</td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                                <c:if test="${con ne null}">
                                    <tr>
                                        <th <label class="radio-inline selecionadoChk"><input type="radio" name="convenioSelecionado" id="convenioSelecionado" value="${con.cpf_cnpj}"></label></th>
                                        <td>${ConvenioUtils.getNumeroConvenioFormatado(con.numeroConvenio)}</td>
                                        <td>${con.nomeConveniado}</td>
                                        <td>${ConvenioUtils.getIdConveniadoFormatado(con.cpf_cnpj)}</td>
                                    </tr>
                                </c:if>
                        </table>
                    </c:if>


                    <c:if test="${ not empty msgBusca }">
                        <div class="alert alert-warning" role="alert">
                            <h1>${ msgBusca }</h1>
                        </div>
                    </c:if>
                     </form> 
                    <div align='center'>
                        <button type="button" class="btn btn-secondary" onclick="javascript:location.href = 'index.jsp'"><fmt:message key = "br.cefetrj.sisgee.form_renovar_convenio.msg_cancelar"/></button>
                    </div>

                </fieldset>
            
        </div>
        <%@include file="import_footer.jspf"%>
        <%@include file="import_finalbodyscripts.jspf"%>
        <script type="text/javascript" 
     src=" https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
        <script type="text/javascript">
            function hablitarButoes() {
                $("#btnRenovarConvenio").prop("disabled", false);
                $("#btnRenovarConvenio").removeClass("btn-secondary");
                $("#btnRenovarConvenio").addClass("btn-primary");
            }
            
            $(document).ready(function () {
                $('#btnRenovarConvenio').click(function () {
                    if (!$("input[type='radio'][name='convenioSelecionado']").is(':checked')) {
                        //alert("Por favor, selecione um convênio.");
                        return false; //para submit habilite esta linha
                    }
                });
            });
        </script>

    </body>
</html>
