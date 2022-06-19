package bs6.control;

import java.io.IOException;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import bs6.entity.User;

/**
 * Servlet implementation class AddUserServlet
 */
@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@PersistenceUnit(unitName = "mypractice")
	private EntityManagerFactory emf;
	@Resource
	UserTransaction utx;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        
        String userNum = request.getParameter("userNum");
        String userName = request.getParameter("userName");
        String userPswd = request.getParameter("userPswd");
        String userStatus = request.getParameter("userStatus");
        
        
        EntityManager manager = emf.createEntityManager();
        User user = new User();
        user.setUserNum(userNum);
        user.setUserName(userName);
        user.setUserPswd(userPswd);
        user.setUserStatus(Integer.parseInt(userStatus));
        //response.getWriter().write(user.toString());
        
        
        try{
        	 utx.begin();
             
             manager.joinTransaction();
             manager.persist(user);
             
             utx.commit();
             //response.getWriter().write("注册成功！3秒钟后跳到主页");
        }catch(Exception e){
        	try {
				utx.rollback();
				e.printStackTrace();

			} catch (IllegalStateException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();

			} catch (SecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();

			} catch (SystemException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();

			}
        }finally{
        	manager.close();
        }
        response.sendRedirect(request.getContextPath()+"/FindAllUserServlet");
        
	}

}
