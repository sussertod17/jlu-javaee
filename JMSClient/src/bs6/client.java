package bs6;


import java.util.logging.Logger;
import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.Context;
import javax.naming.InitialContext;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


public final class client{

	
	private static final String DEFAULT_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
	private static final String DEFAULT_DESTINATION = "jms/topic/test";
	
	private static final String DEFAULT_USERNAME = "testtopic";
	private static final String DEFAULT_PASSWORD = "123456";
	private static final String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
	private static final String PROVIDER_URL = "remote://localhost:4447";
  
  

  public static void main(String[] args) throws Exception 
  {
	  Context context=null;
	  Connection connection=null;
	  TextMessage msg=null;
	  try {
			// 设置上下文的JNDI查找
			System.out.println("设置JNDI访问环境信息也就是设置应用服务器的上下文信息!");
			final Properties env = new Properties();
			env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);// 该KEY的值为初始化Context的工厂类,JNDI驱动的类名
			env.put(Context.PROVIDER_URL,  PROVIDER_URL);// 该KEY的值为Context服务提供者的URL.命名服务提供者的URL
			env.put(Context.SECURITY_PRINCIPAL, DEFAULT_USERNAME);
			env.put(Context.SECURITY_CREDENTIALS, DEFAULT_PASSWORD);//应用用户的登录名,密码.
			// 获取到InitialContext对象.
			context = new InitialContext(env);
			//System.out.println ("获取连接工厂!");
			ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup(DEFAULT_CONNECTION_FACTORY);
			//System.out.println ("获取目的地!");
			
			
			
			Destination destination = (Destination) context.lookup(DEFAULT_DESTINATION);
			if(destination!=null){
				System.out.println(destination.toString());
			}
			
			//System.out.println ("创建JMS连接、会话、生产者和消费者");
			// 创建JMS连接、会话、生产者和消费者
			connection = connectionFactory.createConnection(DEFAULT_USERNAME, DEFAULT_PASSWORD);
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			

			MessageConsumer consumer = session.createConsumer(destination);

			connection.start();
			// 等待30秒退出
			CountDownLatch latch = new CountDownLatch(1);
			while(true){
				if (msg == null) {		
					System.out.println("从核酸系统接收信息-----");
			    	msg = (TextMessage) consumer.receive(5000);
			        latch.await(1, TimeUnit.SECONDS);
				 }else{
					 	String txt = msg.getText();
			           // System.out.println("消息驱动Bean:接收到的消息 " + txt);
			            String tubeNumsTmp = txt.substring(0,txt.length()-1);
			            String tubeNums = tubeNumsTmp.substring(0,tubeNumsTmp.length()-1);
			            String status = txt.substring(txt.length()-1);
			            String[] Nums = tubeNums.split(",");
			            for(String n:Nums)
			            	System.out.println(n);
			            if(status.equals("1")){
			            	 System.out.println("阴性");
			            }else{
			            	 System.out.println("阳性");
			            }
			            msg=null;
				 }
				
			}
			
           
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (context != null) {
				context.close();
			}
			// 关闭连接负责会话,生产商和消费者
			if (connection != null) {
				connection.close();
			}
		}
	}
}


 

