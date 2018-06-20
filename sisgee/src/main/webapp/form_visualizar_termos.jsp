<%-- 
    Document   : form_visualizar_termos
    Created on : 20/06/2018, 01:41:47
    Author     : vinicius
--%>

<!DOCTYPE html>
<%@page import="br.cefetrj.sisgee.view.utils.ConvenioUtils"%>
<%@page import="br.cefetrj.sisgee.view.utils.TermoEstagioUtils"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="import_head.jspf"%>
        <title><fmt:message key="br.cefetrj.sisgee.resources.form.visualizar.termos.titulo"/></title>
    </head>
    <body>
        <c:if test="${not empty termoEstagio}">
            <div class="table-responsive">
                <table class="table table-striped table-bordered">
                    <thead>
                        <tr class="table-active">
                            <th><fmt:message key = "br.cefetrj.sisgee.resources.form.consultar.termo.tipo"/></th>
                            <th><fmt:message key = "br.cefetrj.sisgee.resources.form.visualizar.termos.matriculaAluno"/></th>
                            <th><fmt:message key = "br.cefetrj.sisgee.resources.form.visualizar.termos.nomeAluno"/></th>
                            <th><fmt:message key = "br.cefetrj.sisgee.resources.form.visualizar.termos.numeroConvenio"/></th>
                            <th><fmt:message key = "br.cefetrj.sisgee.resources.form.visualizar.termos.nomeConveniado"/></th>
                            <th><fmt:message key = "br.cefetrj.sisgee.resources.form.visualizar.termos.nomeAgenciada"/></th>
                            <th><fmt:message key = "br.cefetrj.sisgee.resources.form.visualizar.termos.nomeProfessorOrientador"/></th>
                            <th><fmt:message key = "br.cefetrj.sisgee.resources.form.visualizar.termos.eAtivo"/></th>
                            <th><fmt:message key = "br.cefetrj.sisgee.resources.form.estagioObrigatorio"/></th>
                            <th><fmt:message key = "br.cefetrj.sisgee.relatorio.relatorio_consolidado.lbl_data_inicio"/></th>
                            <th><fmt:message key = "br.cefetrj.sisgee.relatorio.relatorio_consolidado.lbl_data_termino"/></th>
                            <th><fmt:message key = "br.cefetrj.sisgee.resources.form_termo_rescisao.data_rescisao"/></th>
                            <th><fmt:message key = "br.cefetrj.sisgee.resources.form.cargaHorariaAluno"/></th>
                            <th><fmt:message key = "br.cefetrj.sisgee.resources.form.valorBolsaEstagio"/></th>
                            <th><fmt:message key = "br.cefetrj.sisgee.resources.form.nomeSupervisor"/></th>
                            <th><fmt:message key = "br.cefetrj.sisgee.resources.form.cargoSupervisor"/></th>
                            <th><fmt:message key = "br.cefetrj.sisgee.resources.form.visualizar.termos.motivoAditivo"/></th>
                            <th><fmt:message key = "br.cefetrj.sisgee.resources.form.endereco"/></th>
                            <th><fmt:message key = "br.cefetrj.sisgee.resources.form.complemento"/></th>
                            <th><fmt:message key = "br.cefetrj.sisgee.resources.form.bairro"/></th>
                            <th><fmt:message key = "br.cefetrj.sisgee.resources.form.cidade"/></th>
                            <th><fmt:message key = "br.cefetrj.sisgee.resources.form.estado"/></th>
                            <th><fmt:message key = "br.cefetrj.sisgee.resources.form.cep"/></th>

                            <th></th>
                        </tr>
                    </thead>
                    <tbody>		
                        <tr>
                            <c:choose>
                                <c:when test="${empty termoEstagio.getTermoEstagioAditivo()}">
                                    <td align='center'><fmt:message key = "br.cefetrj.sisgee.resources.form.consultar.termo.termoEstagio"/></td>
                                </c:when>
                                <c:otherwise>
                                    <td align='center'><fmt:message key = "br.cefetrj.sisgee.resources.form.consultar.termo.termoAditivo"/></td>
                                </c:otherwise>
                            </c:choose>
                            <td align='center'>${termoEstagio.getAluno().matricula}</td>
                            <td align='center'>${termoEstagio.getAluno().nome}</td>
                            <td align='center'>${ConvenioUtils.getNumeroConvenioFormatado(termoEstagio.getConvenio().numeroConvenio)}</td>
                            <td align='center'>${termoEstagio.getConvenio().nomeConveniado}</td>
                            <td align='center'>${termoEstagio.agenciada}</td>
                            <td align='center'>${termoEstagio.getProfessorOrientador().nomeProfessorOrientador}</td>
                            <c:choose>
                                <c:when test="${termoEstagio.getEAtivo()}">
                                    <td align='center'><fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_sim"/></td>
                                </c:when>
                                <c:otherwise>
                                    <td align='center'><fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_nao"/></td>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${termoEstagio.getEEstagioObrigatorio()}">
                                    <td align='center'><fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_sim"/></td>
                                </c:when>
                                <c:otherwise>
                                    <td align='center'><fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_nao"/></td>
                                </c:otherwise>
                            </c:choose>
                            <td align='center'><fmt:formatDate value="${termoEstagio.getDataInicioTermoEstagio()}" type="date" dateStyle="short"/></td>
                            <td align='center'><fmt:formatDate value="${termoEstagio.getDataFimTermoEstagio()}" type="date" dateStyle="short"/></td>
                            <td align='center'><fmt:formatDate value="${termoEstagio.getDataRescisaoTermoEstagio()}" type="date" dateStyle="short"/></td>
                            <td align='center'>${termoEstagio.getCargaHorariaTermoEstagio()}</td>
                            <td align='center'>${termoEstagio.getValorBolsa()}</td>
                            <td align='center'>${termoEstagio.getNomeSupervisor()}</td>
                            <td align='center'>${termoEstagio.getCargoSupervisor()}</td>
                            <td align='center'>${termoEstagio.getMotivoAditivo()}</td>
                            <td align='center'>${termoEstagio.getEnderecoTermoEstagio()}</td>
                            <td align='center'>${termoEstagio.getComplementoEnderecoTermoEstagio()}</td>
                            <td align='center'>${termoEstagio.getBairroEnderecoTermoEstagio()}</td>
                            <td align='center'>${termoEstagio.getCidadeEnderecoTermoEstagio()}</td>
                            <td align='center'>${termoEstagio.getEstadoEnderecoTermoEstagio()}</td>
                            <td align='center'>${TermoEstagioUtils.getCepFormatado(termoEstagio.getCepEnderecoTermoEstagio())}</td>
                        </tr>
                    </tbody>
                </table>
            </div><br><br>
        </c:if>
        <div align='center'>
            <button type="button" class="btn btn-secondary" onclick="javascript:window.close()"><fmt:message key = "br.cefetrj.sisgee.resources.form.fechar"/></button>
        </div>
    </body>
</html>
