package bs6.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import bs6.entity.TU;

/**
 * Session Bean implementation class TUDao
 */
@Stateless
@LocalBean
public class TUDao {
	@PersistenceContext(unitName = "mypractice")
    public EntityManager manager;
	
    /**
     * Default constructor. 
     */
    public TUDao() {
        // TODO Auto-generated constructor stub
    }
    
    public TU FindTUByUserNum(String num){
    	TU tu = new TU();
    	String jpql = "select tu from TU tu where tu.userId=:uid";
		Query query = manager.createQuery(jpql);
		query.setParameter("uid", num);
		tu = (TU) query.getSingleResult();
    	return tu;
    }

}
