package com.rappandpoppa.beans;

import com.rappandpoppa.entities.Student;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Benjamin
 */
@Stateless
public class StudentFacade extends AbstractFacade<Student> implements StudentFacadeLocal {
    @PersistenceContext(unitName = "JavaEEProject-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StudentFacade() {
        super(Student.class);
    }

    @Override
    public Student findOneByFirstName(String firstName) {
        return (Student) getEntityManager().createNamedQuery("Student.findByFirstName")
                .setParameter("firstName", firstName).getSingleResult();
    }

}
