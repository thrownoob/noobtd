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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {//出入库
		// TODO Auto-generated method stub
		 
//		 
//		 req.setCharacterEncoding("utf-8");
//		  //resp.setContentType("text/html;charset=utf-8");
//		 resp.setCharacterEncoding("utf-8"); 
		//从网页取出数据
		String tzhihang=req.getParameter("atzhihang");
		String name=req.getParameter("aname");
		String tnumber=req.getParameter("tnumber");
		String cpers=req.getParameter("cname");
		String tpers=req.getParameter("tname");
		String biaoshi=req.getParameter("tip");
		
	  //。。对象
		Consumables cs=new Consumables();
		Gentity  gt = new Gentity();
	    int a = 0;
	    //实体类赋值
	    gt.setTip(Integer.valueOf(biaoshi));
	    gt.setName(name);
	    gt.setTnumber(Integer.valueOf(tnumber));
	    gt.setTzhihang(tzhihang);
	    gt.setCname(cpers);
	    gt.setTname(tpers);
	    //按name查余量表
	    List<Gentity> ls=cs.axne(gt);
	   for (Gentity gentity : ls) {
		a=gentity.getCnumber();
	}
	    PrintWriter out = resp.getWriter();
	    
	    if(ls.isEmpty()) {//空的话验证是否为新增
	    	if(gt.getTip()==10) {//新增
	    		out.write(cs.add(gt));
	    	}else {
	    		//表内查不到数据 又不为新增
	    		out.write("fail");
	    	}
	    	
	    }else {
	    	//出入库
	    	out.write(cs.dex(gt,a));
	     }
	   
	    
	    
	    
	    
	    
	}

}
