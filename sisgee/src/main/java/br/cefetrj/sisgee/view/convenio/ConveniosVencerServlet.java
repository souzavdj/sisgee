
package br.cefetrj.sisgee.view.convenio;

import br.cefetrj.sisgee.control.ConvenioServices;
import br.cefetrj.sisgee.model.entity.Convenio;
import br.cefetrj.sisgee.view.utils.ConvenioUtils;
import br.cefetrj.sisgee.view.utils.ServletUtils;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet que carrega as listas dos convenios  a vencer do banco antes de ir na pagina form_convenios_a_vencer.E ao chegar lá aparecerá a tabela com as informações desses convneios.
 * @author Andre
 * @since 2.0
 */
@WebServlet("/ConveniosVencerServlet")
public class ConveniosVencerServlet extends HttpServlet {

    
     /**
     *
     * Metodo que recebe informações voltados para convenios a vencer
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    
        Locale locale = ServletUtils.getLocale(req);
	ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);
        String msg ="";
        
        List<Convenio> listaVencidos=null ;
        Date dataInicio,dataFim;
        SimpleDateFormat in= new SimpleDateFormat("yyyy-MM-dd");
        
        /**
         * Descobrindo data atual
         */
        Calendar cal = new GregorianCalendar();
        int anoAtual=cal.get(Calendar.YEAR);
        int mesAtual=cal.get(Calendar.MONTH)+1;
        
        String imprimir = req.getParameter("imprimir");
        
        /**
         * Fazendo intervalo a partir do dia 1 do mes seguinte que termina no ultimo dia do proximo mes 
         */
        
        
        String inicioMes1 =(anoAtual-5)+"-"+(mesAtual)+"-01";
        String fimMes2 = (anoAtual-5)+"-"+(mesAtual+2)+"-31";
        
        /**
         * Transformando o intervalo em Date e usando como parametro na busca de convenios vencidos dentro do intervalo
         */
        try{
            dataInicio =(Date)in.parse(inicioMes1);
            dataFim = (Date)in.parse(fimMes2);
            listaVencidos =ConvenioServices.buscarListaDeVencidos(dataInicio,dataFim);
        }catch(Exception e){
            Logger lg = Logger.getLogger(ConveniosVencerServlet.class);
            lg.error("Problema nas datas de inicio e fim :",e);
            
        }
        
        
        if(listaVencidos==null || listaVencidos.isEmpty()){
            msg = messages.getString("br.cefetrj.sisgee.form_convenios_a_vencer.msg_relatorio_vazio");
            req.setAttribute("msg", msg);
            
        }else{
            req.setAttribute("ListaConveniosAVencer", listaVencidos); 
           // req.setAttribute("convenioUtils",cUtil);
        }
        if (imprimir != null) {
            req.getRequestDispatcher("/form_imprimir_convenios_a_vencer.jsp").forward(req, resp);
        }else {
            req.getRequestDispatcher("/form_convenios_a_vencer.jsp").forward(req, resp);
        }
    }
    
    
}
