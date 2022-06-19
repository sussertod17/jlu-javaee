package bs6;

import java.sql.Timestamp;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Message-Driven Bean implementation class for: UploadBean
 */
@MessageDriven(
		activationConfig = { 
				@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
				@ActivationConfigProperty(propertyName = "destination", propertyValue = "topic/test")
		
		})
public class UploadBean implements MessageListener {
	 @Resource
	 private MessageDrivenContext mdc;
	 
	 @PersistenceContext(unitName="UploadBean")
	 EntityManager manager;
	 
	 
    /**
     * Default constructor. 
     */
    public UploadBean() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
        // TODO Auto-generated method stub
    	TextMessage msg = null;
        try {
            if (message instanceof TextMessage) {
                msg = (TextMessage) message;
              
                String txt = msg.getText();
                System.out.println("消息驱动Bean:接收到的消息 " + txt);
                String tubeNumsTmp = txt.substring(0,txt.length()-1);
                String tubeNums = tubeNumsTmp.substring(0,tubeNumsTmp.length()-1);
                String status = txt.substring(txt.length()-1);
                String[] Nums = tubeNums.split(",");
                for(String n:Nums)
                	System.out.println(n);
                System.out.println(status);
                
                Tube tube = new Tube();
            	
            	
            	try{
            		String jpql = "UPDATE Tube t SET t.tubeRes=:res,t.tubeRestime=:time WHERE t.tubeNum=:num";
            		Query query = manager.createQuery(jpql);
            		for(String n:Nums){
            			//String tmp = "A1";
            			java.util.Date date = new java.util.Date();
            		    Timestamp createDate = new Timestamp(date.getTime());
                		query.setParameter("num", n);
                		query.setParameter("res", Integer.parseInt(status));
                		query.setParameter("time", createDate);
                		query.executeUpdate();
            		}
            		   		
            	}catch(Exception e){
            		e.printStackTrace();
            		System.out.println("有试管不在数据库中！");
            	}
                
                
                
            } else {
                System.out.println("消息的类型不正确： " + message.getClass().getName());
            }
        } catch (JMSException e) {
            e.printStackTrace();
            mdc.setRollbackOnly();
        } catch (Throwable te) {
            te.printStackTrace();
        }

        System.out.println("完成！");
    }

}
