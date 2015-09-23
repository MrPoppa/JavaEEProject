/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rappandpoppa.beans;

import com.rappandpoppa.entities.Attendancelist;
import com.rappandpoppa.entities.Student;
import java.util.ArrayList;
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
    public List<Date> findAllDatesByCourse(long course_id) {
        List<Date> dates
                = em.createNamedQuery("Attendancelist.findAllDatesByCourse")
                .setParameter("course_id", course_id)
                .getResultList();
        return dates;
    }

    @Override
    public List<Student> findAllStudentsByCourseDate(Date attendanceDate, long course_id) {
        List<Student> attendingStudents
                = em.createNamedQuery("AttendanceList.findAllStudentsByCourseDate")
                .setParameter("course_id", course_id)
                .setParameter("attendanceDate", attendanceDate)
                .getResultList();

        return attendingStudents;
    }

}
