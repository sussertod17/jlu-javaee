package bs6.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import bs6.entity.User;

/**
 * Servlet implementation class DeleteUserServlet
 */
@WebServlet("/DeleteUserByIdServlet")
public class DeleteUserByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@PersistenceUnit(unitName = "mypractice")
	private EntityManagerFactory emf;
	@Resource
	UserTransaction utx;      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUserByIdServlet() {
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
		
        
        String id = request.getParameter("id");
        EntityManager manager = emf.createEntityManager();
		PrintWriter out = response.getWriter();
		out.println(id);
        
        try{
        utx.begin();
        manager.joinTransaction();
        String jpql = "select u from User u where u.userId=:id";
        Query query = manager.createQuery(jpql);
		query.setParameter("id", Integer.parseInt(id));
		User user = (User) query.getSingleResult();
		
		
		out.println("Welcome!"+user.getUserId());
		
		manager.remove(user);		        
	    utx.commit();
        }catch(Exception e){
        	e.printStackTrace();
        }
        manager.close();
        
        response.sendRedirect("/MyPractice/FindAllUserServlet");
	}

}
