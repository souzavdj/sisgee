package br.cefetrj.sisgee.view.utils;

/** Classe para recuperar UFs dos estados do Brasil
 * 
 * @author Leticia 
 */

public enum UF {

    AC("AC"), AL("AL"), AP("AP"), AM("AM"), BA("BA"),
    CE("CE"), DF("DF"), ES("ES"), GO("GO"), MA("MA"),
    MT("MT"), MS("MS"), MG("MG"), PA("PA"), PB("PB"),
    PR("PR"), PE("PE"), PI("PI"), RJ("RJ"), RN("RN"),
    RS("RS"), RO("RO"), RR("RR"), SC("SC"), SP("SP"),
    SE("SE"), TO("TO");

    private final String uf;

    
    /** Construtor da classe com 1 parametro
    *  @param uf String com o uf a ser adicionado 
    * 
    * @return String com o cnpj passado formatado
    */
    UF(String uf) {
        this.uf = uf;
    }

      /** Metódo que retorna um array as UFs da classe 
         * 
         * @return array com a lista
         */
    public static UF[] asList() {
        return UF.values();
    }

     /** Metódo que retorna uma uf da classe
         * 
         * @return String com uma uf
         */
    public String getUf() {
        return uf;
    }

}
