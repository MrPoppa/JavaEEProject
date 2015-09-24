/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rappandpoppa.controllers;

import com.rappandpoppa.beans.StudentFacadeLocal;
import com.rappandpoppa.entities.Student;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Benjamin
 */
@ManagedBean
public class StudentController {
    
    @EJB
    StudentFacadeLocal studentFacade;
    
    public void completeStudent(String query) {
        List<Student> allStudents = studentFacade.findAll();
        
    }
}
