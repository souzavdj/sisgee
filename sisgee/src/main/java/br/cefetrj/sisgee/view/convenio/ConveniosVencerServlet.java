/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.sisgee.view.convenio;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet que carrega as listas dos convenios  a vencer do bacno antes de ir na pagina form_convenios_a_vencer.E ao chegar lá aparecerá a tabela com as informações desses convneios.
 * @author Andre
 */
@WebServlet("/ConveniosVencerServlet")
public class ConveniosVencerServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    
    
    
        
        
        req.getRequestDispatcher("/form_convenios_a_vencer.jsp").forward(req, resp);
    }
    
    
}
