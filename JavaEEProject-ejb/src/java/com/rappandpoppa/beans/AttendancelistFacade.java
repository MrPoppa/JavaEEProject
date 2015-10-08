package com.rappandpoppa.beans;

import com.rappandpoppa.entities.Attendancelist;
import com.rappandpoppa.entities.Student;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Benjamin
 */
@Stateless
public class AttendancelistFacade extends AbstractFacade<Attendancelist> implements AttendancelistFacadeLocal {

    @PersistenceContext(unitName = "JavaEEProject-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AttendancelistFacade() {
        super(Attendancelist.class);
    }

    @Override
    public List<Date> findAllDatesByCourse(int course_id) {
        List<Date> dates
                = em.createNamedQuery("Attendancelist.findAllDatesByCourse")
                .setParameter("course_id", course_id)
                .getResultList();
        return dates;
    }

    @Override
    public List<Student> findAllStudentsByCourseDate(Date attendanceDate, int course_id) {
        Attendancelist attendancelist = (Attendancelist) em.createNamedQuery("Attendancelist.findByAttendanceDate")
                .setParameter("attendanceDate", attendanceDate).getSingleResult();

        List<Student> attendingStudents
                = em.createNamedQuery("Student.findAllStudentsByCourseDate")
                .setParameter("course_id", course_id)
                .setParameter("attendancelistId", attendancelist.getId())
                .getResultList();

        return attendingStudents;
    }

    @Override
    public List<Date> findAllDatesByStudent(Integer studentId) {
        return null;
    }

    @Override
    public List<Attendancelist> findPeriod(Date startDate, Date endDate) {
        List<Attendancelist> attendancelistsInPeriod = em.createNamedQuery("Attendancelist.findAllListsInPeriod")
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate).getResultList();
        return attendancelistsInPeriod;
    }

    @Override
    public List<Attendancelist> findCoursePeriod(int course_id, Date startDate, Date endDate) {
        List<Attendancelist> attendancelistsInPeriod = em.createNamedQuery("Attendancelist.findCourseListsInPeriod")
                .setParameter("course_id", course_id)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate).getResultList();
        return attendancelistsInPeriod;
    }
}
