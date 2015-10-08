package com.rappandpoppa.beans;

import com.rappandpoppa.entities.Administrator;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Anders
 */
@Stateless
public class AdministratorFacade extends AbstractFacade<Administrator> implements AdministratorFacadeLocal {

    @PersistenceContext(unitName = "JavaEEProject-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdministratorFacade() {
        super(Administrator.class);
    }

    @Override
    public boolean loginControl(String userName, String password) {
        try {
            Administrator admin = (Administrator) getEntityManager().createNamedQuery("Administrator.loginControl")
                    .setParameter("userName", userName).setParameter("password", password).getSingleResult();
            return true;
        } catch (NoResultException nre) {
            return false;
        }
    }
}
