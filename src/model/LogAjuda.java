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
@Table(name = "logajuda")
public class LogAjuda implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "lajud_pk_id")
    private Integer lajudPkId;
    @Column(name = "lajud_id_tabela")
    private BigInteger lajudIdTabela;
    @Column(name = "lajud_operacao")
    private String lajudOperacao;
    @Column(name = "lajud_o_que_modificou")
    private String lajudOQueModificou;
    @Column(name = "lajud_valor_antigo")
    private String lajudValorAntigo;
    @Column(name = "lajud_valor_atual")
    private String lajudValorAtual;
    @Column(name = "lajud_data_cadastrou")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lajudDataCadastrou;
    @Column(name = "lajud_data_modificou")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lajudDataModificou;
    @Column(name = "lajud_fk_users_pk_id")
    private Integer lajudFkUsersPkId;

    public LogAjuda() {
    }

    public LogAjuda(Integer lajudPkId) {
        this.lajudPkId = lajudPkId;
    }

    public Integer getLajudPkId() {
        return lajudPkId;
    }

    public void setLajudPkId(Integer lajudPkId) {
        this.lajudPkId = lajudPkId;
    }

    public BigInteger getLajudIdTabela() {
        return lajudIdTabela;
    }

    public void setLajudIdTabela(BigInteger lajudIdTabela) {
        this.lajudIdTabela = lajudIdTabela;
    }

    public String getLajudOperacao() {
        return lajudOperacao;
    }

    public void setLajudOperacao(String lajudOperacao) {
        this.lajudOperacao = lajudOperacao;
    }

    public String getLajudOQueModificou() {
        return lajudOQueModificou;
    }

    public void setLajudOQueModificou(String lajudOQueModificou) {
        this.lajudOQueModificou = lajudOQueModificou;
    }

    public String getLajudValorAntigo() {
        return lajudValorAntigo;
    }

    public void setLajudValorAntigo(String lajudValorAntigo) {
        this.lajudValorAntigo = lajudValorAntigo;
    }

    public String getLajudValorAtual() {
        return lajudValorAtual;
    }

    public void setLajudValorAtual(String lajudValorAtual) {
        this.lajudValorAtual = lajudValorAtual;
    }

    public Date getLajudDataCadastrou() {
        return lajudDataCadastrou;
    }

    public void setLajudDataCadastrou(Date lajudDataCadastrou) {
        this.lajudDataCadastrou = lajudDataCadastrou;
    }

    public Date getLajudDataModificou() {
        return lajudDataModificou;
    }

    public void setLajudDataModificou(Date lajudDataModificou) {
        this.lajudDataModificou = lajudDataModificou;
    }

    public Integer getLajudFkUsersPkId() {
        return lajudFkUsersPkId;
    }

    public void setLajudFkUsersPkId(Integer lajudFkUsersPkId) {
        this.lajudFkUsersPkId = lajudFkUsersPkId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lajudPkId != null ? lajudPkId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LogAjuda)) {
            return false;
        }
        LogAjuda other = (LogAjuda) object;
        if ((this.lajudPkId == null && other.lajudPkId != null) || (this.lajudPkId != null && !this.lajudPkId.equals(other.lajudPkId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.LogAjuda[ lajudPkId=" + lajudPkId + " ]";
    }
    
}
