/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author geverson
 */
@Entity
@Table(name = "error")
public class Erro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "erro_pk_id")
    private Integer erroPkId;
    @Basic(optional = false)
    @Column(name = "erro_descricao")
    private String erroDescricao;
    @Basic(optional = false)
    @Column(name = "erro_usuario")
    private String erroUsuario;
    @Basic(optional = false)
    @Column(name = "erro_maquina")
    private String erroMaquina;
    @Basic(optional = false)
    @Column(name = "erro_ip_local")
    private String erroIpLocal;
    @Basic(optional = false)
    @Column(name = "erro_ip_externo")
    private String erroIpExterno;
    @Column(name = "erro_observacao")
    private String erroObservacao;

    public Erro() {
    }

    public Erro(Integer erroPkId) {
        this.erroPkId = erroPkId;
    }

    public Erro(Integer erroPkId, String erroDescricao, String erroUsuario, String erroMaquina, String erroIpLocal, String erroIpExterno, String erroObservacao) {
        this.erroPkId = erroPkId;
        this.erroDescricao = erroDescricao;
        this.erroUsuario = erroUsuario;
        this.erroMaquina = erroMaquina;
        this.erroIpLocal = erroIpLocal;
        this.erroIpExterno = erroIpExterno;
        this.erroObservacao = erroObservacao;
    }

    public Integer getErroPkId() {
        return erroPkId;
    }

    public void setErroPkId(Integer erroPkId) {
        this.erroPkId = erroPkId;
    }

    public String getErroDescricao() {
        return erroDescricao;
    }

    public void setErroDescricao(String erroDescricao) {
        this.erroDescricao = erroDescricao;
    }

    public String getErroUsuario() {
        return erroUsuario;
    }

    public void setErroUsuario(String erroUsuario) {
        this.erroUsuario = erroUsuario;
    }

    public String getErroMaquina() {
        return erroMaquina;
    }

    public void setErroMaquina(String erroMaquina) {
        this.erroMaquina = erroMaquina;
    }

    public String getErroIpLocal() {
        return erroIpLocal;
    }

    public void setErroIpLocal(String erroIpLocal) {
        this.erroIpLocal = erroIpLocal;
    }

    public String getErroIpExterno() {
        return erroIpExterno;
    }

    public void setErroIpExterno(String erroIpExterno) {
        this.erroIpExterno = erroIpExterno;
    }

    public String getErroObservacao() {
        return erroObservacao;
    }

    public void setErroObservacao(String erroObservacao) {
        this.erroObservacao = erroObservacao;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.erroPkId);
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
        final Erro other = (Erro) obj;
        if (!Objects.equals(this.erroPkId, other.erroPkId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return erroDescricao;
    }

}
