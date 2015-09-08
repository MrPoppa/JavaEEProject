package com.rappandpoppa.model;

import com.rappandpoppa.model.origin.Employee;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Anders
 */
@ManagedBean
public class Teacher extends Employee {

    private List<Course> courses;

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
