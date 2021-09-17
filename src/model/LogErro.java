/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author geverson
 */
@Entity
@Table(name = "logerror")
public class LogErro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "lerro_pk_id")
    private Integer lerroPkId;
    @Column(name = "lerro_id_tabela")
    private BigInteger lerroIdTabela;
    @Column(name = "lerro_operacao")
    private String lerroOperacao;
    @Column(name = "lerro_o_que_modificou")
    private String lerroOQueModificou;
    @Column(name = "lerro_valor_atual")
    private String lerroValorAntigo;
    @Column(name = "lauth_valor_atual")
    private String lerroValorAtual;
    @Column(name = "lerro_data_cadastrou")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lerroDataCadastrou;
    @Column(name = "lerro_data_modificou")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lerroDataModificou;

    public LogErro() {
    }

    public LogErro(Integer lerroPkId) {
        this.lerroPkId = lerroPkId;
    }

    public LogErro(Integer lerroPkId, BigInteger lerroIdTabela, String lerroOperacao, String lerroOQueModificou, String lerroValorAntigo, String lerroValorAtual, Date lerroDataCadastrou, Date lerroDataModificou) {
        this.lerroPkId = lerroPkId;
        this.lerroIdTabela = lerroIdTabela;
        this.lerroOperacao = lerroOperacao;
        this.lerroOQueModificou = lerroOQueModificou;
        this.lerroValorAntigo = lerroValorAntigo;
        this.lerroValorAtual = lerroValorAtual;
        this.lerroDataCadastrou = lerroDataCadastrou;
        this.lerroDataModificou = lerroDataModificou;
    }

    public Integer getLerroPkId() {
        return lerroPkId;
    }

    public void setLerroPkId(Integer lerroPkId) {
        this.lerroPkId = lerroPkId;
    }

    public BigInteger getLerroIdTabela() {
        return lerroIdTabela;
    }

    public void setLerroIdTabela(BigInteger lerroIdTabela) {
        this.lerroIdTabela = lerroIdTabela;
    }

    public String getLerroOperacao() {
        return lerroOperacao;
    }

    public void setLerroOperacao(String lerroOperacao) {
        this.lerroOperacao = lerroOperacao;
    }

    public String getLerroOQueModificou() {
        return lerroOQueModificou;
    }

    public void setLerroOQueModificou(String lerroOQueModificou) {
        this.lerroOQueModificou = lerroOQueModificou;
    }

    public String getLerroValorAntigo() {
        return lerroValorAntigo;
    }

    public void setLerroValorAntigo(String lerroValorAntigo) {
        this.lerroValorAntigo = lerroValorAntigo;
    }

    public String getLerroValorAtual() {
        return lerroValorAtual;
    }

    public void setLerroValorAtual(String lerroValorAtual) {
        this.lerroValorAtual = lerroValorAtual;
    }

    public Date getLerroDataCadastrou() {
        return lerroDataCadastrou;
    }

    public void setLerroDataCadastrou(Date lerroDataCadastrou) {
        this.lerroDataCadastrou = lerroDataCadastrou;
    }

    public Date getLerroDataModificou() {
        return lerroDataModificou;
    }

    public void setLerroDataModificou(Date lerroDataModificou) {
        this.lerroDataModificou = lerroDataModificou;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.lerroPkId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LogErro other = (LogErro) obj;
        if (!Objects.equals(this.lerroPkId, other.lerroPkId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LogErro{" + "lerroPkId= " + lerroPkId + '}';
    }

}
