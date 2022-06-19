package bs6.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import bs6.entity.TU;
import bs6.entity.Tube;
import bs6.entity.User;

/**
 * Servlet implementation class AddListToDbServlet
 */
@WebServlet("/AddListToDbServlet")
public class AddListToDbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@PersistenceUnit(unitName = "mypractice")
	private EntityManagerFactory emf;
	@Resource
	UserTransaction utx;      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddListToDbServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		bs6.service.BigwhiteEJB bigwhiteEJB = null;
        HttpSession session = request.getSession();
		
		if(session.getAttribute("WhiteBean")==null){
		       
       	 Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
            jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
            Context context;
			 try {
				 context = new InitialContext(jndiProperties);
	             Object obj = context.lookup("java:app/MyPractice/"+"BigwhiteEJB!bs6.service.BigwhiteEJB");
	             bigwhiteEJB = (bs6.service.BigwhiteEJB)obj;
	            				
			 } catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			 }            
       }else{
       	bigwhiteEJB = (bs6.service.BigwhiteEJB) session.getAttribute("WhiteBean");
       }
		
		
		
		
		ArrayList<User> list = bigwhiteEJB.getList();
		PrintWriter out = response.getWriter();

		EntityManager manager = emf.createEntityManager();
		
		Tube tube = new Tube();	
		String tubeNum = bigwhiteEJB.getTubeNum();
		Date date = new java.util.Date();
	    Timestamp createDate = new Timestamp(date.getTime());
		tube.setTubeGettime(createDate);
		tube.setTubeNum(tubeNum);
		tube.setTubeRes(0); ///0检测中 1阴性 2阳性
		try{
			utx.begin();
			manager.joinTransaction();
	        manager.persist(tube);
	        for(User u:list){
				TU tu  = new TU();
				tu.setTubeId(tubeNum);
				tu.setUserId(u.getUserNum());
				manager.persist(tu);
			}
	        utx.commit();
	        manager.close();
			session.invalidate();
			out.print("<script>alert('添加成功!');window.location.href='/MyPractice/bigwhite.jsp'</script>");
		}catch(Exception e){
			
			e.printStackTrace();
			
			try {
				utx.rollback();
			} catch (IllegalStateException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SystemException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally{
				manager.close();
				session.invalidate();
				out.print("<script>alert('信息填写不完整，添加失败!');window.location.href='/MyPractice/bigwhite.jsp'</script>");
//				out.print("<link href='css/bootstrap.min.css' rel='stylesheet'>");
//				out.print("<script src='js/jquery-2.1.0.min.js'></script>");
//				out.print("<script src='js/bootstrap.min.js'></script>");
//				out.print("<div class='alert alert-danger'>警告！请不要提交。</div>");
			}
		}
			
			//response.sendRedirect("/MyPractice/bigwhite.jsp");
		
		
	}

}
