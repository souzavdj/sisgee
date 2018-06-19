
package br.cefetrj.sisgee.view.convenio;


import br.cefetrj.sisgee.control.AlunoServices;
import br.cefetrj.sisgee.control.ConvenioServices;
import br.cefetrj.sisgee.model.entity.Aluno;
import br.cefetrj.sisgee.model.entity.Convenio;
import br.cefetrj.sisgee.model.entity.TermoEstagio;
import br.cefetrj.sisgee.view.termoestagio.FormTermoEstagioServlet;
import br.cefetrj.sisgee.view.utils.ConvenioUtils;
import br.cefetrj.sisgee.view.utils.ServletUtils;
import br.cefetrj.sisgee.view.utils.ValidaUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 * Servlet focado na busca de convenio (vindo do termo_estagio.jsp)
 * @author Andre
 * @since 2.0
 */
@WebServlet("/BuscaConvenioDoTermoEstagioServlet")
public class BuscaConvenioDoTermoEstagioServlet extends HttpServlet {
     private static final long serialVersionUID = 1L;
    

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        Locale locale = ServletUtils.getLocale(req);
        ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);
        
        String numConvenio = req.getParameter("numeroConvenio");
        String nomeConveniado = req.getParameter("nomeConvenio");
        String idConvenio = "";
        String tipo="";
        String agente="";
        String razao="";
        String numero="";
        Convenio buscado = null;
        boolean isValid=true;
        boolean erroConvenioNumero=false;
        boolean erroConvenioNome=false;
        String numeroConvenioMsg = "";
        String nomeConvenioMsg = "";
        //Todo referencia em ingles
        System.out.println("NomeConveniado:" + nomeConveniado);
        System.out.println("NumeroConveniado:" + numConvenio);
        if(!numConvenio.trim().isEmpty()){
            System.out.println("Entrou no busca convenio pelo numero");
            String num =String.format("%06d",Integer.parseInt(numConvenio.trim()));
            numeroConvenioMsg=ValidaUtils.validaTamanho("numeroConvenio",6,numConvenio);
            if(numeroConvenioMsg.trim().isEmpty()){
                buscado = ConvenioServices.buscarBy6Numero(num);

            }else{
                System.out.println("Aqui erro numero");
                isValid=false;
                erroConvenioNumero=true;
                Logger lg = Logger.getLogger(BuscaConvenioDoTermoEstagioServlet.class);
                lg.info(numeroConvenioMsg);
            }
          
        }else{
            System.out.println("Entrou no busca convenio pelo nome");
            nomeConvenioMsg=ValidaUtils.validaTamanho("nomeConveniado",100,nomeConveniado);
            if(nomeConvenioMsg.trim().isEmpty()){
                buscado = ConvenioServices.buscarConvenioByNomeConveniado(nomeConveniado.toUpperCase());
            }else{
                System.out.println("Aqui erro nome");
                isValid=false;
                erroConvenioNome=true;
                Logger lg = Logger.getLogger(BuscaConvenioDoTermoEstagioServlet.class);
                lg.info(nomeConvenioMsg);
            }
            
          
        }
       
        System.out.println("Passou :" +buscado);
        if (buscado != null) {
            idConvenio = Integer.toString(buscado.getIdConvenio());
            System.out.println("Tipo pessoa :" +buscado.getIsPessoaJuridica());
            tipo=Boolean.toString(buscado.getIsPessoaJuridica());
            System.out.println("Tipo agente :" +buscado.getIsAgenteIntegracao());
            agente = Boolean.toString(buscado.getIsAgenteIntegracao());
            razao = buscado.getNomeConveniado();
            if(buscado.getIsAgenteIntegracao()){
                numero = ConvenioUtils.getCnpjEmpresaFormatado(buscado.getCpf_cnpj());
                
            }else{
                numero = ConvenioUtils.getCpfFormatado(buscado.getCpf_cnpj());
               
            }
            
        }

        //JSON
        JsonObject model = Json.createObjectBuilder()
                .add("idConvenio", idConvenio)
                .add("agente", agente)
                .add("tipo", tipo)
                .add("cpf_cnpj", numero)
                .add("razaoSocial",razao)
                .add("agenciada",razao)
                .add("valido",isValid)
                .add("erroConvenioNumero",erroConvenioNumero)
                .add("erroConvenioNome",erroConvenioNome)
                .build();

        StringWriter stWriter = new StringWriter();
        JsonWriter jsonWriter = Json.createWriter(stWriter);
        jsonWriter.writeObject(model);
        jsonWriter.close();
        String jsonData = stWriter.toString();

        resp.setContentType("application/json");
        resp.getWriter().print(jsonData);
    }
   
}
