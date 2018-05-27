<%-- 
    Document   : form_convenio
    Created on : 24/05/2018, 19:06:07
    Author     : vinicius
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <html>
        <head>
            <%@include file="import_head.jspf"%>
            <style type="text/css">
                botoes{
                    margin-right: 5px;
                }
                
                div.container {
                margin-bottom: 2em;
            }
           

        </style>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title><fmt:message key="br.cefetrj.sisgee.form_convenio.msg_titulo" /></title>
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
                    <fmt:message key = "br.cefetrj.sisgee.form_convenio.msg_titulo"/>
                </h5>

                <form action=ValidaCadastroConvenioServlet method="post" name="validaConvenio">
                    <fieldset class="form-group">
                        <div class="form-inline" >
                            <div class="form-group col-md-3" >
                                <label for="tipo"><fmt:message key="br.cefetrj.sisgee.form_empresa.msg_tipo" /></label>
                            </div>

                            <div class="custom-controls-stacked" >
                                <div class="form-inline">
                                    <label class="custom-control custom-radio"> 
                                        <input id="empresaSim" name="tipo" type="radio" class="custom-control-input isEmpresaChk ${ not empty isEmpresaMsg ? 'is-invalid' : '' }" value = "sim" ${ not empty isEmpresaMsg ? '' : param.isEmpresa == 'sim' ? 'checked' : '' } checked> 
                                        <span class="custom-control-indicator"></span> 
                                        <span class="custom-control-description" ><fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_PJ"/></span>
                                    </label> 
                                    <label class="custom-control custom-radio"> 
                                        <input id="empresaNao" name="tipo" type="radio" class="custom-control-input isEmpresaChk ${ not empty isEmpresaMsg ? 'is-invalid' : '' }" value = "nao" ${ not empty isEmpresaMsg ? '' : param.isEmpresa == 'nao' ? 'checked' : '' } > 
                                        <span class="custom-control-indicator"></span> 
                                        <span class="custom-control-description"><fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_PF"/></span>
                                    </label>
                                </div>
                            </div>
                            <c:if test="${ not empty isEmpresaMsg }">
                                <div class="invalid-feedback">${ isEmpresaMsg }</div>
                            </c:if>
                        </div>

                        <div class="form-row isEM EM" ${ empty param.isEmpresa ? "" : param.isEmpresa == "nao" ? "style='display:none'" : "" }>
                            <div class="form-inline" >
                                <div class="form-group col-md-7" >
                                    <label for="agente"><fmt:message key="br.cefetrj.sisgee.form_empresa.msg_agente" /></label>
                                </div>
                                <div class="custom-controls-stacked" >
                                    <div class="form-inline">
                                        <label class="custom-control custom-radio"> 
                                            <input id="isAgente" name="agente" type="radio" class="custom-control-input ${ not empty isAgenteMsg ? 'is-invalid' : '' }" value = "Sim" > 
                                            <span class="custom-control-indicator"></span> 
                                            <span class="custom-control-description" ><fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_sim"/></span>
                                        </label> 
                                        <label class="custom-control custom-radio"> 
                                            <input id="isAgente" name="agente" type="radio" class="custom-control-input ${ not empty isAgenteMsg ? 'is-invalid' : '' }" value = "Não" checked> 
                                            <span class="custom-control-indicator"></span> 
                                            <span class="custom-control-description"><fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_nao"/></span>
                                        </label>
                                    </div>
                                </div>
                                <c:if test="${ not empty isAgenteMsg }">
                                    <div class="invalid-feedback">${ isAgenteMsg }</div>
                                </c:if>
                            </div>
                        </div>

                        <div class=" isEM EM" ${ empty param.isEmpresa ? "" : param.isEmpresa == "nao" ? "style='display:none'" : "" }>
                            <div class="form-group col-md-6">
                                <label for="cnpj"><fmt:message key = "br.cefetrj.sisgee.form_convenio.msg_cnpj"/></label>
                                <div class="input-group">   
                                    <input type="text" class="form-control ${ not empty cnpjCpfMsg ? 'is-invalid': 'is-valid' }" id="cnpj" name="cnpj" maxlength="100" value="${ param.cnpj_cpf }">

                                    <c:if test="${ not empty cnpj_cpfMsg }">
                                        <div class="invalid-feedback">${ cnpj_cpfMsg }</div>
                                    </c:if>
                                </div>
                            </div>

                            <div class="form-group col-md-6">
                                <label for="razaoSocia">                                                                    
                                    <fmt:message key = "br.cefetrj.sisgee.form_convenio.msg_razaoSocial"/>
                                </label>
                                <div class="input-group">   
                                    <input type="text" class="form-control ${ not empty nomeConveniadoMsg ? 'is-invalid': 'is-valid' }" id="razaoSocial" name="razaoSocial" maxlength="100" value="${ param.nomeConveniado }">
                                    <c:if test="${ not empty nomeConveniadoMsg }">
                                        <div class="invalid-feedback">${ nomeConveniadoMsg }</div>
                                    </c:if>
                                </div>
                            </div>
                        </div>

                        <div class=" notEM EM" ${ empty param.isEmpresa ? "style='display:none'" : param.isEmpresa == "sim" ? "style='display:none'" : "" }>                
                            <div class="form-group col-md-6">
                                <label for="cpf">

                                    <fmt:message key = "br.cefetrj.sisgee.form_convenio.msg_cpf"/>
                                </label>
                                <div class="input-group">   
                                    <input type="text" class="form-control ${ not empty cnpjCpfMsg ? 'is-invalid': 'is-valid' }" id="cpf" name="cpf" maxlength="100" value="${ param.cnpj_cpf }">

                                    <c:if test="${ not empty cnpj_cpfMsg }">
                                        <div class="invalid-feedback">${ cnpj_cpfMsg }</div>
                                    </c:if>
                                </div>
                            </div>

                            <div class="form-group col-md-6">
                                <label for="nome">                                                                    
                                    <fmt:message key = "br.cefetrj.sisgee.form_convenio.msg_nome"/>
                                </label>
                                <div class="input-group">   
                                    <input type="text" class="form-control ${ not empty nomeConveniadoMsg ? 'is-invalid': 'is-valid' }" id="nome" name="nome" maxlength="100" value="${ param.nomeConveniado }">
                                    <c:if test="${ not empty nomeConveniadoMsg }">
                                        <div class="invalid-feedback">${ nomeConveniadoMsg }</div>
                                    </c:if>
                                </div>
                            </div>
                        </div>            

                        <div class="form-group col-md-2">
                            <label for="dataAssiatura"><fmt:message key = "br.cefetrj.sisgee.form_convenio.msg_dataAssinatura"/></label>
                            <div class="input-group">   
                                <input type="text" class="form-control ${ not empty dataAssinaturaMsg ? 'is-invalid': 'is-valid' }" id="dataAssinatura" name="dataAssinatura" maxlength="100" value="${ param.dataAssinatura }">

                                <c:if test="${ not empty dataAssinaturaMsg }">
                                    <div class="invalid-feedback">${ dataAssinaturaMsg }</div>
                                </c:if>
                            </div>
                        </div>

                        <div class="form-group col-md-6">
                            <label for="email"><fmt:message key = "br.cefetrj.sisgee.form_convenio.msg_email"/></label>
                            <div class="input-group">   
                                <input type="text" class="form-control ${ not empty emailMsg ? 'is-invalid': 'is-valid' }" id="email" name="email" maxlength="100" value="${ param.email }">

                                <c:if test="${ not empty emailMsg }">
                                    <div class="invalid-feedback">${ emailMsg }</div>
                                </c:if>
                            </div>
                        </div>

                        <div class="form-group col-md-2">
                            <label for="telefone"><fmt:message key = "br.cefetrj.sisgee.form_convenio.msg_telefone"/></label>
                            <div class="input-group">   
                                <input type="text" class="form-control ${ not empty telefoneMsg? 'is-invalid': 'is-valid' }" id="telefone" name="telefone" maxlength="100" value="${ param.telefone }">
                                <c:if test="${ not empty telefoneMsg }">
                                    <div class="invalid-feedback">${ telefoneMsg }</div>
                                </c:if>
                            </div>
                        </div>

                        <div class=" isEM EM" ${ empty param.isEmpresa ? "" : param.isEmpresa == "nao" ? "style='display:none'" : "" }>            
                            <div class="form-group col-md-6">
                                <label for="pessoaContato"><fmt:message key = "br.cefetrj.sisgee.form_convenio.msg_pessoaContato"/></label>
                                <div class="input-group">   
                                    <input type="text" class="form-control ${ not empty pessoaContatoMsg ? 'is-invalid': 'is-valid' }" id="pessoaCotato" name="pessoaContato" maxlength="100" value="${ param.pessoaContato }">

                                    <c:if test="${ not empty pessoaContatoMsg }">
                                        <div class="invalid-feedback">${ pessoaContatoMsg }</div>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                                    <div align='right' id='botoes' class='botoes' >                
                                        <button type="submit" class="btn btn-primary"><fmt:message key = "br.cefetrj.sisgee.form_convenio.msg_salvar"/></button>                
                                        <button type="button" class="btn btn-secondary" onclick="javascript:location.href = 'index.jsp'"><fmt:message key = "br.cefetrj.sisgee.form_convenio.msg_cancelar"/></button>                
                                    </div>
                    </fieldset>
                </form>
            </div>
            <%@include file="import_footer.jspf"%>
            <%@include file="import_finalbodyscripts.jspf"%>
            <%@include file="import_scripts.jspf"%>
            <script>
                $(document).ready(function () {
                    $('#dataAssinatura').mask('99/99/9999');
                    $('#cnpj').mask('99.999.999/9999-99');
                    $('#cpf').mask('999.999.999-99');
                    $('#telefone').mask('(21) 9999-9999');
                });
            </script>
        </body>
    </html>
