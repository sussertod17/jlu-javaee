package bs6.control;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bs6.service.NoticeEJB;;

/**
 * Servlet implementation class UploadStudent
 */
@WebServlet("/UploadStudent")
public class UploadStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB private NoticeEJB noticeEJB;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadStudent() {
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
		
		
		String tubeNums = request.getParameter("tubeNum");
		String res = request.getParameter("res");
		
		String out = tubeNums+";"+res;
		
		noticeEJB.notice(out);
		request.getRequestDispatcher("uploadSuccess.jsp").forward(request,response);

	}

}
