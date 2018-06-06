<%-- 
    Document   : form_convenio
    Created on : 24/05/2018, 19:06:07
    Author     : vinicius
--%>


<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt">
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

            <form action=ValidarCadastroConvenioServlet method="post" name="validarConvenio">
                <fieldset class="form-group">
                    <div class="form-inline" >
                        <div class="form-group col-md-3" >
                            <label for="tipo"><fmt:message key="br.cefetrj.sisgee.form_empresa.msg_tipo" /></label>
                        </div>

                        <div class="custom-controls-stacked" >
                            <div class="form-inline">
                                <label class="custom-control custom-radio"> 
                                    <input id="empresaSim" name="tipo" type="radio" class="custom-control-input isEmpresaChk ${ not empty tipoMsg ? 'is-invalid' : '' }" value = "true" ${empty param.tipo ? 'checked' : param.tipo == 'true' ? 'checked' : ''} > 
                                    <span class="custom-control-indicator"></span> 
                                    <span class="custom-control-description" ><fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_PJ"/></span>
                                </label> 
                                <label class="custom-control custom-radio"> 
                                    <input id="empresaNao" name="tipo" type="radio" class="custom-control-input isEmpresaChk ${ not empty tipoMsg ? 'is-invalid' : '' }" value = "false" ${empty param.tipo ? '' : param.tipo == 'false' ? 'checked' : '' } > 
                                    <span class="custom-control-indicator"></span> 
                                    <span class="custom-control-description"><fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_PF"/></span>
                                </label>
                            </div>
                        </div>
                        <c:if test="${ not empty tipoMsg }">
                            <div class="invalid-feedback">${ tipoMsg }</div>
                        </c:if>
                    </div>

                    <div class="form-row isEM EMA" ${ empty param.tipo ? "" : param.tipo == "false" ? "style='display:none'" : "" }>
                        <div class="form-inline" >
                            <div class="form-group col-md-7" >
                                <label for="agente"><fmt:message key="br.cefetrj.sisgee.form_empresa.msg_agente" /></label>
                            </div>
                            <div class="custom-controls-stacked" >
                                <div class="form-inline">
                                    <label class="custom-control custom-radio"> 
                                        <input id="isAgente" name="agente" type="radio" class="custom-control-input ${ not empty agenteMsg ? 'is-invalid' : '' }" value = "true" ${empty param.agente? '' : param.agente =='true'?'checked':''}> 
                                        <span class="custom-control-indicator"></span> 
                                        <span class="custom-control-description" ><fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_sim"/></span>
                                    </label> 
                                    <label class="custom-control custom-radio"> 
                                        <input id="isAgente" name="agente" type="radio" class="custom-control-input ${ not empty agenteMsg ? 'is-invalid' : '' }" value = "false" ${empty param.agente? 'checked' : param.agente =='false'?'checked':''}> 
                                        <span class="custom-control-indicator"></span> 
                                        <span class="custom-control-description"><fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_nao"/></span>
                                    </label>
                                </div>
                            </div>
                            <c:if test="${ not empty agenteMsg }">
                                <div class="invalid-feedback">${ agenteMsg }</div>
                            </c:if>
                        </div>
                    </div>

                    <div class=" isEM EM" ${ empty param.tipo ? "" : param.tipo == "false" ? "style='display:none'" : "" }>
                        <div class="form-group col-md-6">
                            <label for="cnpj"><fmt:message key = "br.cefetrj.sisgee.form_convenio.msg_cnpj"/></label>
                            <input type="text" class="form-control ${ not empty cnpjMsg ? 'is-invalid': 'is-valid' }" id="cnpj" name="cnpj" maxlength="14" value="${ param.cnpj }">
                            <c:if test="${ not empty cnpjMsg }">
                                <div class="invalid-feedback">${ cnpjMsg }</div>
                            </c:if>

                        </div>

                        <div class="form-group col-md-6">
                            <label for="razaoSocial"><fmt:message key = "br.cefetrj.sisgee.form_convenio.msg_razaoSocial"/></label>
                            <input type="text" class="form-control ${ not empty razaoSocialMsg ? 'is-invalid': 'is-valid' }" id="razaoSocial" name="razaoSocial" maxlength="100" value="${ param.razaoSocial }">
                            <c:if test="${ not empty razaoSocialMsg }">
                                <div class="invalid-feedback">${ razaoSocialMsg }</div>
                            </c:if>
                        </div>
                    </div>

                    <div class=" notEM EM" ${ empty param.tipo ? "style='display:none'" : param.tipo == "true" ? "style='display:none'" : "" }>                
                        <div class="form-group col-md-6">
                            <label for="cpf"><fmt:message key = "br.cefetrj.sisgee.form_convenio.msg_cpf"/></label>
                            <input type="text" class="form-control ${ not empty cpfMsg ? 'is-invalid': 'is-valid' }" id="cpf" name="cpf" maxlength="11" value="${param.cpf}">
                            <c:if test="${ not empty cpfMsg }">
                                <div class="invalid-feedback">${ cpfMsg }</div>
                            </c:if>
                        </div>

                        <div class="form-group col-md-6">
                            <label for="nome"><fmt:message key = "br.cefetrj.sisgee.form_convenio.msg_nome"/></label>
                            <input type="text" class="form-control ${ not empty nomeMsg ? 'is-invalid': 'is-valid' }" id="nome" name="nome" maxlength="100" value="${ param.nome }">
                            <c:if test="${ not empty nomeMsg }">
                                <div class="invalid-feedback">${ nomeMsg }</div>
                            </c:if>
                        </div>
                    </div>            

                    <div class="form-group col-md-3">
                        <label for="dataAssiatura"><fmt:message key = "br.cefetrj.sisgee.form_convenio.msg_dataAssinatura"/></label>
                        <input type="text" class="form-control ${ not empty dataAssinaturaMsg ? 'is-invalid': 'is-valid' }" id="dataAssinatura" name="dataAssinatura" value="${ param.dataAssinatura }">
                        <c:if test="${ not empty dataAssinaturaMsg }">
                            <div class="invalid-feedback">${ dataAssinaturaMsg }</div>
                        </c:if>
                    </div>

                    <div class="form-group col-md-6">
                        <label for="email"><fmt:message key = "br.cefetrj.sisgee.form_convenio.msg_email"/></label>
                        <input type="text" class="form-control ${ not empty emailMsg ? 'is-invalid': 'is-valid' }" id="email" name="email" maxlength="100" value="${ param.email }">
                            <c:if test="${ not empty emailMsg }">
                                <div class="invalid-feedback">${ emailMsg }</div>
                            </c:if>
                    </div>

                    <div class="form-group col-md-2">
                        <label for="telefone"><fmt:message key = "br.cefetrj.sisgee.form_convenio.msg_telefone"/></label>
                        <input type="text" class="form-control ${ not empty telefoneMsg? 'is-invalid': 'is-valid' }" id="telefone" name="telefone" maxlength="11" value="${ param.telefone }">
                            <c:if test="${ not empty telefoneMsg }">
                                <div class="invalid-feedback">${ telefoneMsg }</div>
                            </c:if>
                    </div>

                    <div class=" isEM EM" ${ empty param.tipo ? "" : param.tipo == "false" ? "style='display:none'" : "" }>            
                        <div class="form-group col-md-6">
                            <label for="pessoaContato"><fmt:message key = "br.cefetrj.sisgee.form_convenio.msg_pessoaContato"/></label>
                            <input type="text" class="form-control ${ not empty pessoaContatoMsg ? 'is-invalid': 'is-valid' }" id="pessoaCotato" name="pessoaContato" maxlength="50" value="${ param.pessoaContato }">
                                <c:if test="${ not empty pessoaContatoMsg }">
                                    <div class="invalid-feedback">${ pessoaContatoMsg }</div>
                                </c:if>
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
        <script type="text/javascript">
            $(document).ready(function () {
                $('#dataAssinatura').mask('99/99/9999');
                $('#cnpj').mask('99.999.999/9999-99');
                $('#cpf').mask('999.999.999-99');
                $('#telefone').mask('(99) 9999-99999');
            });
            $('#dataAssinatura').datepicker({
            <c:if test="${ lang eq 'pt_BR' }">
                language: 'pt-BR'
            </c:if>
            });
        </script>
    </body>
</html>

