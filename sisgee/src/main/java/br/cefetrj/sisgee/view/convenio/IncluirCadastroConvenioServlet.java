/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.sisgee.view.convenio;

import br.cefetrj.sisgee.view.utils.ServletUtils;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author denis
 */

@WebServlet("/IncluirCadastroConvenioServlet")
public class IncluirCadastroConvenioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Locale locale = ServletUtils.getLocale(req);
		ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);
		String cnpj = req.getParameter("cnpj");
                //remove caracteres especiais antes de salvar o CNPJ
                cnpj = cnpj.replaceAll("[.|/|-]", "");
		String razaoSocial = req.getParameter("razaoSocial");
		String agenteIntegracao = req.getParameter("agenteIntegracao");
    
}
