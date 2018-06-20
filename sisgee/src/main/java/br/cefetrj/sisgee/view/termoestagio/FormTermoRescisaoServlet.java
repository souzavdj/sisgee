package br.cefetrj.sisgee.view.termoestagio;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.sisgee.control.AlunoServices;
import br.cefetrj.sisgee.control.TermoAditivoServices;
import br.cefetrj.sisgee.control.TermoEstagioServices;
import br.cefetrj.sisgee.model.entity.Aluno;
import br.cefetrj.sisgee.model.entity.TermoEstagio;
import br.cefetrj.sisgee.view.termoaditivo.TermoAditivoServlet;
import br.cefetrj.sisgee.view.utils.ServletUtils;
import br.cefetrj.sisgee.view.utils.TermoEstagioUtils;
import br.cefetrj.sisgee.view.utils.ValidaUtils;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class FormTermoRescisaoServlet
 * 
 */
@WebServlet("/FormTermoRescisaoServlet")
public class FormTermoRescisaoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {        
        
        request.getRequestDispatcher("/form_consultar_termo.jsp").forward(request, response);
    }

    /**
     * @método doPost
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        Locale locale = ServletUtils.getLocale(request);
        ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);
        
        String dataTermoRescisao = request.getParameter("dataTermoRescisao");
        String idAluno = request.getParameter("idAluno");
        TermoEstagio termoEstagio = null;
        
        Aluno aluno = null;
        boolean isValid = true;        
        Date dataRescisao = null;
        String msg = "";

        /**
         * Validação do Id do Aluno, usando métodos da Classe ValidaUtils.
         * Instanciando o objeto e pegando o TermoEstagio válido (sem data de
         * rescisão)
         */
        String idAlunoMsg = "";
        idAlunoMsg = ValidaUtils.validaObrigatorio("Aluno", idAluno);
        if (idAlunoMsg.trim().isEmpty()) {
            idAlunoMsg = ValidaUtils.validaInteger("Aluno", idAluno);
            if (idAlunoMsg.trim().isEmpty()) {
                Integer idAlunoInt = Integer.parseInt(idAluno);
                aluno = AlunoServices.buscarAluno(new Aluno(idAlunoInt));
                if (aluno != null) {
                    List<TermoEstagio> termosEstagio = aluno.getTermoEstagios();
                    if (TermoEstagioUtils.temTermoEstagioAtivo(termosEstagio) != null) {
                        for (TermoEstagio termo : termosEstagio) {
                            if (termo.getIdTermoEstagio() == Integer.parseInt(TermoEstagioUtils.temTermoEstagioAtivo(termosEstagio))) {
                                termoEstagio = termo;
                            }
                        }
                    }
                } else {
                    idAlunoMsg = messages.getString("br.cefetrj.sisgee.form_termo_aditivo_servlet.msg_alunoEscolhido");
                    request.setAttribute("idAlunoMsg", idAlunoMsg);
                }
                
            } else {
                request.setAttribute("idAlunoMsg", idAlunoMsg);
                isValid = false;
            }
        } else {
            request.setAttribute("idAlunoMsg", idAlunoMsg);
            isValid = false;
        }        
        
        String dataTermoRescisaoMsg = "";
        
        String campo = "Termo Rescisão";
        dataTermoRescisaoMsg = ValidaUtils.validaObrigatorio(campo, dataTermoRescisao);
        if (dataTermoRescisaoMsg.trim().isEmpty()) {
            dataTermoRescisaoMsg = ValidaUtils.validaDate(campo, dataTermoRescisao);
            if (dataTermoRescisaoMsg.trim().isEmpty()) {
                try {
                    try {    
                        SimpleDateFormat format = null;
                        if (messages.getLocale().toString().equals("pt_BR")) {
                            format = new SimpleDateFormat("dd/MM/yyyy");
                        } else if (messages.getLocale().toString().equals("en_US")) {
                            format = new SimpleDateFormat("MM/dd/yyyy");
                        } else {
                            //fazer log de erro com a internacionalização
                            System.out.println("Idioma desconhecido");
                        }
                        if (format != null) {
                            dataRescisao = format.parse(dataTermoRescisao);
                            request.setAttribute("dataRescisao", dataRescisao);
                        }
                    }catch (Exception e) {
                        //Fazer log de erro data vindas do bd do termo invalidas
                        Logger lg = Logger.getLogger(FormTermoRescisaoServlet.class);
                        lg.error("Exception devido a Data Inválida. ", e);
                        System.err.println("Datas de inicio ou de fim do termo de estagio invalidas");
                    }
                    if (termoEstagio != null) {
                        /**
                         * Validação do período (entre o início e fim do
                         * estágio) usando o método validaDatas da Classe
                         * ValidaUtils
                         */
                        Date inicio = termoEstagio.getDataInicioTermoEstagio();
                        TermoEstagio termoAditivo = null;
                        String periodoMsg = "";
                        if (termoEstagio.getTermosAditivos() != null) {
                            for (int i = 0; i < termoEstagio.getTermosAditivos().size(); i++) {
                                if(termoEstagio.getTermosAditivos().get(i).getDataInicioTermoEstagio().compareTo(inicio) > 0) {
                                    inicio = termoEstagio.getTermosAditivos().get(i).getDataInicioTermoEstagio();
                                    termoAditivo = termoEstagio.getTermosAditivos().get(i);
                                }
                            }
                        }
                        
                        periodoMsg = ValidaUtils.validaDatas(inicio, dataRescisao);
                        if (!periodoMsg.trim().isEmpty()) {
                            periodoMsg = messages.getString("br.cefetrj.sisgee.resources.form.consultar.termo.periodoInvalidoDataIncio");
                            msg = periodoMsg;
                            request.setAttribute("periodoMsg", periodoMsg);
                            isValid = false;                            
                        }                        
                        if (termoAditivo!=null) {
                            periodoMsg = ValidaUtils.validaDatas(dataRescisao, termoAditivo.getDataFimTermoEstagio());
                        }else {
                            periodoMsg = ValidaUtils.validaDatas(dataRescisao, termoEstagio.getDataFimTermoEstagio());
                        }
                        if (!periodoMsg.trim().isEmpty()) {
                            periodoMsg = messages.getString("br.cefetrj.sisgee.resources.form.consultar.termo.periodoInvalidoDataFim");
                            msg = periodoMsg;
                            //request.setAttribute("periodoMsg", periodoMsg);
                            isValid = false;                            
                        }
                    } else {
                        msg = messages.getString("br.cefetrj.sisgee.form_termo_rescisao_servlet.msg_termo_estagio_invalido");
                        isValid = false;
                        request.setAttribute("termoEstagioMsg", msg);
                    }
                    
                } catch (Exception e) {
                    //TODO trocar saída de console por Log
                    Logger lg = Logger.getLogger(FormTermoRescisaoServlet.class);
                    lg.error("Exception devido a Data Inválida. ", e);
                    System.out.println("Data em formato incorreto, mesmo após validação na classe ValidaUtils");
                    isValid = false;
                }
            } else {
                dataTermoRescisaoMsg = messages.getString(dataTermoRescisaoMsg);
                msg = dataTermoRescisaoMsg;
                request.setAttribute("dataTermoRescisaoMsg", dataTermoRescisaoMsg);
                isValid = false;
            }
        } else {
            dataTermoRescisaoMsg = messages.getString(dataTermoRescisaoMsg);
            msg = dataTermoRescisaoMsg;
            request.setAttribute("dataTermoRescisaoMsg", dataTermoRescisaoMsg);
            isValid = false;
        }
        
        if (isValid) {
            termoEstagio.setEAtivo(false);
            for (TermoEstagio termo : termoEstagio.getTermosAditivos()) {
                termo.setEAtivo(false);
            }
            TermoEstagio termoAtual = null;
            if (termoEstagio.getTermosAditivos().isEmpty()) {
                termoAtual = termoEstagio;
            }else {
                termoAtual = termoEstagio.getTermosAditivos().get(termoEstagio.getTermosAditivos().size()-1);
            }
            TermoEstagio termoAditivo = new TermoEstagio(termoAtual.getDataInicioTermoEstagio(), termoAtual.getDataFimTermoEstagio(), 
                    dataRescisao, termoAtual.getCargaHorariaTermoEstagio(), termoAtual.getValorBolsa(), 
                    termoAtual.getEnderecoTermoEstagio(), termoAtual.getComplementoEnderecoTermoEstagio(), 
                    termoAtual.getBairroEnderecoTermoEstagio(), termoAtual.getCepEnderecoTermoEstagio(), 
                    termoAtual.getCidadeEnderecoTermoEstagio(), termoAtual.getEstadoEnderecoTermoEstagio(), 
                    termoAtual.getEEstagioObrigatorio(), termoAtual.getNomeSupervisor(), termoAtual.getCargoSupervisor(), 
                    "Rescisão de Contrato", termoAtual.getEAtivo(), aluno, termoAtual.getConvenio(), 
                    termoAtual.getProfessorOrientador(), termoAtual.getAgenciada());
            if (termoEstagio.getTermoEstagioAditivo() == null) {
                termoAditivo.setTermoEstagioAditivo(termoEstagio);
            }else {
                termoAditivo.setTermoEstagioAditivo(termoAtual.getTermoEstagioAditivo());
            }
            termoAditivo.setIdTermoEstagio(TermoAditivoServices.getIdMaxTermoEstagio()+1);
            try {
                TermoAditivoServices.incluirTermoAditivo(termoAditivo);
                msg = messages.getString("br.cefetrj.sisgee.resources.form.consultar.termo.registroSucesso");
                request.setAttribute("msgSucesso", msg);
                request.getRequestDispatcher("/form_consultar_termo.jsp").forward(request, response);
            }catch (Exception e) {
                msg = messages.getString("br.cefetrj.sisgee.incluir_termo_aditivo_servlet.msg_ocorreuErro");
                request.setAttribute("msg", msg);
                Logger lg = Logger.getLogger(FormTermoRescisaoServlet.class);
                lg.error("Exception ao tentar inserir o Termo de Estágio", e);
                request.getRequestDispatcher("/form_consultar_termo.jsp").forward(request, response);
            }
        } else {            
            String rescisao = "sim";
            request.setAttribute("msg", msg);
            request.setAttribute("Rescisao", rescisao);
            
            request.getRequestDispatcher("/form_consultar_termo.jsp").forward(request, response);
        }
    }
    
}
