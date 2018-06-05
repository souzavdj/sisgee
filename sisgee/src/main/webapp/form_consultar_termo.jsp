<%-- 
    Document   : form_consultar_termo
    Created on : 30/05/2018, 17:35:01
    Author     : vinicius
--%>

<!DOCTYPE html>
<%@page import="br.cefetrj.sisgee.view.utils.ConvenioUtils"%>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <%@include file="import_head.jspf"%>
        <jsp:useBean id="convenioUtils" class="br.cefetrj.sisgee.view.utils.ConvenioUtils" scope="page"/>
        <title>
            <fmt:message key = "br.cefetrj.sisgee.resources.form.consultar.termo.titulo"/>
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

            <p class="tituloForm">
            <h5>
                <fmt:message key = "br.cefetrj.sisgee.resources.form.consultar.termo.titulo"/>
            </h5>		

            <form action="BuscaTermoAditivoServlet" method="post" name="dadosAluno">
                <fieldset class="form-group dadosAluno" >
                    <%@include file="import_busca_aluno.jspf"%>
                </fieldset>
                
            </form>
                
            <div class="container">
                <button type="button" id="btnNovoAditivo" class="btn btn-secondary" disabled="true" onclick="abrirModalAditivo()"><fmt:message key = "br.cefetrj.sisgee.resources.form.novo_aditivo"/></button>
                <button type="button" id="btnRescisao" class="btn btn-secondary" disabled="true" onclick="abrirModalRescisao()"><fmt:message key = "br.cefetrj.sisgee.resources.form.registrar_reciscisao"/></button>
                <button type="button" class="btn btn-secondary" onclick="location.href = 'index.jsp'"><fmt:message key = "br.cefetrj.sisgee.resources.form.cancelar"/></button>
            </div>

            <div class="container" id="tabela" style="display: ${not empty termoEstagio ? 'block' : 'none' }">
                <table class = "table table">
                    <thead>		
                        <tr>
                            <th><fmt:message key = "br.cefetrj.sisgee.resources.form.consultar.termo.tipo"/></th>
                            <th><fmt:message key = "br.cefetrj.sisgee.resources.form.consultar.termo.status"/></th>
                            <th><fmt:message key = "br.cefetrj.sisgee.resources.form.consultar.termo.dataCadastro"/></th>
                            <th><fmt:message key = "br.cefetrj.sisgee.resources.form.consultar.termo.vigencia"/></th>
                            <th><fmt:message key = "br.cefetrj.sisgee.resources.form.consultar.termo.cnpj_cpf"/></th>
                            <th><fmt:message key = "br.cefetrj.sisgee.resources.form.consultar.termo.nomeConveniado"/></th>
                        </tr>
                    </thead>			
                    <tbody>
                        <c:forEach items="${termoEstagio}" var="termo" varStatus="status">
                            <tr>
                                <c:choose>
                                    <c:when test="${empty termo.getTermoEstagioAditivo()}">
                                        <td>Termo Estagio</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>Termo Aditivo</td>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${termo.getEAtivo()}">
                                        <td>Ativo</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>Inativo</td>
                                    </c:otherwise>
                                </c:choose>
                                        <td><fmt:formatDate value="${termo.getDataInicioTermoEstagio()}" type="date" dateStyle="short"/></td>
                                <td>${convenioUtils.getVigencia(termo.getDataInicioTermoEstagio())}</td>
                                <c:choose>
                                    <c:when test="${termo.getConvenio().getIsPessoaJuridica()}">
                                        <td>${convenioUtils.getCnpjEmpresaFormatado(termo.getConvenio().getCpf_cnpj())}</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>${convenioUtils.getCpfFormatado(termo.getConvenio().getCpf_cnpj())}</td>
                                    </c:otherwise>
                                </c:choose>
                                <td>${termo.getConvenio().getNomeConveniado()}</td>					
                            </tr>
                            
                        </c:forEach>
                    </tbody>
                </table>
            </div>

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

            <div class="modal fade" id="novoAditivoModal" tabindex="-1" role="dialog" aria-labelledby="novoAditivoModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="novoAditivoModalLabel"><fmt:message key = "br.cefetrj.sisgee.resources.form.selecione_alteracoes_aditivo"/>:</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form class="container" style="margin-top: 0px" name="novoAditivoForm" action="TermoAditivoServlet" method="post" >
                                <table border="0">
                                    <tr>
                                        <td>
                                            <div class="form-check form-check-inline">
                                                <label class="form-check-label">
                                                    <input class="form-check-input" type="checkbox" id="vigencia" name="vigencia"  value="sim"><fmt:message key = "br.cefetrj.sisgee.resources.form.vigenciaEstagio"/>
                                                </label>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="form-check form-check-inline">
                                                <label class="form-check-label">
                                                    <input class="form-check-input" type="checkbox" id="enderecoTermoEstagio" name="endereco" value="sim"><fmt:message key = "br.cefetrj.sisgee.resources.form.endereco"/>
                                                </label>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div class="form-check form-check-inline">
                                                <label class="form-check-label">
                                                    <input class="form-check-input" type="checkbox" id="cargaHorariaTermoEstagio" name="cargaHoraria" value="sim"><fmt:message key = "br.cefetrj.sisgee.resources.form.cargaHorariaAluno"/>
                                                </label>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="form-check form-check-inline">
                                                <label class="form-check-label">
                                                    <input class="form-check-input" type="checkbox" id="professorOrientador" name="professor" value="sim"><fmt:message key = "br.cefetrj.sisgee.resources.form.professorOrientador"/>
                                                </label>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div class="form-check form-check-inline">
                                                <label class="form-check-label">
                                                    <input class="form-check-input" type="checkbox" id="valorBolsa" name="valor" value="sim"><fmt:message key = "br.cefetrj.sisgee.resources.form.valorBolsaEstagio"/>
                                                </label>
                                            </div>
                                        </td>
                                        <td></td>
                                    </tr>
                                </table>
                                <input type="hidden" id="idAlunoAdt" name="idAlunoAdt" value="" />
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" id="btnAbrirNovoAditivo" class="btn btn-primary" onclick="document.forms['novoAditivoForm'].submit();"><fmt:message key = "br.cefetrj.sisgee.resources.form.preencher_aditivo"/></button>
                            <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key = "br.cefetrj.sisgee.resources.form.fechar"/></button>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="modal fade" id="rescisaoModal" tabindex="-1" role="dialog" aria-labelledby="rescisaoModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="rescisaoModalLabel"><fmt:message key = "br.cefetrj.sisgee.resources.form_termo_rescisao.registro_termo"/>:</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form class="container" style="margin-top: 0px" name="rescisaoForm" action="FormTermoRescisaoServlet" method="post" >
                                <fieldset class="form-group">
                                    <div class="form-row">
                                        <div class="form-group col-md-6">
                                            <label for="dataRescisao"><fmt:message key = "br.cefetrj.sisgee.resources.form_termo_rescisao.data_rescisao"/></label>
                                            <input type="text" class="form-control ${ not empty dataTermoRescisaoMsg ? 'is-invalid': not empty periodoMsg ? 'is-invalid' : 'is-valid' }" id="dataRescisao"  name="dataTermoRescisao" value="${ param.dataRescisao }" >
                                            <c:if test="${ not empty dataTermoRescisaoMsg }">
                                                <div class="invalid-feedback">${ dataTermoRescisaoMsg }</div>
                                            </c:if>
                                        </div>					
                                    </div>
                                </fieldset>

                                <button type="submit" class="btn btn-primary"><fmt:message key = "br.cefetrj.sisgee.resources.form.consultar.msg_salvar"/></button>                
                                <button type="button" class="btn btn-secondary" onclick="javascript:location.href = 'index.jsp'"><fmt:message key = "br.cefetrj.sisgee.resources.form.consultar.msg_cancelar"/></button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="import_footer.jspf"%>
        <%@include file="import_finalbodyscripts.jspf"%>
        <script type="text/javascript">
            
            function showMessage(title, msg) {
                $("#myModalLabel").html(title);
                $(".modal-body").html(msg);
                $('#myModal').modal('show');
            }
            function hablitarButoes() {
                $("#btnListarAditivo").prop("disabled", false);
                $("#btnListarAditivo").removeClass("btn-secondary");
                $("#btnListarAditivo").addClass("btn-primary");

                $("#btnNovoAditivo").prop("disabled", false);
                $("#btnNovoAditivo").removeClass("btn-secondary");
                $("#btnNovoAditivo").addClass("btn-primary");
                
                $("#btnRescisao").prop("disabled", false);
                $("#btnRescisao").removeClass("btn-secondary");
                $("#btnRescisao").addClass("btn-primary");
                
            }
            function desablitarButoes() {
                $("#btnListarAditivo").prop("disabled", true);
                $("#btnListarAditivo").removeClass("btn-primary");
                $("#btnListarAditivo").addClass("btn-secondary");

                $("#btnNovoAditivo").prop("disabled", true);
                $("#btnNovoAditivo").removeClass("btn-primary");
                $("#btnNovoAditivo").addClass("btn-secondary");
                
                $("#btnRescisao").prop("disabled", true);
                $("#btnRescisao").removeClass("btn-primary");
                $("#btnRescisao").addClass("btn-secondary");
                
            }
            function esconderTabela(){
                $("#tabela").css("display", "none");
            }
            var buscarAlunoCallback = function myCallback(json) {
                if (json != null) {
                    if (json.idAluno != null && json.idAluno != "") {
                        if (json.idTermoEstagioAtivo != null && json.idTermoEstagioAtivo != "") {
                            //tem termo de estágio, ativa os botões
                            
                            hablitarButoes();
                            document.forms['dadosAluno'].submit();
                        } else {
                            desablitarButoes();
                            esconderTabela();
                            titulo = '<fmt:message key = "br.cefetrj.sisgee.resources.form.termo_nao_encontrado_titulo"/>';
                            msg = '<fmt:message key = "br.cefetrj.sisgee.resources.form.termo_nao_encontrado_msg"/>';
                            //não tem termo de estágio
                            showMessage(titulo, msg);
                        }
                    } else {
                        desablitarButoes();
                        titulo = '<fmt:message key = "br.cefetrj.sisgee.resources.form.aluno_nao_encontrado_titulo"/>';
                        msg = '<fmt:message key = "br.cefetrj.sisgee.resources.form.aluno_nao_encontrado_msg"/>';
                        showMessage(titulo, msg);
                    }
                }
            }

            function abrirModalAditivo() {
                $('#novoAditivoModal').modal('show');
            }
            function abrirModalRescisao() {
                $('#rescisaoModal').modal('show');
            }
        </script>
        <%@include file="import_scripts.jspf"%>
        <script type="text/javascript">

            $(document).ready(function () {
                $(".form-check-input").change(function () {
                    $('#idAlunoAdt').val($("#idAluno").val());
                });
                
                <c:if test="${not empty termoEstagio.get(0).idTermoEstagio}">
                    hablitarButoes();
                </c:if>
                
                
            });

        </script>
        
    </body>
</html>
