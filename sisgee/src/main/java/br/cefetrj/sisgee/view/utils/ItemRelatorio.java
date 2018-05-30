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

    public ItemRelatorio(String nomeCurso) {
        super();
        this.nomeCurso = nomeCurso;
    }

    public ItemRelatorio(String nomeCurso, int qntTermoEstagio, int qntRescReg) {
        super();
        this.nomeCurso = nomeCurso;
        this.qntTermoEstagio = qntTermoEstagio;
        this.qntRescReg = qntRescReg;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public int getQntTermoEstagio() {
        return qntTermoEstagio;
    }

    public void setQntTermoEstagio(int qntTermoEstagio) {
        this.qntTermoEstagio = qntTermoEstagio;
    }

    public int getQntTermoAditivo() {
        return qntTermoAditivo;
    }

    public void setQntTermoAditivo(int qntTermoAditivo) {
        this.qntTermoAditivo = qntTermoAditivo;
    }

    public int getQntRescReg() {
        return qntRescReg;
    }

    public void setQntRescReg(int qntRescReg) {
        this.qntRescReg = qntRescReg;
    }
    
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
