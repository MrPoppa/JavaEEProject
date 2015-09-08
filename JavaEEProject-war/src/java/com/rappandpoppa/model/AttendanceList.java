package com.rappandpoppa.model;

import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Anders
 */
@ManagedBean
public class AttendanceList {

    private Long id;
    private List<Student> studentsPresent;  /*Not necessary? Meh...maybe useful! :-) **/
    private List<Student> studentsAbsent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Student> getStudentsPresent() {
        return studentsPresent;
    }

    public void setStudentsPresent(List<Student> studentsPresent) {
        this.studentsPresent = studentsPresent;
    }

    public List<Student> getStudentsAbsent() {
        return studentsAbsent;
    }

    public void setStudentsAbsent(List<Student> studentsAbsent) {
        this.studentsAbsent = studentsAbsent;
    }
    
    
}
