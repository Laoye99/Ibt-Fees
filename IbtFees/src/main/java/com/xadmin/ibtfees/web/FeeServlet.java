package com.xadmin.ibtfees.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xadmin.ibtfees.bean.Fee;
import com.xadmin.ibtfees.dao.FeeDao;

/**
 * Servlet implementation class FeeServlet
 */
@WebServlet("/")
public class FeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FeeDao feeDao;
       
   
	public void init(ServletConfig config) throws ServletException {
		feeDao = new FeeDao();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		switch (action) {
	    case "/new":
	        showNewForm(request, response);
	        break;

	    case "/insert":
	        try {
	            insertFee(request, response);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        break;

	    case "/delete":
	        try {
	            deleteFee(request, response);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        break;

	    case "/edit":
	        try {
	            showEditForm(request, response);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (ServletException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        break;

	    case "/update":
	        try {
	            updateFee(request, response);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        break;

	    default:
	        try {
	            listFee(request, response);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (ServletException e) {
	            e.printStackTrace();
	        }
	        break;
	}
	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("fee-form.jsp");
		dispatcher.forward(request, response);
	
}
	private void insertFee(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
		int acsn = Integer.parseInt(request.getParameter("acsn"));
		float feeAmt = Float.parseFloat(request.getParameter("fee_amt"));
		String feeTypeId = request.getParameter("fee_type_id");
		
		  HttpSession session = request.getSession();
		    RequestDispatcher dispatcher = null;
		    
		    if (feeTypeId == null || feeTypeId.equals("") || (!feeTypeId.equals("B") && !feeTypeId.equals("V") && !feeTypeId.equals("F"))) {
		        dispatcher = request.getRequestDispatcher("fee-form.jsp");
		        dispatcher.forward(request, response);
		    } else { 
		        Fee newFee = new Fee(acsn, feeAmt, feeTypeId);
		        feeDao.insertFee(newFee);
		        response.sendRedirect("list");
		    }

	}	    
	
	private void deleteFee(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int fsn = Integer.parseInt(request.getParameter("fsn"));
		feeDao.deleteFee(fsn);
		response.sendRedirect("list");

	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int fsn = Integer.parseInt(request.getParameter("fsn"));
		Fee existingFee;
		try {
		existingFee= feeDao.selectFee(fsn);
		RequestDispatcher dispatcher = request.getRequestDispatcher("fee-form.jsp");
		request.setAttribute("fee", existingFee);
		dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	private void updateFee(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
	    int fsn = Integer.parseInt(request.getParameter("fsn"));
	    int acsn = Integer.parseInt(request.getParameter("acsn"));
	    float feeAmt = Float.parseFloat(request.getParameter("fee_amt"));
	    String feeTypeId = request.getParameter("fee_type_id");
	    
	    HttpSession session = request.getSession();
	    RequestDispatcher dispatcher = null;
	    
	    if (feeTypeId == null || feeTypeId.equals("") || (!feeTypeId.equals("B") && !feeTypeId.equals("V") && !feeTypeId.equals("F"))) {
	        dispatcher = request.getRequestDispatcher("fee-form.jsp");
	        dispatcher.forward(request, response);
	    } else { 
	        Fee newFee = new Fee(acsn, feeAmt, feeTypeId);
	        feeDao.insertFee(newFee);
	        response.sendRedirect("list");
	    }
	}
	private void listFee(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		
		try {
		List<Fee> listFee = feeDao.selectAllFees();
		request.setAttribute("listFee", listFee);
		RequestDispatcher dispatcher = request.getRequestDispatcher("fee-list.jsp");
		dispatcher.forward(request, response);
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
}

