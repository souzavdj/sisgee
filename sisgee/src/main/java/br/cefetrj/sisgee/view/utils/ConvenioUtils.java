
package br.cefetrj.sisgee.view.utils;

import br.cefetrj.sisgee.control.ConvenioServices;
import br.cefetrj.sisgee.model.entity.Convenio;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;

/* Class Utils associada ao Convenio, com métodos focados ao tratamaneto dos atributos do convenio

 * @author Vinicius Souza
 * @since 2.0
 */
public class ConvenioUtils {

    
        /** Recupera o cnpj formatado da empresa
         *  @param cnpjEmpresa String com o cnpj a ser formatado 
         * 
         * @return String com o cnpj passado formatado
         */
    public static String getCnpjEmpresaFormatado(String cnpjEmpresa) {
        if (cnpjEmpresa != null && cnpjEmpresa.trim().length() > 0) {
            StringBuilder cnpjFormatado = new StringBuilder();
            try {
                cnpjFormatado.append(cnpjEmpresa.charAt(0));
                cnpjFormatado.append(cnpjEmpresa.charAt(1));
                cnpjFormatado.append(".");
                cnpjFormatado.append(cnpjEmpresa.charAt(2));
                cnpjFormatado.append(cnpjEmpresa.charAt(3));
                cnpjFormatado.append(cnpjEmpresa.charAt(4));
                cnpjFormatado.append(".");
                cnpjFormatado.append(cnpjEmpresa.charAt(5));
                cnpjFormatado.append(cnpjEmpresa.charAt(6));
                cnpjFormatado.append(cnpjEmpresa.charAt(7));
                cnpjFormatado.append("/");
                cnpjFormatado.append(cnpjEmpresa.charAt(8));
                cnpjFormatado.append(cnpjEmpresa.charAt(9));
                cnpjFormatado.append(cnpjEmpresa.charAt(10));
                cnpjFormatado.append(cnpjEmpresa.charAt(11));
                cnpjFormatado.append("-");
                cnpjFormatado.append(cnpjEmpresa.charAt(12));
                cnpjFormatado.append(cnpjEmpresa.charAt(13));
            } catch (IndexOutOfBoundsException e) {
                Logger lg = Logger.getLogger(Convenio.class);
                lg.error("CNPJ com menos de 14 caracteres. Cnpj = " + cnpjEmpresa, e);
                return cnpjEmpresa;
            }
            return cnpjFormatado.toString();
        } else {
            return null;
        }
    }

    /** Recupera o cpf formatado da empresa
    *   @param cpf String com o cpf a ser formatado 
    * 
    *   @return String com o cpf passado formatado
    */
    public static String getCpfFormatado(String cpf) {
        if (cpf != null && cpf.trim().length() > 0) {
            StringBuilder cpfFormatado = new StringBuilder();
            try {
                cpfFormatado.append(cpf.charAt(0));
                cpfFormatado.append(cpf.charAt(1));
                cpfFormatado.append(cpf.charAt(2));
                cpfFormatado.append(".");
                cpfFormatado.append(cpf.charAt(3));
                cpfFormatado.append(cpf.charAt(4));
                cpfFormatado.append(cpf.charAt(5));
                cpfFormatado.append(".");
                cpfFormatado.append(cpf.charAt(6));
                cpfFormatado.append(cpf.charAt(7));
                cpfFormatado.append(cpf.charAt(8));
                cpfFormatado.append("-");
                cpfFormatado.append(cpf.charAt(9));
                cpfFormatado.append(cpf.charAt(10));
            } catch (IndexOutOfBoundsException e) {
                Logger lg = Logger.getLogger(Convenio.class);
                lg.error("CPF com menos de 11 caracteres. CPF = " + cpf, e);
                return cpf;
            }
            return cpfFormatado.toString();
        } else {
            return null;
        }
    }

