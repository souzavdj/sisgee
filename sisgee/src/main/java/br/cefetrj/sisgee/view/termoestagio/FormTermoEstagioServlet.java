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
import br.cefetrj.sisgee.control.ConvenioServices;
import br.cefetrj.sisgee.control.ProfessorOrientadorServices;
import br.cefetrj.sisgee.model.entity.Aluno;
import br.cefetrj.sisgee.model.entity.Convenio;
import br.cefetrj.sisgee.model.entity.ProfessorOrientador;
import br.cefetrj.sisgee.model.entity.TermoEstagio;
import br.cefetrj.sisgee.view.convenio.ConveniosVencerServlet;
import br.cefetrj.sisgee.view.utils.ServletUtils;
import br.cefetrj.sisgee.view.utils.UF;
import br.cefetrj.sisgee.view.utils.ValidaUtils;
import org.apache.log4j.Logger;

/**
 * Servlet para tratar os dados da tela de cadastro de Termo
 * de Estágio.
 * 
 * @author Paulo Cantuária
 * @since 1.0
 *
 */
@WebServlet("/FormTermoEstagioServlet")
public class FormTermoEstagioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	/**
         * Método doGet: carrega as listas necessárias para seleção dos atributos de relacionamento e redireciona para a tela de Registro de Termo de Estágio
         * @param request
         * @param response
         * @throws ServletException
         * @throws IOException 
         */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request = carregarListas(request); 
		
		request.getRequestDispatcher("/form_termo_estagio.jsp").forward(request, response);

	}
	
	/**
         * Método doPost: Valida os campos da tela de Registro de Termo de Estágio. 
	 * Retorna para a tela caso não passe em alguma validação 
	 * ou encaminha para o servlet de inclusão de Termo.
         * @param request
         * @param response
         * @throws ServletException
         * @throws IOException 
         */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Locale locale = ServletUtils.getLocale(request);
		ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);
		
		String dataInicioTermoEstagio = request.getParameter("dataInicioTermoEstagio");
		String dataFimTermoEstagio = request.getParameter("dataFimTermoEstagio");		
		String cargaHorariaTermoEstagio = request.getParameter("cargaHorariaTermoEstagio");
		String valorBolsa = request.getParameter("valorBolsa");
		String enderecoTermoEstagio = request.getParameter("enderecoTermoEstagio");
		String complementoEnderecoTermoEstagio = request.getParameter("complementoEnderecoTermoEstagio");
		String bairroEnderecoTermoEstagio = request.getParameter("bairroEnderecoTermoEstagio");
		String cepEnderecoTermoEstagio = request.getParameter("cepEnderecoTermoEstagio");
		String cidadeEnderecoTermoEstagio = request.getParameter("cidadeEnderecoTermoEstagio");
		String estadoEnderecoTermoEstagio = request.getParameter("estadoEnderecoTermoEstagio");
		String eEstagioObrigatorio = request.getParameter("eEstagioObrigatorio");
		String nomesupervisor = request.getParameter("nomeSupervisor");
		String cargosupervisor = request.getParameter("cargoSupervisor");
		String idProfessorOrientador = request.getParameter("idProfessorOrientador");		
		String idAluno = request.getParameter("idAluno");
		String idConvenio = request.getParameter("idConvenio");
		String agenciada = request.getParameter("agenciada");
			
		boolean isValid = true;
		String msg = "";
		String campo = "";
		Integer tamanho = 0;		
		
		/**
		 * Validação da Data de início do estágio usando os métodos da Classe ValidaUtils
		 * Campo obrigatório
		 */		
		Date dataInicio = null;
		String dataInicioMsg = "";
		campo = "Data de Início";
		
		dataInicioMsg = ValidaUtils.validaObrigatorio(campo, dataInicioTermoEstagio);
		if (dataInicioMsg.trim().isEmpty()) {
			dataInicioMsg = ValidaUtils.validaDate(campo, dataInicioTermoEstagio);
			if (dataInicioMsg.trim().isEmpty()) {	
                            //TODO Fazer o data inicio e fim aparecer no cadastro quando o usuario errar um campo distinto
				try {
					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
					dataInicio = format.parse(dataInicioTermoEstagio);
					request.setAttribute("dataInicioTermoEstagio", dataInicio);
				} catch (Exception e) {
					Logger lg = Logger.getLogger(FormTermoEstagioServlet.class);
                                        lg.error("Data em formato incorreto, mesmo após validação na classe ValidaUtils :",e);
					isValid = false;
				}
			}else {
				dataInicioMsg = messages.getString(dataInicioMsg);
				request.setAttribute("dataInicioMsg", dataInicioMsg);
				Logger lg = Logger.getLogger(FormTermoEstagioServlet.class);
                                lg.info(dataInicioMsg);
                                isValid = false;
					
			}
		} else {
			dataInicioMsg = messages.getString(dataInicioMsg);
			request.setAttribute("dataInicioMsg", dataInicioMsg);
			isValid = false;
			Logger lg = Logger.getLogger(FormTermoEstagioServlet.class);
                        lg.info(dataInicioMsg);
			
		}
		
		/**
		 * Validação da Data de fim do estágio usando os métodos da Classe ValidaUtils
		 */
		Date dataFim = null;
		campo = "Data de Término";
                String dataFimMsg="";
                
                dataFimMsg = ValidaUtils.validaObrigatorio(campo, dataInicioTermoEstagio);
		if (dataFimMsg.trim().isEmpty()) {	
			dataFimMsg = ValidaUtils.validaDate(campo , dataFimTermoEstagio);
			if (dataFimMsg.trim().isEmpty()) {
				try {
                                        //TODO Fazer o data inicio e fim aparecer no cadastro quando o usuario errar um campo distinto
					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                                        dataFim = format.parse(dataFimTermoEstagio);
					request.setAttribute("dataFimTermoEstagio", dataFim);
					
				} catch (Exception e) {
					
                                        Logger lg = Logger.getLogger(FormTermoEstagioServlet.class);
                                        lg.error("Data em formato incorreto, mesmo após validação na classe ValidaUtils : ",e);
                                        isValid = false;
                                }
			} else {
				dataFimMsg = messages.getString(dataFimMsg);
				request.setAttribute("dataFimMsg", dataFimMsg);
				isValid = false;
				Logger lg = Logger.getLogger(FormTermoEstagioServlet.class);
                                lg.info(dataFimMsg);
				
			} 
		}else{
                        dataFimMsg = messages.getString(dataFimMsg);
			request.setAttribute("dataFimMsg", dataFimMsg);
			isValid = false;
			Logger lg = Logger.getLogger(FormTermoEstagioServlet.class);
                        lg.info(dataFimMsg);
                }    
		
		
		
		/**
		 * Validação do período (entre o início e fim do estágio) usando o método validaDatas da Classe ValidaUtils
                 * TODO Resolver o periodo caso as datas estejam no formato em ingles
		 */
		String periodoMsg = "";
		if(!(dataFimTermoEstagio == null || dataFimTermoEstagio.isEmpty())) {
			periodoMsg = ValidaUtils.validaDatas(dataInicio, dataFim);
			if(!periodoMsg.trim().isEmpty()) {
				periodoMsg = messages.getString(ValidaUtils.validaDatas(dataInicio, dataFim));
				request.setAttribute("periodoMsg", periodoMsg);
				isValid = false;
				
                                Logger lg = Logger.getLogger(FormTermoEstagioServlet.class);
                                lg.info(periodoMsg);
				
			}	
		}	
		
		/**
		 * Validação da carga horária usando os métodos da Classe ValidaUtils
		 * Campo obrigatório e valor menor que 255 (No banco), valor menor que 24, por ser horas diárias.
		 */
		String cargaHorariaMsg = "";
		campo = "Horas por dia";
		tamanho = 6;		
		cargaHorariaMsg = ValidaUtils.validaObrigatorio(campo , cargaHorariaTermoEstagio);
		if (cargaHorariaMsg.trim().isEmpty()) {
			cargaHorariaMsg = ValidaUtils.validaInteger(campo, cargaHorariaTermoEstagio);
			if (cargaHorariaMsg.trim().isEmpty()) {
				Integer cargaHoraria = Integer.parseInt(cargaHorariaTermoEstagio);
				if (cargaHorariaMsg.trim().isEmpty()) {
					cargaHorariaMsg = ValidaUtils.validaTamanho(campo, tamanho, cargaHoraria);
					if (cargaHorariaMsg.trim().isEmpty()) {
                                                cargaHorariaMsg = ValidaUtils.validaIntervaloPositivo(cargaHoraria,7);
                                                if(cargaHorariaMsg.trim().isEmpty()){
                                                    request.setAttribute("cargaHorariaTermoEstagio", cargaHoraria);
                                                }else{
                                                    cargaHorariaMsg = messages.getString(cargaHorariaMsg);
                                                    request.setAttribute("cargaHorariaMsg", cargaHorariaMsg);
                                                    isValid = false;
                                                    Logger lg = Logger.getLogger(FormTermoEstagioServlet.class);
                                                    lg.info(cargaHorariaMsg);
                                                }
                                                
					}else {
						cargaHorariaMsg = messages.getString(cargaHorariaMsg);
						cargaHorariaMsg = ServletUtils.mensagemFormatada(cargaHorariaMsg, locale, tamanho);
						request.setAttribute("cargaHorariaMsg", cargaHorariaMsg);
					}
				} else {
					cargaHorariaMsg = messages.getString(cargaHorariaMsg);
					request.setAttribute("cargaHorariaMsg", cargaHorariaMsg);
					isValid = false;
					Logger lg = Logger.getLogger(FormTermoEstagioServlet.class);
                                        lg.info(cargaHorariaMsg);
                                       
					
				}
			} else {
				cargaHorariaMsg = messages.getString(cargaHorariaMsg);
				request.setAttribute("cargaHorariaMsg", cargaHorariaMsg);
				isValid = false;
				Logger lg = Logger.getLogger(FormTermoEstagioServlet.class);
                                lg.info(cargaHorariaMsg);
			}
		} else {
			cargaHorariaMsg = messages.getString(cargaHorariaMsg);
			request.setAttribute("cargaHorariaMsg", cargaHorariaMsg);
			isValid = false;
			Logger lg = Logger.getLogger(FormTermoEstagioServlet.class);
                        lg.info(cargaHorariaMsg);
		}
				
		/**
		 * Validação do valor da bolsa usando os métodos da Classe ValidaUtils
		 * Campo obrigatório e valor float.
		 */
		String valorBolsaMsg = "";
		campo = "Valor";
		valorBolsaMsg = ValidaUtils.validaObrigatorio(campo, valorBolsa);
		if (valorBolsaMsg.trim().isEmpty()) {
                        valorBolsa = valorBolsa.replaceAll("[.|,]", "");
			valorBolsaMsg = ValidaUtils.validaFloat(campo, valorBolsa);
			if (valorBolsaMsg.trim().isEmpty()) {
				Float valor = Float.parseFloat(valorBolsa);
				valor = valor / 100;
				request.setAttribute("valorBolsa", valor);
                                //ToDo botar internacionalização nisso
				if(valor > 2000) 
				{ 
                                    request.setAttribute("valorBolsaMsg", "Esse valor é alto para uma bolsa de estágio. Tem certeza?");
				}	
			} else {
				valorBolsaMsg = messages.getString(valorBolsaMsg);
				request.setAttribute("valorBolsaMsg", valorBolsaMsg);
				isValid = false;
				Logger lg = Logger.getLogger(FormTermoEstagioServlet.class);
                                lg.info(valorBolsaMsg);
				
			}
		} else {
			valorBolsaMsg = messages.getString(valorBolsaMsg);
			request.setAttribute("valorBolsaMsg", valorBolsaMsg);
			isValid = false;
			Logger lg = Logger.getLogger(FormTermoEstagioServlet.class);
                        lg.info(valorBolsaMsg);
		}			
		
		/**
		 * Validação do endereço do TermoEstagio usando métodos da Classe ValidaUtils.
		 * Campo obrigatório e tamanho máximo de 255 caracteres.
		 */
		String enderecoMsg = "";
		campo = "Endereço";
		tamanho = 255;
		enderecoMsg = ValidaUtils.validaObrigatorio(campo, enderecoTermoEstagio);
		if(enderecoMsg.trim().isEmpty()) {
			enderecoMsg = ValidaUtils.validaTamanho(campo, tamanho, enderecoTermoEstagio);
			if(enderecoMsg.trim().isEmpty()) {
				request.setAttribute("enderecoTermoEstagio", enderecoTermoEstagio);
			}else {
				enderecoMsg = messages.getString(enderecoMsg);
				enderecoMsg = ServletUtils.mensagemFormatada(enderecoMsg, locale, tamanho);
				request.setAttribute("enderecoMsg", enderecoMsg);
				isValid = false;
				Logger lg = Logger.getLogger(FormTermoEstagioServlet.class);
                                lg.info(enderecoMsg);
			}
		}else {
			enderecoMsg = messages.getString(enderecoMsg);
			request.setAttribute("enderecoMsg", enderecoMsg);
			isValid = false;
			Logger lg = Logger.getLogger(FormTermoEstagioServlet.class);
                        lg.info(enderecoMsg);
		}		
		
		
		/**
		 * Validação do complemento do endereço do TermoEstagio usando os métodos da Classe ValidaUtils.
		 * Campo opcional e tamanho máximo de 150 caracteres.
		 */		
		String complementoEnderecoMsg = "";
		campo = "Complemento";
		tamanho = 100;
		if(!complementoEnderecoTermoEstagio.trim().isEmpty()) {
			complementoEnderecoMsg = ValidaUtils.validaTamanho(campo, tamanho, complementoEnderecoTermoEstagio);
			if(complementoEnderecoMsg.trim().isEmpty()) {
				request.setAttribute("complementoEnderecoTermoEstagio", complementoEnderecoTermoEstagio);
				
			}else {				
				complementoEnderecoMsg = messages.getString(complementoEnderecoMsg);
				complementoEnderecoMsg = ServletUtils.mensagemFormatada(complementoEnderecoMsg, locale, tamanho);
				request.setAttribute("complementoEnderecoMsg", complementoEnderecoMsg);
				isValid = false;
				Logger lg = Logger.getLogger(FormTermoEstagioServlet.class);
                                lg.info(complementoEnderecoMsg);
				
			}
		}		
		
		/**
		 * Validação do bairro do endereço do TermoEstagio usando métodos da Classe ValidaUtils.
		 * Campo opcional e tamanho máximo de 150 caracteres.
		 */
		String bairroEnderecoMsg = "";
		campo = "Bairro";
		tamanho = 100;
		if(!bairroEnderecoTermoEstagio.trim().isEmpty()) {
			bairroEnderecoMsg = ValidaUtils.validaTamanho(campo, tamanho, bairroEnderecoTermoEstagio);
			if(bairroEnderecoMsg.trim().isEmpty()) {
				request.setAttribute("bairroEnderecoTermoEstagio", bairroEnderecoTermoEstagio);
			}else {				
				bairroEnderecoMsg = messages.getString(bairroEnderecoMsg);
				bairroEnderecoMsg = ServletUtils.mensagemFormatada(bairroEnderecoMsg, locale, tamanho);
				request.setAttribute("bairroEnderecoMsg", bairroEnderecoMsg);
				isValid = false;
				Logger lg = Logger.getLogger(FormTermoEstagioServlet.class);
                                lg.info(bairroEnderecoMsg);
				
			}
		}		
				
		/**
		 * Validação do cep do endereço do TermoEstagio usando métodos da Classe ValidaUtils.
		 * Campo opcional e tamanho máximo de 15 caracteres.
		 */
		String cepEnderecoMsg = "";	
		campo = "CEP";
		tamanho = 8;
		if(!cepEnderecoTermoEstagio.trim().isEmpty()) {
			cepEnderecoMsg = ValidaUtils.validaTamanho(campo, tamanho, cepEnderecoTermoEstagio);
			if(bairroEnderecoMsg.trim().isEmpty()) {
				request.setAttribute("cepEnderecoTermoEstagio", cepEnderecoTermoEstagio);
			}else {				
				cepEnderecoMsg = messages.getString(cepEnderecoMsg);	
				cepEnderecoMsg = ServletUtils.mensagemFormatada(bairroEnderecoMsg, locale, tamanho);
				request.setAttribute("cepEnderecoMsg", cepEnderecoMsg);
				isValid = false;
				Logger lg = Logger.getLogger(FormTermoEstagioServlet.class);
                                lg.info(cepEnderecoMsg);
			}
		}			
		
		
		/**
		 * Validação da Cidade do endereço do TermoEstagio, usando métodos da Classe ValidaUtils.
		 * Campo obrigatório e tamanho máximo de 150 caracteres.
		 */
		String cidadeEnderecoMsg = "";
		campo = "Cidade";
		tamanho = 100;
		cidadeEnderecoMsg = ValidaUtils.validaObrigatorio(campo, cidadeEnderecoTermoEstagio);
		if(cidadeEnderecoMsg.trim().isEmpty()) {
			cidadeEnderecoMsg = ValidaUtils.validaTamanho(campo, tamanho, cidadeEnderecoTermoEstagio);
			if(cidadeEnderecoMsg.trim().isEmpty()) {
				request.setAttribute("cidadeEnderecoTermoEstagio", cidadeEnderecoTermoEstagio);
			}else {
				cidadeEnderecoMsg = messages.getString(cidadeEnderecoMsg);	
				cidadeEnderecoMsg = ServletUtils.mensagemFormatada(cidadeEnderecoMsg, locale, tamanho);
				request.setAttribute("cidadeEnderecoMsg", cidadeEnderecoMsg);
				isValid = false;
				Logger lg = Logger.getLogger(FormTermoEstagioServlet.class);
                                lg.info(cidadeEnderecoMsg);
				
			}
		}else {
			cidadeEnderecoMsg = messages.getString(cidadeEnderecoMsg);	
			request.setAttribute("cidadeEnderecoMsg", cidadeEnderecoMsg);
			isValid = false;
			Logger lg = Logger.getLogger(FormTermoEstagioServlet.class);
                        lg.info(cidadeEnderecoMsg);
		}					
		
		/**
		 * Validação do Estado do endereço do TermoEstagio, usando métodos da Classe ValidaUtils.
		 * Campo obrigatório e contido na Enum de UFs.
		 */
		String estadoEnderecoMsg = "";
		campo = "Estado";
		estadoEnderecoMsg = ValidaUtils.validaObrigatorio(campo, estadoEnderecoTermoEstagio);
		if(estadoEnderecoMsg.trim().isEmpty()) {
			estadoEnderecoMsg = ValidaUtils.validaUf(campo, estadoEnderecoTermoEstagio);
			if(estadoEnderecoMsg.trim().isEmpty()) {
				request.setAttribute("estadoEnderecoTermoEstagio", estadoEnderecoTermoEstagio);
			}else {
				estadoEnderecoMsg = messages.getString(estadoEnderecoMsg);
				request.setAttribute("estadoEnderecoMsg", estadoEnderecoMsg);
				isValid = false;
				Logger lg = Logger.getLogger(FormTermoEstagioServlet.class);
                                lg.info(estadoEnderecoMsg);
			}
		}else {			
			estadoEnderecoMsg = messages.getString(estadoEnderecoMsg);
			request.setAttribute("estadoEnderecoMsg", estadoEnderecoMsg);
			isValid = false;
			Logger lg = Logger.getLogger(FormTermoEstagioServlet.class);
                        lg.info(estadoEnderecoMsg);
		}					
		
		/**
		 * Validação do campo Estágio Obrigatório, usando métodos da Classe ValidaUtils.
		 * Deve ser campo booleano
		 */
		String eEstagioObrigatorioMsg = "";
		campo = "Estágio obrigatório";
		eEstagioObrigatorioMsg = ValidaUtils.validaObrigatorio(campo, eEstagioObrigatorio);
		if(eEstagioObrigatorioMsg.trim().isEmpty()) {
			Boolean obrigatorio;
			if(eEstagioObrigatorio.equals("sim")) {	
                                request.setAttribute("eEstagioObrigatorio", "sim");
				obrigatorio = true;
				request.setAttribute("obrigatorio", obrigatorio);
			} else if(eEstagioObrigatorio.equals("nao")) {
                            request.setAttribute("eEstagioObrigatorio", "nao");
				obrigatorio = false;
				request.setAttribute("obrigatorio", obrigatorio);
			}else {
				eEstagioObrigatorioMsg = "Valor inválido";
				request.setAttribute("eEstagioObrigatorioMsg", eEstagioObrigatorioMsg);
				isValid = false;
				Logger lg = Logger.getLogger(FormTermoEstagioServlet.class);
                                lg.info(eEstagioObrigatorioMsg);
			}
				
                }else {
                        eEstagioObrigatorioMsg = messages.getString(eEstagioObrigatorioMsg);
                        request.setAttribute("eEstagioObrigatorioMsg", eEstagioObrigatorioMsg);
                        isValid = false;
                        Logger lg = Logger.getLogger(FormTermoEstagioServlet.class);
                        lg.info(eEstagioObrigatorioMsg);
                }			
		
			/**
		 * Validação do Professor Supervisor, usando métodos da Classe ValidaUtils.
		 * É um campo obrigatório
		 */		
		String nomeSupervisorMsg = "";
		campo = "Nome Supervisor";
		tamanho = 100;
                nomeSupervisorMsg = ValidaUtils.validaObrigatorio(campo, nomesupervisor);
		if(nomeSupervisorMsg.trim().isEmpty()) {
		
                        
			nomeSupervisorMsg = ValidaUtils.validaTamanho(campo, tamanho, nomesupervisor);
			if(nomeSupervisorMsg.trim().isEmpty()) {
				request.setAttribute("nomeSupervisor", nomesupervisor);
			}else {
				nomeSupervisorMsg = messages.getString(nomeSupervisorMsg);
				request.setAttribute("nomeSupervisorMsg", nomeSupervisorMsg);
				isValid = false;
				Logger lg = Logger.getLogger(FormTermoEstagioServlet.class);
                                lg.info(nomeSupervisorMsg);
			}
		}else{
                    nomeSupervisorMsg = messages.getString(nomeSupervisorMsg);
                    request.setAttribute("nomeSupervisorMsg", nomeSupervisorMsg);
                    isValid = false;
                    Logger lg = Logger.getLogger(FormTermoEstagioServlet.class);
                    lg.info(nomeSupervisorMsg);
                }
		
		
		/**
		 * Validação do Cargo do professor Supervisor, usando métodos da Classe ValidaUtils.
		 * Não é obrigatório
		 */
		
		String cargoSupervisorMsg = "";
		campo = "Cargo Supervisor";
		if(!cargosupervisor.isEmpty())
		{
			cargoSupervisorMsg = ValidaUtils.validaTamanho(campo, tamanho, cargosupervisor);
			if(cargoSupervisorMsg.trim().isEmpty()) {
				request.setAttribute("cargoSupervisor", cargosupervisor);
			}else {
				cargoSupervisorMsg = messages.getString(cargoSupervisorMsg);
				request.setAttribute("cargoSupervisorMsg", cargoSupervisorMsg);
				isValid = false;
				Logger lg = Logger.getLogger(FormTermoEstagioServlet.class);
                                lg.info(cargoSupervisorMsg);
			}
		}	
		
		/**
		 * Validação do Id do Professor Orientador, usando métodos da Classe ValidaUtils.
		 * Consultando a lista de Professores para validar.É um campo obrigatorio
		 */
		String idProfessorMsg = "";
		campo = "Professor Orientador";
		//Boolean hasProfessor = false;
                idProfessorMsg = ValidaUtils.validaObrigatorio(campo, idProfessorOrientador);
		if(idProfessorMsg.trim().isEmpty()) {
			idProfessorMsg = ValidaUtils.validaInteger(campo, idProfessorOrientador);
			if (idProfessorMsg.trim().isEmpty()) {
				Integer idProfessor = Integer.parseInt(idProfessorOrientador);
				List<ProfessorOrientador> listaProfessores = ProfessorOrientadorServices.listarProfessorOrientador();
				if (listaProfessores != null) {
					if (listaProfessores.contains(new ProfessorOrientador(idProfessor))) {
						request.setAttribute("idProfessor", idProfessor);
						//hasProfessor = true;
					} else {
						idProfessorMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.professor_invalido");
						isValid = false;
						Logger lg = Logger.getLogger(FormTermoEstagioServlet.class);
                                                lg.info(idProfessorMsg);
					}
				} else {
					idProfessorMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.lista_professores_vazia");
					isValid = false;
					Logger lg = Logger.getLogger(FormTermoEstagioServlet.class);
                                        lg.info(idProfessorMsg);
				}
			} else {
				idProfessorMsg = messages.getString(idProfessorMsg);
				request.setAttribute("idProfessorMsg", idProfessorMsg);
				isValid = false;
				Logger lg = Logger.getLogger(FormTermoEstagioServlet.class);
                                lg.info(idProfessorMsg);
			
                        }
		}else{
                    idProfessorMsg = messages.getString(idProfessorMsg);
                    request.setAttribute("idProfessorMsg", idProfessorMsg);
                    isValid = false;
                    Logger lg = Logger.getLogger(FormTermoEstagioServlet.class);
                    lg.info(idProfessorMsg);
                }
		
		
		/**
		 * Validação do Id do Aluno, usando métodos da Classe ValidaUtils.
		 * Valor obrigatório e Integer
		 */
		Boolean alunoExiste = false;
		String idAlunoMsg = "";
		campo = "Aluno";
		idAlunoMsg = ValidaUtils.validaObrigatorio(campo, idAluno);
		if (idAlunoMsg.trim().isEmpty()) {
			idAlunoMsg = ValidaUtils.validaInteger(campo, idAluno);
			if (idAlunoMsg.trim().isEmpty()) {
				Integer idAlunoInt = Integer.parseInt(idAluno);
				List<Aluno> listaAlunos = AlunoServices.listarAlunos();
				if (listaAlunos != null) {
					if (listaAlunos.contains(new Aluno(idAlunoInt))) {
						request.setAttribute("idAluno", idAlunoInt);
						alunoExiste = true;
					} else {
						idAlunoMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.aluno_invalido");
						isValid = false;
						Logger lg = Logger.getLogger(FormTermoEstagioServlet.class);
                                                 lg.info(idAlunoMsg);
						
					}
				} else {
					idAlunoMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.lista_alunos_vazia");
					isValid = false;
					Logger lg = Logger.getLogger(FormTermoEstagioServlet.class);
                                        lg.info(idAlunoMsg);
						
				}

			} else {
				idAlunoMsg = messages.getString(idAlunoMsg);
				request.setAttribute("idAlunoMsg", idAlunoMsg);
				isValid = false;
				Logger lg = Logger.getLogger(FormTermoEstagioServlet.class);
                                lg.info(idAlunoMsg);
						
			}
		} else {
			idAlunoMsg = messages.getString(idAlunoMsg);
			request.setAttribute("idAlunoMsg", idAlunoMsg);
			isValid = false;
			Logger lg = Logger.getLogger(FormTermoEstagioServlet.class);
                        lg.info(idAlunoMsg);
						
		}
		
		/**
		 * Validação do Id do Convenio, usando métodos da Classe ValidaUtils.
		 * Valor obrigatório e Integer
		 * 
		 */
                
		String idConvenioMsg = "";
		campo = "Convênio";
		idConvenioMsg = ValidaUtils.validaObrigatorio(campo, idConvenio);
		if (idConvenioMsg.trim().isEmpty()) {
			idConvenioMsg = ValidaUtils.validaInteger(campo, idConvenio);
			if (idConvenioMsg.trim().isEmpty()) {
				Integer idConvenioInt = Integer.parseInt(idConvenio);
				List<Convenio> listaConvenio = ConvenioServices.listarConvenios();
				if (listaConvenio != null) {
					if (listaConvenio.contains(new Convenio(idConvenioInt))) {
						request.setAttribute("idConvenio", idConvenioInt);
						
					} else {
                                                 //TODO(Antigo) consertar referencia da Internacionalização
						idConvenioMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.convenio_invalido");
						request.setAttribute("idConvenioMsg", idConvenioMsg);
                                                isValid = false;
						Logger lg = Logger.getLogger(FormTermoEstagioServlet.class);
                                                lg.info(idConvenioMsg);
						
					}
				} else {
					idConvenioMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.lista_convenio_vazia");
					request.setAttribute("idConvenioMsg", idConvenioMsg);
                                        isValid = false;
					Logger lg = Logger.getLogger(FormTermoEstagioServlet.class);
                                        lg.info(idConvenioMsg);
						
				}

			} else {
				idConvenioMsg = messages.getString(idConvenioMsg);
				request.setAttribute("idConvenioMsg", idConvenioMsg);
				isValid = false;
				Logger lg = Logger.getLogger(FormTermoEstagioServlet.class);
                                lg.info(idConvenioMsg);
						
			}
		} else {
			idConvenioMsg = messages.getString(idConvenioMsg);
                        request.setAttribute("idConvenioMsg", idConvenioMsg);
                        isValid = false;
                        Logger lg = Logger.getLogger(FormTermoEstagioServlet.class);
                        lg.info(idConvenioMsg);
						
		}
                
                String agenciadaMsg = "";
		campo = "Agenciada";
                tamanho=255;
		if(!agenciada.isEmpty())
		{
                        agenciadaMsg=ValidaUtils.validaNaoInteger(campo, agenciada);
                        if(agenciadaMsg.trim().isEmpty()) {
				agenciadaMsg = ValidaUtils.validaTamanho(campo, tamanho, agenciada);
                                if(agenciadaMsg.trim().isEmpty()) {
                                        request.setAttribute("agenciada", agenciada);
                                }else {
                                        agenciadaMsg = messages.getString(agenciadaMsg);
                                        agenciadaMsg  = ServletUtils.mensagemFormatada(agenciadaMsg , locale, tamanho);
                                        request.setAttribute("agenciadaMsg", agenciadaMsg);
                                        isValid = false;
                                        Logger lg = Logger.getLogger(FormTermoEstagioServlet.class);
                                        lg.info(agenciadaMsg);
                                }
			}else {
				agenciadaMsg = messages.getString(agenciadaMsg);
				request.setAttribute("agenciadaMsg", agenciadaMsg);
				isValid = false;
				Logger lg = Logger.getLogger(FormTermoEstagioServlet.class);
                                lg.info(agenciadaMsg);
			}
                        
		}
                
		/**
		 * *************************************************************************
		 * Se aluno já possui estágio aberto, não pode cadastrar outro
		 * *************************************************************************
		 */
		if(alunoExiste) {
			Integer idAlunoInt = Integer.parseInt(idAluno);
			Aluno a = new Aluno(idAlunoInt);
			Aluno aluno = AlunoServices.buscarAluno(a);
			Boolean hasTermoAberto = false;
			
			List<TermoEstagio> termosEstagio = aluno.getTermoEstagios();	
			for (TermoEstagio t : termosEstagio) {				
				if((t.getDataRescisaoTermoEstagio() == null) || (t.getEAtivo()==true)) {
					hasTermoAberto = true;
					break;
				}
			}
			
			if(hasTermoAberto) {
				String msg2 = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.msg_aluno_has_termo_aberto");
				request.setAttribute("msg2", msg2);
				isValid = false;
			}
		}
		
		
		
		/**
		 * Teste da variável booleana após validação. Redirecionamento para a inclusão
		 * ou devolver para o formulário com as mensagens.
		 */
		if (isValid) {
			System.out.println("Entrou no valido");
                        request.getRequestDispatcher("/IncluirTermoEstagioServlet").forward(request, response);
                        //request.getRequestDispatcher("/form_termo_estagio.jsp").forward(request, response);
		} else {
			System.out.println("Entrou no invalido");
			msg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.msg_atencao");
			request.setAttribute("msg", msg);
			request = carregarListas(request);
                        request.getRequestDispatcher("/form_termo_estagio.jsp").forward(request, response);
		}
	}	
	
	private static HttpServletRequest carregarListas(HttpServletRequest request) {
		
		List<Aluno> alunos = AlunoServices.listarAlunos();
		List<ProfessorOrientador> professores = ProfessorOrientadorServices.listarProfessorOrientador();
		UF[] uf = UF.asList();
		List<Convenio> conv = ConvenioServices.listarConvenios();
		
		request.setAttribute("alunos", alunos);
		request.setAttribute("professores", professores);
		request.setAttribute("uf", uf);
		request.setAttribute("convenios", conv);
		
		return request;		
	}	
}
