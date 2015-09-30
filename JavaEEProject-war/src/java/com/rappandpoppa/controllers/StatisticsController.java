package com.rappandpoppa.controllers;

import com.rappandpoppa.beans.AttendancelistFacadeLocal;
import com.rappandpoppa.beans.CourseFacadeLocal;
import com.rappandpoppa.beans.TeacherFacadeLocal;
import com.rappandpoppa.model.AttendanceListMB;
import com.rappandpoppa.model.CourseMB;
import com.rappandpoppa.model.StudentMB;
import com.rappandpoppa.model.TeacherMB;
import javax.ejb.EJB;

/**
 *
 * @author Anders
 */
public abstract class StatisticsController {

    TeacherMB teacherMB = new TeacherMB();
    StudentMB studentMB = new StudentMB();
    AttendanceListMB attendancelistMB = new AttendanceListMB();
    CourseMB courseMB = new CourseMB();

    @EJB
    TeacherFacadeLocal teacherFacade;

    @EJB
    CourseFacadeLocal courseFacade;

    @EJB
    AttendancelistFacadeLocal attendancelistFacade;

    public TeacherMB getTeacherMB() {
        return teacherMB;
    }

    public void setTeacherMB(TeacherMB teacherMB) {
        this.teacherMB = teacherMB;
    }

    public StudentMB getStudentMB() {
        return studentMB;
    }

    public void setStudentMB(StudentMB studentMB) {
        this.studentMB = studentMB;
    }

    public AttendanceListMB getAttendanceListMB() {
        return attendancelistMB;
    }

    public void setAttendanceListMB(AttendanceListMB attendancelistMB) {
        this.attendancelistMB = attendancelistMB;
    }

    public CourseMB getCourseMB() {
        return courseMB;
    }

    public void setCourseMB(CourseMB courseMB) {
        this.courseMB = courseMB;
    }

}
