package com.on.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.xdevapi.JsonArray;
import com.on.dao.Consumables;
import com.on.bean.Gentity;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class Allowan
 */
@WebServlet("/Allowan")
public class Allowan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Allowan() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("sname");
		request.setCharacterEncoding("utf-8");
	    response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8"); 
		Consumables cs=new Consumables();
		Gentity  gt = new Gentity();
		gt.setName(name);
		response.setContentType("text/json;charset=UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    PrintWriter out = response.getWriter();
	    List<Gentity> ls=cs.axsb(gt);
	    String json= cs.djson2(ls);
		out.write(json.toString());
		out.flush();
		out.close();
	}

}
