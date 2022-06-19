//package bs6.control;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//
//import javax.ejb.EJB;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * Servlet implementation class StudentServlet
// */
//@WebServlet("/StudentServlet")
//public class StudentServlet extends HttpServlet {
//	
//	private static final long serialVersionUID = 1L;
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public StudentServlet() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//    
//    @EJB
//	private bs6.service.StudentEJB studentEjb;
//	
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		String buffer = studentEjb.showAll();
//		PrintWriter out = response.getWriter();
//		out.println("Welcome!"+buffer);
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		
//	}
//
//}
