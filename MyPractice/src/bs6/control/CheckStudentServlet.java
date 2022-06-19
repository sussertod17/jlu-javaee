package bs6.control;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.hc.client5.http.fluent.Request;

/**
 * Servlet implementation class CheckStudentServlet
 */
@WebServlet("/CheckStudentServlet")
public class CheckStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        
        String tubeNum = request.getParameter("InputTubeNum");
        String userNum = request.getParameter("InputUserNum");
        HttpSession session = request.getSession();
        session.setAttribute("InputTubeNum", tubeNum);
        
        String userName = null;
        String URI = "http://localhost:8888/RESTWS_getName/getname/get?Num=" + userNum;
      
        	String result = Request.get(URI).execute().returnContent().asString(Charset.forName("gbk"));  //Charset.forName("utf-8")
    		
    		request.setAttribute("userName", result);
    		if(result.equals("")){
    			request.setAttribute("userNum", userNum);
    			request.setAttribute("userName", "没有找到这名同学哦！");
            	request.getRequestDispatcher("/bigwhiteNotFound.jsp").forward(request, response);
            	return;
    		}
        	
  
        	//request.setAttribute("InputTubeNum", tubeNum);
    		request.setAttribute("userNum", userNum);
    		request.getRequestDispatcher("/bigwhiteAdd.jsp").forward(request, response);
			return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
