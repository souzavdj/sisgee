package br.cefetrj.sisgee.view.convenio;

import br.cefetrj.sisgee.control.ConvenioServices;
import br.cefetrj.sisgee.control.EmpresaServices;
import br.cefetrj.sisgee.model.entity.Convenio;
import br.cefetrj.sisgee.model.entity.Empresa;
import br.cefetrj.sisgee.view.utils.ServletUtils;
import br.cefetrj.sisgee.view.utils.ValidaUtils;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Classe responsavel por verificar se as informações dos campos da pagina de
 * cadastrar convenio estão corretas.Se as informações forem validas vai para o
 * IncluirConvenioServlet.Se não forem validas aparecerá na pagina de cadastro
 * de convenio mensagens de erro especificias.
 *
 * @author Andre
 * @version 1
 *
 */
@WebServlet("/ValidarCadastroConvenioServlet")
public class ValidarCadastroConvenioServlet extends HttpServlet {

    /**
     *
     * Metodo que recebe informações do formulario da pagina de cadastrar
     * convenio e valida as informações.
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Locale locale = ServletUtils.getLocale(req);
        ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);
        String tipo = req.getParameter("tipo");

        String agente = req.getParameter("agente");
        String cnpj = req.getParameter("cnpj");
        String razaoSocial = req.getParameter("razaoSocial");

        String cpf = req.getParameter("cpf");
        String nome = req.getParameter("nome");
        String pessoaContato = req.getParameter("pessoaContato");

        String dataAssinaturaConvenio = req.getParameter("dataAssinatura");
        String telefone = req.getParameter("telefone");
        String email = req.getParameter("email");
        

        boolean isValid = true;
        int tamanho;

        /**
         * Validação do campo Tipo, usando métodos da Classe ValidaUtils. Deve
         * ser campo booleano
         */
        String tipoMsg = "";
        tipoMsg = ValidaUtils.validaObrigatorio("Tipo de Pessoa", tipo);
        if (tipoMsg.trim().isEmpty()) {
            tipoMsg = ValidaUtils.validaBoolean("Tipo de Pessoa", tipo);
            if (tipoMsg.trim().isEmpty()) {
                Boolean obrigatorio = Boolean.parseBoolean(tipo);
                req.setAttribute("obrigatorio", obrigatorio);
            } else {
                tipoMsg = messages.getString(tipoMsg);
                req.setAttribute("tipoMsg", tipoMsg);
                isValid = false;
            }
        } else {
            tipoMsg = messages.getString(tipoMsg);
            req.setAttribute("tipoMsg", tipoMsg);
            isValid = false;         
        }
        
