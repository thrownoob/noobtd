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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {//记录表动态查询
		// TODO Auto-generated method stub
		//从页面获取信息
		String name=req.getParameter("name");
		String zname=req.getParameter("tzhihang");
	    String ttime=req.getParameter("ttime");
		String cname=req.getParameter("cname");
		String tname=req.getParameter("tname");
         //转码
		 req.setCharacterEncoding("utf-8");
		  resp.setContentType("text/html;charset=utf-8");
		  resp.setCharacterEncoding("utf-8"); 
		  //new一些对象
		  Consumables cons = new Consumables();
		  Gentity gt = new Gentity();
		 Snamedao sd=new Snamedao();
		 //放入实体类
		 gt.setName(new String(name));
	     gt.setTzhihang(new String(zname));
		 gt.setTname(new String(tname));
		 gt.setTtime(new String(ttime));
		 gt.setCname(new String(cname));
	

        PrintWriter out = resp.getWriter();
        //查询返回list
		 ArrayList<Gentity> list = cons.axse(gt);
		 //list转json格式
		 String json=cons.djson(list);
		 out.write(json);
		 out.flush();
		 out.close();
           
	

		
	}  
}
