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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author administrador
 */
@Entity
@Table(name = "users")
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_pk_id")
    private Integer userPkId;
    @Basic(optional = false)
    @Column(name = "user_name")
    private String userName;
    @Basic(optional = false)
    @Column(name = "user_password")
    private String userPassword;
    @Basic(optional = false)
    @Column(name = "user_status")
    private String userStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ajudFkUsersPkId")
    private List<Ajuda> ajudaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "acesFkUsersPkId")
    private List<Acesso> acessoList;
    @JoinColumn(name = "user_fk_authority_pk_id", referencedColumnName = "auth_pk_id")
    @ManyToOne(optional = false)
    private Authority userFkAuthorityPkId;

    public Users() {
    }

    public Users(Integer userPkId) {
        this.userPkId = userPkId;
    }

    public Users(Integer userPkId, String userName, String userPassword, String userStatus) {
        this.userPkId = userPkId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userStatus = userStatus;
    }

    public Integer getUserPkId() {
        return userPkId;
    }

    public void setUserPkId(Integer userPkId) {
        this.userPkId = userPkId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    @XmlTransient
    public List<Ajuda> getAjudaList() {
        return ajudaList;
    }

    public void setAjudaList(List<Ajuda> ajudaList) {
        this.ajudaList = ajudaList;
    }

    @XmlTransient
    public List<Acesso> getAcessoList() {
        return acessoList;
    }

    public void setAcessoList(List<Acesso> acessoList) {
        this.acessoList = acessoList;
    }

    public Authority getUserFkAuthorityPkId() {
        return userFkAuthorityPkId;
    }

    public void setUserFkAuthorityPkId(Authority userFkAuthorityPkId) {
        this.userFkAuthorityPkId = userFkAuthorityPkId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userPkId != null ? userPkId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.userPkId == null && other.userPkId != null) || (this.userPkId != null && !this.userPkId.equals(other.userPkId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return userName;
    }
    
}
