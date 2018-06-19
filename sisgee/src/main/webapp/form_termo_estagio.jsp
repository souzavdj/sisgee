<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="import_head.jspf"%>

        <style type="text/css">
            div.container {
                margin-bottom: 2em;
            }
            form {
                margin-top: 50px;
            }
            fieldset.form-group {
                border:1px solid #999999;
            }
            fieldset legend.col-form-legend {
                font-weight: bold;
            }
            div.form-row {
                padding: 0px 15px;
            }
        </style>

        <title>
            <c:choose>
                <c:when test="${ not empty termoEstagio }">
                    <fmt:message key = "br.cefetrj.sisgee.resources.form.registroTermoAditivo"/>
                </c:when>
                <c:otherwise>
                    <fmt:message key = "br.cefetrj.sisgee.resources.form.registroTermoEstagio"/>
                </c:otherwise>
            </c:choose>
        </title>
    </head>
    <body>
        <%@include file="import_navbar.jspf"%>	


        <div class="container">
            <c:if test="${ not empty msg }">
                <div class="alert alert-warning" role="alert">
                    ${ msg }
                </div>
            </c:if>
            <c:if test="${ not empty msg2 }">
                <div class="alert alert-warning" role="alert">
                    ${ msg2 }
                </div>
            </c:if>

            <p class="tituloForm">           

                <h5 class="text-center">
                    <c:choose>
                        <c:when test="${ not empty termoEstagio }">
                            <fmt:message key = "br.cefetrj.sisgee.resources.form.registroTermoAditivo"/>
                        </c:when>
                        <c:otherwise>
                            <fmt:message key = "br.cefetrj.sisgee.resources.form.registroTermoEstagio"/>
                        </c:otherwise>
                    </c:choose>
                </h5>		
           </p>		

            <c:choose>
                <c:when test="${ not empty termoEstagio }">
                    <form action="FormTermoAditivoServlet" method="post">
                </c:when>
                <c:otherwise>
                    <form action="FormTermoEstagioServlet" method="post">
                </c:otherwise>
            </c:choose>
                <fieldset class="form-group dadosAluno" ${ not empty termoEstagio ? 'disabled' :'' }>
                    <%@include file="import_busca_aluno.jspf"%>
                </fieldset>

                <fieldset class="form-group dadosConvenio" ${ not empty termoEstagio ? 'disabled' : '' }>
                        <legend class="col-form-legend col-lg"><fmt:message key = "br.cefetrj.sisgee.resources.form.dadosDoConvenio"/></legend>
                        <div class="form-row"> 
                            <div class="form-group col-md-6">
                                <label for="numeroConvenio"><fmt:message key = "br.cefetrj.sisgee.resources.form.numeroConvenio"/></label>
                                <div class="input-group">
                                    <input type="hidden" id="idConvenio" name="idConvenio" value="${ idConvenio }">
                                    <input type="text" maxlength="6" class="form-control infoConvenio ${ not empty idConvenioMsg ? 'is-invalid': 'is-valid' } ${ not empty numeroConvenioMsg ? 'is-invalid': 'is-valid' }" placeholder="<fmt:message key = "br.cefetrj.sisgee.import_busca_convenio.placeholder_numero_convenio"/>" id="numeroConvenio" name="numeroConvenio" value="${numeroConvenio}">
                                       <span class="input-group-btn"> 
                                        <button class="btn btn-primary btnBuscarConvenio" type="button"  id="btnBuscarConvenioNumero"><fmt:message key = "br.cefetrj.sisgee.resources.form.buscar"/></button>
                                    </span>

                                    <c:if test="${ not empty idConvenioMsg }">
                                        <div class="invalid-feedback">${ idConvenioMsg }</div>
                                    </c:if>
                                    <c:if test="${ not empty numeroConvenioMsg }">
                                        <div class="invalid-feedback">${ numeroConvenioMsg }</div>
                                    </c:if> 
                                </div>           

                            </div>
                            <div class="form-group col-md-6">
                            <label for="nomeConvenio"><fmt:message key = "br.cefetrj.sisgee.resources.form.razaoSocial_e_nome"/></label>
                                <div class="input-group">
                                    <input type="hidden" id="idConvenio" name="idConvenio" value="${ idConvenio }">
                                    <input type="text" maxlength="100" class="form-control infoConvenio ${ not empty idConvenioMsg ? 'is-invalid': 'is-valid' } ${ not empty nomeConvenioMsg ? 'is-invalid': 'is-valid' }" placeholder="<fmt:message key = "br.cefetrj.sisgee.import_busca_convenio.placeholder_nome_convenio"/>" id="nomeConvenio" name="nomeConvenio" value="${nomeConvenio }">
                                    <span class="input-group-btn"> 
                                        <button class="btn btn-primary btnBuscarConvenio convenioBotao" type="button"  id="btnBuscarConvenioNome"><fmt:message key = "br.cefetrj.sisgee.resources.form.buscar"/></button>
                                    </span>
                                    <c:if test="${ not empty idConvenioMsg }">
                                        <div class="invalid-feedback">${ idConvenioMsg }</div>
                                    </c:if>
                                    <c:if test="${ not empty nomeConvenioMsg }">
                                        <div class="invalid-feedback">${ nomeConvenioMsg }</div>
                                    </c:if>     
                                </div>
                            </div>
                        </div>

                        <div class="form-row">
                           <div class="form-group col-md-1">        
                                <label for="tipo">
                                        <fmt:message key = "br.cefetrj.sisgee.resources.form.consultar.termo.tipo"/>
                                </label>
                           </div>
                           <div class="custom-controls-stacked d-block my-4">							
                              <label class="custom-control custom-radio">
                                 <input id="tipoPJ" class="custom-control-input" type="radio" name="tipo"  value="true" ${tipo == 'true' ? 'checked' : 'disabled="disabled"'}> 
                                 <span class="custom-control-indicator"></span> 
                                 <span class="custom-control-description" ><fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_PJ"/></span>
                              </label>						
                             <label class="custom-control custom-radio">
                                 <input id="tipoPF" class="custom-control-input" type="radio" name="tipo" value="false" ${tipo == 'false' ? 'checked' : 'disabled="disabled"'}> 
                                 <span class="custom-control-indicator"></span> 
                                 <span class="custom-control-description"><fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_PF"/></span>
                              </label>

                           </div>


                           <div class="form-group col-md-2">
                              <label for="agente">
                                      <fmt:message key = "br.cefetrj.sisgee.resources.form.agenteIntegracao"/>
                              </label>
                           </div>
                           <div class="custom-controls-stacked d-block my-3">							
                               <label class="custom-control custom-radio">
                                       <input id="agenteSim" class="custom-control-input" type="radio" name="agente" value="true" ${agente == 'true' ? 'checked' : 'disabled'}> 
                                        <span class="custom-control-indicator"></span> 
                                       <span class="custom-control-description" ><fmt:message key = "br.cefetrj.sisgee.resources.form.sim"/></span>
                               </label>						
                                        <label class="custom-control custom-radio">
                                        <input id="agenteNao" class="custom-control-input" type="radio" name="agente" value="false" ${agente == 'false' ? 'checked' : 'disabled'}> 
                                   <span class="custom-control-indicator"></span> 
                                   <span class="custom-control-description"><fmt:message key = "br.cefetrj.sisgee.resources.form.nao"/></span>
                               </label>

                            </div>
                        </div>

                        <div class="form-row"> 
                           <div class="form-group col-md-6">
                                <label for="agenciada"><fmt:message key = "br.cefetrj.sisgee.resources.form.agenciada"/></label>
                                <input type="text" class="form-control ${ not empty agenciadaMsg ? 'is-invalid': not empty agenciadaMsg ? 'is-invalid' : 'is-valid' }" id="agenciada"  name="agenciada" value="${ agenciada }" readonly>
                                   
                                <c:if test="${ not empty agenciadaMsg }">
                                    <div class="invalid-feedback">${ agenciadaMsg }</div>
                                </c:if>
                            </div>
                            
                            
                        </div>
                        <div class="form-row">
                           <div class="form-group col-md-6">
                               <label for="cpf_cnpj"><fmt:message key = "br.cefetrj.sisgee.resources.form.cnpj_e_Cpf"/></label>
                                <input type="text" class="form-control" id="cpf_cnpj"  name="cpf_cnpj" value="${ CpfCnpj }" readonly>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="razaoSocial"><fmt:message key = "br.cefetrj.sisgee.resources.form.razaoSocial_e_nome"/></label>
                                <input type="text" class="form-control" id="razaoSocial"  name="razaoSocial" value="${ razaoSocial }" readonly>
                            </div>

                        </div>    
                </fieldset> 

                <c:if test="${ not empty periodoMsg }">
                    <div class="alert alert-danger" role="alert">${ periodoMsg }</div>
                </c:if>
                <fieldset class="form-group" ${ isVisualizacao eq true ? 'disabled' :'' }>
                    <legend class="col-form-legend col-lg"><fmt:message key = "br.cefetrj.sisgee.resources.form.vigenciaEstagio"/></legend>
                    <div class="form-row">
                        <div class="form-group col-md-6">

                            <label for="dataInicioTermoEstagio"><fmt:message key = "br.cefetrj.sisgee.resources.form.dataInicio"/></label>
                            <input type="text" class="form-control col-sm-4 ${ not empty dataInicioMsg ? 'is-invalid': not empty periodoMsg ? 'is-invalid' : 'is-valid' }" id="dataInicioTermoEstagio"  name="dataInicioTermoEstagio" value="${ dataInicioTermoEstagio }" ${ not empty termoEstagio ? 'disabled' : '' } >
                            <c:if test="${ not empty dataInicioMsg }">
                                <div class="invalid-feedback">${ dataInicioMsg }</div>
                            </c:if>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="dataFimTermoEstagio"><fmt:message key = "br.cefetrj.sisgee.resources.form.dataTermino"/></label>
                            <input type="text" class="form-control col-sm-4 ${ not empty dataFimMsg ? 'is-invalid': not empty periodoMsg ? 'is-invalid' : 'is-valid' }" id="dataFimTermoEstagio"   name="dataFimTermoEstagio" value="${ dataFimTermoEstagio }" ${ empty termoEstagio ? '' : empty updVigencia ? 'disabled' : '' } >
                            <c:if test="${ not empty dataFimMsg }">
                                <div class="invalid-feedback">${ dataFimMsg }</div>
                            </c:if>
                        </div>
                    </div>
                </fieldset>


                <fieldset class="form-group" ${ isVisualizacao eq true ? 'disabled' :'' }>
                    <legend class="col-form-legend col-lg"><fmt:message key = "br.cefetrj.sisgee.resources.form.cargaHorariaAluno"/></legend>
                    <div class="form-row">
                        <div class="form-group col-md-4">
                            <label for="cargaHorariaTermoEstagio"><fmt:message key = "br.cefetrj.sisgee.resources.form.horasDia"/></label>
                            <input type="text" maxlength="1" class="form-control col-sm-2 ${ not empty cargaHorariaMsg ? 'is-invalid': 'is-valid' }" id="cargaHorariaTermoEstagio" name="cargaHorariaTermoEstagio" value="${ cargaHorariaTermoEstagio }" ${ empty termoEstagio ? '' : empty updCargaHoraria ? 'disabled' : '' }>
                            <c:if test="${ not empty cargaHorariaMsg }">
                                <div class="invalid-feedback">${ cargaHorariaMsg }</div>
                            </c:if>
                        </div>
                    </div>
                </fieldset>


                <fieldset class="form-group" ${ isVisualizacao eq true ? 'disabled' :'' }>
                    <legend class="col-form-legend col-lg"><fmt:message key = "br.cefetrj.sisgee.resources.form.valorBolsaEstagio"/></legend>
                    <div class="form-row">
                        <div class="form-group col-md-4">
                            <label for="valorBolsa">Valor (R$)</label>
                            <input type="text" class="form-control col-sm-6 ${ not empty valorBolsaMsg ? 'is-invalid': 'is-valid' }" id="valorBolsa" name="valorBolsa" value="${ valorBolsa }" ${ empty termoEstagio ? '' : empty updValorBolsa ? 'disabled' : '' }>
                            <c:if test="${ not empty valorBolsaMsg }">
                                <div class="invalid-feedback">${ valorBolsaMsg }</div>
                            </c:if>
                        </div>
                    </div>
                </fieldset>


                <fieldset class="form-group" ${ isVisualizacao eq true ? 'disabled' :'' } ${ empty termoEstagio ? '' : empty updEndereco ? 'disabled' : '' }>
                    <legend class="col-form-legend col-lg"><fmt:message key = "br.cefetrj.sisgee.resources.form.localEstagio"/></legend>
                    <div class="form-row">
                        <div class="form-group col-md-12">

                            <label for="enderecoTermoEstagio"><fmt:message key = "br.cefetrj.sisgee.resources.form.endereco"/></label>
                            <input type="text" maxlength="255" class="form-control ${ not empty enderecoMsg ? 'is-invalid': not empty enderecoMsg ? 'is-invalid' : 'is-valid' }" id="enderecoTermoEstagio" name="enderecoTermoEstagio" value="${ enderecoTermoEstagio }">
                            <c:if test="${ not empty enderecoMsg }">
                                <div class="invalid-feedback">${ enderecoMsg }</div>
                            </c:if>
                        </div>
                    </div>
                    <div class="form-row">                       
                        <div class="form-group col-md-4">
                            <label for="complementoEnderecoTermoEstagio"><fmt:message key = "br.cefetrj.sisgee.resources.form.complemento"/></label>
                            <input maxlength="150" type="text" class="form-control ${ not empty complementoEnderecoMsg ? 'is-invalid': not empty complementoEnderecoMsg ? 'is-invalid' : 'is-valid' }" id="complementoEnderecoTermoEstagio" name="complementoEnderecoTermoEstagio" value="${ complementoEnderecoTermoEstagio }">
                            <c:if test="${ not empty complementoEnderecoMsg }">
                                <div class="invalid-feedback">${ complementoEnderecoMsg }</div>
                            </c:if>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="bairroEnderecoTermoEstagio"><fmt:message key = "br.cefetrj.sisgee.resources.form.bairro"/></label>
                            <input type="text" maxlength="150" class="form-control ${ not empty bairroEnderecoMsg ? 'is-invalid': not empty bairroEnderecoMsg ? 'is-invalid' : 'is-valid' }" id="bairroEnderecoTermoEstagio" name="bairroEnderecoTermoEstagio" value="${ bairroEnderecoTermoEstagio }">
                            <c:if test="${ not empty bairroEnderecoMsg }">
                                <div class="invalid-feedback">${ bairroEnderecoMsg }</div>
                            </c:if>
                        </div>
                    </div>
                    <div class="form-row">					
                        <div class="form-group col-md-6">
                            <label for="cidadeEnderecoTermoEstagio"><fmt:message key = "br.cefetrj.sisgee.resources.form.cidade"/></label>
                            <input type="text" maxlength="150" class="form-control ${ not empty cidadeEnderecoMsg ? 'is-invalid': not empty cidadeEnderecoMsg ? 'is-invalid' : 'is-valid' }" id="cidadeEnderecoTermoEstagio" name="cidadeEnderecoTermoEstagio" value="${ cidadeEnderecoTermoEstagio }">
                            <c:if test="${ not empty cidadeEnderecoMsg }">
                                <div class="invalid-feedback">${ cidadeEnderecoMsg }</div>
                            </c:if>
                        </div>
                        <div class="form-group col-md-2">
                            <label for="estadoEnderecoTermoEstagio"><fmt:message key = "br.cefetrj.sisgee.resources.form.estado"/></label>
                            <select name = "estadoEnderecoTermoEstagio" id="estadoEnderecoTermoEstagio" class="form-control ${ not empty estadoEnderecoMsg ? 'is-invalid': not empty estadoEnderecoMsg ? 'is-invalid' : 'is-valid' }">
                                <c:choose>
                                    <c:when test="${not empty termoEstagio}">
                                        <option value="${ufTermo}">${ufTermo}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="" selected>---</option>
                                        <c:forEach items="${ uf }" var="uf">
                                            <option value="${ uf }" ${uf eq estadoEnderecoTermoEstagio ? "selected": ""}>${ uf }</option>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>							
                            </select>
                            <c:if test="${ not empty estadoEnderecoMsg }">
                                <div class="invalid-feedback">${ estadoEnderecoMsg }</div>
                            </c:if>
                        </div>
                        <div class="form-group col-md-4">
                            <label for="cepEnderecoTermoEstagio"><fmt:message key = "br.cefetrj.sisgee.resources.form.cep"/></label>
                            <input type="text" class="form-control ${ not empty cepEnderecoMsg ? 'is-invalid': not empty cepEnderecoMsg ? 'is-invalid' : 'is-valid' }" id="cepEnderecoTermoEstagio" name="cepEnderecoTermoEstagio" value="${ cepEnderecoTermoEstagio }">
                            <c:if test="${ not empty cepEnderecoMsg }">
                                <div class="invalid-feedback">${ cepEnderecoMsg }</div>
                            </c:if>
                        </div>
                    </div>
                </fieldset>


                <div class="form-row" >
                    <div class="form-group d-inline" ${ isVisualizacao eq true ? 'disabled' :'' } >
                        <label for="eEstagioObrigatorio"><fmt:message key = "br.cefetrj.sisgee.resources.form.estagioObrigatorio"/></label>
                    </div>

                    <div class="custom-controls-stacked d-inline ml-4" ${ isVisualizacao eq true ? 'disabled' :'' }>
                        <label class="custom-control custom-radio"> 
                            <input id="estagioSim" name="eEstagioObrigatorio" type="radio" class="custom-control-input ${ not empty eEstagioObrigatorioMsg ? 'is-invalid' : '' }" value = "sim" ${ not empty termoEstagio ? 'disabled' :'' } ${ not empty eEstagioObrigatorioMsg ? '' : eEstagioObrigatorio == 'sim' ? 'checked' : '' }> 
                            <span class="custom-control-indicator"></span> 
                            <span class="custom-control-description" ><fmt:message key = "br.cefetrj.sisgee.resources.form.sim"/></span>
                        </label> 
                        <label class="custom-control custom-radio"> 
                            <input id="estagioNao" name="eEstagioObrigatorio" type="radio" class="custom-control-input ${ not empty eEstagioObrigatorioMsg ? 'is-invalid' : '' }" value = "nao" ${ not empty termoEstagio ? 'disabled' :'' } ${ not empty eEstagioObrigatorioMsg ? '' : eEstagioObrigatorio == 'nao' ? 'checked' : '' }> 
                            <span class="custom-control-indicator"></span> 
                            <span class="custom-control-description"><fmt:message key = "br.cefetrj.sisgee.resources.form.nao"/></span>
                        </label>
                    </div>				
                </div>

                <fieldset ${ isVisualizacao eq true ? 'disabled' :'' }>
                     <div class="form-row"> 
                        <div class="form-group col-md-6">
                            <label for="nomeSupervisor" ><fmt:message key = "br.cefetrj.sisgee.resources.form.nomeSupervisor"/></label>
                            <input type="text" maxlength="100" class="form-control ${ not empty nomeSupervisorMsg ? 'is-invalid': not empty nomeSupervisorMsg ? 'is-invalid' : 'is-valid' }" id="nomeSupervisor" name="nomeSupervisor" value="${ nomeSupervisor }" ${ empty termoEstagio ? '' : empty updSupervisor ? 'disabled' : '' }>                            
                            <c:if test="${ not empty nomeSupervisorMsg }">
                                <div class="invalid-feedback">${ nomeSupervisorMsg }</div>
                            </c:if>				
                        </div>
                       <div class="form-group col-md-6">
                            <label for="cargoSupervisor" ><fmt:message key = "br.cefetrj.sisgee.resources.form.cargoSupervisor"/></label>
                            <input type="text" maxlength="100" class="form-control ${ not empty cargoSupervisorMsg ? 'is-invalid': not empty cargoSupervisorMsg ? 'is-invalid' : 'is-valid' }" id="cargoSupervisor" name="cargoSupervisor" value="${ cargoSupervisor }" ${ empty termoEstagio ? '' : empty updSupervisor ? 'disabled' : '' }>                            
                            <c:if test="${ not empty cargoSupervisorMsg }">
                                <div class="invalid-feedback">${ cargoSupervisorMsg }</div>
                            </c:if>				
                        </div>     
                    </div>   
                </fieldset>
                        
                <fieldset ${ isVisualizacao eq true ? 'disabled' :'' }>
                    <div class="form-row">
                        <label for="idProfessorOrientador" class="col-form-label col-sm-2" ><fmt:message key = "br.cefetrj.sisgee.resources.form.professorOrientador"/></label>
                        <select name="idProfessorOrientador" id="idProfessorOrientador" class="form-control col-sm-10 ${ not empty idProfessorMsg ? 'is-invalid': not empty idProfessorMsg ? 'is-invalid' : 'is-valid' }" ${ empty termoEstagio ? '' : empty updProfessor ? 'disabled' : '' }>
                            <c:choose>
                                <c:when test="${not empty termoEstagio}">
                                    <option value="${professorTermo.idProfessorOrientador}">${professorTermo.nomeProfessorOrientador}</option>
                                    <c:forEach items="${ professores }" var="professor">
                                        <option value="${ professor.idProfessorOrientador }">${ professor.nomeProfessorOrientador }</option>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <option value="" selected>---</option>
                                    <c:forEach items="${ professores }" var="professor">
                                        <option value="${ professor.idProfessorOrientador }" ${professor.idProfessorOrientador eq idProfessorOrientador ? "selected": ""}>${ professor.nomeProfessorOrientador }</option>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>				
                        </select>
                        <c:if test="${ not empty idProfessorMsg }">
                            <div class="invalid-feedback">${ idProfessorMsg }</div>
                        </c:if>				
                    </div>
                </fieldset>            

                <c:if test="${ not empty termoEstagio }">
                    <input type="hidden" name="idTermoEstagio" value="${ termoEstagio.idTermoEstagio }" />
                    <input type="hidden" name="idAluno" value="${ idAluno }" />
                    <input type="hidden" name="matricula" value="${ matricula }" />
                    <input type="hidden" name="idProfessorOrientador" value="${ professorTermo.idProfessorOrientador }" />
                    <input type="hidden" name="idConvenio" value="${ idConvenio }" />
                    <input type="hidden" name="cargaHorariaTermoEstagio" value="${ cargaHorariaTermoEstagio }" />
                    <input type="hidden" name="valorBolsa" value="${ valorBolsa }" />
                    <input type="hidden" name="updVigencia" value="${ updVigencia }" />
                    <input type="hidden" name="updCargaHoraria" value="${ updCargaHoraria }" />
                    <input type="hidden" name="updProfessor" value="${ updProfessor }" />
                    <input type="hidden" name="updValorBolsa" value="${ updValorBolsa }" />
                    <input type="hidden" name="updEndereco" value="${ updEndereco }" />
                    <input type="hidden" name="updSupervisor" value="${ updSupervisor }" />
                    <input type="hidden" name="cidadeEnderecoTermoEstagio" value="${ cidadeEnderecoTermoEstagio }" />
                    <input type="hidden" name="enderecoTermoEstagio" value="${ enderecoTermoEstagio }" />
                    <input type="hidden" name="complementoEnderecoTermoEstagio" value="${ complementoEnderecoTermoEstagio }" />
                    <input type="hidden" name="bairroEnderecoTermoEstagio" value="${ bairroEnderecoTermoEstagio }" />
                    <input type="hidden" name="cepEnderecoTermoEstagio" value="${ cepEnderecoTermoEstagio }" />
                    <input type="hidden" name="ufTermo" value="${ ufTermo }" />
                    <input type="hidden" name="estadoEnderecoTermoEstagio" value="${ estadoEnderecoTermoEstagio }" />
                    <input type="hidden" name="nomeSupervisor" value="${ nomeSupervisor }" />
                    <input type="hidden" name="cargoSupervisor" value="${ cargoSupervisor }" />
                    <input type="hidden" name="dataInicioTermoEstagio" value="${ dataInicioTermoEstagio }" />
                    <input type="hidden" name="dataFimTermoEstagio" value="${ dataFimTermoEstagio }" />
                    
                </c:if>
                    <br>
                <button type="submit" class="btn btn-primary" ${ isVisualizacao eq true ? 'disabled' :'' }><fmt:message key = "br.cefetrj.sisgee.resources.form.salvar"/></button>
                <c:choose>
                    <c:when test="${ not empty termoEstagio }">
                        <button type="button" class="btn btn-secondary" onclick="javascript:location.href = 'form_termo_aditivo.jsp'"><fmt:message key = "br.cefetrj.sisgee.resources.form.cancelar"/></button>
                    </c:when>
                    <c:otherwise>
                        <button type="button" class="btn btn-secondary" onclick="javascript:location.href = 'index.jsp'"><fmt:message key = "br.cefetrj.sisgee.resources.form.cancelar"/></button>
                    </c:otherwise>
                </c:choose>	
            </form>

            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="myModalLabel"></h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body"></div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" data-dismiss="modal"><fmt:message key = "br.cefetrj.sisgee.resources.form.fechar"/></button>
                        </div>
                    </div>
                </div>
            </div>
                        

        </div>
        <%@include file="import_footer.jspf"%>
        <%@include file="import_finalbodyscripts.jspf"%>
        
        <script>
            $(document).ready(function () {
                $('#cargaHorariaTermoEstagio').mask('9');
                $('#valorBolsa').mask('000.000,00', {reverse: true});
                $('#dataInicioTermoEstagio').mask('99/99/9999');
                $('#dataFimTermoEstagio').mask('99/99/9999');
                $('#cepEnderecoTermoEstagio').mask('99.999-999');
            });
            
            $('#numeroConvenio').on('keypress', function(e){
	        if (e.keyCode == 13) {
	            e.preventDefault();
	            $(".btnBuscarConvenio").click();
	        }
	    });	    
	    
            $('#nomeConveniado').on('keypress', function(e){
	        if (e.keyCode == 13) {
	            e.preventDefault();
	            $(".btnBuscarConvenio").click();
	        }

	    });	  
	    $('.btnBuscarConvenio').click(function(){
                if(($.trim($('#numeroConvenio').val()) == "") && ($.trim($('#nomeConvenio').val()) == "")){
                    $(".dadosConvenio input:not([id=numeroConvenio])").val("");
                    $(".dadosConvenio input:not([id=nomeConvenio])").val("");
                    $("#myModalLabel").html("<fmt:message key="br.cefetrj.sisgee.resources.form.numeroConvenio_nomeConvenio_vazios_titulo"/>");
                    $(".modal-body").html("<fmt:message key="br.cefetrj.sisgee.resources.form.numeroConvenio_nomeConvenio_vazios_msg"/>");      	
                    $('#myModal').modal('show');
                    return;
	    	}
	    	if(($.trim($('#numeroConvenio').val()) != "") && ($.trim($('#nomeConvenio').val()) != "")){
                    $(".dadosConvenio input:not([id=numeroConvenio])").val("");
                    $(".dadosConvenio input:not([id=nomeConvenio])").val("");
                    $("#myModalLabel").html("<fmt:message key="br.cefetrj.sisgee.resources.form.numeroConvenio_nomeConvenio_dois_preenchidos_titulo"/>");
                    $(".modal-body").html("<fmt:message key="br.cefetrj.sisgee.resources.form.numeroConvenio_nomeConvenio_dois_preenchidos_msg"/>");      	
                    $('#myModal').modal('show');
                    return;
	    	}
	    	var result = null;
                
	        $.ajax({
	            type: 'GET',
	            url: 'BuscaConvenioDoTermoEstagioServlet', //Servlet
	            async: true, // habilita a função ajax() repassar os dados para a função pai
	            data: $('.infoConvenio').serialize(),
	            dataType: "json",
	            success: function(json){
                            result = json;
                            if((result.idConvenio != "")&&(result.valido == true)){
                                $("#idConvenio").val(result.idConvenio);
                                $("#cpf_cnpj").val(result.cpf_cnpj);
                                $("#razaoSocial").val(result.razaoSocial);
                                $("#tipo").val(result.tipo);
                                $("#agente").val(result.agente);

                                
                                if((result.agente == "true") && (result.tipo == "true")){
                                    $("#agenteSim").attr("checked",true);
                                    $("#tipoPJ").attr("checked",true);
                                    $("#agenciada").attr("readonly",false);
                                }else if((result.agente == "false") && (result.tipo == "true")){
                                    $("#agenteNao").attr("checked",true);
                                    $("#tipoPJ").attr("checked",true);
                                    $("#agenciada").attr("readonly",true);
                                }else{
                                    $("#agenteNao").attr("checked",true);
                                    $("#tipoPF").attr("checked",true);
                                    $("#agenciada").attr("readonly",true);
                                }    
                            }
                            if((result.idConvenio == "")&&(result.valido == false)){
                                    $(".dadosConvenio input:not([id=numeroConvenio])").val("");
                                    $(".dadosConvenio input:not([id=nomeConvenio])").val("");
                                    if(result.erroConvenioNumero == true){
                                        $("#myModalLabel").html("<fmt:message key="br.cefetrj.sisgee.valida_busca_convenio_termo_estagio_servlet.msg_titulo_numero_convenio"/>");
                                        $(".modal-body").html("<fmt:message key="br.cefetrj.sisgee.valida_busca_convenio_termo_estagio_servlet.msg_tamanho_numero_convenio"/>");	        	
                                        $('#myModal').modal('show');
                                        
                                    }
                                    if(result.erroConvenioNome == true){
                                        $("#myModalLabel").html("<fmt:message key="br.cefetrj.sisgee.valida_busca_convenio_termo_estagio_servlet.msg_titulo_nome_convenio"/>");
                                        $(".modal-body").html("<fmt:message key="br.cefetrj.sisgee.valida_busca_convenio_termo_estagio_servlet.msg_tamanho_nome_convenio"/>");	        	
                                        $('#myModal').modal('show');
                                        
                                    }    
                             }
                             if((result.idConvenio == "")&&(result.valido == true)){
                                    
                                    
                                    $("#agenteSim").attr("checked",false);
                                    $("#tipoPF").attr("checked",false);
                                    $("#agenteNao").attr("checked",false);
                                    $("#tipoPJ").attr("checked",false);
                                    $("#agenciada").attr("readonly",true);
                                    
                                    $(".dadosConvenio input:not([id=numeroConvenio])").val("");
                                    $(".dadosConvenio input:not([id=nomeConvenio])").val("");
                                    $("#myModalLabel").html("<fmt:message key="br.cefetrj.sisgee.resources.form.convenio_nao_encontrado"/>");
                                    $(".modal-body").html("<fmt:message key="br.cefetrj.sisgee.resources.form.informacoes_nao_encontraram_convenio"/>");	        	
                                    $('#myModal').modal('show');
                                    
                                    
                            }
                            
                    }  

	        });
	    });
        </script>
        <%@include file="import_scripts.jspf"%>
    </body>
</html>