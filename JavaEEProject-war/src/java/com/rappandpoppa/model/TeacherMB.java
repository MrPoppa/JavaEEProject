package com.rappandpoppa.model;

import com.rappandpoppa.model.origin.Employee;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Anders
 */
@ManagedBean
public class TeacherMB extends Employee {

    private List<CourseMB> courses = new ArrayList<>();

    public List<CourseMB> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseMB> courses) {
        this.courses = courses;
    }
}
