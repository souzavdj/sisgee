/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.sisgee.view.convenio;

import br.cefetrj.sisgee.control.ConvenioServices;
import br.cefetrj.sisgee.model.entity.Convenio;
import br.cefetrj.sisgee.view.utils.ServletUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet que carrega as listas dos convenios  a vencer do banco antes de ir na pagina form_convenios_a_vencer.E ao chegar lá aparecerá a tabela com as informações desses convneios.
 * @author Andre
 */
@WebServlet("/ConveniosVencerServlet")
public class ConveniosVencerServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    
        Locale locale = ServletUtils.getLocale(req);
	ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);
        String msg ="";
        
        List<Convenio> listaVencidos = new ArrayList<Convenio>();
        
        
        /*Calendar cal = new GregorianCalendar();
        Date dataAtual = new Date();
        Date dataAntiga= new Date();
        int anoAtual=cal.get(Calendar.YEAR);
        int mesAtual=cal.get(Calendar.MONTH);//pegar daqui a 1 e 2 meses
        int diaAtual=cal.get(Calendar.DAY_OF_MONTH);
        String formatoDataAtual = diaAtual +"/" +mesAtual +"/"+anoAtual;*/ 
        
        Convenio c = ConvenioServices.buscarConvenio(new Convenio(1));
        Date d = c.getDataAssinatura();
        System.out.println(d.toString());
        
        SimpleDateFormat in= new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat out = new SimpleDateFormat("MM/yy");
        
        String result;
        String ano,mes;
        
        String anoFuturo,tempoFuturo;
        try {
            result = out.format(in.parse(d.toString()));
             System.out.println(result);
             ano = result.substring(3);
             mes=result.substring(0,2);
             System.out.println(ano);
             System.out.println(mes);
             
             
             anoFuturo=String.valueOf(Integer.parseInt(ano+5));
             tempoFuturo=mes+"/"+anoFuturo;
             System.out.println(tempoFuturo);
             
        } catch (ParseException ex) {
            Logger.getLogger(ConveniosVencerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int b =6;
        String s = String.valueOf(b);
        System.out.println(b);
        
        listaVencidos.add(c);
        req.setAttribute("ListaConveniosAVencer", listaVencidos); 
       
        //Tenho que conseguir a lista dos convenios que serão vencidos daqui a 1 mes e 2 meses.
        
        //Objetivo fazer um listaVencidos =(List<Convenio>) ConveniosService.buscarListaDeVencidos(anoAtual,dataAntiga,dataAtual).
       
        /*Dentro desse metodo ConvenioDAO convenioDao = new ConvenioDAO();
            try{
                    List<Convenio> lista =List<Convenio> convenioDao.buscarVencidos(paramentros);
                    return lista;
            }catch(Exception e){
                    return null;
            }
        
        */
        
        //Fazer a query magica no ConvenioDAO que retorne a lista dos Vencidos .
        
        //Se retornar null é pq n tem convenios vencidos daqui a1 e 2 meses e botar msg de retorno
        
        
        

        //Parte final para botar no jsp:req.setAttribute("ListaConveniosAVencer", listaVencidos);
        
        
        /*Mensagem caso a lista retorne nada e ira no jsp
            if(listaVencidos==null){
            msg = messages.getString("br.cefetrj.sisgee.form_convenios_a_vencer.msg_relatorio_vazio");
            req.setAttribute("MsgDeErro",msg);
        }*/
        
        req.getRequestDispatcher("/form_convenios_a_vencer.jsp").forward(req, resp);
    }
    
    
}
