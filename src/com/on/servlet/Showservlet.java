package com.on.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.xdevapi.JsonArray;
import com.on.dao.Consumables;
import com.on.bean.Gentity;

import com.on.jdbc.Dbhelp;

import net.sf.json.JSONObject;
import sun.net.www.content.image.gif;
@WebServlet("/showservlet")
public class Showservlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
  @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	
    

	super.doGet(req, resp);
}
  @Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	  req.setCharacterEncoding("utf-8");
	  resp.setContentType("text/html;charset=utf-8");
	  resp.setCharacterEncoding("utf-8"); 
	  Consumables cons = new Consumables();
	  List<Gentity> list = cons.getall();
	  String json=cons.djson(list);
	  PrintWriter out = resp.getWriter();
//	  req.setAttribute("jso", list);
	  out.println(json);
	  out.close();
	  
  }
}
