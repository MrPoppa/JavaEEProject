package com.rappandpoppa.beans;

import com.rappandpoppa.entities.Course;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Benjamin
 */
@Stateless
public class CourseFacade extends AbstractFacade<Course> implements CourseFacadeLocal {
    @PersistenceContext(unitName = "JavaEEProject-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CourseFacade() {
        super(Course.class);
    }

    @Override
    public Course findByCourseName(Object courseName) {
        return (Course) getEntityManager().createNamedQuery("Course.findByCourseName")
                .setParameter("courseName", courseName).getSingleResult();
    }
    
}
