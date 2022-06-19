package bs6.control;

import java.io.IOException;
import java.io.PrintWriter;



import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;

import bs6.entity.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;	
	@PersistenceUnit(unitName = "mypractice")
	private EntityManagerFactory emf;
	@Resource
	UserTransaction utx;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();
        
		String userNum = request.getParameter("userNum");
		String userPswd = request.getParameter("userPswd");
		String jpql = "select u from User u where u.userNum=:num";
		
		EntityManager manager = emf.createEntityManager();
		Query query = manager.createQuery(jpql);
		query.setParameter("num", userNum);
		try{
			User user = (User) query.getSingleResult();
			PrintWriter out = response.getWriter();
			out.println("Welcome!" + "<br>");
			
			out.println(user.getUserName());
			
			session.setAttribute("Name", user.getUserName());
			
			int status = user.getUserStatus();
			if(status == 0){
				
				manager.close();
				//response.sendRedirect("/MyPractice/admin.jsp");
				response.sendRedirect("/MyPractice/FindAllUserServlet");
			}else if (status == 1){
				manager.close();
				response.sendRedirect("/MyPractice/bigwhite.jsp");
			}else if (status == 2){
				manager.close();
				response.sendRedirect("/MyPractice/upload.jsp");
			}else{
				manager.close();
				response.sendRedirect("/MyPractice/student.jsp");
			}
			
			
			
			
		}catch(Exception e){
			manager.close();
			response.sendRedirect("/MyPractice/error.jsp");
		
		}			
	}

}
