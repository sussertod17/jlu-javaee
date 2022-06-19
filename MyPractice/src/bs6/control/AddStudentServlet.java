package bs6.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bs6.entity.User;

/**
 * Servlet implementation class AddStudentServlet
 */
@WebServlet("/AddStudentServlet")
public class AddStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
//	@EJB
//    public bs6.service.bigwhiteEJB bigwhiteEJB;
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddStudentServlet() {
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
		request.setCharacterEncoding("UTF-8");
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
        
        String tubeNum = (String) session.getAttribute("InputTubeNum");
        String userNum = request.getParameter("userNum");
        String userName = request.getParameter("userName");
		
        if(bigwhiteEJB.getTubeNum()==null){
        	bigwhiteEJB.setTubeNum(tubeNum);
        }
        
        if(userName.equals("没有找到这名同学哦！")){
        	
        }else{
        	User user = new User();
            user.setUserNum(userNum);
            user.setUserName(userName);
            
            bigwhiteEJB.addList(user);
        }
       
        ArrayList<User> list = bigwhiteEJB.getList();
        //PrintWriter out = response.getWriter();
        //out.println("Welcome!"+tubeNum+userNum);
		//out.println(list.get(0).getUserNum());
		
		session.setAttribute("InputTubeNum",tubeNum);////request
		session.setAttribute("list",list);////request
		session.setAttribute("WhiteBean",bigwhiteEJB);
		
		//request.getRequestDispatcher("/bigwhite.jsp").forward(request, response);	
		
		response.sendRedirect("/MyPractice/bigwhite.jsp");
              
	}

}
