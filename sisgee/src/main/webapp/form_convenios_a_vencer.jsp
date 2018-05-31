<%-- 
    Document   : convenios_a_vencer
    Created on : 24/05/2018, 19:35:32
    Author     : vinicius
--%>

<!DOCTYPE html>
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
                <table class="table table-striped table-bordered col-6">
                    <thead>
                        <tr class="table-active">
                            <th>Vigência</th>
                            <th>Convênio</th>
                            <th>Razão Social/Nome</th>
                            <th>CNPJ/CPF</th>
                            <th> E-mail </th>
                            <th> Telefone</th>
                            <th> Pessoa de contato</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${not empty ListaConveniosAVencer}">
                            <c:forEach items="${ListaConveniosAVencer}" var="conv">
                                <tr>
                                    <td>${conv.dataAssinatura}</td>
                                    <td>${conv.numeroConvenio}</td>
                                    <td>${conv.nomeConveniado}</td>
                                    <td>${conv.cpf_cnpj}</td>
                                    <td>${conv.email}</td>
                                    <td>${conv.telefone}</td>
                                    <td>${conv.pessoaContato}</td>
                                   
                                </tr>
                                
                                
                            </c:forEach>
                        </c:if>
                        
                    </tbody>
                </table>
                <div class="text-center">
                    <button type="button" class="btn btn-secondary mx-auto" onclick="javascript:location.href='index.jsp'"><fmt:message key = "br.cefetrj.sisgee.form_convenios_a_vencer.msg_cancelar"/></button>
                </div> 
                       
        </div>
                
        <%@include file="import_footer.jspf"%>
	<%@include file="import_finalbodyscripts.jspf"%>
    </body>
</html>
