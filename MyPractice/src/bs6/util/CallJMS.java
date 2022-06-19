package bs6.util;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class CallJMS {
	private static final String DEFAULT_CONNECTION_FACTORY = "java:/ConnectionFactory";
	private static final String DEFAULT_DESTINATION = "java:/topic/test";
	private static final String DEFAULT_MESSAGE_COUNT = "1";

	private static final String DEFAULT_USERNAME = "testtopic";
	private static final String DEFAULT_PASSWORD = "123456";


	
	public void produce(String n) throws Exception {
		Context context=null;
		Connection connection=null;
		// 设置上下文的JNDI查找
		try{
			System.out.println("设置JNDI访问环境信息也就是设置应用服务器的上下文信息!");
			
			// 获取到InitialContext对象.
			context = new InitialContext();
			//System.out.println ("获取连接工厂!");
			ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup(DEFAULT_CONNECTION_FACTORY);
			//System.out.println ("获取目的地!");
			Destination destination = (Destination) context.lookup(DEFAULT_DESTINATION);

			// 创建JMS连接、会话、生产者和消费者
			connection = connectionFactory.createConnection(DEFAULT_USERNAME, DEFAULT_PASSWORD);
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer producer = session.createProducer(destination);
			connection.start();
			int count = Integer.parseInt(DEFAULT_MESSAGE_COUNT);
			// 发送特定数目的消息
			TextMessage message = null;
			for (int i = 0; i < count; i++) {
				message = session.createTextMessage(n);
				producer.send(message);
				System.out.println ("message:"+message);
				System.out.println ("message:"+n);
			}
			// 等待30秒退出
//			CountDownLatch latch = new CountDownLatch(1);
//			latch.await(30, TimeUnit.SECONDS);
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if (context != null) {
				try {
					context.close();
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// 关闭连接负责会话,生产商和消费者
			if (connection != null) {
				try {
					connection.close();
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
					
	}
}
