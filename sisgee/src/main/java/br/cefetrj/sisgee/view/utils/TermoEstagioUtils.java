/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.sisgee.view.utils;

import br.cefetrj.sisgee.model.entity.Convenio;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;

/**
 *
 * @author vinicius
 */
public class TermoEstagioUtils {
    public static String getVigencia(Date inicio, Date dfinal){
        String ini="";
        String fim="";
        SimpleDateFormat in= new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat out = new SimpleDateFormat("dd/MM/yy");
        String ano,mes;
        String futuro;
        
        try{
            ini = out.format(in.parse(inicio.toString()));
            ano = ini.substring(3);
            mes=ini.substring(0,2);
            
            fim = out.format(in.parse(dfinal.toString()));
            ano = fim.substring(3);
            mes=fim.substring(0,2);
            return ini + " - " + fim;

        }catch(Exception e){
            Logger lg = Logger.getLogger(Convenio.class);
            lg.error("Erro para determinar a vigência do termo de estagio", e);
            return ini;
        }
        
    }
}
