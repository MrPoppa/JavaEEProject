package com.rappandpoppa.beans;

import com.rappandpoppa.entities.ContactInformation;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Anders
 */
@Stateless
public class ContactinformationFacade extends AbstractFacade<ContactInformation> implements ContactinformationFacadeLocal {
    @PersistenceContext(unitName = "JavaEEProject-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContactinformationFacade() {
        super(ContactInformation.class);
    }
    
}
