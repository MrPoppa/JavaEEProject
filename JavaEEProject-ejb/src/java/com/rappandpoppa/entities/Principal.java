/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rappandpoppa.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Benjamin
 */
@Entity
@Table(name = "principal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Principal.findAll", query = "SELECT p FROM Principal p"),
    @NamedQuery(name = "Principal.findById", query = "SELECT p FROM Principal p WHERE p.id = :id"),
    @NamedQuery(name = "Principal.findByFirstName", query = "SELECT p FROM Principal p WHERE p.firstName = :firstName"),
    @NamedQuery(name = "Principal.findByLastName", query = "SELECT p FROM Principal p WHERE p.lastName = :lastName"),
    @NamedQuery(name = "Principal.findByGender", query = "SELECT p FROM Principal p WHERE p.gender = :gender"),
    @NamedQuery(name = "Principal.findByAge", query = "SELECT p FROM Principal p WHERE p.age = :age"),
    @NamedQuery(name = "Principal.findByUserName", query = "SELECT p FROM Principal p WHERE p.userName = :userName"),
    @NamedQuery(name = "Principal.findByPassword", query = "SELECT p FROM Principal p WHERE p.password = :password"),
    @NamedQuery(name = "Principal.findBySalary", query = "SELECT p FROM Principal p WHERE p.salary = :salary"),
    @NamedQuery(name = "Principal.findByDateHired", query = "SELECT p FROM Principal p WHERE p.dateHired = :dateHired")})
public class Principal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 32)
    @Column(name = "firstName")
    private String firstName;
    @Size(max = 32)
    @Column(name = "lastName")
    private String lastName;
    @Size(max = 16)
    @Column(name = "gender")
    private String gender;
    @Column(name = "age")
    private Integer age;
    @Size(max = 16)
    @Column(name = "userName")
    private String userName;
    @Size(max = 16)
    @Column(name = "password")
    private String password;
    @Column(name = "salary")
    private Integer salary;
    @Column(name = "dateHired")
    @Temporal(TemporalType.DATE)
    private Date dateHired;
    @JoinColumn(name = "contact_id", referencedColumnName = "id")
    @OneToOne(cascade = CascadeType.PERSIST)
    private ContactInformation contactInformation;

    public Principal() {
    }

    public Principal(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Date getDateHired() {
        return dateHired;
    }

    public void setDateHired(Date dateHired) {
        this.dateHired = dateHired;
    }

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Principal)) {
            return false;
        }
        Principal other = (Principal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rappandpoppa.entities.Principal[ id=" + id + " ]";
    }
    
}