    /** Recupera o numero de convenio formatado de forma diferente da empresa
     * 
     *  @param numeroConvenio String com o numero de convenio a ser formatado 
     *  @return String com o numero de convenio passado formatado
     */
    public static String getNumeroConvenioFormatado2(String numeroConvenio) {
        if (numeroConvenio != null && numeroConvenio.trim().length() > 0) {
            StringBuilder numeroConvenioFormatado = new StringBuilder();
            try {
                numeroConvenioFormatado.append(numeroConvenio.charAt(0));
                numeroConvenioFormatado.append(numeroConvenio.charAt(1));
                numeroConvenioFormatado.append(numeroConvenio.charAt(2));
                numeroConvenioFormatado.append(numeroConvenio.charAt(3));
                numeroConvenioFormatado.append(numeroConvenio.charAt(4));
                numeroConvenioFormatado.append(numeroConvenio.charAt(5));
                numeroConvenioFormatado.append("/");
                numeroConvenioFormatado.append(numeroConvenio.charAt(6));
                numeroConvenioFormatado.append(numeroConvenio.charAt(7));
                numeroConvenioFormatado.append(numeroConvenio.charAt(8));
                numeroConvenioFormatado.append(numeroConvenio.charAt(9));
            } catch (IndexOutOfBoundsException e) {
                Logger lg = Logger.getLogger(Convenio.class);
                lg.error("Numero com menos de 10 caracteres. NúmeroConvenio = " + numeroConvenio, e);
                return numeroConvenio;
            }
            return numeroConvenioFormatado.toString();
        } else {
            return null;
        }
    }

    
     /** Serviço que gera um numero de convenio a partir da data de assinatura
     *
     *  @param dataAssinatura Date com a data de assinatura do convenio     * 
     *  @return String com o numero de convenio
     */
    public static String gerarNumeroConvenio(Date dataAssinatura) {
        String numeroConvenio;
        SimpleDateFormat ano = new SimpleDateFormat("yyyy");
        Integer idConvenio = ConvenioServices.getMaxIdConvenio() + 1;
        numeroConvenio = String.format("%06d", idConvenio) + ano.format(dataAssinatura);
        return numeroConvenio;
    }

    /** Recupera o numero de convenio formatado da empresa
     * 
     *  @param num String com o numero de convenio a ser formatado 
     *  @return String com o numero de convenio passado formatado
     */
    public static String getNumeroConvenioFormatado(String num) {
        if (num != null && num.trim().length() > 0) {
            String numConvenio = num;
            long x = Long.parseLong(numConvenio);
            numConvenio = Long.toString(x);
            StringBuilder numConvenioFormatado = new StringBuilder(numConvenio);
            try {
                numConvenioFormatado.insert((numConvenio.length() - 4), "/");
            } catch (IndexOutOfBoundsException e) {
                Logger lg = Logger.getLogger(Convenio.class);
                lg.error("Numéro de Convênio com menos de 10 numeros. Número de Convênio = " + numConvenio, e);
                return numConvenio;
            }
            return numConvenioFormatado.toString();
        } else {
            return null;
        }
    }

