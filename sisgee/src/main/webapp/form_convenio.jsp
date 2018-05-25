<%-- 
    Document   : form_convenio
    Created on : 24/05/2018, 19:06:07
    Author     : vinicius
--%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="import_head.jspf"%>
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

		<p class="tituloForm"><h5><fmt:message key="br.cefetrj.sisgee.form_convenio.msg_titulo" /></h5>		
		
		<form action="ValidarCadastroConvenioServlet" method="post">
			<fieldset class="form-group">
				<div class="form-row" >
                                    
                                   

                                    <div class="custom-controls-stacked d-block my-3">
                                            <label for="Tipo"><fmt:message key="br.cefetrj.sisgee.form_convenio.msg_tipo_de_pessoa" /></label>
                                            <label class="custom-control custom-radio"> 
                                                    <input id="Juridica" name="tipoPessoa" type="radio" class="custom-control-input" required value = "true" > 
                                                    <span class="custom-control-indicator"></span> 
                                                    <span class="custom-control-description" ><fmt:message key = "br.cefetrj.sisgee.form_convenio.msg_juridica"/></span>
                                            </label> 
                                            <label class="custom-control custom-radio"> 
                                                    <input id="Fisica" name="tipoPessoa" type="radio" class="custom-control-input" required value = "false" > 
                                                    <span class="custom-control-indicator"></span> 
                                                    <span class="custom-control-description"><fmt:message key = "br.cefetrj.sisgee.form_convenio.msg_fisica"/></span>
                                            </label>
                                    </div>
                                            
                                </div>
				
                        </fieldset>
					
			<button type="submit" class="btn btn-primary"><fmt:message key = "br.cefetrj.sisgee.form_convenio.msg_salvar"/></button>
			<button type="button" class="btn btn-secondary" onclick="javascript:location.href='index.jsp'"><fmt:message key = "br.cefetrj.sisgee.form_convenio.msg_cancelar"/></button>
								
		</form>
	</div>
        <%@include file="import_footer.jspf"%>
	<%@include file="import_finalbodyscripts.jspf"%>
    </body>
</html>
