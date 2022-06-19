package bs6.control;

import java.io.IOException;
import java.io.PrintWriter;

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
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@PersistenceUnit(unitName = "mypractice")
	private EntityManagerFactory emf;
	@Resource
	UserTransaction utx;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
        
        String userNum = request.getParameter("userNum");
        String userName = request.getParameter("userName");
        String userPswd = request.getParameter("userPswd");

        
        EntityManager manager = emf.createEntityManager();
        User user = new User();
        user.setUserNum(userNum);
        user.setUserName(userName);
        user.setUserPswd(userPswd);
        user.setUserStatus(3);
        try{
        	 utx.begin();
             
             manager.joinTransaction();
             manager.persist(user);
             
             utx.commit();
             response.getWriter().write("×¢²á³É¹¦£¡3ÃëÖÓºóÌøµ½Ö÷Ò³");
        }catch(Exception e){
        	try {
				utx.rollback();
				response.getWriter().write("×¢²áÊ§°Ü£¡3ÃëÖÓºóÌøµ½Ö÷Ò³");
			} catch (IllegalStateException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				response.getWriter().write("×¢²áÊ§°Ü£¡3ÃëÖÓºóÌøµ½Ö÷Ò³");
			} catch (SecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				response.getWriter().write("×¢²áÊ§°Ü£¡3ÃëÖÓºóÌøµ½Ö÷Ò³");
			} catch (SystemException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				response.getWriter().write("×¢²áÊ§°Ü£¡3ÃëÖÓºóÌøµ½Ö÷Ò³");
			}
        }finally{
        	manager.close();
        }
       
       
		//ÉèÖÃ3ÃëÖÓÌø×ª
		response.setHeader("refresh", "3;url=/MyPractice/login.jsp");
        
        
//        PrintWriter out = response.getWriter();
//		
//       
//		out.println("×¢²á³É¹¦!");
	}

}
