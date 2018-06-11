package br.cefetrj.sisgee.view.termoaditivo;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.sisgee.control.AlunoServices;
import br.cefetrj.sisgee.control.ProfessorOrientadorServices;
import br.cefetrj.sisgee.control.TermoAditivoServices;
import br.cefetrj.sisgee.model.entity.Aluno;
import br.cefetrj.sisgee.model.entity.ProfessorOrientador;
import br.cefetrj.sisgee.model.entity.TermoEstagio;
import br.cefetrj.sisgee.view.utils.ConvenioUtils;
import br.cefetrj.sisgee.view.utils.ServletUtils;
import br.cefetrj.sisgee.view.utils.TermoEstagioUtils;
import br.cefetrj.sisgee.view.utils.UF;
import br.cefetrj.sisgee.view.utils.ValidaUtils;

@WebServlet("/TermoAditivoServlet")
public class TermoAditivoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Locale locale = ServletUtils.getLocale(request);
        ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);
        String vigencia = request.getParameter("vigencia");
        String endereco = request.getParameter("endereco");
        String carga = request.getParameter("cargaHoraria");
        String professor = request.getParameter("professor");
        String valorBolsa = request.getParameter("valor");
        String supervisor = request.getParameter("supervisor");
        String idAluno = request.getParameter("idAluno");

        Aluno aluno = null;
        TermoEstagio termoEstagio = null;

        boolean isValid = true;
        String campo = "";
        String msg = "";

        /**
         * Validação do Id do Aluno, usando métodos da Classe ValidaUtils.
         * Instanciando o objeto e pegando o TermoEstagio válido (sem data de
         * rescisão)
         */
        String idAlunoMsg = "";
        campo = "Aluno";
        idAlunoMsg = ValidaUtils.validaObrigatorio(campo, idAluno);
        if (idAlunoMsg.trim().isEmpty()) {
            idAlunoMsg = ValidaUtils.validaInteger(campo, idAluno);
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
                    idAlunoMsg = messages.getString("br.cefetrj.sisgee.incluir_termo_aditivo_servlet.msg_AlunoEscolhido");
                    msg = idAlunoMsg;
                    request.setAttribute("idAlunoMsg", idAlunoMsg);
                }

            } else {
                idAlunoMsg = messages.getString(idAlunoMsg);
                msg = idAlunoMsg;
                request.setAttribute("idAlunoMsg", idAlunoMsg);
                isValid = false;
            }
        } else {
            idAlunoMsg = messages.getString(idAlunoMsg);
            msg = idAlunoMsg;
            request.setAttribute("idAlunoMsg", idAlunoMsg);
            isValid = false;
        }

        if (vigencia == null && endereco == null && carga == null && professor == null && valorBolsa == null && supervisor == null) {
            msg = messages.getString("br.cefetrj.sisgee.resources.form.consultar.termo.motivoAditivoInvalido");
            isValid = false;
        }

        if (termoEstagio != null) {
            //TODO implementar lógica de encaminhamento para a tela de registro
            //termosAditivos = termoEstagio.getTermosAditivos();
            //if (termosAditivos != null && !termosAditivos.isEmpty()) {
            //    termoAditivo = termosAditivos.get(termosAditivos.size() - 1);
            //}

            // se existe algum termo aditivo para o termo estagio
            //if (termoAditivo != null) {
            //    termoEstagio = TermoAditivoServices.termoEstagioAtualizadoByTermoAditivo(termoAditivo);
            //}
            List<ProfessorOrientador> professores = ProfessorOrientadorServices.listarProfessorOrientador();
            UF[] uf = UF.asList();
            
            request.setAttribute("termoEstagio", termoEstagio);
            
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
            request.setAttribute("isAgenteIntegracao", termoEstagio.getConvenio().getIsAgenteIntegracao());
            request.setAttribute("agencia", termoEstagio.getConvenio().getNomeConveniado());
            if (termoEstagio.getConvenio().getIsPessoaJuridica()) {
                request.setAttribute("CpfCnpj", ConvenioUtils.getCnpjEmpresaFormatado(termoEstagio.getConvenio().getCpf_cnpj()));
            }else {
                request.setAttribute("CpfCnpj", new ConvenioUtils().getCpfFormatado(termoEstagio.getConvenio().getCpf_cnpj()));
            }
            
            
            request.setAttribute("razaoSocial", termoEstagio.getConvenio().getNomeConveniado());
            //Datas
            request.setAttribute("dataInicioTermoEstagio", termoEstagio.getDataInicioTermoEstagio());
            request.setAttribute("dataFimTermoEstagio", termoEstagio.getDataFimTermoEstagio());
            
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
            request.setAttribute("estadoEnderecoTermoEstagio", termoEstagio.getEstadoEnderecoTermoEstagio());
            if (termoEstagio.getEEstagioObrigatorio()) {
                request.setAttribute("eEstagioObrigatorio", "sim");
            }else {
                request.setAttribute("eEstagioObrigatorio", "nao");
            }
            
            request.setAttribute("nomeSupervisor", termoEstagio.getNomeSupervisor());
            request.setAttribute("cargoSupervisor", termoEstagio.getCargoSupervisor());
            request.setAttribute("professorTermo", termoEstagio.getProfessorOrientador());
            professores.remove(termoEstagio.getProfessorOrientador());
            request.setAttribute("professores", professores);
            request.setAttribute("uf", uf);
            //request.setAttribute("professor", termoEstagio.getProfessorOrientador());

            request.setAttribute("updVigencia", vigencia);
            request.setAttribute("updCargaHoraria", carga);
            request.setAttribute("updProfessor", professor);
            request.setAttribute("updValorBolsa", valorBolsa);
            request.setAttribute("updEndereco", endereco);
            request.setAttribute("updSupervisor", supervisor);

        } else {
            msg = messages.getString("br.cefetrj.sisgee.form_termo_aditivo_servlet.msg_termo_estagio_invalido");
            request.setAttribute("msg", msg);
            isValid = false;
        }

        if (isValid) {
            request.getRequestDispatcher("/form_termo_estagio.jsp").forward(request, response);
        } else {
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("/form_consultar_termo.jsp").forward(request, response);
        }

    }
}
