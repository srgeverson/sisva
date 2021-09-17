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
@Table(name = "logusers")
public class LogUsers implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "luser_pk_id")
    private Integer luserPkId;
    @Column(name = "luser_id_tabela")
    private BigInteger luserIdTabela;
    @Column(name = "luser_operacao")
    private String luserOperacao;
    @Column(name = "luser_o_que_modificou")
    private String luserOQueModificou;
    @Column(name = "luser_valor_antigo")
    private String luserValorAntigo;
    @Column(name = "luser_valor_atual")
    private String luserValorAtual;
    @Column(name = "luser_data_cadastrou")
    @Temporal(TemporalType.TIMESTAMP)
    private Date luserDataCadastrou;
    @Column(name = "luser_data_modificou")
    @Temporal(TemporalType.TIMESTAMP)
    private Date luserDataModificou;
    @Column(name = "luser_fk_users_pk_id")
    private Integer luserFkUsersPkId;

    public LogUsers() {
    }

    public LogUsers(Integer luserPkId) {
        this.luserPkId = luserPkId;
    }

    public Integer getLuserPkId() {
        return luserPkId;
    }

    public void setLuserPkId(Integer luserPkId) {
        this.luserPkId = luserPkId;
    }

    public BigInteger getLuserIdTabela() {
        return luserIdTabela;
    }

    public void setLuserIdTabela(BigInteger luserIdTabela) {
        this.luserIdTabela = luserIdTabela;
    }

    public String getLuserOperacao() {
        return luserOperacao;
    }

    public void setLuserOperacao(String luserOperacao) {
        this.luserOperacao = luserOperacao;
    }

    public String getLuserOQueModificou() {
        return luserOQueModificou;
    }

    public void setLuserOQueModificou(String luserOQueModificou) {
        this.luserOQueModificou = luserOQueModificou;
    }

    public String getLuserValorAntigo() {
        return luserValorAntigo;
    }

    public void setLuserValorAntigo(String luserValorAntigo) {
        this.luserValorAntigo = luserValorAntigo;
    }

    public String getLuserValorAtual() {
        return luserValorAtual;
    }

    public void setLuserValorAtual(String luserValorAtual) {
        this.luserValorAtual = luserValorAtual;
    }

    public Date getLuserDataCadastrou() {
        return luserDataCadastrou;
    }

    public void setLuserDataCadastrou(Date luserDataCadastrou) {
        this.luserDataCadastrou = luserDataCadastrou;
    }

    public Date getLuserDataModificou() {
        return luserDataModificou;
    }

    public void setLuserDataModificou(Date luserDataModificou) {
        this.luserDataModificou = luserDataModificou;
    }

    public Integer getLuserFkUsersPkId() {
        return luserFkUsersPkId;
    }

    public void setLuserFkUsersPkId(Integer luserFkUsersPkId) {
        this.luserFkUsersPkId = luserFkUsersPkId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (luserPkId != null ? luserPkId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LogUsers)) {
            return false;
        }
        LogUsers other = (LogUsers) object;
        if ((this.luserPkId == null && other.luserPkId != null) || (this.luserPkId != null && !this.luserPkId.equals(other.luserPkId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.LogUsers[ luserPkId=" + luserPkId + " ]";
    }
    
}
