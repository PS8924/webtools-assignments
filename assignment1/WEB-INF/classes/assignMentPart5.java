import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;
import java.util.Map.*;
import java.lang.reflect.Method;

public class assignMentPart5 extends HttpServlet{
  public void init(){ }

  public void destroy(){ }

  public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException{ 
    handler(request,response); 
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
    handler(request,response);
  }

  public void handler(HttpServletRequest request, HttpServletResponse response) throws IOException{
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

		Class obj = request.getClass();
		Method[] methods = obj.getMethods();
		
		out.println("<html>");
		out.println("<style> table { font-family: arial, sans-serif; border-collapse: collapse; width: 50%;} td, th { border: 1px dashed #dddddd; font-size: 12px; text-align: left; padding: 4px; } tr:nth-child(even) { background-color: #dddddd; } </style>");
		out.println("<body>");
		out.println("<h2>Display all the getX() methods from the HttpServletRequest class</h2>");
		out.println("<table>");
		out.println("<tr>");
		out.println("<th>Name</th>");
		out.println("<th>Value</th>");
		out.println("</tr>");
		// get the name of every method present in the list
		for (Method method : methods) {
				String methodName = method.getName();
			if(methodName.contains("get")) {
				try {
					out.print("<tr><td>" + methodName + "</td>\n<td> " + method.invoke(request) + "</td>");
				}
				catch(Exception e){}
			}
		}
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
  }
}