        /**
         * Caso seja pessoa jurídica
         */
        if (tipo.equals("sim")) {            
            
            /**
             * Validação do campo Agente Integração, usando métodos da Classe
             * ValidaUtils. Deve ser campo booleano
             */
            String agenteMsg = "";
            agenteMsg = ValidaUtils.validaObrigatorio("Agente Integração", agente);
            if (agenteMsg.trim().isEmpty()) {
                agenteMsg = ValidaUtils.validaBoolean("Agente Integração", agente);
                if (agenteMsg.trim().isEmpty()) {
                    Boolean obrigatorio = Boolean.parseBoolean(agente);
                    req.setAttribute("obrigatorio", obrigatorio);
                } else {
                    agenteMsg = messages.getString(agenteMsg);
                    req.setAttribute("agenteMsg", agenteMsg);
                    isValid = false;
                    
                }
            } else {
                agenteMsg = messages.getString(agenteMsg);
                req.setAttribute("agenteMsg", agenteMsg);
                isValid = false;
                
            }

            /**
             * Validação do CNPJ da empresa usando os métodos da Classe
             * ValidaUtils Campo obrigatório; Tamanho de 14 caracteres; CNPJ
             * repetido.
             */
            String cnpjMsg = "";
            tamanho = 14;
            cnpjMsg = ValidaUtils.validaObrigatorio("CNPJ", cnpj);
            if (cnpjMsg.trim().isEmpty()) {
                //remove caracteres especiais antes de vazer a validação numérica do CNPJ
                cnpj = cnpj.replaceAll("[.|/|-]", "");
                cnpjMsg = ValidaUtils.validaInteger("CNPJ", cnpj);
                if (cnpjMsg.trim().isEmpty()) {
                    cnpjMsg = ValidaUtils.validaTamanhoExato("CNPJ", tamanho, cnpj);
                    if (cnpjMsg.trim().isEmpty()) {
                        Convenio e = ConvenioServices.buscarConvenioByCpf_Cnpj(cnpj);
                        if (e == null) {
                            if (!(cnpjMsg.trim().isEmpty())) {
                                req.setAttribute("cnpj", cnpj);
                            }
                        } else {
                            cnpjMsg = messages.getString("br.cefetrj.sisgee.validar_cadastro_convenio_servlet.msg_empresa_duplicada");
                            req.setAttribute("cnpjMsg", cnpjMsg);
                            isValid = false;
                            
                        }
                    } else {
                        cnpjMsg = messages.getString(cnpjMsg);
                        cnpjMsg = ServletUtils.mensagemFormatada(cnpjMsg, locale, tamanho);
                        req.setAttribute("cnpjMsg", cnpjMsg);
                        isValid = false;
                        
                    }
                } else {
                    cnpjMsg = messages.getString(cnpjMsg);
                    req.setAttribute("cnpjMsg", cnpjMsg);
                    isValid = false;
                    
                }
            } else {
                cnpjMsg = messages.getString(cnpjMsg);
                req.setAttribute("cnpjMsg", cnpjMsg);
                isValid = false;
                
            }

            /**
             * Validação da Razão Social do Cadastro Empresa usando mÃ©todos da
             * Classe ValidaUtils. Campo obrigatório; Tamanho máximo de 100
             * caracteres; Razão Social já existente.
             */
            String razaoSocialMsg = "";
            tamanho=100;
            razaoSocialMsg = ValidaUtils.validaObrigatorio("Razão Social", razaoSocial);
            if (razaoSocialMsg.trim().isEmpty()) {
                razaoSocialMsg = ValidaUtils.validaTamanho("Razão Social", tamanho, razaoSocial);
                if (razaoSocialMsg.trim().isEmpty()) {
                    Convenio e = ConvenioServices.buscarConvenioByNomeConveniado(razaoSocial);
                    if (e == null) {
                        req.setAttribute("razaoSocial", razaoSocial);
                    } else {
                        razaoSocialMsg = messages.getString("br.cefetrj.sisgee.validar_cadastro_convenio_servlet.msg_empresa_duplicada");
                        req.setAttribute("razaoSocialMsg", razaoSocialMsg);
                        isValid = false;
                        
                    }
                } else {
                    razaoSocialMsg = messages.getString(razaoSocialMsg);
                    razaoSocialMsg = ServletUtils.mensagemFormatada(razaoSocialMsg, locale, tamanho);
                    req.setAttribute("razaoSocialMsg", razaoSocialMsg);
                    isValid = false;
                    
                }
            } else {
                razaoSocialMsg = messages.getString(razaoSocialMsg);
                req.setAttribute("razaoSocialMsg", razaoSocialMsg);
                isValid = false;
            }

            /**
             * Validação do campo Pessoa de Contato usando os métodos da Classe
             * ValidaUtils Campo não obrigatório; Tamanho máximo de 150 caracteres.
             */
            String pessoaContatoMsg = "";
            tamanho = 50;
                pessoaContatoMsg = ValidaUtils.validaTamanho("Pessoa de Contato", tamanho, pessoaContato);
                if (pessoaContatoMsg.trim().isEmpty()) {
                    req.setAttribute("Pessoa de Contato", pessoaContato);
                } else {
                    pessoaContatoMsg = messages.getString(pessoaContatoMsg);
                    pessoaContatoMsg = ServletUtils.mensagemFormatada(pessoaContatoMsg, locale, tamanho);
                    req.setAttribute("pessoaContatoMsg", pessoaContatoMsg);
                    isValid = false;
                }
            
        } else {
            
            
            
            /**
             * Validação do CPF da pessoa usando os métodos da Classe
             * ValidaUtils Campo obrigatório; Tamanho de 11 caracteres; CPF
             * repetido.
             */
            String cpfMsg = "";
            tamanho = 11;
            cpf = ValidaUtils.validaObrigatorio("CPF", cpf);
            if (cpfMsg.trim().isEmpty()) {
                //remove caracteres especiais antes de vazer a validação numérica do CPF
                cpf = cpf.replaceAll("[.|-]", "");
                cpfMsg = ValidaUtils.validaInteger("CPF", cpf);
                if (cpfMsg.trim().isEmpty()) {
                    cpfMsg = ValidaUtils.validaTamanhoExato("CPF", tamanho, cpf);
                    if (cpfMsg.trim().isEmpty()) {
                        Convenio e = ConvenioServices.buscarConvenioByCpf_Cnpj(cpf);
                        if (e == null) {
                            req.setAttribute("cpf", cpf);
                        } else {
                            cpfMsg = messages.getString("br.cefetrj.sisgee.validar_cadastro_convenio_servlet.msg_pessoa_duplicada");
                            req.setAttribute("cpfMsg", cpfMsg);
                            isValid = false;
                        }
                    } else {
                        cpfMsg = messages.getString(cpfMsg);
                        cpfMsg = ServletUtils.mensagemFormatada(cpfMsg, locale, tamanho);
                        req.setAttribute("cpfMsg", cpfMsg);
                        isValid = false;
                    }
                } else {
                    cpfMsg = messages.getString(cpfMsg);
                    req.setAttribute("cpfMsg", cpfMsg);
                    isValid = false;
                }
            } else {
                cpfMsg = messages.getString(cpfMsg);
                req.setAttribute("cpfMsg", cpfMsg);
                isValid = false;
            }

            /**
             * Validação do campo nome usando os métodos da Classe ValidaUtils
             * Campo obrigatório; Tamanho máximo de 150 caracteres.
             */
            String nomeMsg = "";
            tamanho = 100;
            nomeMsg = ValidaUtils.validaObrigatorio("Nome", nome);
            if (nomeMsg.trim().isEmpty()) {
                nomeMsg = ValidaUtils.validaTamanho("Nome", tamanho, nome);
                if (nomeMsg.trim().isEmpty()) {
                    req.setAttribute("Nome", nome);
                } else {
                    nomeMsg = messages.getString(nomeMsg);
                    nomeMsg = ServletUtils.mensagemFormatada(nomeMsg, locale, tamanho);
                    req.setAttribute("nomeMsg", nomeMsg);
                    isValid = false;
                }
            } else {
                nomeMsg = messages.getString(nomeMsg);
                req.setAttribute("nomeMsg", nomeMsg);
                isValid = false;
            }

        }

