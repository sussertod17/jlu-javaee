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
		// ���������ĵ�JNDI����
		try{
			System.out.println("����JNDI���ʻ�����ϢҲ��������Ӧ�÷���������������Ϣ!");
			
			// ��ȡ��InitialContext����.
			context = new InitialContext();
			//System.out.println ("��ȡ���ӹ���!");
			ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup(DEFAULT_CONNECTION_FACTORY);
			//System.out.println ("��ȡĿ�ĵ�!");
			Destination destination = (Destination) context.lookup(DEFAULT_DESTINATION);

			// ����JMS���ӡ��Ự�������ߺ�������
			connection = connectionFactory.createConnection(DEFAULT_USERNAME, DEFAULT_PASSWORD);
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer producer = session.createProducer(destination);
			connection.start();
			int count = Integer.parseInt(DEFAULT_MESSAGE_COUNT);
			// �����ض���Ŀ����Ϣ
			TextMessage message = null;
			for (int i = 0; i < count; i++) {
				message = session.createTextMessage(n);
				producer.send(message);
				System.out.println ("message:"+message);
				System.out.println ("message:"+n);
			}
			// �ȴ�30���˳�
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
			// �ر����Ӹ���Ự,�����̺�������
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
