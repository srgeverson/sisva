/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
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
 * @author administrador
 */
@Entity
@Table(name = "logauthority")
public class LogAuthority implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "lauth_pk_id")
    private Integer lauthPkId;
    @Column(name = "lauth_id_tabela")
    private BigInteger lauthIdTabela;
    @Column(name = "lauth_operacao")
    private String lauthOperacao;
    @Column(name = "lauth_o_que_modificou")
    private String lauthOQueModificou;
    @Column(name = "lauth_valor_antigo")
    private String lauthValorAntigo;
    @Column(name = "lauth_valor_atual")
    private String lauthValorAtual;
    @Column(name = "lauth_data_cadastrou")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lauthDataCadastrou;
    @Column(name = "lauth_data_modificou")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lauthDataModificou;

    public LogAuthority() {
    }

    public LogAuthority(Integer lauthPkId) {
        this.lauthPkId = lauthPkId;
    }

    public Integer getLauthPkId() {
        return lauthPkId;
    }

    public void setLauthPkId(Integer lauthPkId) {
        this.lauthPkId = lauthPkId;
    }

    public BigInteger getLauthIdTabela() {
        return lauthIdTabela;
    }

    public void setLauthIdTabela(BigInteger lauthIdTabela) {
        this.lauthIdTabela = lauthIdTabela;
    }

    public String getLauthOperacao() {
        return lauthOperacao;
    }

    public void setLauthOperacao(String lauthOperacao) {
        this.lauthOperacao = lauthOperacao;
    }

    public String getLauthOQueModificou() {
        return lauthOQueModificou;
    }

    public void setLauthOQueModificou(String lauthOQueModificou) {
        this.lauthOQueModificou = lauthOQueModificou;
    }

    public String getLauthValorAntigo() {
        return lauthValorAntigo;
    }

    public void setLauthValorAntigo(String lauthValorAntigo) {
        this.lauthValorAntigo = lauthValorAntigo;
    }

    public String getLauthValorAtual() {
        return lauthValorAtual;
    }

    public void setLauthValorAtual(String lauthValorAtual) {
        this.lauthValorAtual = lauthValorAtual;
    }

    public Date getLauthDataCadastrou() {
        return lauthDataCadastrou;
    }

    public void setLauthDataCadastrou(Date lauthDataCadastrou) {
        this.lauthDataCadastrou = lauthDataCadastrou;
    }

    public Date getLauthDataModificou() {
        return lauthDataModificou;
    }

    public void setLauthDataModificou(Date lauthDataModificou) {
        this.lauthDataModificou = lauthDataModificou;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lauthPkId != null ? lauthPkId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LogAuthority)) {
            return false;
        }
        LogAuthority other = (LogAuthority) object;
        if ((this.lauthPkId == null && other.lauthPkId != null) || (this.lauthPkId != null && !this.lauthPkId.equals(other.lauthPkId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.LogAuthority[ lauthPkId=" + lauthPkId + " ]";
    }
    
}
