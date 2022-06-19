package bs6.service;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import bs6.util.CallJMS;

/**
 * Session Bean implementation class UploadEJB
 */
@Stateless
@LocalBean
public class NoticeEJB {

    /**
     * Default constructor. 
     */
    public NoticeEJB() {
        // TODO Auto-generated constructor stub
    	
    }
    
    public void notice(String n){
    	CallJMS calljms = new CallJMS();
    	try {
    		calljms.produce(n);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