    /** Recupera o numero de telefone formatado da empresa
     * 
     *  @param telefone String com o numero de telefone a ser formatado 
     *  @return String com o numero de telefone passado formatado
     */
    public static String getNumeroTelefoneFormatado(String telefone) {
        if (telefone != null && telefone.trim().length() > 0) {
            StringBuilder telefoneFormatado = new StringBuilder();
            int tam = telefone.trim().length();
            try {
                switch (tam) {
                    case 13:
                        telefoneFormatado.append("(");
                        telefoneFormatado.append(telefone.charAt(2));
                        telefoneFormatado.append(telefone.charAt(3));
                        telefoneFormatado.append(")");
                        telefoneFormatado.append(" ");
                        telefoneFormatado.append(telefone.charAt(4));
                        telefoneFormatado.append(telefone.charAt(5));
                        telefoneFormatado.append(telefone.charAt(6));
                        telefoneFormatado.append(telefone.charAt(7));
                        telefoneFormatado.append(telefone.charAt(8));
                        telefoneFormatado.append("-");
                        telefoneFormatado.append(telefone.charAt(9));
                        telefoneFormatado.append(telefone.charAt(10));
                        telefoneFormatado.append(telefone.charAt(11));
                        telefoneFormatado.append(telefone.charAt(12));
                        break;
                    case 12:
                        telefoneFormatado.append("(");
                        telefoneFormatado.append(telefone.charAt(2));
                        telefoneFormatado.append(telefone.charAt(3));
                        telefoneFormatado.append(")");
                        telefoneFormatado.append(" ");
                        telefoneFormatado.append(telefone.charAt(4));
                        telefoneFormatado.append(telefone.charAt(5));
                        telefoneFormatado.append(telefone.charAt(6));
                        telefoneFormatado.append(telefone.charAt(7));
                        telefoneFormatado.append("-");
                        telefoneFormatado.append(telefone.charAt(8));
                        telefoneFormatado.append(telefone.charAt(9));
                        telefoneFormatado.append(telefone.charAt(10));
                        telefoneFormatado.append(telefone.charAt(11));
                        break;
                        
                    case 10:
                        telefoneFormatado.append("(");
                        telefoneFormatado.append(telefone.charAt(0));
                        telefoneFormatado.append(telefone.charAt(1));
                        telefoneFormatado.append(")");
                        telefoneFormatado.append(" ");
                        telefoneFormatado.append(telefone.charAt(2));
                        telefoneFormatado.append(telefone.charAt(3));
                        telefoneFormatado.append(telefone.charAt(4));
                        telefoneFormatado.append(telefone.charAt(5));
                        telefoneFormatado.append("-");
                        telefoneFormatado.append(telefone.charAt(6));
                        telefoneFormatado.append(telefone.charAt(7));
                        telefoneFormatado.append(telefone.charAt(8));
                        telefoneFormatado.append(telefone.charAt(9));
                        break;
                        
                    case 11:
                        telefoneFormatado.append("(");
                        telefoneFormatado.append(telefone.charAt(0));
                        telefoneFormatado.append(telefone.charAt(1));
                        telefoneFormatado.append(")");
                        telefoneFormatado.append(" ");
                        telefoneFormatado.append(telefone.charAt(2));
                        telefoneFormatado.append(telefone.charAt(3));
                        telefoneFormatado.append(telefone.charAt(4));
                        telefoneFormatado.append(telefone.charAt(5));
                        telefoneFormatado.append(telefone.charAt(6));
                        telefoneFormatado.append("-");
                        telefoneFormatado.append(telefone.charAt(7));
                        telefoneFormatado.append(telefone.charAt(8));
                        telefoneFormatado.append(telefone.charAt(9));
                        telefoneFormatado.append(telefone.charAt(10));
                        break;
                        
                    default:
                        System.out.println("Telefone num formato desconhecido");
                }
                
            } catch (IndexOutOfBoundsException e) {
                Logger lg = Logger.getLogger(Convenio.class);
                lg.error("telefone com mais de 15 caracteres. telefone = " + telefone, e);
                return telefone;
            }
            return telefoneFormatado.toString();
        } else {
            return null;
        }
    }

    
     /** Serviço que gera um numero de convenio a partir da data de assinatura e de um Convenio
     *
     *  @param dataAssinatura Date com a data de assinatura do convenio  
     *  @param c Convenio a ser associado
     *  @return String com o numero de convenio
     */
    public static String gerarNumeroConvenioAtt(Date dataAssinatura, Convenio c) {
        String numeroConvenio;
        String num = c.getNumeroConvenio();
        num = num.substring(0, num.length() - 4);
        SimpleDateFormat ano = new SimpleDateFormat("yyyy");
        numeroConvenio = num + ano.format(dataAssinatura);
        return numeroConvenio;
    }

     /** Recupera a data no formato correto
     *
     *  @param date Date com a data a ser formatada 
     *  @throws ParseException
     *  @return Date com a data a formatada 
     */
    public static Date formataDate(Date date) throws ParseException {
        SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy");
        String result = out.format(date);
        Date d = out.parse(result);
        return d;
    }

    /** Recupera a a vigencia do convenio
     *
     *  @param d Date com a data a ser formatada 
     *  @return String com a data a formatada 
     */
    public static String getVigencia(Date d) {
        String presente = "";
        SimpleDateFormat in = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat out = new SimpleDateFormat("MM/yy");
        String ano, mes;
        String futuro;

        try {
            presente = out.format(in.parse(d.toString()));
            ano = presente.substring(3);
            mes = presente.substring(0, 2);
            futuro = mes + "/" + String.valueOf(Integer.parseInt(ano) + 5);
            return presente + " - " + futuro;

        } catch (Exception e) {
            Logger lg = Logger.getLogger(Convenio.class);
            lg.error("Numero Convenio com menos de 10 caracteres. NumeroConvenio = " + presente, e);
            return presente;
        }

    }

    /** Recupera o id formatado do conveniado
     *
     *  @param id String com o id a ser formatado
     *  @return String com a data a formatada 
     */
    public static String getIdConveniadoFormatado(String id) {
        if (id.length() == 11) {
            return getCpfFormatado(id);
        } else if (id.length() == 14) {
            return getCnpjEmpresaFormatado(id);
        } else {
            return null;
        }
    }

}
