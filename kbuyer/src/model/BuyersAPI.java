package model;

import java.io.IOException;
import java.util.HashMap; 
import java.util.Map; 
import java.util.Scanner;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class projectsAPI
 */
@WebServlet("/ProjectsAPI")
public class BuyersAPI extends HttpServlet {
	Buyer buyerObj = new Buyer();
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyersAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String output = buyerObj.insertBuyer(request.getParameter("buyerFname"), 
				request.getParameter("buyerLname"), 
				request.getParameter("buyerGender"), 
				request.getParameter("buyerAddress"),
				request.getParameter("buyerPhone"),
				request.getParameter("buyerNic"),
				request.getParameter("buyerBirthday"),
				request.getParameter("buyerEmail"),
				request.getParameter("buyerPassword") 
				);
				response.getWriter().write(output);
	}

	
	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request) 
	{ 
	 Map<String, String> map = new HashMap<String, String>();
	try
	 { 
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
	 String queryString = scanner.hasNext() ? 
	 scanner.useDelimiter("\\A").next() : ""; 
	 scanner.close(); 
	 String[] params = queryString.split("&"); 
	 for (String param : params) 
	 { 
	String[] p = param.split("=");
	 map.put(p[0], p[1]); 
	 } 
	 } 
	catch (Exception e) 
	 { 
	 } 
	return map; 
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map paras = getParasMap(request); 
		 String output = buyerObj.updateBuyer(paras.get("hidBuyerIDSave").toString(), 
				 paras.get("buyerFname").toString(), 
				 paras.get("buyerLname").toString(), 
				paras.get("buyerGender").toString(),
				paras.get("buyerAddress").toString(), 
				paras.get("buyerPhone").toString(),
				paras.get("buyerNic").toString(),
				paras.get("buyerBirthday").toString()); 
		response.getWriter().write(output); 
	}


	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	Map paras = getParasMap(request); 
		
		String output = buyerObj.deleteBuyer(paras.get("BuyerID").toString()); 
		response.getWriter().write(output);
	}

}