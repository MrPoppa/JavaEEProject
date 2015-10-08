package com.rappandpoppa.beans;

import com.rappandpoppa.entities.Attendancelist;
import com.rappandpoppa.entities.Student;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Benjamin
 */
@Local
public interface AttendancelistFacadeLocal {

    void create(Attendancelist attendancelist);

    void edit(Attendancelist attendancelist);

    void remove(Attendancelist attendancelist);

    Attendancelist find(Object id);

    List<Attendancelist> findAll();

    List<Attendancelist> findRange(int[] range);

    int count();

    List<Date> findAllDatesByCourse(int id);

    List<Student> findAllStudentsByCourseDate(Date date, int course_id);

    List<Date> findAllDatesByStudent(Integer studentId);

    List<Attendancelist> findPeriod(Date startDate, Date endDate);

    List<Attendancelist> findCoursePeriod(int course_id, Date startDate, Date endDate);
}
