package bs6;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class nameEJB
 */
@Stateless
@LocalBean
public class nameEJB {

    /**
     * Default constructor. 
     */
	@PersistenceContext(unitName="RESTWS_getName")
	 EntityManager manager;
	
    public nameEJB() {
        // TODO Auto-generated constructor stub
    }
    
    public String getName(String Num){
    	
    	User user = new User();
    	String res = "";
    	try{
    		String jpql = "select u from User u where u.userNum=:num";
    		Query query = manager.createQuery(jpql);
    		query.setParameter("num", Num);
    		user = (User) query.getSingleResult();
    		res = user.getUserName();
    	}catch(Exception e){
    		
    	}
    
	
    	return res;
    }

}
