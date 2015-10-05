/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rappandpoppa.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Anders
 */
@Entity
@Table(name = "administrator")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Administrator.loginControl", query = "SELECT a FROM Administrator a WHERE a.userName = :userName AND a.password = :password"),
    @NamedQuery(name = "Administrator.findAll", query = "SELECT a FROM Administrator a"),
    @NamedQuery(name = "Administrator.findById", query = "SELECT a FROM Administrator a WHERE a.id = :id"),
    @NamedQuery(name = "Administrator.findByFirstName", query = "SELECT a FROM Administrator a WHERE a.firstName = :firstName"),
    @NamedQuery(name = "Administrator.findByLastName", query = "SELECT a FROM Administrator a WHERE a.lastName = :lastName"),
    @NamedQuery(name = "Administrator.findByGender", query = "SELECT a FROM Administrator a WHERE a.gender = :gender"),
    @NamedQuery(name = "Administrator.findByAge", query = "SELECT a FROM Administrator a WHERE a.age = :age"),
    @NamedQuery(name = "Administrator.findByUserName", query = "SELECT a FROM Administrator a WHERE a.userName = :userName"),
    @NamedQuery(name = "Administrator.findByPassword", query = "SELECT a FROM Administrator a WHERE a.password = :password"),
    @NamedQuery(name = "Administrator.findBySalary", query = "SELECT a FROM Administrator a WHERE a.salary = :salary"),
    @NamedQuery(name = "Administrator.findByDateHired", query = "SELECT a FROM Administrator a WHERE a.dateHired = :dateHired")})
public class Administrator implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 20)
    @Column(name = "firstName")
    private String firstName;
    @Size(max = 20)
    @Column(name = "lastName")
    private String lastName;
    @Size(max = 15)
    @Column(name = "gender")
    private String gender;
    @Column(name = "age")
    private Integer age;
    @Size(max = 20)
    @Column(name = "userName")
    private String userName;
    @Size(max = 30)
    @Column(name = "password")
    private String password;
    @Column(name = "salary")
    private Integer salary;
    @Column(name = "dateHired")
    @Temporal(TemporalType.DATE)
    private Date dateHired;

    public Administrator() {
    }

    public Administrator(Integer id) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Administrator)) {
            return false;
        }
        Administrator other = (Administrator) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rappandpoppa.entities.Administrator[ id=" + id + " ]";
    }
    
}
