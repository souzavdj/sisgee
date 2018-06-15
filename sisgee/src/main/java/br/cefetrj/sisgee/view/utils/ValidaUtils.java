package br.cefetrj.sisgee.view.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe criada para métodos de validação, para melhor reuso de código.
 * @author Paulo Cantuária
 * @since 1.0
 */
public class ValidaUtils {
	
	/**
	 * Método para validar campo por tamanho, para valores de texto(String)
	 * @param nomeCampo texto com o nome do campo.
	 * @param tamanho tamanho do campo.
	 * @param param valor do texto a ser testado.
	 * @return String com mensagem de erro ou vazia
	 */
	public static String validaTamanho(String nomeCampo, int tamanho, String param) {
		String msg = "";
		if(param.length() > tamanho) {
			msg = "br.cefetrj.sisgee.valida_utils.msg_valida_tamanho_txt";
		}		
		return msg;
	}
	
	/**
	 * Método para validar campo por tamanho, para valores inteiros
	 * @param nomeCampo texto com o nome do campo.
	 * @param tamanho tamanho do campo.
	 * @param param valor inteiro a ser testado.
	 * @return String com mensagem de erro ou vazia
	 */
	public static String validaTamanho(String nomeCampo, int tamanho, Integer param) {
		String msg = "";
		if(param > tamanho) {
			msg = "br.cefetrj.sisgee.valida_utils.msg_valida_tamanho_num";
		}		
		return msg;
	}	
	
	/**
	 * Método para validar campo com tamanho restito, para valores de texto(String)
	  * @param nomeCampo texto com o nome do campo.
	 * @param tamanho tamanho do campo.
	 * @param param valor do texto a ser testado.
	 * @return String com mensagem de erro ou vazia
	 */
	public static String validaTamanhoExato(String nomeCampo, int tamanho, String param) {
		String msg = "";
		if(param.length() != tamanho) {
			msg = "br.cefetrj.sisgee.valida_utils.msg_valida_tamanho_exato";
		}		
		return msg;
	}
	
	/**
	 * Método para validar campo obrigatório
	 * @param nomeCampo texto com o nome do campo.
	 * @param param valor a ser testado como obrigatório.
	 * @return String com mensagem de erro ou vazia
	 */
	public static String validaObrigatorio(String nomeCampo, String param) {
		String msg = "";
		if(param == null || param.trim().isEmpty()) {
			msg = "br.cefetrj.sisgee.valida_utils.msg_valida_obrigatorio";
		}		
		return msg;
	}
	
	/**
	 * Método para validar campos numéricos inteiros
	 * @param nomeCampo texto com o nome do campo.
	 * @param param com o valor a ser convertido para integer.
	 * @return String com mensagem de erro ou vazia
	 */
	public static String validaInteger(String nomeCampo, String param) {
		String msg = "";
		if(!param.matches("\\d*")) {
			msg = "br.cefetrj.sisgee.valida_utils.msg_valida_numerico";
		}		
		return msg;
	}
	
	/**
	 * Método para validar campos numéricos de ponto flutuante
	 * @param nomeCampo texto com o nome do campo.
	 * @param param String com o valor a ser convertido para float.
	 * @return String com mensagem de erro ou vazia.
	 */
	public static String validaFloat(String nomeCampo, String param) {
		String msg = "";		
		
		try {
		@SuppressWarnings("unused")
		Float valorFloat = Float.parseFloat(param);				
		
		}catch(Exception e) {
			msg = "br.cefetrj.sisgee.valida_utils.msg_valida_numerico";
		}
		
		return msg;	
		
		/*NumberFormat nf = NumberFormat.getInstance(new Locale("pt", "BR"));
		Number n;
		try {
			n = nf.parse(param);
			//double d = n.doubleValue();
			float d = n.floatValue();
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			msg = "br.cefetrj.sisgee.valida_utils.msg_valida_numerico";
		}
		
		return msg;
		*/

	}
	
	/**
	 * Método para validar campos booleanos
	 * @param nomeCampo texto com o nome do campo.
	 * @param param String com o valor a ser convertido para boolean.
	 * @return String com mensagem de erro ou vazia.
	 */
	public static String validaBoolean(String nomeCampo, String param) {
		String msg = "";		
		try {
			@SuppressWarnings("unused")
			Boolean valorBoolean = Boolean.parseBoolean(param);				
		}catch(Exception e) {
			msg = "br.cefetrj.sisgee.valida_utils.msg_valida_booleano";
		}		
		return msg;
	}
	
	
	/**
	 * Método para validar campos de data (java.util.Date)
	 * @param nomeCampo texto com o nome do campo.
	 * @param param data a ser convertida para Date
	 * @return String com mensagem de erro ou vazia.
	 */
	public static String validaDate(String nomeCampo, String param) {
		String msg = "";
		
		try {
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
			@SuppressWarnings("unused")
			Date dataFormatada = format.parse(param);
		}catch(Exception e) {
			msg = "br.cefetrj.sisgee.valida_utils.msg_valida_date";
		}		
		return msg;
	}
	
