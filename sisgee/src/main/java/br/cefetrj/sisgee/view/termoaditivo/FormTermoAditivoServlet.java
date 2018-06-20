package br.cefetrj.sisgee.view.termoaditivo;

import br.cefetrj.sisgee.control.AlunoServices;
import br.cefetrj.sisgee.control.ConvenioServices;
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

import br.cefetrj.sisgee.control.ProfessorOrientadorServices;
import br.cefetrj.sisgee.control.TermoAditivoServices;
import br.cefetrj.sisgee.control.TermoEstagioServices;
import br.cefetrj.sisgee.model.dao.TermoEstagioDAO;
import br.cefetrj.sisgee.model.entity.Aluno;
import br.cefetrj.sisgee.model.entity.Convenio;
import br.cefetrj.sisgee.model.entity.ProfessorOrientador;
import br.cefetrj.sisgee.model.entity.TermoEstagio;
import br.cefetrj.sisgee.view.termoestagio.IncluirTermoEstagioServlet;
import br.cefetrj.sisgee.view.utils.ConvenioUtils;
import br.cefetrj.sisgee.view.utils.ServletUtils;
import br.cefetrj.sisgee.view.utils.TermoEstagioUtils;
import br.cefetrj.sisgee.view.utils.UF;
import br.cefetrj.sisgee.view.utils.ValidaUtils;
import org.apache.log4j.Logger;

/**
 * Servlet para trazer os dados do banco para a tela de cadastro de Termo
 * Aditivo.
 *
 * @author Paulo Cantuária
 * @since 1.0
 *
 */
