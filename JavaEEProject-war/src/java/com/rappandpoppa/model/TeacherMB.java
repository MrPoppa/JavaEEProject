package com.rappandpoppa.model;

import com.rappandpoppa.beans.CourseFacadeLocal;
import com.rappandpoppa.beans.TeacherFacadeLocal;
import com.rappandpoppa.entities.Attendancelist;
import com.rappandpoppa.entities.Course;
import com.rappandpoppa.entities.Student;
import com.rappandpoppa.entities.Teacher;
import com.rappandpoppa.model.origin.Employee;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Anders
 */
@ManagedBean
@SessionScoped
public class TeacherMB extends Employee {

    private Teacher teacher;
    private List<Course> teacherCourses;
    private Course chosenCourse;
    private List<Attendancelist> attendanceListsByCourse = new ArrayList<>();
    private List<Date> courseDates = new ArrayList<>();
    private Date chosenDate;
    private Attendancelist chosenAttendancelistByDate;
    private List<Student> studentsByCourse;
    private List<Student> attendingStudentsByCourseDate = new ArrayList<>();
    private List<String> courseNames;
    int chosenCourseId;

    @EJB
    TeacherFacadeLocal teacherFacade;

    @EJB
    CourseFacadeLocal courseFacade;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Course> getTeacherCourses() {
        return teacherCourses = teacherFacade.findTeacherCourses(1);
    }

    public void setTeacherCourses(List<Course> teacherCourses) {
        this.teacherCourses = teacherCourses;
    }

    public Course getChosenCourse() {
        return chosenCourse = courseFacade.find(chosenCourseId);
    }

    public void setChosenCourse(Course chosenCourse) {
        this.chosenCourse = chosenCourse;
    }

    public List<Attendancelist> getAttendanceListsByCourse() {
        return attendanceListsByCourse;
    }

    public void setAttendanceListsByCourse(List<Attendancelist> attendanceListsByCourse) {
        this.attendanceListsByCourse = attendanceListsByCourse;
    }

    public List<Date> getCourseDates() {
        return courseDates;
    }

    public void setCourseDates(List<Date> courseDates) {
        this.courseDates = courseDates;
    }

    public Date getChosenDate() {
        return chosenDate;
    }

    public void setChosenDate(Date chosenDate) {
        this.chosenDate = chosenDate;
    }

    public Attendancelist getChosenAttendancelistByDate() {
        return chosenAttendancelistByDate;
    }

    public void setChosenAttendancelistByDate(Attendancelist chosenAttendancelistByDate) {
        this.chosenAttendancelistByDate = chosenAttendancelistByDate;
    }

    public List<Student> getStudentsByCourse() {
        return studentsByCourse;
    }

    public void setStudentsByCourse(List<Student> studentsByCourse) {
        this.studentsByCourse = studentsByCourse;
    }

    public List<Student> getAttendingStudentsByCourseDate() {
        return attendingStudentsByCourseDate;
    }

    public void setAttendingStudentsByCourseDate(List<Student> attendingStudentsByCourseDate) {
        this.attendingStudentsByCourseDate = attendingStudentsByCourseDate;
    }

    public List<String> getCourseNames() {
        return courseNames;
    }

    public void setCourseNames(List<String> courseNames) {
        this.courseNames = courseNames;
    }

    public int getChosenCourseId() {
        return chosenCourseId;
    }

    public void setChosenCourseId(int chosenCourseId) {
        this.chosenCourseId = chosenCourseId;
    }

    public void onDateChange() {
        attendanceListsByCourse = courseFacade.find(chosenCourseId).getAttendancelistList();
        boolean notNull = chosenCourse != null;
        if (chosenCourse != null) {
            for (Attendancelist a : attendanceListsByCourse) {
                if (a.getAttendanceDate() == chosenDate) {
                    attendingStudentsByCourseDate = a.getStudentList();
                    break;
                }
            }
        }
    }

    private void fetchTeacher() {
        teacher = teacherFacade.find(1);
    }

    private void fetchTeacherCourses() {
        teacherCourses = teacherFacade.findTeacherCourses(1);
    }

    public List<Student> viewStudents() {
        if (chosenDate != null && chosenCourse != null) {
            for (Attendancelist a : chosenCourse.getAttendancelistList()) {
                if (a.getAttendanceDate().equals(chosenDate)) {
                    attendingStudentsByCourseDate = a.getStudentList();
                    break;
                }
            }
        }
        return attendingStudentsByCourseDate;
    }

    public void onCourseChange() {
        chosenCourse = courseFacade.find(chosenCourseId);
        if (chosenCourse.getId() != null) {
            if (courseDates.size() > 0) {
                courseDates.clear();
            }
            if (attendanceListsByCourse.size() > 0) {
                attendanceListsByCourse.clear();
            }

            defineAttendanceListDates();
        } else {
            courseDates = new ArrayList<>();
        }
    }

    private void defineAttendanceListDates() {
        attendanceListsByCourse = chosenCourse.getAttendancelistList();
        for (Attendancelist a : attendanceListsByCourse) {
//            LocalDate date1 = convertDateToLocalDate(a.getAttendanceDate());
//            courseDates.add(date1);

            courseDates.add(a.getAttendanceDate());

        }
    }

    private LocalDate convertDateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
