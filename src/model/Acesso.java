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
@Table(name = "acesso")
public class Acesso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "aces_pk_id")
    private Integer acesPkId;
    @Basic(optional = false)
    @Column(name = "aces_data")
    private String acesData;
    @Basic(optional = false)
    @Column(name = "aces_hora")
    private String acesHora;
    @JoinColumn(name = "aces_fk_users_pk_id", referencedColumnName = "user_pk_id")
    @ManyToOne(optional = false)
    private Users acesFkUsersPkId;

    public Acesso() {
    }

    public Acesso(Integer acesPkId) {
        this.acesPkId = acesPkId;
    }

    public Acesso(Integer acesPkId, String acesData, String acesHora) {
        this.acesPkId = acesPkId;
        this.acesData = acesData;
        this.acesHora = acesHora;
    }

    public Integer getAcesPkId() {
        return acesPkId;
    }

    public void setAcesPkId(Integer acesPkId) {
        this.acesPkId = acesPkId;
    }

    public String getAcesData() {
        return acesData;
    }

    public void setAcesData(String acesData) {
        this.acesData = acesData;
    }

    public String getAcesHora() {
        return acesHora;
    }

    public void setAcesHora(String acesHora) {
        this.acesHora = acesHora;
    }

    public Users getAcesFkUsersPkId() {
        return acesFkUsersPkId;
    }

    public void setAcesFkUsersPkId(Users acesFkUsersPkId) {
        this.acesFkUsersPkId = acesFkUsersPkId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (acesPkId != null ? acesPkId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Acesso)) {
            return false;
        }
        Acesso other = (Acesso) object;
        if ((this.acesPkId == null && other.acesPkId != null) || (this.acesPkId != null && !this.acesPkId.equals(other.acesPkId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Acesso[ acesPkId=" + acesPkId + " ]";
    }
    
}
