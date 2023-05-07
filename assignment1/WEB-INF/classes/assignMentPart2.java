import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;

public class assignMentPart2 extends HttpServlet{

  public void init(){
  }

  public void destroy(){
    //clean up all variables
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException{ 
    handler(request,response); 
  }

  public void handler(HttpServletRequest request, HttpServletResponse response) throws IOException{
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

		out.println("HTTP headers sent client:<br>");  

		Enumeration allHeaders = request.getHeaderNames();  
			while (allHeaders.hasMoreElements()) {  
			String headerName = (String) allHeaders.nextElement();  
			String headerValue = request.getHeader(headerName);  
			out.print("<b>"+headerName + "</b>: ");  
			out.println(headerValue + "<br>");  
    }  
  }
}