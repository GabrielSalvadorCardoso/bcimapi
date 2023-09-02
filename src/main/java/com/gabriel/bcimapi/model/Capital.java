package com.gabriel.bcimapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;
import org.locationtech.jts.geom.Point;
//@Data
@Entity
@Table(name = "loc_capital_p", schema = "bcim")
public class Capital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_objeto")
    private Long idObjeto;

    @Column(name="nome")
    private String nome;

    @Column(name="nomeabrev")
    private String nomeabrev;

    @Column(name="geometriaaproximada")
    private String geometriaaproximada;

    @Column(name="tipocapital")
    private String tipocapital;

    @Column(name="id_produtor")
    private Integer idProdutor;

    @Column(name="id_elementoprodutor")
    private Integer idElementoProdutor;

    @Column(name="cd_insumo_orgao")
    private Integer cdInsumoOrgao;
    
    @Column(name="nr_insumo_mes")
    private Integer nrInsumoMes;

    @Column(name="nr_insumo_ano")
    private Integer nrInsumoAno;

    @Column(name="tx_insumo_documento")
    private String txInsumoDocumento;

    //https://stackoverflow.com/questions/27624940/map-a-postgis-geometry-point-field-with-hibernate-on-spring-boot
    //@Column(columnDefinition = "geometry(Point, 4326)")
	private Point geom;
    //@JsonSerialize(using = GeometrySerializer.class)
    //@JsonDeserialize(using = GeometryDeserializer.class)
    //@Column(name = "geom", columnDefinition = "Geometry")
    @Column(columnDefinition = "geometry(Point,4326)")
    public Point getGeom() {
        return this.geom;
    }




    public Capital() {
    }

    public Capital(Long idObjeto, String nome, String nomeabrev, String geometriaaproximada, String tipocapital, Integer idProdutor, Integer idElementoProdutor, Integer cdInsumoOrgao, Integer nrInsumoMes, Integer nrInsumoAno, String txInsumoDocumento) {
        this.idObjeto = idObjeto;
        this.nome = nome;
        this.nomeabrev = nomeabrev;
        this.geometriaaproximada = geometriaaproximada;
        this.tipocapital = tipocapital;
        this.idProdutor = idProdutor;
        this.idElementoProdutor = idElementoProdutor;
        this.cdInsumoOrgao = cdInsumoOrgao;
        this.nrInsumoMes = nrInsumoMes;
        this.nrInsumoAno = nrInsumoAno;
        this.txInsumoDocumento = txInsumoDocumento;
    }

    public Long getIdObjeto() {
        return this.idObjeto;
    }

    

    public void setIdObjeto(Long idObjeto) {
        this.idObjeto = idObjeto;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeabrev() {
        return this.nomeabrev;
    }

    public void setNomeabrev(String nomeabrev) {
        this.nomeabrev = nomeabrev;
    }

    public String getGeometriaaproximada() {
        return this.geometriaaproximada;
    }

    public void setGeometriaaproximada(String geometriaaproximada) {
        this.geometriaaproximada = geometriaaproximada;
    }

    public String getTipocapital() {
        return this.tipocapital;
    }

    public void setTipocapital(String tipocapital) {
        this.tipocapital = tipocapital;
    }

    public Integer getIdProdutor() {
        return this.idProdutor;
    }

    public void setIdProdutor(Integer idProdutor) {
        this.idProdutor = idProdutor;
    }

    public Integer getIdElementoProdutor() {
        return this.idElementoProdutor;
    }

    public void setIdElementoProdutor(Integer idElementoProdutor) {
        this.idElementoProdutor = idElementoProdutor;
    }

    public Integer getCdInsumoOrgao() {
        return this.cdInsumoOrgao;
    }

    public void setCdInsumoOrgao(Integer cdInsumoOrgao) {
        this.cdInsumoOrgao = cdInsumoOrgao;
    }

    public Integer getNrInsumoMes() {
        return this.nrInsumoMes;
    }

    public void setNrInsumoMes(Integer nrInsumoMes) {
        this.nrInsumoMes = nrInsumoMes;
    }

    public Integer getNrInsumoAno() {
        return this.nrInsumoAno;
    }

    public void setNrInsumoAno(Integer nrInsumoAno) {
        this.nrInsumoAno = nrInsumoAno;
    }

    public String getTxInsumoDocumento() {
        return this.txInsumoDocumento;
    }

    public void setTxInsumoDocumento(String txInsumoDocumento) {
        this.txInsumoDocumento = txInsumoDocumento;
    }

    public Capital idObjeto(Long idObjeto) {
        setIdObjeto(idObjeto);
        return this;
    }

    public Capital nome(String nome) {
        setNome(nome);
        return this;
    }

    public Capital nomeabrev(String nomeabrev) {
        setNomeabrev(nomeabrev);
        return this;
    }

    public Capital geometriaaproximada(String geometriaaproximada) {
        setGeometriaaproximada(geometriaaproximada);
        return this;
    }

    public Capital tipocapital(String tipocapital) {
        setTipocapital(tipocapital);
        return this;
    }

    public Capital idProdutor(Integer idProdutor) {
        setIdProdutor(idProdutor);
        return this;
    }

    public Capital idElementoProdutor(Integer idElementoProdutor) {
        setIdElementoProdutor(idElementoProdutor);
        return this;
    }

    public Capital cdInsumoOrgao(Integer cdInsumoOrgao) {
        setCdInsumoOrgao(cdInsumoOrgao);
        return this;
    }

    public Capital nrInsumoMes(Integer nrInsumoMes) {
        setNrInsumoMes(nrInsumoMes);
        return this;
    }

    public Capital nrInsumoAno(Integer nrInsumoAno) {
        setNrInsumoAno(nrInsumoAno);
        return this;
    }

    public Capital txInsumoDocumento(String txInsumoDocumento) {
        setTxInsumoDocumento(txInsumoDocumento);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Capital)) {
            return false;
        }
        Capital capital = (Capital) o;
        return Objects.equals(idObjeto, capital.idObjeto) && Objects.equals(nome, capital.nome) && Objects.equals(nomeabrev, capital.nomeabrev) && Objects.equals(geometriaaproximada, capital.geometriaaproximada) && Objects.equals(tipocapital, capital.tipocapital) && Objects.equals(idProdutor, capital.idProdutor) && Objects.equals(idElementoProdutor, capital.idElementoProdutor) && Objects.equals(cdInsumoOrgao, capital.cdInsumoOrgao) && Objects.equals(nrInsumoMes, capital.nrInsumoMes) && Objects.equals(nrInsumoAno, capital.nrInsumoAno) && Objects.equals(txInsumoDocumento, capital.txInsumoDocumento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idObjeto, nome, nomeabrev, geometriaaproximada, tipocapital, idProdutor, idElementoProdutor, cdInsumoOrgao, nrInsumoMes, nrInsumoAno, txInsumoDocumento);
    }

    @Override
    public String toString() {
        return "{" +
            " idObjeto='" + getIdObjeto() + "'" +
            ", nome='" + getNome() + "'" +
            ", nomeabrev='" + getNomeabrev() + "'" +
            ", geometriaaproximada='" + getGeometriaaproximada() + "'" +
            ", tipocapital='" + getTipocapital() + "'" +
            ", idProdutor='" + getIdProdutor() + "'" +
            ", idElementoProdutor='" + getIdElementoProdutor() + "'" +
            ", cdInsumoOrgao='" + getCdInsumoOrgao() + "'" +
            ", nrInsumoMes='" + getNrInsumoMes() + "'" +
            ", nrInsumoAno='" + getNrInsumoAno() + "'" +
            ", txInsumoDocumento='" + getTxInsumoDocumento() + "'" +
            "}";
    }
    
}
