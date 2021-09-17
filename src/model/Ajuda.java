/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author administrador
 */
@Entity
@Table(name = "ajuda")
public class Ajuda implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ajud_pk_id")
    private Integer ajudPkId;
    @Basic(optional = false)
    @Column(name = "ajud_descricao")
    private String ajudDescricao;
    @Column(name = "ajud_status")
    private boolean ajudStatus;
    @JoinColumn(name = "ajud_fk_users_pk_id", referencedColumnName = "user_pk_id")
    @ManyToOne(optional = false)
    private Users ajudFkUsersPkId;

    public Ajuda() {
    }

    public Ajuda(Integer ajudPkId) {
        this.ajudPkId = ajudPkId;
    }

    public Ajuda(Integer ajudPkId, String ajudDescricao, boolean ajudStatus) {
        this.ajudPkId = ajudPkId;
        this.ajudDescricao = ajudDescricao;
        this.ajudStatus = ajudStatus;
    }

    public Integer getAjudPkId() {
        return ajudPkId;
    }

    public void setAjudPkId(Integer ajudPkId) {
        this.ajudPkId = ajudPkId;
    }

    public String getAjudDescricao() {
        return ajudDescricao;
    }

    public void setAjudDescricao(String ajudDescricao) {
        this.ajudDescricao = ajudDescricao;
    }

    public boolean getAjudStatus() {
        return ajudStatus;
    }

    public void setAjudStatus(boolean ajudStatus) {
        this.ajudStatus = ajudStatus;
    }

    public Users getAjudFkUsersPkId() {
        return ajudFkUsersPkId;
    }

    public void setAjudFkUsersPkId(Users ajudFkUsersPkId) {
        this.ajudFkUsersPkId = ajudFkUsersPkId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ajudPkId != null ? ajudPkId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ajuda)) {
            return false;
        }
        Ajuda other = (Ajuda) object;
        if ((this.ajudPkId == null && other.ajudPkId != null) || (this.ajudPkId != null && !this.ajudPkId.equals(other.ajudPkId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Ajuda[ ajudPkId=" + ajudPkId + " ]";
    }
    
}
