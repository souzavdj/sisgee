
package br.cefetrj.sisgee.view.utils;

import br.cefetrj.sisgee.model.entity.Convenio;
import br.cefetrj.sisgee.model.entity.TermoEstagio;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

/** Class Utils associada ao TermoEstagio, com métodos focados ao tratamaneto dos atributos do termo de estagio
 *
 * @author vinicius
 */
public class TermoEstagioUtils {
    
     /** Recupera a vigencia de convenio a partir de um intervalos de datas
     * 
     *  @param inicio Date com a data limite inferior do intervalo 
     *  @param dfinal Date com a data limite final do intervalo 
     * 
     *  @return String com vigencia do termo
     */
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
    
     /** Recupera a informação de termo estágio ativo
     * 
     *  @param termos List de TermoEstagio  
     * 
     *  @return String com o id do termo ativo
     */
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
    
    /** Recupera o cep formatado do Termo
    *   @param cep String com o cep a ser formatado 
    * 
    *   @return String com o cep passado formatado
    */
    public static String getCepFormatado(String cep) {
        if (cep != null && cep.trim().length() > 0) {
            StringBuilder cepFormatado = new StringBuilder();
            try {
                cepFormatado.append(cep.charAt(0));
                cepFormatado.append(cep.charAt(1));
                cepFormatado.append(cep.charAt(2));
                cepFormatado.append(cep.charAt(3));
                cepFormatado.append(cep.charAt(4));
                cepFormatado.append("-");
                cepFormatado.append(cep.charAt(5));
                cepFormatado.append(cep.charAt(6));
                cepFormatado.append(cep.charAt(7));
            } catch (IndexOutOfBoundsException e) {
                Logger lg = Logger.getLogger(TermoEstagio.class);
                lg.error("CEP com menos de 8 caracteres. CEP = " + cep, e);
                return cep;
            }
            return cepFormatado.toString();
        } else {
            return null;
        }
    }
}
