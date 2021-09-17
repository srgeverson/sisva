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
@Table(name = "logteste")
public class LogTeste implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ltest_pk_id")
    private Integer ltestPkId;
    @Column(name = "ltest_id_tabela")
    private BigInteger ltestIdTabela;
    @Column(name = "ltest_operacao")
    private String ltestOperacao;
    @Column(name = "ltest_o_que_modificou")
    private String ltestOQueModificou;
    @Column(name = "ltest_valor_antigo")
    private String ltestValorAntigo;
    @Column(name = "ltest_valor_atual")
    private String ltestValorAtual;
    @Column(name = "ltest_data_cadastrou")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ltestDataCadastrou;
    @Column(name = "ltest_data_modificou")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ltestDataModificou;
    @Column(name = "ltest_fk_users_pk_id")
    private Integer ltestFkUsersPkId;

    public LogTeste() {
    }

    public LogTeste(Integer ltestPkId) {
        this.ltestPkId = ltestPkId;
    }

    public Integer getLtestPkId() {
        return ltestPkId;
    }

    public void setLtestPkId(Integer ltestPkId) {
        this.ltestPkId = ltestPkId;
    }

    public BigInteger getLtestIdTabela() {
        return ltestIdTabela;
    }

    public void setLtestIdTabela(BigInteger ltestIdTabela) {
        this.ltestIdTabela = ltestIdTabela;
    }

    public String getLtestOperacao() {
        return ltestOperacao;
    }

    public void setLtestOperacao(String ltestOperacao) {
        this.ltestOperacao = ltestOperacao;
    }

    public String getLtestOQueModificou() {
        return ltestOQueModificou;
    }

    public void setLtestOQueModificou(String ltestOQueModificou) {
        this.ltestOQueModificou = ltestOQueModificou;
    }

    public String getLtestValorAntigo() {
        return ltestValorAntigo;
    }

    public void setLtestValorAntigo(String ltestValorAntigo) {
        this.ltestValorAntigo = ltestValorAntigo;
    }

    public String getLtestValorAtual() {
        return ltestValorAtual;
    }

    public void setLtestValorAtual(String ltestValorAtual) {
        this.ltestValorAtual = ltestValorAtual;
    }

    public Date getLtestDataCadastrou() {
        return ltestDataCadastrou;
    }

    public void setLtestDataCadastrou(Date ltestDataCadastrou) {
        this.ltestDataCadastrou = ltestDataCadastrou;
    }

    public Date getLtestDataModificou() {
        return ltestDataModificou;
    }

    public void setLtestDataModificou(Date ltestDataModificou) {
        this.ltestDataModificou = ltestDataModificou;
    }

    public Integer getLtestFkUsersPkId() {
        return ltestFkUsersPkId;
    }

    public void setLtestFkUsersPkId(Integer ltestFkUsersPkId) {
        this.ltestFkUsersPkId = ltestFkUsersPkId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ltestPkId != null ? ltestPkId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LogTeste)) {
            return false;
        }
        LogTeste other = (LogTeste) object;
        if ((this.ltestPkId == null && other.ltestPkId != null) || (this.ltestPkId != null && !this.ltestPkId.equals(other.ltestPkId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.LogTeste[ ltestPkId=" + ltestPkId + " ]";
    }
    
}
