package bs6.service;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import bs6.entity.TU;
import bs6.entity.Tube;

/**
 * Session Bean implementation class CheckEJB
 */
@Stateless
@LocalBean
public class CheckEJB {
	@PersistenceContext(unitName="mypractice")
	 EntityManager manager;
    /**
     * Default constructor. 
     */
    public CheckEJB() {
        // TODO Auto-generated constructor stub
    }
    
    public Tube check(String Num){
    	TU tu = null;
    	Tube tube = null;
    	try{
    		
    		String jpql = "select tu from TU tu where tu.userId=:uid";
    		Query query = manager.createQuery(jpql);
    		query.setParameter("uid", Num);
    		tu = (TU) query.getSingleResult();
    		
    	
    		jpql = "select t from Tube t where t.tubeNum=:tid";
    		Query query1 = manager.createQuery(jpql);
    		query1.setParameter("tid", tu.getTubeId());
    		tube = (Tube) query1.getSingleResult();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
		
    	return tube;
    }
    
}
