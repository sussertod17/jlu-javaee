package bs6.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import bs6.entity.Tube;

/**
 * Session Bean implementation class TubeDao
 */
@Stateless
@LocalBean
public class TubeDao {
	@PersistenceContext(unitName = "mypractice")
    public EntityManager manager;
    /**
     * Default constructor. 
     */
    public TubeDao() {
        // TODO Auto-generated constructor stub
    }
    
    public Tube FindTubeByNum(String num){
    	Tube tube = new Tube();
		String jpql = "select t from Tube t where t.tubeNum=:tid";
		Query query1 = manager.createQuery(jpql);
		query1.setParameter("tid", num);
		tube = (Tube) query1.getSingleResult();
		return tube;
    }

}
