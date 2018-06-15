package br.cefetrj.sisgee.model.entity;

import java.io.Serializable;
import java.io.StringReader;
import java.util.Date;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.annotation.JsonbTransient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entidade que representa um Termo Estagio.
 * @author padu
 * @since 1.0
 */
@Entity
public class TermoEstagio implements Serializable, Comparable<TermoEstagio> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Integer idTermoEstagio;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataInicioTermoEstagio;
    
    @Temporal(TemporalType.DATE)
    private Date dataFimTermoEstagio;
    
    @Temporal(TemporalType.DATE)
    private Date dataRescisaoTermoEstagio;

    @Column(nullable = false)
    private Integer cargaHorariaTermoEstagio;

    @Column(nullable = false)
    private Float valorBolsa;

    @Column(length = 255, nullable = false)
    private String enderecoTermoEstagio;
    
    @Column(length = 100, nullable = true)
    private String complementoEnderecoTermoEstagio;

    @Column(length = 100, nullable = true)
    private String bairroEnderecoTermoEstagio;

    @Column(length = 8, nullable = true)
    private String cepEnderecoTermoEstagio;

    @Column(length = 100, nullable = false)
    private String cidadeEnderecoTermoEstagio;

    @Column(length = 2, nullable = false)
    private String estadoEnderecoTermoEstagio;

    @Column(nullable = false)
    private Boolean eEstagioObrigatorio;
    
    @Column(length = 100, nullable = false)
    private String nomeSupervisor;
    
    
    @Column(length = 100)
    private String cargoSupervisor;
    
    @Column(length = 50)
    private String motivoAditivo;
    
    @Column(nullable = false)
    private boolean eAtivo;
    
    @Column(length= 255)
    private String agenciada;
    
    @JsonbTransient
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Aluno aluno;

    @JsonbTransient
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Convenio convenio;

    @JsonbTransient
    @ManyToOne(fetch = FetchType.EAGER)
    private ProfessorOrientador professorOrientador;

    
    @JsonbTransient
    @ManyToOne(fetch = FetchType.EAGER)
    private TermoEstagio termoEstagioAditivo;
    
    @JsonbTransient
    @OneToMany(mappedBy = "termoEstagioAditivo")
    private List<TermoEstagio> termosAditivos;
    
    public TermoEstagio() {
    }

    public TermoEstagio(Date dataInicioTermoEstagio, Date dataFimTermoEstagio, Integer cargaHorariaTermoEstagio,
            Float valorBolsa, String enderecoTermoEstagio,
            String complementoEnderecoTermoEstagio, String bairroEnderecoTermoEstagio, String cepEnderecoTermoEstagio,
            String cidadeEnderecoTermoEstagio, String estadoEnderecoTermoEstagio, Boolean eEstagioObrigatorio, boolean eAtivo,
            Aluno aluno, Convenio convenio, ProfessorOrientador professorOrientador) {

        this.dataInicioTermoEstagio = dataInicioTermoEstagio;
        this.dataFimTermoEstagio = dataFimTermoEstagio;
        this.cargaHorariaTermoEstagio = cargaHorariaTermoEstagio;
        this.valorBolsa = valorBolsa;
        this.enderecoTermoEstagio = enderecoTermoEstagio;
        this.complementoEnderecoTermoEstagio = complementoEnderecoTermoEstagio;
        this.bairroEnderecoTermoEstagio = bairroEnderecoTermoEstagio;
        this.cepEnderecoTermoEstagio = cepEnderecoTermoEstagio;
        this.cidadeEnderecoTermoEstagio = cidadeEnderecoTermoEstagio;
        this.estadoEnderecoTermoEstagio = estadoEnderecoTermoEstagio;
        this.eEstagioObrigatorio = eEstagioObrigatorio;
        this.eAtivo = eAtivo;
        this.aluno = aluno;
        this.convenio = convenio;
        this.professorOrientador = professorOrientador;
    }

    public TermoEstagio(Date dataInicioTermoEstagio, Date dataFimTermoEstagio, Date dataRescisaoTermoEstagio, Integer cargaHorariaTermoEstagio, Float valorBolsa, String enderecoTermoEstagio,String complementoEnderecoTermoEstagio, String bairroEnderecoTermoEstagio, String cepEnderecoTermoEstagio, String cidadeEnderecoTermoEstagio, String estadoEnderecoTermoEstagio, Boolean eEstagioObrigatorio, String nomeSupervisor, String cargoSupervisor, boolean eAtivo, Aluno aluno, Convenio convenio, ProfessorOrientador professorOrientador) {
        this.dataInicioTermoEstagio = dataInicioTermoEstagio;
        this.dataFimTermoEstagio = dataFimTermoEstagio;
        this.dataRescisaoTermoEstagio = dataRescisaoTermoEstagio;
        this.cargaHorariaTermoEstagio = cargaHorariaTermoEstagio;
        this.valorBolsa = valorBolsa;
        this.enderecoTermoEstagio = enderecoTermoEstagio;
        this.complementoEnderecoTermoEstagio = complementoEnderecoTermoEstagio;
        this.bairroEnderecoTermoEstagio = bairroEnderecoTermoEstagio;
        this.cepEnderecoTermoEstagio = cepEnderecoTermoEstagio;
        this.cidadeEnderecoTermoEstagio = cidadeEnderecoTermoEstagio;
        this.estadoEnderecoTermoEstagio = estadoEnderecoTermoEstagio;
        this.eEstagioObrigatorio = eEstagioObrigatorio;
        this.nomeSupervisor = nomeSupervisor;
        this.cargoSupervisor = cargoSupervisor;
        this.eAtivo = eAtivo;
        this.aluno = aluno;
        this.convenio = convenio;
        this.professorOrientador = professorOrientador;
    }
    public TermoEstagio(Date dataInicioTermoEstagio, Date dataFimTermoEstagio, Date dataRescisaoTermoEstagio, Integer cargaHorariaTermoEstagio, Float valorBolsa, String enderecoTermoEstagio, String complementoEnderecoTermoEstagio, String bairroEnderecoTermoEstagio, String cepEnderecoTermoEstagio, String cidadeEnderecoTermoEstagio, String estadoEnderecoTermoEstagio, Boolean eEstagioObrigatorio, String nomeSupervisor, String cargoSupervisor, String motivoAditivo, boolean eAtivo, Aluno aluno, Convenio convenio, ProfessorOrientador professorOrientador,String agenciada) {
        this.dataInicioTermoEstagio = dataInicioTermoEstagio;
        this.dataFimTermoEstagio = dataFimTermoEstagio;
        this.dataRescisaoTermoEstagio = dataRescisaoTermoEstagio;
        this.cargaHorariaTermoEstagio = cargaHorariaTermoEstagio;
        this.valorBolsa = valorBolsa;
        this.enderecoTermoEstagio = enderecoTermoEstagio;
        this.complementoEnderecoTermoEstagio = complementoEnderecoTermoEstagio;
        this.bairroEnderecoTermoEstagio = bairroEnderecoTermoEstagio;
        this.cepEnderecoTermoEstagio = cepEnderecoTermoEstagio;
        this.cidadeEnderecoTermoEstagio = cidadeEnderecoTermoEstagio;
        this.estadoEnderecoTermoEstagio = estadoEnderecoTermoEstagio;
        this.eEstagioObrigatorio = eEstagioObrigatorio;
        this.nomeSupervisor = nomeSupervisor;
        this.cargoSupervisor = cargoSupervisor;
        this.motivoAditivo = motivoAditivo;
        this.eAtivo = eAtivo;
        this.aluno = aluno;
        this.convenio = convenio;
        this.professorOrientador = professorOrientador;
        this.agenciada=agenciada;
    }
    /**
     * Método para retornar o parametro idTermoEstagio.
     * @return idTermoEstagio.
     */
    public Integer getIdTermoEstagio() {
        return idTermoEstagio;
    }
    /**
     * Método para alterar o parametro idTermoEstagio.
     * @param idTermoEstagio Novo valor do parametro.
     */
    public void setIdTermoEstagio(Integer idTermoEstagio) {
        this.idTermoEstagio = idTermoEstagio;
    }
    /**
     * Método para retornar o parametro dataInicioTermoEstagio.
     * @return dataInicioTermoEstagio.
     */
    public Date getDataInicioTermoEstagio() {
        return dataInicioTermoEstagio;
    }
    /**
     * Método para alterar o parametro dataInicioTermoEstagio.
     * @param dataInicioTermoEstagio Novo valor do parametro.
     */
    public void setDataInicioTermoEstagio(Date dataInicioTermoEstagio) {
        this.dataInicioTermoEstagio = dataInicioTermoEstagio;
    }
    /**
     * Método para retornar o parametro dataFimTermoEstagio.
     * @return dataFimTermoEstagio.
     */
    public Date getDataFimTermoEstagio() {
        return dataFimTermoEstagio;
    }
    /**
     * Método para alterar o parametro dataFimTermoEstagio.
     * @param dataFimTermoEstagio Novo valor do parametro.
     */
    public void setDataFimTermoEstagio(Date dataFimTermoEstagio) {
        this.dataFimTermoEstagio = dataFimTermoEstagio;
    }
    /**
     * Método para retornar o parametro dataRescisaoTermoEstagio.
     * @return dataRescisaoTermoEstagio.
     */
    public Date getDataRescisaoTermoEstagio() {
        return dataRescisaoTermoEstagio;
    }
    /**
     * Método para alterar o parametro dataRescisaoTermoEstagio.
     * @param dataRescisaoTermoEstagio Novo valor do parametro.
     */
    public void setDataRescisaoTermoEstagio(Date dataRescisaoTermoEstagio) {
        this.dataRescisaoTermoEstagio = dataRescisaoTermoEstagio;
    }
    /**
     * Método para retornar o parametro cargaHorariaTermoEstagio.
     * @return cargaHorariaTermoEstagio.
     */
    public Integer getCargaHorariaTermoEstagio() {
        return cargaHorariaTermoEstagio;
    }
    /**
     * Método para alterar o parametro cargaHorariaTermoEstagio.
     * @param cargaHorariaTermoEstagio Novo valor do parametro.
     */
    public void setCargaHorariaTermoEstagio(Integer cargaHorariaTermoEstagio) {
        this.cargaHorariaTermoEstagio = cargaHorariaTermoEstagio;
    }
    /**
     * Método para retornar o parametro valorBolsa.
     * @return valorBolsa.
     */
    public Float getValorBolsa() {
        return valorBolsa;
    }
    /**
     * Método para alterar o parametro valorBolsa.
     * @param valorBolsa Novo valor do parametro.
     */
    public void setValorBolsa(Float valorBolsa) {
        this.valorBolsa = valorBolsa;
    }
    /**
     * Método para retornar o parametro enderecoTermoEstagio.
     * @return enderecoTermoEstagio.
     */
    public String getEnderecoTermoEstagio() {
        return enderecoTermoEstagio;
    }
    /**
     * Método para alterar o parametro enderecoTermoEstagio.
     * @param enderecoTermoEstagio Novo valor do parametro.
     */
    public void setEnderecoTermoEstagio(String enderecoTermoEstagio) {
        this.enderecoTermoEstagio = enderecoTermoEstagio;
    }

   
    /**
     * Método para retornar o parametro complementoEnderecoTermoEstagio.
     * @return complementoEnderecoTermoEstagio.
     */
    public String getComplementoEnderecoTermoEstagio() {
        return complementoEnderecoTermoEstagio;
    }
    /**
     * Método para alterar o parametro complementoEnderecoTermoEstagio.
     * @param complementoEnderecoTermoEstagio Novo valor do parametro.
     */
    public void setComplementoEnderecoTermoEstagio(String complementoEnderecoTermoEstagio) {
        this.complementoEnderecoTermoEstagio = complementoEnderecoTermoEstagio;
    }
    /**
     * Método para retornar o parametro bairroEnderecoTermoEstagio.
     * @return bairroEnderecoTermoEstagio.
     */
    public String getBairroEnderecoTermoEstagio() {
        return bairroEnderecoTermoEstagio;
    }
    /**
     * Método para alterar o parametro bairroEnderecoTermoEstagio.
     * @param bairroEnderecoTermoEstagio Novo valor do parametro.
     */
    public void setBairroEnderecoTermoEstagio(String bairroEnderecoTermoEstagio) {
        this.bairroEnderecoTermoEstagio = bairroEnderecoTermoEstagio;
    }
    /**
     * Método para retornar o parametro cepEnderecoTermoEstagio.
     * @return cepEnderecoTermoEstagio.
     */
    public String getCepEnderecoTermoEstagio() {
        return cepEnderecoTermoEstagio;
    }
    /**
     * Método para alterar o parametro cepEnderecoTermoEstagio.
     * @param cepEnderecoTermoEstagio Novo valor do parametro.
     */
    public void setCepEnderecoTermoEstagio(String cepEnderecoTermoEstagio) {
        this.cepEnderecoTermoEstagio = cepEnderecoTermoEstagio;
    }
    /**
     * Método para retornar o parametro cidadeEnderecoTermoEstagio.
     * @return cidadeEnderecoTermoEstagio.
     */
    public String getCidadeEnderecoTermoEstagio() {
        return cidadeEnderecoTermoEstagio;
    }
    /**
     * Método para alterar o parametro cidadeEnderecoTermoEstagio.
     * @param cidadeEnderecoTermoEstagio Novo valor do parametro.
     */
    public void setCidadeEnderecoTermoEstagio(String cidadeEnderecoTermoEstagio) {
        this.cidadeEnderecoTermoEstagio = cidadeEnderecoTermoEstagio;
    }
    /**
     * Método para retornar o parametro estadoEnderecoTermoEstagio.
     * @return estadoEnderecoTermoEstagio.
     */
    public String getEstadoEnderecoTermoEstagio() {
        return estadoEnderecoTermoEstagio;
    }
    /**
     * Método para alterar o parametro estadoEnderecoTermoEstagio.
     * @param estadoEnderecoTermoEstagio Novo valor do parametro.
     */
    public void setEstadoEnderecoTermoEstagio(String estadoEnderecoTermoEstagio) {
        this.estadoEnderecoTermoEstagio = estadoEnderecoTermoEstagio;
    }
    /**
     * Método para retornar o parametro eEstagioObrigatorio.
     * @return eEstagioObrigatorio.
     */
    public Boolean getEEstagioObrigatorio() {
        return eEstagioObrigatorio;
    }
    /**
     * Método para alterar o parametro eEstagioObrigatorio.
     * @param eEstagioObrigatorio Novo valor do parametro.
     */
    public void setEEstagioObrigatorio(Boolean eEstagioObrigatorio) {
        this.eEstagioObrigatorio = eEstagioObrigatorio;
    }
    /**
     * Método para retornar o parametro aluno.
     * @return aluno.
     */
    public Aluno getAluno() {
        return aluno;
    }
    /**
     * Método para alterar o parametro aluno.
     * @param aluno Novo valor do parametro.
     */
    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
    /**
     * Método para retornar o parametro convenio.
     * @return convenio.
     */
    public Convenio getConvenio() {
        return convenio;
    }
    /**
     * Método para alterar o parametro convenio.
     * @param convenio Novo valor do parametro.
     */
    public void setConvenio(Convenio convenio) {
        this.convenio = convenio;
    }
    /**
     * Método para retornar o parametro professorOrientador.
     * @return professorOrientador.
     */
    public ProfessorOrientador getProfessorOrientador() {
        return professorOrientador;
    }
    /**
     * Método para alterar o parametro professorOrientador.
     * @param professorOrientador Novo valor do parametro.
     */
    public void setProfessorOrientador(ProfessorOrientador professorOrientador) {
        this.professorOrientador = professorOrientador;
    }

    /**
     * Método para retornar o parametro nomeSupervisor.
     * @return nomeSupervisor.
     */
    public String getNomeSupervisor() {
        return nomeSupervisor;
    }
    /**
     * Método para alterar o parametro nomeSupervisor.
     * @param nomeSupervisor Novo valor do parametro.
     */
    public void setNomeSupervisor(String nomeSupervisor) {
        this.nomeSupervisor = nomeSupervisor;
    }
    /**
     * Método para retornar o parametro cargoSupervisor.
     * @return cargoSupervisor.
     */
    public String getCargoSupervisor() {
        return cargoSupervisor;
    }
    /**
     * Método para alterar o parametro cargoSupervisor.
     * @param cargoSupervisor Novo valor do parametro.
     */
    public void setCargoSupervisor(String cargoSupervisor) {
        this.cargoSupervisor = cargoSupervisor;
    }
    /**
     * Método para retornar o parametro termoEstagioAditivo.
     * @return termoEstagioAditivo.
     */
    public TermoEstagio getTermoEstagioAditivo() {
        return termoEstagioAditivo;
    }
    /**
     * Método para alterar o parametro termoEstagioAditivo.
     * @param termoEstagioAditivo Novo valor do parametro.
     */
    public void setTermoEstagioAditivo(TermoEstagio termoEstagioAditivo) {
        this.termoEstagioAditivo = termoEstagioAditivo;
    }
    /**
     * Método para retornar o parametro termosAditivos.
     * @return termosAditivos.
     */
    public List<TermoEstagio> getTermosAditivos() {
        return termosAditivos;
    }
    /**
     * Método para alterar o parametro termosAditivos.
     * @param termosAditivos Novo valor do parametro.
     */
    public void setTermosAditivos(List<TermoEstagio> termosAditivos) {
        this.termosAditivos = termosAditivos;
    }
    /**
     * Método para retornar o parametro motivoAditivo.
     * @return motivoAditivo.
     */
    public String getMotivoAditivo() {
        return motivoAditivo;
    }
    /**
     * Método para alterar o parametro motivoAditivo.
     * @param motivoAditivo Novo valor do parametro.
     */
    public void setMotivoAditivo(String motivoAditivo) {
        this.motivoAditivo = motivoAditivo;
    }

    public boolean getEAtivo() {
        return eAtivo;
    }

    public void setEAtivo(boolean eAtivo) {
        this.eAtivo = eAtivo;
    }
    
    public String getAgenciada() {
        return this.agenciada;
    }

    public void setAgenciada(String agenciada) {
        this.agenciada = agenciada;
    }
    
    /**
     * Método para retornar um hash para a Entidade TermoEstagio.
     * @return Um hash para TermoEstagio.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idTermoEstagio == null) ? 0 : idTermoEstagio.hashCode());
        return result;
    }
    /**
     * Método para comparação entre dois objetos.
     * @param obj Objeto a ser comparado.
     * @return True caso sejam o mesmo objeto, false caso não.
     */
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
        TermoEstagio other = (TermoEstagio) obj;
        if (idTermoEstagio == null) {
            if (other.idTermoEstagio != null) {
                return false;
            }
        } else if (!idTermoEstagio.equals(other.idTermoEstagio)) {
            return false;
        }
        return true;
    }
    /**
     * Método para retorna essa Entidade como um formato Json.
     * @return A Entidade em formato Json.
     */
    public String toJson() {
        Jsonb jsonb = JsonbBuilder.create();
        String result = jsonb.toJson(this);

        JsonReader jsonReader = Json.createReader(new StringReader(result));
        JsonObject jobj = jsonReader.readObject();

        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("convenio", this.getConvenio().toJsonObj());
        jobj.entrySet().
                forEach(e -> builder.add(e.getKey(), e.getValue()));
        return builder.build().toString();

    }

    @Override
    public int compareTo(TermoEstagio o) {
        return this.dataInicioTermoEstagio.compareTo(o.dataInicioTermoEstagio);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
