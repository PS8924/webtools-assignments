import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;
import java.util.Map.*;

public class assignMentPart4 extends HttpServlet{
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

		//data fetching with getParameterMap()
		Map<String, String[]> map = request.getParameterMap();
		Set set = map.entrySet();
    Iterator it = set.iterator();

		//data printing with getParameter() method
		out.println("<html>");
		out.println("<style> table { font-family: arial, sans-serif; border-collapse: collapse;} td, th { border: 1px dashed #dddddd; font-size: 12px; text-align: left; padding: 4px; } tr:nth-child(even) { background-color: #dddddd; } </style>");
		out.println("<body>");

		//data printing with getParameterMap() method
		out.println("<h2>Tuition Waiver form data</h2>");
		out.println("<table>");
		out.println("<tr>");
		out.println("<th>Attribute</th>");
		out.println("<th>Value</th>");
		out.println("</tr>");

		while (it.hasNext()) {
			Map.Entry<String, String[]> entry = (Entry<String, String[]>) it.next();
			String paramName = entry.getKey();
			out.print("<tr><td>" + paramName + "</td><td>");
			String[] paramValues = entry.getValue();
			if (paramValues.length == 1) {
				String paramValue = paramValues[0];
				if (paramValue.length() == 0)
					out.println("<b>No Value</b>");
				else
					out.println(paramValue);
			} else {
				out.println("<ul>");
				for (int i = 0; i < paramValues.length; i++) {
					out.println("<li>" + paramValues[i] + "</li>");
				}
				out.println("</ul>");
			}
			out.print("</td></tr>");
    }

		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
  }
}