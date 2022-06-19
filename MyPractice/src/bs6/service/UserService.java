package bs6.service;



import org.junit.Test;

import bs6.entity.User;
import bs6.dao.t_UserDao;
import bs6.dao.t_UserDao;

public class UserService {
//	
//	private UserDao userDao;
//	
//	public UserService(){
//		userDao = new UserDao();
//	}
//	
	public User login(String userName, String userPswd){
		
		User user = null;
		t_UserDao userDao = new t_UserDao();
			
		try {
			user = userDao.findUserByName(userName);
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		if(user!= null){
			if(user.getUserPswd().equals(userPswd)){
				return user;
			}else{
				return null;
			}
		}else{
			return null;	
		}
	}
	
//	public static void main(String[] args){
//		
//		String userName = "admin";
//		String userPswd = "123456";
//		
//		User user = null;	
//		
//		UserService userService = new UserService();
//		user = userService.login(userName, userPswd);
//
//		if (user!=null){
//			System.out.print(user.getUserName());
//		}else{
//			System.out.print("Dao!");
//		}
//		
//		
//	}

	
}
