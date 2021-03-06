package br.cefetrj.sisgee.view.convenio;

import br.cefetrj.sisgee.control.ConvenioServices;
import br.cefetrj.sisgee.model.entity.Convenio;
import br.cefetrj.sisgee.view.utils.ConvenioUtils;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet para recuperar informações do Convenio 
 * 
 * @author denis
 * @since 2.0
 */
@WebServlet("/InfoConvenioServlet")
public class InfoConvenioServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    
    
     /**
     * Metodo doGet para fazer o RequestDispatcher para o form de renovar convenio
     *
     * @param req é a requisição que o servidor recebe do navegador
     * @param resp é a resposta que o servidor envia ao navegador
     * @throws ServletException exceção do Servlet
     * @throws IOException exceção de IO
     */
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/form_renovar_convenio_infos.jsp").forward(req, resp);
    }
    
     /* Metodo doPost para receber a requisição do convenio
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String id = req.getParameter("convenioSelecionado");
        
        Convenio c = ConvenioServices.buscarConvenioByCpf_Cnpj(id);
        
        String convenio = ConvenioUtils.getNumeroConvenioFormatado(c.getNumeroConvenio());
        req.setAttribute("convenio", convenio);
        req.setAttribute("tipo", c.getIsPessoaJuridica());
        req.setAttribute("agente", c.getIsAgenteIntegracao());
        req.setAttribute("nomeConveniado", c.getNomeConveniado());
        req.setAttribute("cpf_cnpj", c.getCpf_cnpj());
        req.setAttribute("dataAssinatura",c.getDataAssinatura());
        req.setAttribute("email", c.getEmail());
        req.setAttribute("telefone", c.getTelefone());
        req.setAttribute("pessoaContato", c.getPessoaContato());
        
        req.getRequestDispatcher("/form_renovar_convenio_infos.jsp").forward(req, resp);
        
    }
    
}
