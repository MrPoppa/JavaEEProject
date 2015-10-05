/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rappandpoppa.controllers;

import com.rappandpoppa.beans.CourseFacadeLocal;
import com.rappandpoppa.beans.StudentFacadeLocal;
import com.rappandpoppa.entities.Contactinformation;
import com.rappandpoppa.entities.Course;
import com.rappandpoppa.entities.Student;
import com.rappandpoppa.model.ContactInformationMB;
import com.rappandpoppa.model.StudentMB;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Benjamin
 */
@ManagedBean
@ViewScoped
public class StudentController implements Serializable {

    private Integer studentId;
    private String searchTextLabel;
    private final List<Student> students = new ArrayList<>();
    private StudentMB studentMB = new StudentMB();
    private Student studentToBeEdited;

    @EJB
    StudentFacadeLocal studentFacade;
    @EJB
    CourseFacadeLocal courseFacade;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getSearchTextLabel() {
        return searchTextLabel;
    }

    public void setSearchTextLabel(String searchTextLabel) {
        this.searchTextLabel = searchTextLabel;
    }

    public List<Student> getStudents() {
        return students;
    }

    public StudentMB getStudentMB() {
        return studentMB;
    }

    public void setStudentMB(StudentMB studentMB) {
        this.studentMB = studentMB;
    }

    public Student getStudentToBeEdited() {
        return studentToBeEdited;
    }

    public void setStudentToBeEdited(Student studentToBeEdited) {
        this.studentToBeEdited = studentToBeEdited;
    }

    public void createStudent() {
        Student student = new Student();
        student.setFirstName(studentMB.getFirstName());
        student.setAge(studentMB.getAge());
        student.setLastName(studentMB.getLastName());
        student.setGender(studentMB.getGender());
        Contactinformation contact = new Contactinformation();
        contact.setCity(studentMB.getContactInformation().getCity());
        contact.setEmailAddress(studentMB.getContactInformation().getEmailAddress());
        contact.setPhoneNumber(studentMB.getContactInformation().getPhoneNumber());
        contact.setStreetName(studentMB.getContactInformation().getStreetName());
        contact.setZipCode(studentMB.getContactInformation().getZipCode());
        student.setContactInformation(contact);
        studentFacade.create(student);
    }

    public List<Student> completeStudent(String query) {
        List<Student> allStudents = studentFacade.findAll();
        List<Student> filteredStudents = new ArrayList<>();

        for (Student student : allStudents) {
            if (student.getFirstName().toLowerCase().startsWith(query.toLowerCase())) {
                filteredStudents.add(student);
            }
        }
        return filteredStudents;
    }

    public void getStudent() {
        students.clear();
        Student foundStudent = studentFacade.find(studentMB.getId());
        if (foundStudent != null) {
            students.add(foundStudent);
            searchTextLabel = foundStudent.getFirstName() + " " + foundStudent.getLastName();
        }
    }

    public void getAllStudents() {
        students.clear();
        List<Student> foundStudents = studentFacade.findAll();
        for (Student foundStudent : foundStudents) {
            students.add(foundStudent);
        }
    }

    public void selectStudent() {
        if (studentMB.getId() != null) {
            studentToBeEdited = studentFacade.find(studentMB.getId());
            studentMB.setAge(studentToBeEdited.getAge());
            studentMB.setFirstName(studentToBeEdited.getFirstName());
            studentMB.setLastName(studentToBeEdited.getLastName());
            studentMB.setGender(studentToBeEdited.getGender());
            ContactInformationMB contact = new ContactInformationMB();
            contact.setCity(studentToBeEdited.getContactInformation().getCity());
            contact.setEmailAddress(studentToBeEdited.getContactInformation().getEmailAddress());
            contact.setPhoneNumber(studentToBeEdited.getContactInformation().getPhoneNumber());
            contact.setStreetName(studentToBeEdited.getContactInformation().getStreetName());
            contact.setZipCode(studentToBeEdited.getContactInformation().getZipCode());
            studentMB.setContactInformation(contact);
        }
    }

    public void selectStudent(Integer studentId) {
        studentToBeEdited = studentFacade.find(studentMB.getId());
        studentMB.setAge(studentToBeEdited.getAge());
        studentMB.setFirstName(studentToBeEdited.getFirstName());
        studentMB.setLastName(studentToBeEdited.getLastName());
        studentMB.setGender(studentToBeEdited.getGender());
        ContactInformationMB contact = new ContactInformationMB();
        contact.setCity(studentToBeEdited.getContactInformation().getCity());
        contact.setEmailAddress(studentToBeEdited.getContactInformation().getEmailAddress());
        contact.setPhoneNumber(studentToBeEdited.getContactInformation().getPhoneNumber());
        contact.setStreetName(studentToBeEdited.getContactInformation().getStreetName());
        contact.setZipCode(studentToBeEdited.getContactInformation().getZipCode());
        studentMB.setContactInformation(contact);
    }

    public void updateStudent() {
        if (studentMB.getId() != null) {
            studentToBeEdited.setAge(studentMB.getAge());
            studentToBeEdited.setFirstName(studentMB.getFirstName());
            studentToBeEdited.setLastName(studentMB.getLastName());
            studentToBeEdited.setGender(studentMB.getGender());
            Contactinformation contactEdit = new Contactinformation();
            contactEdit.setCity(studentMB.getContactInformation().getCity());
            contactEdit.setEmailAddress(studentMB.getContactInformation().getEmailAddress());
            contactEdit.setPhoneNumber(studentMB.getContactInformation().getPhoneNumber());
            contactEdit.setStreetName(studentMB.getContactInformation().getStreetName());
            contactEdit.setZipCode(studentMB.getContactInformation().getZipCode());
            studentToBeEdited.setContactInformation(contactEdit);
            studentFacade.edit(studentToBeEdited);
        }
    }

    public void deleteStudent() {
        if (studentMB.getId() != null) {
            studentToBeEdited = studentFacade.find(this.studentMB.getId());

            for (Course course : studentToBeEdited.getCourseList()) {
                course.getStudentList().remove(studentToBeEdited);
                courseFacade.edit(course);
            }
            studentFacade.edit(studentToBeEdited);
            studentFacade.remove(studentToBeEdited);
        }
    }
}
