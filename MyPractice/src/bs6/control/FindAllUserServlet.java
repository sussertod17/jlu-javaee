package bs6.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

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
import javax.transaction.UserTransaction;

import bs6.entity.User;

/**
 * Servlet implementation class FindAllUserServlet
 */
@WebServlet("/FindAllUserServlet")
public class FindAllUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@PersistenceUnit(unitName = "mypractice")
	private EntityManagerFactory emf;
	@Resource
	UserTransaction utx;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindAllUserServlet() {
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
        EntityManager manager = emf.createEntityManager();
        
        
		Query query = manager.createNativeQuery("select * from user",User.class);
		
		List<User> result= (List<User>)query.getResultList();
					
		manager.close();
		request.setAttribute("list", result);

		request.getRequestDispatcher("/admin.jsp").forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
