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

import com.mysql.cj.xdevapi.JsonArray;
import com.on.dao.Consumables;
import com.on.dao.Gentity;
import com.on.dao.Snamedao;
import com.on.jdbc.Dbhelp;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/snameservlet")
public class Snameservlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name=req.getParameter("name");
		String zname=req.getParameter("tzhihang");
	    String ttime=req.getParameter("ttime");
		String cname=req.getParameter("cname");
		String tname=req.getParameter("tname");

		 req.setCharacterEncoding("utf-8");
		  resp.setContentType("text/html;charset=utf-8");
		  resp.setCharacterEncoding("utf-8"); 
		  Consumables cons = new Consumables();
		  Gentity gt = new Gentity();
		 Snamedao sd=new Snamedao();
		 gt.setName(new String(name.getBytes("ISO-8859-1"),"UTF-8"));
		 
//		 gt.setName(new String(req.getParameter(name).getBytes("ISO-8859-1"),"UTF-8"));
		 
		 gt.setTzhihang(new String(zname.getBytes("ISO-8859-1"),"UTF-8"));
		  gt.setTname(new String(tname.getBytes("ISO-8859-1"),"UTF-8"));
		  gt.setTtime(new String(ttime.getBytes("ISO-8859-1"),"UTF-8"));
		gt.setCname(new String(cname.getBytes("ISO-8859-1"),"UTF-8"));
	
		resp.setContentType("text/json;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
		 ArrayList<Gentity> list = cons.axse(gt);
		 String json=cons.djson(list);
		 out.write(json);
		 out.flush();
		 out.close();
           
	

		
	}  
}
