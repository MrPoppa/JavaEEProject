/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rappandpoppa.beans;

import com.rappandpoppa.entities.Principal;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Benjamin
 */
@Local
public interface PrincipalFacadeLocal {

    void create(Principal principal);

    void edit(Principal principal);

    void remove(Principal principal);

    Principal find(Object id);

    List<Principal> findAll();

    List<Principal> findRange(int[] range);

    int count();
    
}
