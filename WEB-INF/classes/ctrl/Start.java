package ctrl;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.SIS;
@WebServlet(urlPatterns = {"/Start", "/Startup/*"})
public class Start extends HttpServlet 
{
	private static final long serialVersionUID = 1L;  
	SIS sis;
	String error;


    public Start() {
        super();
    }

	public void init() throws ServletException
	{
		sis = new SIS();
		this.getServletContext().setAttribute("sis", sis);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{ 
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String submit = request.getParameter("report");
		String name = request.getParameter("name");
		String credit = request.getParameter("credit");
		
		if(submit != null && submit.equals("report"))
		{
			SIS object = (SIS) this.getServletContext().getAttribute("sis");
			
			//Checking for errors
			
			if(name.length() == 0 || credit.length() == 0)
			{
				if(name.length() == 0)
				{
					error = "Enter name!";
				}
				else if (credit.length() == 0)
				{
					error = "Enter credits!";
				}
				request.setAttribute("error", error);
				request.setAttribute("check", null);
			}
			else if(name.length() >0 || credit.length() > 0){
			try{
				int credit2 = 0;
				
				if(credit != null)
				{
					credit2 = Integer.parseInt(credit);
				}
				
				boolean valid = true;
				
				char[] validation = name.toCharArray();
				for(char c: validation)
				{
					valid =((c >= 'A') && (c <= 'Z') || (c>='a') &&(c<='z') );
					
					if(!valid)
					{
						error = "Please enter letters only for the name!";
						request.setAttribute("error", error);
						request.setAttribute("check", null);
						break;
					}
					else
					{
						request.setAttribute("check", "correct");
					}
				}
			
				if(credit2 <0)
				{
					error ="Please enter a positive number for credits!";
					request.setAttribute("error", error);
					request.setAttribute("check", null);
				}
				else if(valid != false)
				{
					try{
						request.setAttribute("student", object.retriveStudent(name, Integer.parseInt(credit)));
						request.setAttribute("check", "correct");
					}
					catch (NumberFormatException e) 
					{
						e.printStackTrace();
					} 
					catch (Exception e) 
					{
						e.printStackTrace();
					}
				}
			}
			
			catch (NumberFormatException nfe) 
			{			
				try
				{
					Integer.parseInt(credit);
				}
				catch(NumberFormatException nf)
				{
					error = "Please enter digits only for credits!";
				}
				request.setAttribute("error", error);
				request.setAttribute("check", null);
			}
		
		}
	}
	
		else
		{
			request.setAttribute("check", null);
		}
		if(error != null)
		{
			request.setAttribute("name", name);
			request.setAttribute("credit", credit);
		}
		request.getRequestDispatcher("/Form.jspx").forward(request, response);
}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}
}

