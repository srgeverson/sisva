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
@Table(name = "logacesso")
public class LogAcesso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "laces_pk_id")
    private Integer lacesPkId;
    @Column(name = "laces_id_tabela")
    private BigInteger lacesIdTabela;
    @Column(name = "laces_operacao")
    private String lacesOperacao;
    @Column(name = "laces_o_que_modificou")
    private String lacesOQueModificou;
    @Column(name = "laces_valor_antigo")
    private String lacesValorAntigo;
    @Column(name = "laces_valor_atual")
    private String lacesValorAtual;
    @Column(name = "laces_data_cadastrou")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lacesDataCadastrou;
    @Column(name = "laces_data_modificou")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lacesDataModificou;
    @Column(name = "laces_fk_users_pk_id")
    private Integer lacesFkUsersPkId;

    public LogAcesso() {
    }

    public LogAcesso(Integer lacesPkId) {
        this.lacesPkId = lacesPkId;
    }

    public Integer getLacesPkId() {
        return lacesPkId;
    }

    public void setLacesPkId(Integer lacesPkId) {
        this.lacesPkId = lacesPkId;
    }

    public BigInteger getLacesIdTabela() {
        return lacesIdTabela;
    }

    public void setLacesIdTabela(BigInteger lacesIdTabela) {
        this.lacesIdTabela = lacesIdTabela;
    }

    public String getLacesOperacao() {
        return lacesOperacao;
    }

    public void setLacesOperacao(String lacesOperacao) {
        this.lacesOperacao = lacesOperacao;
    }

    public String getLacesOQueModificou() {
        return lacesOQueModificou;
    }

    public void setLacesOQueModificou(String lacesOQueModificou) {
        this.lacesOQueModificou = lacesOQueModificou;
    }

    public String getLacesValorAntigo() {
        return lacesValorAntigo;
    }

    public void setLacesValorAntigo(String lacesValorAntigo) {
        this.lacesValorAntigo = lacesValorAntigo;
    }

    public String getLacesValorAtual() {
        return lacesValorAtual;
    }

    public void setLacesValorAtual(String lacesValorAtual) {
        this.lacesValorAtual = lacesValorAtual;
    }

    public Date getLacesDataCadastrou() {
        return lacesDataCadastrou;
    }

    public void setLacesDataCadastrou(Date lacesDataCadastrou) {
        this.lacesDataCadastrou = lacesDataCadastrou;
    }

    public Date getLacesDataModificou() {
        return lacesDataModificou;
    }

    public void setLacesDataModificou(Date lacesDataModificou) {
        this.lacesDataModificou = lacesDataModificou;
    }

    public Integer getLacesFkUsersPkId() {
        return lacesFkUsersPkId;
    }

    public void setLacesFkUsersPkId(Integer lacesFkUsersPkId) {
        this.lacesFkUsersPkId = lacesFkUsersPkId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lacesPkId != null ? lacesPkId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LogAcesso)) {
            return false;
        }
        LogAcesso other = (LogAcesso) object;
        if ((this.lacesPkId == null && other.lacesPkId != null) || (this.lacesPkId != null && !this.lacesPkId.equals(other.lacesPkId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.LogAcesso[ lacesPkId=" + lacesPkId + " ]";
    }
    
}
