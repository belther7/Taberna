/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author anatoliandrei
 * @author lucasbeccon
 */
public class JPAUtil {
    private static String PUNAME = "TabernaPU";
    private static EntityManagerFactory factory = 
            Persistence.createEntityManagerFactory(PUNAME);
   
    public static EntityManager getManager(){
        return (factory.createEntityManager());
    }
    
}
