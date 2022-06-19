package bs6.control;

import java.io.IOException;
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

import bs6.service.BigwhiteEJB;

/**
 * Servlet implementation class DeleteStuById
 */
@WebServlet("/DeleteStuByCountServlet")
public class DeleteStuByCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
     
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	
    public DeleteStuByCountServlet() {
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
			
			
			
            //int res = bigwhiteEJB.deleteList();
    		
    		//response.getWriter().write(String.valueOf(res));
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=utf-8");
			
			bs6.service.BigwhiteEJB bigwhiteEJB = null;
	        HttpSession session = request.getSession();
	        if(session.getAttribute("WhiteBean")==null){
	        	response.getWriter().print("error");
	        	///
	        }else{
	        	bigwhiteEJB = (bs6.service.BigwhiteEJB) session.getAttribute("WhiteBean");
	        	String count = request.getParameter("count");
	        	//response.getWriter().print(count);
	        	
	        	bigwhiteEJB.deleteList(Integer.parseInt(count)-1);
	        	
	           // response.getWriter().print(bigwhiteEJB.getList().get(0).toString());
	            
	            session.setAttribute("list",bigwhiteEJB.getList());////request
	    		session.setAttribute("WhiteBean",bigwhiteEJB);	            
	        }
	        response.sendRedirect("/MyPractice/bigwhite.jsp");
       
	}

}
