package com.rappandpoppa.beans;

import com.rappandpoppa.entities.Contactinformation;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Anders
 */
@Stateless
public class ContactinformationFacade extends AbstractFacade<Contactinformation> implements ContactinformationFacadeLocal {
    @PersistenceContext(unitName = "JavaEEProject-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContactinformationFacade() {
        super(Contactinformation.class);
    }
    
}
