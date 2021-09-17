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
@Table(name = "teste")
public class Teste implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "test_pk_id")
    private Integer testPkId;
    @Basic(optional = false)
    @Column(name = "test_data")
    private String testData;
    @Basic(optional = false)
    @Column(name = "test_hora")
    private String testHora;
    @JoinColumn(name = "test_fk_users_pk_id", referencedColumnName = "user_pk_id")
    @ManyToOne(optional = false)
    private Users testFkUsersPkId;

    public Teste() {
    }

    public Teste(Integer testPkId) {
        this.testPkId = testPkId;
    }

    public Teste(Integer testPkId, String testData, String testHora) {
        this.testPkId = testPkId;
        this.testData = testData;
        this.testHora = testHora;
    }

    public Integer getTestPkId() {
        return testPkId;
    }

    public void setTestPkId(Integer testPkId) {
        this.testPkId = testPkId;
    }

    public String getTestData() {
        return testData;
    }

    public void setTestData(String testData) {
        this.testData = testData;
    }

    public String getTestHora() {
        return testHora;
    }

    public void setTestHora(String testHora) {
        this.testHora = testHora;
    }

    public Users getTestFkUsersPkId() {
        return testFkUsersPkId;
    }

    public void setTestFkUsersPkId(Users testFkUsersPkId) {
        this.testFkUsersPkId = testFkUsersPkId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (testPkId != null ? testPkId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Teste)) {
            return false;
        }
        Teste other = (Teste) object;
        if ((this.testPkId == null && other.testPkId != null) || (this.testPkId != null && !this.testPkId.equals(other.testPkId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Testso[ testPkId=" + testPkId + " ]";
    }
    
}