@WebServlet("/FormTermoAditivoServlet")
public class FormTermoAditivoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Método doGet: carrega as listas necessárias para seleção dos atributos de
     * relacionamento e redireciona para a tela de Registro de Termo
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String aditivo = "sim";
        request = carregarListas(request);
        request.setAttribute("aditivo", aditivo);

        request.getRequestDispatcher("/form_consulta_termo.jsp").forward(request, response);

    }

    /**
     * Método para validação e inclusão do Termo Aditivo
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Locale locale = ServletUtils.getLocale(request);
        ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);
        String motivo = "";
        
        String dataInicioTermoAditivo = request.getParameter("dataInicioTermoEstagio");
        String dataFimTermoAditivo = request.getParameter("dataFimTermoEstagio");
        String cargaHorariaTermoAditivo = request.getParameter("cargaHorariaTermoEstagio");
        String valorBolsaTermoAditivo = request.getParameter("valorBolsa");
        String matricula = request.getParameter("matricula");
        
        /**
         * campos de endereço
         */
        String enderecoTermoAditivo = request.getParameter("enderecoTermoEstagio");
        //String numeroEnderecoTermoAditivo = request.getParameter("numeroEnderecoTermoEstagio");
        String complementoEnderecoTermoAditivo = request.getParameter("complementoEnderecoTermoEstagio");
        String bairroEnderecoTermoAditivo = request.getParameter("bairroEnderecoTermoEstagio");
        String cepEnderecoTermoAditivo = request.getParameter("cepEnderecoTermoEstagio");
        String cidadeEnderecoTermoAditivo = request.getParameter("cidadeEnderecoTermoEstagio");
        String estadoEnderecoTermoAditivo = request.getParameter("estadoEnderecoTermoEstagio");

        String eObrigatorio = request.getParameter("eEstagioObrigatorio");
        String nomeSurpervisor = request.getParameter("nomeSupervisor");
        String cargoSurpervisor = request.getParameter("cargoSupervisor");

        /**
         * Campos possíveis selecionados para atualização
         */
        String updValorBolsa = request.getParameter("updValorBolsa");
        String updVigencia = request.getParameter("updVigencia");
        String updCargaHoraria = request.getParameter("updCargaHoraria");
        String updProfessor = request.getParameter("updProfessor");
        String updEndereco = request.getParameter("updEndereco");
        String updSupervisor = request.getParameter("updSupervisor");

        String idProfessorOrientador = request.getParameter("idProfessorOrientador");
        String idTermoEstagio = request.getParameter("idTermoEstagio");
        String idAluno = request.getParameter("idAluno");
        String idConvenio = request.getParameter("idConvenio");
        
        TermoEstagio termoEstagio = null;
        Integer idTermo = null;

        boolean isValid = true;
        String msg = "";
        String campo = "";
        Integer tamanho = 0;

        Date dataFim = null;
        Float valor = null;
        Integer cargaHoraria = null;
        ProfessorOrientador professorOrientador = null;

        /**
         * Validação do idTermoEstagio
         */
        campo = "Termo de Estágio";
        msg = ValidaUtils.validaObrigatorio(campo, idTermoEstagio);
        if (msg.trim().isEmpty()) {
            msg = ValidaUtils.validaInteger(campo, idTermoEstagio);
            if (msg.trim().isEmpty()) {
                idTermo = Integer.parseInt(idTermoEstagio);
                termoEstagio = TermoEstagioServices.buscarTermoEstagio(idTermo);

            } else {
                msg = messages.getString(msg);
                isValid = false;
            }

        } else {
            msg = messages.getString(msg);
            isValid = false;
        }

        /**
         * Validações dos campos, com base nas opções selecionadas para
         * alteração
         */
        if (termoEstagio != null) {
            request.setAttribute("termoEstagio", termoEstagio);

            /**
             * Validação de vigência
             */
            if (updVigencia != null && !updVigencia.trim().isEmpty()) {
                motivo = motivo + messages.getString("br.cefetrj.sisgee.resources.form.vigenciaEstagio");
                campo = "Data de Término";
                Boolean hasDataFim = false;
                String dataFimMsg = "";
                dataFimMsg = ValidaUtils.validaObrigatorio(campo, dataFimTermoAditivo);
                if (dataFimMsg.trim().isEmpty()) {
                    dataFimMsg = ValidaUtils.validaDate(campo, dataFimTermoAditivo);
                    if (dataFimMsg.trim().isEmpty()) {
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                        try {
                            dataFim = format.parse(dataFimTermoAditivo);
                            request.setAttribute("dataFim", dataFim);
                            hasDataFim = true;
                        } catch (Exception e) {
                            isValid = false;
                        }
                    } else {
                        dataFimMsg = messages.getString(dataFimMsg);
                        request.setAttribute("dataFimMsg", dataFimMsg);
                        isValid = false;
                    }
                }
                request.setAttribute("hasDataFim", hasDataFim);

                String periodoMsg = "";
                if (dataFim == null) {
                    msg = messages.getString("br.cefetrj.sisgee.relatorio.relatorio_consolidado_servlet.alert_data_termino");
                    isValid = false;
                }else {
                    periodoMsg = ValidaUtils.validaDatas(termoEstagio.getDataInicioTermoEstagio(), dataFim);
                    if (!periodoMsg.trim().isEmpty()) {
                        periodoMsg = messages.getString(periodoMsg);
                        request.setAttribute("periodoMsg", periodoMsg);
                        isValid = false;
                    }
                }
                
            }

            /**
             * Validação de valor da Bolsa
             */
            if (updValorBolsa != null && !updValorBolsa.trim().isEmpty()) {
                String valorBolsaMsg = "";
                motivo = motivo + messages.getString("br.cefetrj.sisgee.resources.form.valorBolsaEstagio");
                campo = "Valor";
                valorBolsaMsg = ValidaUtils.validaObrigatorio(campo, valorBolsaTermoAditivo);
                if (valorBolsaMsg.trim().isEmpty()) {
                    valorBolsaMsg = ValidaUtils.validaFloat(campo, valorBolsaTermoAditivo);
                    if (valorBolsaMsg.trim().isEmpty()) {
                        valor = Float.parseFloat(valorBolsaTermoAditivo);
                        request.setAttribute("valor", valor);
                    } else {
                        valorBolsaMsg = messages.getString(valorBolsaMsg);
                        request.setAttribute("valorBolsaMsg", valorBolsaMsg);
                        isValid = false;
                        //TODO Fazer log
                        
                    }
                } else {
                    valorBolsaMsg = messages.getString(valorBolsaMsg);
                    request.setAttribute("valorBolsaMsg", valorBolsaMsg);
                    isValid = false;
                    //TODO Fazer log
                    
                }
            }

            /**
             * Validação de Carga Horária
             */
            if (updCargaHoraria != null && !updCargaHoraria.trim().isEmpty()) {
                String cargaHorariaMsg = "";
                motivo = motivo + messages.getString("br.cefetrj.sisgee.resources.form.cargaHorariaAluno");
                campo = "Horas por dia";
                tamanho = 6;
                cargaHorariaMsg = ValidaUtils.validaObrigatorio(campo, cargaHorariaTermoAditivo);
                if (cargaHorariaMsg.trim().isEmpty()) {
                    cargaHorariaMsg = ValidaUtils.validaInteger(campo, cargaHorariaTermoAditivo);
                    if (cargaHorariaMsg.trim().isEmpty()) {
                        cargaHoraria = Integer.parseInt(cargaHorariaTermoAditivo);
                        if (cargaHorariaMsg.trim().isEmpty()) {
                            cargaHorariaMsg = ValidaUtils.validaTamanho(campo, tamanho, cargaHoraria);
                            if (cargaHorariaMsg.trim().isEmpty()) {
                                request.setAttribute("cargaHoraria", cargaHoraria);
                            } else {
                                cargaHorariaMsg = messages.getString(cargaHorariaMsg);
                                cargaHorariaMsg = ServletUtils.mensagemFormatada(cargaHorariaMsg, locale, tamanho);
                                request.setAttribute("cargaHorariaMsg", cargaHorariaMsg);
                            }
                        } else {
                            cargaHorariaMsg = messages.getString(cargaHorariaMsg);
                            request.setAttribute("cargaHorariaMsg", cargaHorariaMsg);
                            isValid = false;

                        }
                    } else {
                        cargaHorariaMsg = messages.getString(cargaHorariaMsg);
                        request.setAttribute("cargaHorariaMsg", cargaHorariaMsg);
                        isValid = false;

                    }
                } else {
                    cargaHorariaMsg = messages.getString(cargaHorariaMsg);
                    request.setAttribute("cargaHorariaMsg", cargaHorariaMsg);
                    isValid = false;

                }
            }

            /**
             * Validação de Professor
             */
            if (updProfessor != null && !updProfessor.trim().isEmpty()) {
                String idProfessorMsg = "";
                motivo = motivo + messages.getString("br.cefetrj.sisgee.resources.form.professorOrientador");
                campo = "Professor Orientador";
                Boolean hasProfessor = false;
                idProfessorMsg = ValidaUtils.validaObrigatorio(campo, idProfessorOrientador);
                if (idProfessorMsg.trim().isEmpty()) {
                    idProfessorMsg = ValidaUtils.validaInteger(campo, idProfessorOrientador);
                    if (idProfessorMsg.trim().isEmpty()) {
                        Integer idProfessor = Integer.parseInt(idProfessorOrientador);
                        List<ProfessorOrientador> listaProfessores = ProfessorOrientadorServices.listarProfessorOrientador();
                        if (listaProfessores != null) {
                            if (listaProfessores.contains(new ProfessorOrientador(idProfessor))) {
                                professorOrientador = ProfessorOrientadorServices.buscarProfessorOrientador(new ProfessorOrientador(idProfessor));
                                request.setAttribute("idProfessor", idProfessor);
                                hasProfessor = true;
                            } else {
                                idProfessorMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.professor_invalido");
                                isValid = false;

                            }
                        } else {
                            idProfessorMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.lista_professores_vazia");
                            isValid = false;
                            //TODO Fazer log
                            
                        }
                    } else {
                        idProfessorMsg = messages.getString(idProfessorMsg);
                        request.setAttribute("idProfessorMsg", idProfessorMsg);
                        isValid = false;
                        //TODO Fazer log
                        
                    }
                }
                request.setAttribute("hasProfessor", hasProfessor);
            }

            /**
             * Validação de Endereço
             */
            if (updEndereco != null && !updEndereco.trim().isEmpty()) {
                motivo = motivo + messages.getString("br.cefetrj.sisgee.resources.form.endereco");
                /**
                 * Validação do endereço do TermoEstagio usando métodos da
                 * Classe ValidaUtils. Campo obrigatório e tamanho máximo de 255
                 * caracteres.
                 */
                String enderecoMsg = "";
                campo = "Endereço";
                tamanho = 255;
                enderecoMsg = ValidaUtils.validaObrigatorio(campo, enderecoTermoAditivo);
                if (enderecoMsg.trim().isEmpty()) {
                    enderecoMsg = ValidaUtils.validaTamanho(campo, tamanho, enderecoTermoAditivo);
                    if (enderecoMsg.trim().isEmpty()) {
                        request.setAttribute("enderecoTermoEstagio", enderecoTermoAditivo);
                    } else {
                        enderecoMsg = messages.getString(enderecoMsg);
                        enderecoMsg = ServletUtils.mensagemFormatada(enderecoMsg, locale, tamanho);
                        request.setAttribute("enderecoMsg", enderecoMsg);
                        isValid = false;
                        //TODO Fazer log
                        
                    }
                } else {
                    enderecoMsg = messages.getString(enderecoMsg);
                    request.setAttribute("enderecoMsg", enderecoMsg);
                    isValid = false;
                    //TODO Fazer log
                    
                }
                /**
                 * Validação do complemento do endereço do TermoEstagio usando
                 * os métodos da Classe ValidaUtils. Campo obrigatório e tamanho
                 * máximo de 150 caracteres.
                 */
                String complementoEnderecoMsg = "";
                campo = "Complemento";
                tamanho = 150;
                complementoEnderecoMsg = ValidaUtils.validaObrigatorio(campo, complementoEnderecoTermoAditivo);
                if (complementoEnderecoMsg.trim().isEmpty()) {
                    //numeroEnderecoMsg = ValidaUtils.validaTamanho(campo, tamanho, complementoEnderecoTermoAditivo);
                    if (complementoEnderecoMsg.trim().isEmpty()) {
                        request.setAttribute("complementoEnderecoTermoEstagio", complementoEnderecoTermoAditivo);
                    } else {
                        complementoEnderecoMsg = messages.getString(complementoEnderecoMsg);
                        complementoEnderecoMsg = ServletUtils.mensagemFormatada(complementoEnderecoMsg, locale, tamanho);
                        request.setAttribute("complementoEnderecoMsg", complementoEnderecoMsg);
                        isValid = false;

                    }
                } else {
                    complementoEnderecoMsg = messages.getString(complementoEnderecoMsg);
                    request.setAttribute("complementoEnderecoMsg", complementoEnderecoMsg);
                    isValid = false;
                }

                /**
                 * Validação do bairro do endereço do TermoEstagio usando
                 * métodos da Classe ValidaUtils. Campo obrigatório e tamanho
                 * máximo de 150 caracteres.
                 */
                String bairroEnderecoMsg = "";
                campo = "Bairro";
                tamanho = 150;
                bairroEnderecoMsg = ValidaUtils.validaObrigatorio(campo, bairroEnderecoTermoAditivo);
                if (bairroEnderecoMsg.trim().isEmpty()) {
                    bairroEnderecoMsg = ValidaUtils.validaTamanho(campo, tamanho, bairroEnderecoTermoAditivo);
                    if (bairroEnderecoMsg.trim().isEmpty()) {
                        request.setAttribute("bairroEnderecoTermoEstagio", bairroEnderecoTermoAditivo);
                    } else {
                        bairroEnderecoMsg = messages.getString(bairroEnderecoMsg);
                        bairroEnderecoMsg = ServletUtils.mensagemFormatada(bairroEnderecoMsg, locale, tamanho);
                        request.setAttribute("bairroEnderecoMsg", bairroEnderecoMsg);
                        isValid = false;
                        //TODO Fazer log
                        
                    }
                } else {
                    bairroEnderecoMsg = messages.getString(bairroEnderecoMsg);
                    request.setAttribute("bairroEnderecoMsg", bairroEnderecoMsg);
                    isValid = false;
                    //TODO Fazer log
                    
                }

                /**
                 * Validação do cep do endereço do TermoEstagio usando métodos
                 * da Classe ValidaUtils. Campo obrigatório e tamanho máximo de
                 * 15 caracteres.
                 */
                String cepEnderecoMsg = "";
                campo = "CEP";
                tamanho = 15;
                cepEnderecoMsg = ValidaUtils.validaObrigatorio(campo, cepEnderecoTermoAditivo);
                if (cepEnderecoMsg.trim().isEmpty()) {
                    cepEnderecoMsg = ValidaUtils.validaTamanho(campo, tamanho, cepEnderecoTermoAditivo);
                    if (bairroEnderecoMsg.trim().isEmpty()) {
                        request.setAttribute("cepEnderecoTermoEstagio", cepEnderecoTermoAditivo);
                    } else {
                        cepEnderecoMsg = messages.getString(cepEnderecoMsg);
                        cepEnderecoMsg = ServletUtils.mensagemFormatada(bairroEnderecoMsg, locale, tamanho);
                        request.setAttribute("cepEnderecoMsg", cepEnderecoMsg);
                        isValid = false;
                        //TODO Fazer log
                        
                    }
                } else {
                    cepEnderecoMsg = messages.getString(cepEnderecoMsg);
                    request.setAttribute("cepEnderecoMsg", cepEnderecoMsg);
                    isValid = false;
                    //TODO Fazer log
                    
                }

                /**
                 * Validação da Cidade do endereço do TermoEstagio, usando
                 * métodos da Classe ValidaUtils. Campo obrigatório e tamanho
                 * máximo de 150 caracteres.
                 */
                String cidadeEnderecoMsg = "";
                campo = "Cidade";
                tamanho = 150;
                cidadeEnderecoMsg = ValidaUtils.validaObrigatorio(campo, cidadeEnderecoTermoAditivo);
                if (cidadeEnderecoMsg.trim().isEmpty()) {
                    cidadeEnderecoMsg = ValidaUtils.validaTamanho(campo, tamanho, cidadeEnderecoTermoAditivo);
                    if (cidadeEnderecoMsg.trim().isEmpty()) {
                        request.setAttribute("cidadeEnderecoTermoEstagio", cidadeEnderecoTermoAditivo);
                    } else {
                        cidadeEnderecoMsg = messages.getString(cidadeEnderecoMsg);
                        cidadeEnderecoMsg = ServletUtils.mensagemFormatada(cidadeEnderecoMsg, locale, tamanho);
                        request.setAttribute("cidadeEnderecoMsg", cidadeEnderecoMsg);
                        isValid = false;
                        //TODO Fazer log
                        
                    }
                } else {
                    cidadeEnderecoMsg = messages.getString(cidadeEnderecoMsg);
                    request.setAttribute("cidadeEnderecoMsg", cidadeEnderecoMsg);
                    isValid = false;
                    //TODO Fazer log
                    
                }

                /**
                 * Validação do Estado do endereço do TermoEstagio, usando
                 * métodos da Classe ValidaUtils. Campo obrigatório e contido na
                 * Enum de UFs.
                 */
                String estadoEnderecoMsg = "";
                campo = "Estado";
                estadoEnderecoMsg = ValidaUtils.validaObrigatorio(campo, estadoEnderecoTermoAditivo);
                if (estadoEnderecoMsg.trim().isEmpty()) {
                    estadoEnderecoMsg = ValidaUtils.validaUf(campo, estadoEnderecoTermoAditivo);
                    if (estadoEnderecoMsg.trim().isEmpty()) {
                        request.setAttribute("estadoEnderecoTermoEstagio", estadoEnderecoTermoAditivo);
                    } else {
                        estadoEnderecoMsg = messages.getString(estadoEnderecoMsg);
                        request.setAttribute("estadoEnderecoMsg", estadoEnderecoMsg);
                        isValid = false;
                        //TODO Fazer log
                        
                    }
                } else {
                    estadoEnderecoMsg = messages.getString(estadoEnderecoMsg);
                    request.setAttribute("estadoEnderecoMsg", estadoEnderecoMsg);
                    isValid = false;
                    //TODO Fazer log
                   
                }
            }
            if (updSupervisor != null && !updSupervisor.trim().isEmpty()) {
                motivo = motivo + messages.getString("br.cefetrj.sisgee.resources.form.consultar.termo.supervisor");
                if(nomeSurpervisor.trim().isEmpty() || nomeSurpervisor == null) {
                    msg = messages.getString("br.cefetrj.sisgee.resources.form.consultar.termo.nomeSupervisorInvalido");
                    isValid = false;
                }
                if(cargoSurpervisor.trim().isEmpty()) {
                    msg = messages.getString("br.cefetrj.sisgee.resources.form.consultar.termo.cargoSupervisorInvalido");
                    isValid = false;
                }
            }
            

        } else {
            msg = messages.getString("br.cefetrj.sisgee.form_termo_aditivo_servlet.msg_termo_estagio_invalido");
            isValid = false;

        }
        if(isValid) {
            Aluno aluno = AlunoServices.buscarAlunoByMatricula(matricula);
            Convenio convenio = termoEstagio.getConvenio();
            Date dataInicioJsp = null;
            Date rescisao = null;
            Date dataFimJsp = null;
            try {
                SimpleDateFormat format = null;
                if (messages.getLocale().toString().equals("pt_BR")) {
                    format = new SimpleDateFormat("dd/MM/yyyy");
                } else if (messages.getLocale().toString().equals("en_US")) {
                    format = new SimpleDateFormat("MM/dd/yyyy");
                } else {
                    //fazer log de erro com a internacionalização
                    
                }

                if (format != null) {
                    dataInicioJsp = format.parse(dataInicioTermoAditivo);
                    dataFimJsp = format.parse(dataFimTermoAditivo);
                } else {
                    //fazer o log de erro com a internacionalização
                    
                } 
            }catch (Exception e) {
                
            }
            
            TermoEstagio termoAditivo = new TermoEstagio(dataInicioJsp, dataFimJsp, termoEstagio.getDataRescisaoTermoEstagio(), 
                    termoEstagio.getCargaHorariaTermoEstagio(), termoEstagio.getValorBolsa(), termoEstagio.getEnderecoTermoEstagio(), 
                    termoEstagio.getComplementoEnderecoTermoEstagio(), termoEstagio.getBairroEnderecoTermoEstagio(), 
                    termoEstagio.getCepEnderecoTermoEstagio(), termoEstagio.getCidadeEnderecoTermoEstagio(), 
                    termoEstagio.getEstadoEnderecoTermoEstagio(), termoEstagio.getEEstagioObrigatorio(), 
                    termoEstagio.getNomeSupervisor(), termoEstagio.getCargoSupervisor(), motivo, 
                    termoEstagio.getEAtivo(), aluno, convenio, termoEstagio.getProfessorOrientador(), termoEstagio.getAgenciada());
            
            if (updCargaHoraria.equals("sim")) {
                termoAditivo.setCargaHorariaTermoEstagio(Integer.parseInt(cargaHorariaTermoAditivo));
            }
            if(updEndereco.equals("sim")) {
                termoAditivo.setBairroEnderecoTermoEstagio(bairroEnderecoTermoAditivo);
                termoAditivo.setCepEnderecoTermoEstagio(cepEnderecoTermoAditivo);
                termoAditivo.setCidadeEnderecoTermoEstagio(cidadeEnderecoTermoAditivo);
                termoAditivo.setComplementoEnderecoTermoEstagio(complementoEnderecoTermoAditivo);
                termoAditivo.setEnderecoTermoEstagio(enderecoTermoAditivo);
                termoAditivo.setEstadoEnderecoTermoEstagio(estadoEnderecoTermoAditivo);
            }
            if(updProfessor.equals("sim")) {
                termoAditivo.setProfessorOrientador(professorOrientador);
            }
            if(updSupervisor.equals("sim")) {
                termoAditivo.setNomeSupervisor(nomeSurpervisor);
                termoAditivo.setCargoSupervisor(cargoSurpervisor);
            }
            if(updValorBolsa.equals("sim")) {
                termoAditivo.setValorBolsa(Float.parseFloat(valorBolsaTermoAditivo));
            }
            if(updVigencia.equals("sim")) {
                termoAditivo.setDataFimTermoEstagio(dataFimJsp);
            }
            termoAditivo.setMotivoAditivo(motivo);
            
            termoAditivo.setIdTermoEstagio(null);
            if (termoEstagio.getTermoEstagioAditivo() == null) {
                termoAditivo.setTermoEstagioAditivo(termoEstagio);
            }else {
                termoAditivo.setTermoEstagioAditivo(termoEstagio.getTermoEstagioAditivo());
            }
            Logger lg = Logger.getLogger(IncluirTermoEstagioServlet.class);
            try{
                TermoAditivoServices.incluirTermoAditivo(termoAditivo);
                msg = messages.getString("br.cefetrj.sisgee.incluir_termo_aditivo_servlet.msg_registroAditivoConcluido");
                request.setAttribute("msg", msg);
                lg.info(msg);
                request.getRequestDispatcher("/index.jsp").forward(request, response);			
            }catch(Exception e) {
                msg = messages.getString("br.cefetrj.sisgee.incluir_termo_aditivo_servlet.msg_ocorreuErro");
                request.setAttribute("msg", msg);
                lg.error("Exception ao tentar inserir o Termo de Estágio", e);
                request.getRequestDispatcher("/form_termo_estagio.jsp").forward(request, response);
            }
        }else {
            Aluno aluno = null;
            request.setAttribute("termoEstagio", termoEstagio);
            
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

                }
            
            //Aluno
            request.setAttribute("idAluno", aluno.getIdAluno());
            request.setAttribute("matricula", aluno.getMatricula());
            request.setAttribute("nome", aluno.getNome());
            request.setAttribute("nomeCurso", aluno.getNomeCurso());
            request.setAttribute("nomeCampus", aluno.getNomeCampus());
            
            //Convenio
            request.setAttribute("idConvenio", termoEstagio.getConvenio().getIdConvenio());
            request.setAttribute("numeroConvenio", ConvenioUtils.getNumeroConvenioFormatado(termoEstagio.getConvenio().getNumeroConvenio()));
            request.setAttribute("nomeConveniado", termoEstagio.getConvenio().getNomeConveniado());
            request.setAttribute("nomeConvenio", termoEstagio.getConvenio().getNomeConveniado());
            request.setAttribute("tipo", termoEstagio.getConvenio().getIsPessoaJuridica());
            request.setAttribute("agente", termoEstagio.getConvenio().getIsAgenteIntegracao());
            request.setAttribute("agencia", termoEstagio.getConvenio().getNomeConveniado());
            if (termoEstagio.getConvenio().getIsPessoaJuridica()) {
                request.setAttribute("CpfCnpj", ConvenioUtils.getCnpjEmpresaFormatado(termoEstagio.getConvenio().getCpf_cnpj()));
            }else {
                request.setAttribute("CpfCnpj", ConvenioUtils.getCpfFormatado(termoEstagio.getConvenio().getCpf_cnpj()));
            }
            
            request.setAttribute("agenciada", termoEstagio.getAgenciada());
            request.setAttribute("razaoSocial", termoEstagio.getConvenio().getNomeConveniado());
            try {    
                SimpleDateFormat format = null;
                if (messages.getLocale().toString().equals("pt_BR")) {
                    format = new SimpleDateFormat("dd/MM/yyyy");
                } else if (messages.getLocale().toString().equals("en_US")) {
                    format = new SimpleDateFormat("MM/dd/yyyy");
                } else {
                    //fazer log de erro com a internacionalização
                    
                }

                if (format != null) {
                    //Datas
                    SimpleDateFormat in= new SimpleDateFormat("yyyy-MM-dd");
                    request.setAttribute("dataInicioTermoEstagio", format.format(in.parse(termoEstagio.getDataInicioTermoEstagio().toString())));
                    request.setAttribute("dataFimTermoEstagio", format.format(in.parse(termoEstagio.getDataFimTermoEstagio().toString())));
                }
            }catch (Exception e) {
                //Fazer log de erro data vindas do bd do termo invalidas
                System.err.println("Datas de inicio ou de fim do termo de estagio invalidas");
            }
            
            //Termo
            request.setAttribute("cargaHorariaTermoEstagio", termoEstagio.getCargaHorariaTermoEstagio());
            request.setAttribute("valorBolsa", termoEstagio.getValorBolsa());
            
            //Endereço
            request.setAttribute("enderecoTermoEstagio", termoEstagio.getEnderecoTermoEstagio());
            request.setAttribute("complementoEnderecoTermoEstagio", termoEstagio.getComplementoEnderecoTermoEstagio());
            request.setAttribute("bairroEnderecoTermoEstagio", termoEstagio.getBairroEnderecoTermoEstagio());
            request.setAttribute("cidadeEnderecoTermoEstagio", termoEstagio.getCidadeEnderecoTermoEstagio());
            request.setAttribute("cepEnderecoTermoEstagio", termoEstagio.getCepEnderecoTermoEstagio());
            request.setAttribute("ufTermo", termoEstagio.getEstadoEnderecoTermoEstagio()); 
            if (termoEstagio.getEEstagioObrigatorio()) {
                request.setAttribute("eEstagioObrigatorio", "sim");
            }else {
                request.setAttribute("eEstagioObrigatorio", "nao");
            }
            
            request.setAttribute("nomeSupervisor", termoEstagio.getNomeSupervisor());
            request.setAttribute("cargoSupervisor", termoEstagio.getCargoSupervisor());
            request.setAttribute("professorTermo", termoEstagio.getProfessorOrientador());
            
            List<ProfessorOrientador> professores = ProfessorOrientadorServices.listarProfessorOrientador();
            UF[] uf = UF.asList();
            professores.remove(termoEstagio.getProfessorOrientador());
            request.setAttribute("professores", professores);
            request.setAttribute("uf", uf);
            //request.setAttribute("professor", termoEstagio.getProfessorOrientador());

            request.setAttribute("updVigencia", updVigencia);
            request.setAttribute("updCargaHoraria", updCargaHoraria);
            request.setAttribute("updProfessor", updProfessor);
            request.setAttribute("updValorBolsa", updValorBolsa);
            request.setAttribute("updEndereco", updEndereco);
            request.setAttribute("updSupervisor", updSupervisor);
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("/form_termo_estagio.jsp").forward(request, response);
        }
    }

    /** Metodo que carrega lista 
     *
     * @param request
     * @throws ServletException
     * @throws IOException
     * @return HttpServletRequest lista dos professores
     */
    private static HttpServletRequest carregarListas(HttpServletRequest request) {

        List<ProfessorOrientador> professores = ProfessorOrientadorServices.listarProfessorOrientador();
        UF[] uf = UF.asList();

        request.setAttribute("professores", professores);
        request.setAttribute("uf", uf);

        return request;

    }

}
