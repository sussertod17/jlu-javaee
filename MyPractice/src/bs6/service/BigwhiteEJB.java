package bs6.service;

import java.util.ArrayList;


import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import bs6.entity.User;

/**
 * Session Bean implementation class bigwhiteEJB
 */
@Stateful
@LocalBean
public class BigwhiteEJB {

    /**
     * Default constructor. 
     */
	@PersistenceContext(unitName = "mypractice")
    public EntityManager manager;
	
    public BigwhiteEJB() {
        // TODO Auto-generated constructor stub
    }
    
    private ArrayList<User> list = new ArrayList<User>();
    private String tubeNum = null;
    
    
    public void setTubeNum(String tubeNum){
    	this.tubeNum = tubeNum;
    }
    
    public String getTubeNum(){
    	return this.tubeNum;
    }
    public ArrayList getList(){
    	return this.list;
    }
    
    public void addList(User user){
    	this.list.add(user);
    }
    
    
	public void deleteList(int id) {
		// TODO Auto-generated method stub
		this.list.remove(id);
	}

}
