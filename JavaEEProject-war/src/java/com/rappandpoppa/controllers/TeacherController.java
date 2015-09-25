/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rappandpoppa.controllers;

import com.rappandpoppa.beans.TeacherFacadeLocal;
import com.rappandpoppa.entities.ContactInformation;
import com.rappandpoppa.entities.Teacher;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Benjamin
 */
@ManagedBean
public class TeacherController {

    @EJB
    TeacherFacadeLocal teacherFacade;

    public void createTeachers() {
        if (teacherFacade.findAll().isEmpty()) {

            Teacher teacher1 = new Teacher();
            teacher1.setAge(28);
            teacher1.setFirstName("Mister");
            teacher1.setLastName("Monkey");
            teacher1.setDateHired(new Date(System.currentTimeMillis()));
            teacher1.setGender("Male");
            teacher1.setUserName("MMon");
            teacher1.setPassword("mister1234monkey");
            teacher1.setSalary(250000);
            ContactInformation contact1 = new ContactInformation();
            contact1.setCity("Göteborg");
            contact1.setStreetName("Monkey Street 62");
            contact1.setZipCode("666");
            contact1.setEmailAddress("m.m@school.com");
            contact1.setPhoneNumber("0702564852");
            teacher1.setContactInformation(contact1);

            Teacher teacher2 = new Teacher();
            teacher2.setAge(35);
            teacher2.setFirstName("Herr");
            teacher2.setLastName("Apa");
            teacher2.setDateHired(new Date(System.currentTimeMillis()));
            teacher2.setGender("Male");
            teacher2.setUserName("HApa");
            teacher2.setPassword("herr1234apa");
            teacher2.setSalary(225000);
            ContactInformation contact2 = new ContactInformation();
            contact2.setCity("Göteborg");
            contact2.setStreetName("Apgatan 62");
            contact2.setZipCode("555");
            contact2.setEmailAddress("h.a@school.com");
            contact2.setPhoneNumber("123456789");
            teacher2.setContactInformation(contact2);

            Teacher teacher3 = new Teacher();
            teacher3.setAge(55);
            teacher3.setFirstName("Professor");
            teacher3.setLastName("Moriarty");
            teacher3.setDateHired(new Date(System.currentTimeMillis()));
            teacher3.setGender("Male");
            teacher3.setUserName("ProfMor");
            teacher3.setPassword("prof1234mor");
            teacher3.setSalary(300000);
            ContactInformation contact3 = new ContactInformation();
            contact3.setCity("Göteborg");
            contact3.setStreetName("Gatan Där Borta 12");
            contact3.setZipCode("123456789");
            contact3.setEmailAddress("p.m@school.com");
            contact3.setPhoneNumber("693258741");
            teacher3.setContactInformation(contact3);
            teacherFacade.create(teacher1);
            teacherFacade.create(teacher2);
            teacherFacade.create(teacher3);

        }
    }

    public List<Teacher> completeTeacher(String query) {
        List<Teacher> allTeachers = teacherFacade.findAll();
        List<Teacher> filteredTeachers = new ArrayList<>();

        for (Teacher teacher : allTeachers) {
            if (teacher.getFirstName().toLowerCase().startsWith(query.toLowerCase())) {
                filteredTeachers.add(teacher);
            }
        }
        return filteredTeachers;
    }

    public List<Teacher> getTeachers() {
        return teacherFacade.findAll();
    }
}
