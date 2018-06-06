/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.sisgee.view.utils;

import br.cefetrj.sisgee.model.entity.Convenio;
import br.cefetrj.sisgee.model.entity.TermoEstagio;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
    
    public static String temTermoEstagioAtivo(List<TermoEstagio> termos) {
        String idTermoEstagioAtivo = "";
        int pos [] = new int[termos.size()];
        int contPos = 0;
        int qtdtermos = 0;
        if (termos != null) {
            for (int i = 0; i < termos.size(); i++) {
                if (termos.get(i).getTermoEstagioAditivo() == null) {
                    qtdtermos++;
                    pos[contPos] = i;
                    contPos++;
                }
            }
        }
        if(qtdtermos == 1) {
            if (termos.get(pos[contPos-1]).getEAtivo()) {
                idTermoEstagioAtivo = termos.get(pos[contPos-1]).getIdTermoEstagio().toString();
                return idTermoEstagioAtivo;
            }else {
                return null;
            }
            
        }else if (qtdtermos > 1) {
            boolean temTermoAtivo = false; 
            for (int i = 0; i < qtdtermos; i++) {
                if (termos.get(pos[i]).getEAtivo()) {
                    idTermoEstagioAtivo = termos.get(pos[i]).getIdTermoEstagio().toString();
                    temTermoAtivo = true;
                    return idTermoEstagioAtivo;
                }
            }
            if (temTermoAtivo==false) {
                return null;
            }
        }else {
            return null;
        }
        return null;
    }
}
