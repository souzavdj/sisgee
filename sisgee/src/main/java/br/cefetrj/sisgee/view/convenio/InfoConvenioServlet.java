/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author denis
 */
@WebServlet("/InfoConvenioServlet")
public class InfoConvenioServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/form_renovar_convenio_infos.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String id = req.getParameter("convenioSelecionado");
        
        Convenio c = ConvenioServices.buscarConvenioByCpf_Cnpj(id);
        
        String convenio = ConvenioUtils.getNumeroCovenioFormatado(c.getNumeroConvenio());
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
