/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rappandpoppa.controllers;

import com.rappandpoppa.beans.StudentFacadeLocal;
import com.rappandpoppa.entities.Student;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Benjamin
 */
@ManagedBean
@RequestScoped
public class StudentController {
    
    private Integer studentId;
    private String searchTextLabel;
    private final List<Student> students = new ArrayList<>();
    
    @EJB
    StudentFacadeLocal studentFacade;

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

    public List<Student> completeStudent(String query) {
        List<Student> allStudents = studentFacade.findAll();
        List<Student> filteredStudents = new ArrayList<>();
        
        for(Student student : allStudents) {
            if(student.getFirstName().toLowerCase().startsWith(query.toLowerCase())) {
                filteredStudents.add(student);
            }
        }
        return filteredStudents;
    }
    
    public void viewStudent() {
        students.clear();
        Student foundStudent = studentFacade.find(studentId);
        if (foundStudent != null) {
            students.add(foundStudent);
            searchTextLabel = foundStudent.getFirstName() +  " " + foundStudent.getLastName();
        }
    }
    
        public void viewAllStudents() {
        students.clear();
        List<Student> foundStudents = studentFacade.findAll();
        for (Student foundStudent : foundStudents) {
            students.add(foundStudent);
        }
    }
}
