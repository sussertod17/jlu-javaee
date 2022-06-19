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
			// ���������ĵ�JNDI����
			System.out.println("����JNDI���ʻ�����ϢҲ��������Ӧ�÷���������������Ϣ!");
			final Properties env = new Properties();
			env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);// ��KEY��ֵΪ��ʼ��Context�Ĺ�����,JNDI����������
			env.put(Context.PROVIDER_URL,  PROVIDER_URL);// ��KEY��ֵΪContext�����ṩ�ߵ�URL.���������ṩ�ߵ�URL
			env.put(Context.SECURITY_PRINCIPAL, DEFAULT_USERNAME);
			env.put(Context.SECURITY_CREDENTIALS, DEFAULT_PASSWORD);//Ӧ���û��ĵ�¼��,����.
			// ��ȡ��InitialContext����.
			context = new InitialContext(env);
			//System.out.println ("��ȡ���ӹ���!");
			ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup(DEFAULT_CONNECTION_FACTORY);
			//System.out.println ("��ȡĿ�ĵ�!");
			
			
			
			Destination destination = (Destination) context.lookup(DEFAULT_DESTINATION);
			if(destination!=null){
				System.out.println(destination.toString());
			}
			
			//System.out.println ("����JMS���ӡ��Ự�������ߺ�������");
			// ����JMS���ӡ��Ự�������ߺ�������
			connection = connectionFactory.createConnection(DEFAULT_USERNAME, DEFAULT_PASSWORD);
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			

			MessageConsumer consumer = session.createConsumer(destination);

			connection.start();
			// �ȴ�30���˳�
			CountDownLatch latch = new CountDownLatch(1);
			while(true){
				if (msg == null) {		
					System.out.println("�Ӻ���ϵͳ������Ϣ-----");
			    	msg = (TextMessage) consumer.receive(5000);
			        latch.await(1, TimeUnit.SECONDS);
				 }else{
					 	String txt = msg.getText();
			           // System.out.println("��Ϣ����Bean:���յ�����Ϣ " + txt);
			            String tubeNumsTmp = txt.substring(0,txt.length()-1);
			            String tubeNums = tubeNumsTmp.substring(0,tubeNumsTmp.length()-1);
			            String status = txt.substring(txt.length()-1);
			            String[] Nums = tubeNums.split(",");
			            for(String n:Nums)
			            	System.out.println(n);
			            if(status.equals("1")){
			            	 System.out.println("����");
			            }else{
			            	 System.out.println("����");
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
			// �ر����Ӹ���Ự,�����̺�������
			if (connection != null) {
				connection.close();
			}
		}
	}
}


 

