package br.cefetrj.sisgee.view.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe para auxiliar na organizacao do relatorio consolidado.
 *
 * @author Marcos Eduardo
 * @since 1.0
 */
public class ItemRelatorio {

    private String nomeCurso;
    private int qntTermoEstagio;
    private int qntTermoAditivo;
    private int qntRescReg;

    
    /** Construtor da classe com 1 parametro
    * @param nomeCurso String com o nome do curso
    */
    public ItemRelatorio(String nomeCurso) {
        super();
        this.nomeCurso = nomeCurso;
    }

     
    /** Construtor da classe com 3 parametrs
    * @param  nomeCurso String com o nome do curso
    * @param qntTermoEstagio int com a quantidade de termo estagio
    * @param qntRescReg int com a quantidade de MUDAR
    */
    public ItemRelatorio(String nomeCurso, int qntTermoEstagio, int qntRescReg) {
        super();
        this.nomeCurso = nomeCurso;
        this.qntTermoEstagio = qntTermoEstagio;
        this.qntRescReg = qntRescReg;
    }

    /** Serviço que recupera o nome do curso
    * 
    * @return String com o nome
    */
    public String getNomeCurso() {
        return nomeCurso;
    }

    /** Serviço que atribui o nome do curso
    * 
    * @param nomeCurso String com o nome
    */
    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    
    /** Serviço que recupera a quantidade de termos estagio
    * 
    * @return int com a quantidade
    */
    public int getQntTermoEstagio() {
        return qntTermoEstagio;
    }

    /** Serviço que atribui a quantidade de termos estagio
    * 
    * @param qntTermoEstagio int com quantidade
    */
    public void setQntTermoEstagio(int qntTermoEstagio) {
        this.qntTermoEstagio = qntTermoEstagio;
    }

    /** Serviço que recupera a quantidade de termos aditivios
    * 
    * @return int com a quantidade
    */
    public int getQntTermoAditivo() {
        return qntTermoAditivo;
    }

    /** Serviço que atribui a quantidade de termos aditivios
    * 
    * @param qntTermoAditivo int com quantidade
    */
    public void setQntTermoAditivo(int qntTermoAditivo) {
        this.qntTermoAditivo = qntTermoAditivo;
    }

    /** Serviço que recupera a quantidade de MUDAR
    * 
    * @return int com quantidade
    */
    public int getQntRescReg() {
        return qntRescReg;
    }

    
    /** Serviço que atribui a quantidade de MUDAR
    * 
    * @param qntRescReg int com quantidade
    */
    public void setQntRescReg(int qntRescReg) {
        this.qntRescReg = qntRescReg;
    }
    
    /** Serviço que recupera o formato da data do Relatorio
    * 
    * @param date é a data que será formatada
    * @return Date com o formato da data d
    */
    public static String formateDate (Date date) {
        SimpleDateFormat ano = new SimpleDateFormat("yyyy");
        SimpleDateFormat mes = new SimpleDateFormat("MM");
        SimpleDateFormat dia = new SimpleDateFormat("dd");
        
        String dataFormatada = String.valueOf(ano.format(date)) + "-" + String.valueOf(mes.format(date)) + "-" + String.valueOf(dia.format(date));
        return dataFormatada;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nomeCurso == null) ? 0 : nomeCurso.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ItemRelatorio other = (ItemRelatorio) obj;
        if (nomeCurso == null) {
            if (other.nomeCurso != null) {
                return false;
            }
        } else if (!nomeCurso.equals(other.nomeCurso)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ItemRelatorio [nomeCurso=" + nomeCurso + ", qntTermoEstagio=" + qntTermoEstagio + ", qntTermoAditivo="
                + qntTermoAditivo + ", qntRescReg=" + qntRescReg + "]";
    }

}