	/**
	 * Método para validar duas datas (iní­cio e fim de um perí­odo)
	 * 
	 * @param dataInicio data que marca o iní­cio do período.
	 * @param dataFim data que marca o final do perí­odo.
	 * @return String com mensagem de erro ou vazia.
	 */
	public static String validaDatas(Date dataInicio, Date dataFim) {
		String msg = "";
		if(dataInicio.compareTo(dataFim) > 0) {
			msg = "br.cefetrj.sisgee.valida_utils.msg_valida_datas";
		}		
		return msg;
	}	
	
	
	/**
	 * Método para validar a seleção de Estados (UFs)
	 * 
	 * @param nomeCampo texto com o nome do campo.
	 * @param param texto com a UF
	 * @return String com mensagem de erro ou vazia.
	 */
	public static String validaUf(String nomeCampo, String param) {
		String msg = "";
		boolean valid = false;
		UF [] ufs = UF.asList();
		for (UF uf : ufs) {
			if(param.equals(uf.getUf())) {
				valid = true;
				return "";
			}
		}
		if(valid == false) {
			msg = "br.cefetrj.sisgee.valida_utils.msg_valida_UF";
		}
		
		return msg;
	}
	
	/**
	 * Método que combina a validação de campo obrigatório e tamanho de campo
	 * 
	 * @param nomeCampo texto com o nome do campo.
	 * @param tamanho tamanho do campo
	 * @param param valor do texto a ser testado.
	 * @return String com mensagem de erro ou vazia.
	 */
	public static String validaObrigatorioETamanho(String nomeCampo, int tamanho, String param) {
		String msg = "";
		msg = validaObrigatorio(nomeCampo, param);
		if(msg.trim().isEmpty()) {
			msg = validaTamanho(nomeCampo, tamanho, param);
		}
		return msg;
	}
        
        /**
	 * Método para validar campos que recebem telefone
	 * @param nomeCampo texto com o nome do campo.
	 * @param param String do Telefone.
	 * @return String com mensagem de erro ou vazia
	 */
	public static String validaTelefone(String nomeCampo,String param) {
		String msg = "";
                if(!(param.trim().isEmpty())){
                    if(param.length() != 10 && param.length() != 11){
                        msg = "br.cefetrj.sisgee.valida_utils.msg_valida_telefone";
                    }   
                }
		return msg;
	}
        
        /**
	 * Método para validar campos não numéricos inteiros
	 * @param nomeCampo texto com o nome do campo.
	 * @param param o texto que não pode ser numeros.
	 * @return String com mensagem de erro ou vazia
	 */
	public static String validaNaoInteger(String nomeCampo, String param) {
		String msg = "";
                if(param.matches("\\d*")){
                    msg = "br.cefetrj.sisgee.valida_utils.msg_valida_nao_numerico";
                }
				
		return msg;
                }    
			
    /**
	 * Método para validar duas datas (iní­cio e fim de um perí­odo)
	 * 
	 * @param dataAnterior data que marca a data assinatura anterior.
	 * @param dataNova data que marca a nova data de assinatura.
	 * @return String com mensagem de erro ou vazia.
	 */
	public static String validaDataRenovacao(Date dataAnterior, Date dataNova) {
		String msg = "";
		if(dataAnterior.compareTo(dataNova) > 0) {
			msg = "br.cefetrj.sisgee.valida_utils.msg_valida_data_renovacao";
		}		
		return msg;
	}
        
        /**
         * Método para verificar se um numero esta dentro do intervalo de zero ate valor maximo(parametro) 
         * 
         * @param num numero que será validado
         * @param max valor maximo do intervalo
         * @return String com mensagem de erro ou vazia.
         */
        
	public static String validaIntervaloPositivo(int num,int max){
            String msg="";
            if((num <=0) || (num>=max)){
                msg="br.cefetrj.sisgee.valida_utils.msg_valida_valor_positivo";
            }
            return msg;
            
        }
}

