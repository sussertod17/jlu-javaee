package bs6.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import bs6.entity.User;

/**
 * Session Bean implementation class UserDao
 */
@Stateless
@LocalBean
public class UserDao {
	@PersistenceContext(unitName = "mypractice")
    public EntityManager manager;
    /**
     * Default constructor. 
     */
    public UserDao() {
        // TODO Auto-generated constructor stub
    }
    
    public User FindUserByNum(String num){
    	String jpql = "select u from User u where u.userNum=:num";
    	Query query = manager.createQuery(jpql);
		query.setParameter("num", num);
		User user = (User) query.getSingleResult();
    	return user;
    }
}