        /**
             * Validação do campo Data de Assinatura usando os métodos da Classe
             * ValidaUtils Campo obrigatório; 
             * Tipo Date.
             */
        Date dataAssinatura = null;
        String dataAssinaturaMsg = "";

        dataAssinaturaMsg = ValidaUtils.validaObrigatorio("Data de Assintura", dataAssinaturaConvenio);
        if (dataAssinaturaMsg.trim().isEmpty()) {
            dataAssinaturaMsg = ValidaUtils.validaDate("Data de Assintura", dataAssinaturaConvenio);
            if (dataAssinaturaMsg.trim().isEmpty()) {
                try {
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    dataAssinatura = format.parse(dataAssinaturaConvenio);
                    req.setAttribute("dataAssinatura", dataAssinatura);
                } catch (Exception e) {
                    //TODO trocar saída de console por Log
                    System.out.println("Data em formato incorreto, mesmo após validação na classe ValidaUtils");
                    isValid = false;
                }
            } else {
                dataAssinaturaMsg = messages.getString(dataAssinaturaMsg);
                req.setAttribute("dataInicioMsg", dataAssinaturaMsg);
                isValid = false;
                //TODO Fazer log
                System.out.println(dataAssinaturaMsg);
            }
        } else {
            dataAssinaturaMsg = messages.getString(dataAssinaturaMsg);
            req.setAttribute("dataAssinaturaMsg", dataAssinaturaMsg);
            isValid = false;
            //TODO Fazer log
            System.out.println(dataAssinaturaMsg);
        }
        
        
        /**
             * Validação do campo Email usando os métodos da Classe
             * ValidaUtils Campo não obrigatório; 
             * Tamanho máximo de 50 caracteres.
             */
            String emailMsg = "";
            tamanho = 50;
                emailMsg = ValidaUtils.validaTamanho("Email", tamanho, email);
                if (emailMsg.trim().isEmpty()) {
                    req.setAttribute("Email", email);
                } else {
                    emailMsg = messages.getString(emailMsg);
                    emailMsg = ServletUtils.mensagemFormatada(emailMsg, locale, tamanho);
                    req.setAttribute("emailMsg", emailMsg);
                    isValid = false;
                }
        
        /**
             * Validação do campo Telefone usando os métodos da Classe
             * ValidaUtils Campo não obrigatório; 
             * Tamanho máximo de 11 caracteres.
             */
            String telefoneMsg = "";
            tamanho = 11;
                telefone = telefone.replaceAll("[(|)|-]", "");
                telefoneMsg = ValidaUtils.validaTamanho("Telefone", tamanho, telefone);
                if (emailMsg.trim().isEmpty()) {
                    req.setAttribute("Telefone", telefone);
                } else {
                    telefoneMsg = messages.getString(telefoneMsg);
                    telefoneMsg = ServletUtils.mensagemFormatada(telefoneMsg, locale, tamanho);
                    req.setAttribute("telefoneMsg", telefoneMsg);
                    isValid = false;
                }

         
        if (isValid) {
            req.getRequestDispatcher("/IncluirCadastroConvenioServlet").forward(req, resp);
        } else {
            String msg = messages.getString("br.cefetrj.sisgee.validar_cadastro_convenio_servlet.msg_atencao");
            req.setAttribute("msg", msg);
            req.getRequestDispatcher("/form_convenio.jsp").forward(req, resp);

        }

    }

}
