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
import javax.ejb.Local;

/**
 *
 * @author Benjamin
 */
@Local
public interface TeacherFacadeLocal {

    void create(Teacher teacher);

    void edit(Teacher teacher);

    void remove(Teacher teacher);

    Teacher find(Object id);

    List<Teacher> findAll();

    List<Teacher> findRange(int[] range);

    int count();

    List<Course> findTeacherCourses(Object id);

    Course findCourseByCourseName(Object id, String courseName);

    List<Attendancelist> findAttendanceListsByCourse(String courseName, Object id);

    Attendancelist findAttendanceListByDate(Object id, String courseName, LocalDate chosenDate);

    List<Student> findAttendingStudentsByDate(Object id, String courseName, LocalDate chosenDate);
    
}
