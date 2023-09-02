package com.gabriel.bcimapi.model;

import java.io.Serializable;

import org.locationtech.jts.geom.MultiPolygon;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "lim_unidade_federacao_a", schema = "bcim")
public class UnidadeFederacao implements Serializable {
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

    @Column(name="sigla")
    private String sigla;

    @Column(name="geocodigo")
    private String geocodigo;

    //@Column(columnDefinition = "geometry(MultiPolygon,4326)")
	private MultiPolygon geom;

    @Column(columnDefinition = "geometry(MultiPolygon,4326)")
    public MultiPolygon getGeom() {
        return this.geom;
    }

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

    public UnidadeFederacao() {
    }

    public UnidadeFederacao(Long idObjeto, String nome, String nomeabrev, String geometriaaproximada, String sigla,
            String geocodigo, MultiPolygon geom, Integer idProdutor, Integer idElementoProdutor, Integer cdInsumoOrgao,
            Integer nrInsumoMes, Integer nrInsumoAno, String txInsumoDocumento) {
        this.idObjeto = idObjeto;
        this.nome = nome;
        this.nomeabrev = nomeabrev;
        this.geometriaaproximada = geometriaaproximada;
        this.sigla = sigla;
        this.geocodigo = geocodigo;
        this.geom = geom;
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

    public String getSigla() {
        return this.sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getGeocodigo() {
        return this.geocodigo;
    }

    public void setGeocodigo(String geocodigo) {
        this.geocodigo = geocodigo;
    }

    public void setGeom(MultiPolygon geom) {
        this.geom = geom;
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
    
}
