package com.wellsfargo.cpk.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wellsfargo.cpk.exception.CPKException;
import com.wellsfargo.cpk.model.CoronaKit;
import com.wellsfargo.cpk.model.KitDetail;
import com.wellsfargo.cpk.model.OrderSummary;
import com.wellsfargo.cpk.model.ProductMaster;
import com.wellsfargo.cpk.service.CKItemService;
import com.wellsfargo.cpk.service.CKItemServiceImpl;
import com.wellsfargo.cpk.service.PKItemService;
import com.wellsfargo.cpk.service.PKItemServiceImpl;


/**
 * Servlet implementation class FrontController
 */
@WebServlet({ "/list", "/validate", "/newItem", "/addItem", "/deleteItem", "/editItem", "/saveItem", "/saveuser" , "/include",
	"/newuser","/showproducts","/showkit","/placeorder","/saveaddress"})
public class SingleController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PKItemService itemService;
	private CKItemService itemService1;

	@Override
	public void init() throws ServletException {
		itemService = new PKItemServiceImpl();
		itemService1 = new CKItemServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url  = request.getServletPath();
		String viewName="";
		
		switch(url) {
		case "/validate":viewName = doValidate(request, response);break;
		case "/list":viewName = doList(request, response);break;
		case "/newItem":viewName=doNewITem(request, response);break;
		case "/addItem":viewName=doAddItem(request, response); break;
		case "/deleteItem":viewName=doDeleteItem(request, response);break;
		case "/editItem":viewName=doEditItem(request, response);break;
		case "/saveItem":viewName=doSaveItem(request, response);break;
		case "/newuser" : viewName = doNewUser(request, response);break;
		case "/saveuser" : viewName = doSaveUser(request, response);break;
		case "/showproducts" : viewName = doShowProducts(request, response);break;
		case "/showkit" : viewName = doShowKit(request, response);break;
		case "/placeorder" : viewName = doPlaceOrder(request, response);break;
		case "/include" : viewName = doInclude(request,response);break;
		case "/saveaddress" : viewName = doSaveAddress(request, response);break;
		
		}
		
		request.getRequestDispatcher(viewName).forward(request, response);
	}


	private String doSaveAddress(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		CoronaKit item = new CoronaKit();
		OrderSummary order = new OrderSummary();
		
		CoronaKit user =  (CoronaKit) session.getAttribute("user");
		List<KitDetail> kitList =  (List<KitDetail>) session.getAttribute("kitList");
		
		if(user==null)
		{
			request.setAttribute("msg", "User details not loading..Start new session");
		}else
		{item.setId(user.getId());
		item.setDeliveryAddress(request.getParameter("address"));}
		String address = request.getParameter("address");
		int i = kitList.size();
		int j=0;
		int qtotal=0;
		double total = 0;
		while(i>0)
		{ 
			
			total = total + kitList.get(j).getAmount();
			j++;
			i--;
		}	
		
		String view="";
		
		try {
			itemService1.cvalidateAndSave(item);
			request.setAttribute("msg", "User details updated with address!");
			request.setAttribute("address", address);
			request.setAttribute("total", total);
			view="ordersummary.jsp";
		} catch (CPKException e) {
			request.setAttribute("errMsg", e.getMessage());
			view="errorPage.jsp";
		}
		return view;
	}

	private String doInclude(HttpServletRequest request, HttpServletResponse response){
	
		HttpSession session = request.getSession();
		String view ="";

		List<KitDetail> kitList =  (List<KitDetail>) session.getAttribute("kitList");
		KitDetail item = new KitDetail();
		
		
		//System.out.println("BEFORE"+kitList.size());
		if(kitList==null)
		{
			kitList = new ArrayList<KitDetail>();
			
		}
		item.setId(Integer.parseInt(request.getParameter("pid")));
		
		double quantity = Double.parseDouble(request.getParameter("quantity"));
		item.setQuantity(Integer.parseInt(request.getParameter("quantity")));
		double cost = Double.parseDouble(request.getParameter("cost"));
		item.setCost(cost);
		double amount = cost*quantity;
		item.setAmount(amount);
		if(item!=null)
		{
			kitList.add(item);
		}
		session.setAttribute("kitList", kitList);
		request.setAttribute("msg", " Kit is updated!");
		
		view = "showproductstoadd.jsp";
		
		return view;
	}

	private String doSaveUser(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		CoronaKit item = new CoronaKit();
		
		item.setId(Integer.parseInt(request.getParameter("userid")));
		item.setPersonName(request.getParameter("username"));
		item.setEmail(request.getParameter("email"));
		item.setContactNumber((request.getParameter("contact")));
	
		
		String view="";
		
		try {
			itemService1.cvalidateAndAdd(item);
			session.setAttribute("user", item);
			request.setAttribute("msg", " New User details saved!");
			view="showproductstoadd.jsp";
		} catch (CPKException e) {
			request.setAttribute("errMsg", e.getMessage());
			view="errorPage.jsp";
		}
		return view;
	}

	private String doValidate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(request.getParameter("loginid").equalsIgnoreCase("admin") && request.getParameter("password").equalsIgnoreCase("admin"))
		{
		 return doList(request, response);}
		else {
			return "notfound.jsp";}
			
	}


	private String doPlaceOrder(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		CoronaKit item = new CoronaKit();
		request.setAttribute("item", item);
		
		return "placeorder.jsp";
	
	}

	private String doShowKit(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		// TODO Auto-generated method stub
		String view="";

		try {
			List<ProductMaster> items = itemService.getAllItems();
			session.setAttribute("items", items);
			view="placeorder.jsp";
		} catch (CPKException e) {
			request.setAttribute("errMsg", e.getMessage());
			view="errorPage.jsp";
		}
		
		return view;
	}

	private String doShowProducts(HttpServletRequest request, HttpServletResponse response) {
			String view="";
		
		try {
			List<ProductMaster> items = itemService.getAllItems();
			HttpSession session = request.getSession();
			session.setAttribute("items", items);
			view="showproductstoadd.jsp";
		} catch (CPKException e) {
			request.setAttribute("errMsg", e.getMessage());
			view="errorPage.jsp";
		}
		
		return view;
		// TODO Auto-generated method stub
	}

	private String doNewUser(HttpServletRequest request, HttpServletResponse response) {
		CoronaKit item = new CoronaKit();
		request.setAttribute("item", item);
		
		request.setAttribute("msg", "Welcome Admin");
		return "newuser.jsp";
		// TODO Auto-generated method stub
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private String doList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String view="";
		
		try {
			List<ProductMaster> items = itemService.getAllItems();
			request.setAttribute("items", items);
			view="listproducts.jsp";
		} catch (CPKException e) {
			request.setAttribute("errMsg", e.getMessage());
			view="errorPage.jsp";
		}
		
		return view;
	}

	private String doNewITem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ProductMaster item = new ProductMaster();
		request.setAttribute("item", item);
		request.setAttribute("msg", "Add New Item!");
		
		return "newproduct.jsp";
	}

	private String doAddItem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
		ProductMaster item = new ProductMaster();
		
		item.setId(Integer.parseInt(request.getParameter("pid")));
		item.setProductName(request.getParameter("pname"));
		item.setProductDescription(request.getParameter("pdesc"));
		item.setCost(Double.parseDouble(request.getParameter("cost")));

	
		
		String view="";
		
		try {
			itemService.validateAndAdd(item);
			request.setAttribute("msg", "Item Got Added!");
			view= doList(request, response);
		} catch (CPKException e) {
			request.setAttribute("errMsg", e.getMessage());
			view="errorPage.jsp";
		}
		return view;
	}

	private String doDeleteItem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("pid"));
		String view="";
		
		try {
			itemService.deleteItem(id);
			request.setAttribute("msg", "Item Got Deleted!");
			view=doList(request, response);
		} catch (CPKException e) {
			request.setAttribute("errMsg", e.getMessage());
			view="errorPage.jsp";
		}
		return view;
	}

	private String doEditItem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("pid"));
		String view="";
						
		try {
			ProductMaster item = itemService.getItemById(id);
			request.setAttribute("item", item);
			request.setAttribute("msg", "Update Item");
			view="editproduct.jsp";
		} catch (CPKException e) {
			request.setAttribute("errMsg", e.getMessage());
			view="errorPage.jsp";
		}
		return view;
	}

	private String doSaveItem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductMaster item = new ProductMaster();
		
		item.setId(Integer.parseInt(request.getParameter("pid")));
		item.setProductName(request.getParameter("pname"));
		item.setProductDescription(request.getParameter("pdesc"));
		item.setCost(Double.parseDouble(request.getParameter("cost")));
		
		
		String view="";
		
		try {
			itemService.validateAndSave(item);
			request.setAttribute("msg", "Item Got Saved!");
			view=doList(request, response);
		} catch (CPKException e) {
			request.setAttribute("errMsg", e.getMessage());
			view="errorPage.jsp";
		}
		return view;
	}
}
