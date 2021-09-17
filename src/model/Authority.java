/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author administrador
 */
@Entity
@Table(name = "authority")
public class Authority implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "auth_pk_id")
    private Integer authPkId;
    @Basic(optional = false)
    @Column(name = "auth_name")
    private String authName;
    @Basic(optional = false)
    @Column(name = "auth_descricao")
    private String authDescricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userFkAuthorityPkId")
    private List<Users> usersList;

    public Authority() {
    }

    public Authority(Integer authPkId) {
        this.authPkId = authPkId;
    }

    public Authority(Integer authPkId, String authName, String authDescricao) {
        this.authPkId = authPkId;
        this.authName = authName;
        this.authDescricao = authDescricao;
    }

    public Integer getAuthPkId() {
        return authPkId;
    }

    public void setAuthPkId(Integer authPkId) {
        this.authPkId = authPkId;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public String getAuthDescricao() {
        return authDescricao;
    }

    public void setAuthDescricao(String authDescricao) {
        this.authDescricao = authDescricao;
    }

    @XmlTransient
    public List<Users> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<Users> usersList) {
        this.usersList = usersList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (authPkId != null ? authPkId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Authority)) {
            return false;
        }
        Authority other = (Authority) object;
        if ((this.authPkId == null && other.authPkId != null) || (this.authPkId != null && !this.authPkId.equals(other.authPkId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return authName;
    }
    
}
