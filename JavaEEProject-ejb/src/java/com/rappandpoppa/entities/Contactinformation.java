/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rappandpoppa.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Benjamin
 */
@Entity
@Table(name = "contactinformation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contactinformation.findAll", query = "SELECT c FROM Contactinformation c"),
    @NamedQuery(name = "Contactinformation.findById", query = "SELECT c FROM Contactinformation c WHERE c.id = :id"),
    @NamedQuery(name = "Contactinformation.findByStreetName", query = "SELECT c FROM Contactinformation c WHERE c.streetName = :streetName"),
    @NamedQuery(name = "Contactinformation.findByZipCode", query = "SELECT c FROM Contactinformation c WHERE c.zipCode = :zipCode"),
    @NamedQuery(name = "Contactinformation.findByCity", query = "SELECT c FROM Contactinformation c WHERE c.city = :city"),
    @NamedQuery(name = "Contactinformation.findByPhoneNumber", query = "SELECT c FROM Contactinformation c WHERE c.phoneNumber = :phoneNumber"),
    @NamedQuery(name = "Contactinformation.findByEmailAddress", query = "SELECT c FROM Contactinformation c WHERE c.emailAddress = :emailAddress")})
public class Contactinformation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 32)
    @Column(name = "streetName")
    private String streetName;
    @Size(max = 16)
    @Column(name = "zipCode")
    private String zipCode;
    @Size(max = 64)
    @Column(name = "city")
    private String city;
    @Size(max = 16)
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Size(max = 64)
    @Column(name = "emailAddress")
    private String emailAddress;
    @OneToOne(mappedBy = "contactId")
    private Principal principal;
    @OneToOne(mappedBy = "contactId")
    private Teacher teacher;
    @OneToOne(mappedBy = "contactInformation")
    private Student student;

    public Contactinformation() {
    }

    public Contactinformation(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @XmlTransient
    public Principal getPrincipal() {
        return principal;
    }

    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }

    @XmlTransient
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @XmlTransient
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
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
        if (!(object instanceof Contactinformation)) {
            return false;
        }
        Contactinformation other = (Contactinformation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rappandpoppa.entities.Contactinformation[ id=" + id + " ]";
    }
    
}
