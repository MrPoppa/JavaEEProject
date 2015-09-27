/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rappandpoppa.beans;

import com.rappandpoppa.entities.Attendancelist;
import com.rappandpoppa.entities.Course;
import com.rappandpoppa.entities.Student;
import com.rappandpoppa.entities.Teacher;
import java.time.LocalDate;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Benjamin
 */
@Stateless
public class TeacherFacade extends AbstractFacade<Teacher> implements TeacherFacadeLocal {

    @PersistenceContext(unitName = "JavaEEProject-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TeacherFacade() {
        super(Teacher.class);
    }

    public List<Course> findTeacherCourses(Object id) {
        return find(id).getCourseList();
    }

    @Override
    public Course findCourseByCourseName(Object id, String courseName) {
        Course foundCourse = new Course();
        for (Course course : findTeacherCourses(id)) {
            if (course.getCourseName().equals(courseName)) {
                foundCourse = course;
            }
        }
        return foundCourse;
    }

    @Override
    public List<Attendancelist> findAttendanceListsByCourse(String courseName, Object id) {
        return findCourseByCourseName(id, courseName).getAttendancelistList();
    }

    @Override
    public Attendancelist findAttendanceListByDate(Object id, String courseName, LocalDate chosenDate) {
        Attendancelist foundAttendancelist = new Attendancelist();
        for (Attendancelist a : findAttendanceListsByCourse(courseName, id)) {
            if (a.getAttendanceDate().equals(chosenDate)) {
                foundAttendancelist = a;
            }
        }
        return foundAttendancelist;
    }

    @Override
    public List<Student> findAttendingStudentsByDate(Object id, String courseName, LocalDate chosenDate) {
        return findAttendanceListByDate(id, courseName, chosenDate).getStudentList();
    }

}
