package bs6.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bs6.entity.TU;
import bs6.entity.User;
import bs6.entity.Tube;
import bs6.service.CheckEJB;

/**
 * Servlet implementation class CheckServlet
 */
@WebServlet("/CheckServlet")
public class CheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@PersistenceUnit(unitName = "mypractice")
	private EntityManagerFactory emf;
	
	@EJB private CheckEJB checkEJB;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
		
        PrintWriter out = response.getWriter();
        EntityManager manager = emf.createEntityManager();
        
        
        String userNum = request.getParameter("userNum");
        
		Tube tube = checkEJB.check(userNum);
		
		if(tube!=null){
			User user = new User();
			TU tu = new TU();
	    	String userName = "";
	    	

	    	String jpql = "select u from User u where u.userNum=:num";
	    	Query query = manager.createQuery(jpql);
	    	query.setParameter("num", userNum);
	   		user = (User) query.getSingleResult();
	   		userName = user.getUserName();
	    		
	    				
			request.setAttribute("userNum",userNum);
			request.setAttribute("userName", userName);
			request.setAttribute("getTime", tube.getTubeGettime());
			request.setAttribute("resTime", tube.getTubeRestime());
			request.setAttribute("status", tube.getTubeRes());
			
			request.getRequestDispatcher("/checkResult.jsp").forward(request, response);	
		}else{
			out.print("<script>alert('这名同学还没有做核酸!');window.location.href='/MyPractice/student.jsp'</script>");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
