package com.on.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.on.dao.Consumables;
import com.on.dao.Gentity;

@WebServlet("/dexservlet")
public class Dexservlet extends HttpServlet{
	
	 private static final long serialVersionUID = 1L;
	 
	 @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	 
	 @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 
//		 
//		 req.setCharacterEncoding("utf-8");
//		  //resp.setContentType("text/html;charset=utf-8");
//		 resp.setCharacterEncoding("utf-8"); 
		
		String tzhihang=req.getParameter("atzhihang");
		String name=req.getParameter("aname");
		String tnumber=req.getParameter("tnumber");
		String cpers=req.getParameter("cname");
		String tpers=req.getParameter("tname");
		String biaoshi=req.getParameter("tip");
		
	  
		Consumables cs=new Consumables();
		Gentity  gt = new Gentity();
	
	    
	    gt.setTip(Integer.valueOf(biaoshi));
	    gt.setName(name);
	    gt.setTnumber(Integer.valueOf(tnumber));
	    gt.setTzhihang(tzhihang);
	    gt.setCname(cpers);
	    gt.setTname(tpers);
	    List<Gentity> ls=cs.axne(gt);
	    PrintWriter out = resp.getWriter();
	    System.out.println(ls);
	    if(ls.isEmpty()) {
	    	if(gt.getTip()==10) {
	    		out.write(cs.add(gt));
	    	//	cs.add(gt);
	    	//	 req.getRequestDispatcher("index.jsp").forward(req, resp);
	    	}else {
	    		resp.sendRedirect("index.jsp");
	    	}
	    	
	    }else {
	    	out.write(cs.dex(gt));
	    	//cs.dex(gt);
	    	// req.getRequestDispatcher("index.jsp").forward(req, resp);
		}
	   
	    
	    
	    
	    
	    
	}

}
