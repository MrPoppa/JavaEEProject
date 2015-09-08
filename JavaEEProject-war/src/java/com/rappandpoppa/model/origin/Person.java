package com.rappandpoppa.model.origin;

import com.rappandpoppa.model.ContactInformation;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author Anders
 */
public abstract class Person {

    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    @ManagedProperty(value = "#{contactInformation}")  /* The point of this being? **/
    private ContactInformation contactInformation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }
}
