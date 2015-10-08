package com.rappandpoppa.model.origin;

import com.rappandpoppa.model.ContactInformationMB;

/**
 *
 * @author Anders
 */
public abstract class Person {

    private Integer id;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private ContactInformationMB contactInformation = new ContactInformationMB();

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

    public ContactInformationMB getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(ContactInformationMB contactInformation) {
        this.contactInformation = contactInformation;
    }
}
