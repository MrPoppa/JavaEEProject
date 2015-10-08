package com.rappandpoppa.beans;

import com.rappandpoppa.entities.Principal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Benjamin
 */
@Stateless
public class PrincipalFacade extends AbstractFacade<Principal> implements PrincipalFacadeLocal {
    @PersistenceContext(unitName = "JavaEEProject-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrincipalFacade() {
        super(Principal.class);
    }
    
}
