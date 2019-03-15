package com.on.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.on.jdbc.Dbhelp;
import com.sun.org.apache.regexp.internal.recompile;

import javafx.scene.shape.Line;

public class Consumables {
           
	
	public List<Gentity> getall(){ //全部查询
		

		String sql= "select * from haocai";
		 
			
		System.out.println(Dbhelp.getList(Gentity.class, sql, null));
		
		return Dbhelp.getList(Gentity.class, sql, null);
		
		
		
		
	}
	 public static String djson(List<Gentity> list) {
		 String json="";
		 for (Gentity gt : list) { 
			 String line="{\"name\":\""+gt.getName()+ "\",\"tzhihang\":\""+gt.getTzhihang()+"\","+ "\"number\":"+gt.getTnumber()+","+"\"cname\":\"" +gt.getCname()+"\","+"\"gcnum\":\"" +gt.getGcnum()+"\","+"\"tip\":" +gt.getTip()+","+"\"tname\":\""+gt.getTname()+"\","+"\"time\":\""+gt.getTtime()+"\"},"+ "\r\n"; json=json+line; } 
          
         if(json.length()>3) {
		 json="["+json.substring(0,json.length()-3)+"]";
         }else {
			return "[{\"name\":\"null\"}]";
		}
		 return json;
		
		
		 
	 }
	 public static String djson2(List<Gentity> list) {
		 String json="";
		 for (Gentity gt : list) { 
			 String line="{\"name\":\""+gt.getName()+"\","+ "\"number\":\""+gt.getCnumber()+"\","+"\"time\":\""+gt.getCtime()+"\"},"+ "\r\n"; json=json+line; } 
          
         if(json.length()>3) {
		 json="["+json.substring(0,json.length()-3)+"]";
         }else {
			return "[{\"name\":\"null\"}]";
		}
		 return json;
		
		
		 
	 }
	 public String add(Gentity gt) { //入库新增
		 String sql = " INSERT INTO haocai (name,cnumber,cid,ctime) VALUES (?,?,?,now())";
		 Connection conn =Dbhelp.getConnection();
		   try {
			   //预编译
			   PreparedStatement ps=conn.prepareStatement(sql);
			   //？赋值
			   ps.setString(1, gt.getName());
			   ps.setInt(2, gt.getTnumber());
			   ps.setString(3, Uuiddao.getUUID());
	          //执行
			   ps.executeUpdate();
			   ps.close();
			   return "ok";
			  
		   }catch (SQLException e) {
			// TODO: handle exception
			   e.printStackTrace();
			   
		}
		return "ok";
		 
	 }
	 
	 public String dex(Gentity y , int a) { //出入库变更
		 
		 String sql1 ="INSERT INTO jilu (name,ttime,tnumber,tzhihang,tname,cname,tip) VALUES (?,now(),?,?,?,?,?)";//记录添加 
		 String sql2 = "UPDATE haocai SET cnumber=cnumber-? WHERE name=?";//出库操作余量 
		 String sql3="UPDATE haocai SET cnumber=cnumber+? WHERE name=?";//入库操作余量
		 String sql4="UPDATE jilu SET gcnum = (SELECT cnumber FROM haocai WHERE name = ?) WHERE ttime = now()";//记录实时余量
         
		
		 Connection conn =Dbhelp.getConnection();
		 Consumables cons = null;
	
		 try {
			 if(y.getTip()==10) {
				 return "nokey";//判断是否新增
			 }
			 else {
		      //出入库添加纪录
				PreparedStatement ps1=conn.prepareStatement(sql1);
			    ps1.setString(1, y.getName()); 
				ps1.setInt(2, y.getTnumber());
				ps1.setString(3, y.getTzhihang());
				ps1.setString(4, y.getTname());
				ps1.setString(5, y.getCname());
				ps1.setInt(6, y.getTip());
				int cont=ps1.executeUpdate();//执行成功为1 失败为0
				ps1.close();
				  if(cont>0) {//成功
					 if(y.getTip()==1) {//出库
						 if(a>=y.getTnumber()) {
							 
							   PreparedStatement ps2= conn.prepareStatement(sql2);
							   ps2.setInt(1, y.getTnumber());
							   ps2.setString(2, y.getName());
							   ps2.executeUpdate();//执行sql2改变余量
							   PreparedStatement ps4= conn.prepareStatement(sql4);
							   ps4.setString(1, y.getName());
							   ps4.executeUpdate();//执行sql4记录实时余量
							   ps2.close();
							   ps4.close();
						 }else {
							 return "numer";
						 }
						
					 }else {
						 if(y.getTip()==0){//入库
							PreparedStatement ps3= conn.prepareStatement(sql3);
							ps3.setInt(1, y.getTnumber());
							ps3.setString(2, y.getName());
							ps3.executeUpdate();//执行sql3改变余量
							PreparedStatement ps4= conn.prepareStatement(sql4);
							ps4.setString(1, y.getName());
							ps4.executeUpdate();//执行sql4记录实时余量
							ps3.close();
							ps4.close();
					  }
					  
				  }		
				  }
			 }
			 } catch (SQLException e) {
				e.printStackTrace();
			}
		 
		
		return "ok";
		 
		 
	 }
	
	 public void cnum(Gentity y){//按name查询
		  
	     Snamedao sname = new Snamedao();
		 String sql="SELECT cnumber FROM haocai WHERE name=?";
		 Connection conn= Dbhelp.getConnection();
		 List<Gentity> list = new ArrayList<Gentity>();
		 ResultSet rs =null;
		 PreparedStatement ps=null;
			try {
				//预编译sql
				ps=conn.prepareStatement(sql);
				//？赋值
				ps.setString(1, y.getName());
				//执行返回
			    rs=ps.executeQuery();
			int t =rs.getInt(1);
	          
			 
			    
			
			    rs.close();
			    ps.close();
				
			}  catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		 }	 
	 public List<Gentity> axne(Gentity y){//按name查询
		  
     Snamedao sname = new Snamedao();
	 String sql="SELECT * FROM haocai WHERE name=?";
	 Connection conn= Dbhelp.getConnection();
	 List<Gentity> list = new ArrayList<Gentity>();
	 ResultSet rs =null;
	 PreparedStatement ps=null;
		try {
			//预编译sql
			ps=conn.prepareStatement(sql);
			//？赋值
			ps.setString(1, y.getName());
			//执行返回
		    rs=ps.executeQuery();
           //放入list
		    while(rs.next()) {
		    	
		    	 y.setName(rs.getString("name"));
		    	 y.setCnumber(rs.getInt("cnumber"));
		    	 y.setCtime(rs.getString("ctime"));
		    	list.add(y);
		    }
		    rs.close();
		    ps.close();
			
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	
	 }	 
	 public static List<String> getDatime(String title) {
	        List<String> dateStrList = new ArrayList<>();
	        String dateStr = "";
	        if (StringUtils.isNotEmpty(title)) {
	            Pattern p = Pattern.compile("(\\d{4})-(\\d{1,2})-(\\d{1,2})");
	            Matcher m = p.matcher(title);
	            while (m.find()) {
	                dateStr += m.group() + "到";
	            }
	        }
	        if (StringUtils.isNotEmpty(dateStr)) {
	            String str[] = dateStr.split("到");
	            if (StringUtils.isNotEmpty(str[0])) {
	                dateStrList.add(str[0]);
	            }
	            if (StringUtils.isNotEmpty(str[1])) {
	                dateStrList.add(str[1]);
	            }
	        }
	        return dateStrList;
	    }


	 public ArrayList<Gentity> axsb(Gentity y) {//动态查询耗材量
		 
		  Snamedao sname = new Snamedao();
		 String sql="SELECT * FROM haocai WHERE 1=1 "+sname.Gentitywhere(y);
		 Connection conn= Dbhelp.getConnection();
		 ArrayList<Gentity> list = new ArrayList<Gentity>();
		ResultSet rs =null;
		PreparedStatement ps=null;
			 
		 try {
				 ps=conn.prepareStatement(sql);
				
				 sname.Gentityget(y, ps);
				 
				 rs=ps.executeQuery();
			 
				while(rs.next()) {
					
					
				String name=rs.getString("name");
		
				String ctime=rs.getString("ctime");
				int cnumber = rs.getInt("cnumber");
		
					Gentity gy = new Gentity(name,ctime,cnumber);
					list.add(gy);
					
				}
				rs.close();
				ps.close();
				
			}  catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			 
			 
			
	
		 
		return list;
		 
		 
	 }
	 
	 public ArrayList<Gentity> axse(Gentity y) {//动态查询耗材量
		 
		  Snamedao sname = new Snamedao();
		  //动态sql语句 保持永远成立1=1 
		 String sql="SELECT * FROM jilu WHERE 1=1 "+sname.Gentitywhere(y);
		 Connection conn= Dbhelp.getConnection();
		 ArrayList<Gentity> list = new ArrayList<Gentity>();
		ResultSet rs =null;
		PreparedStatement ps=null;
			 
		 try {
			 //预编译sql
				 ps=conn.prepareStatement(sql);
			// preparestatement 也要动态
				 sname.Gentityget(y, ps);
		     // 执行并返回数据
				 rs=ps.executeQuery();
			 //取出数据
				while(rs.next()) {
					
					
				String name=rs.getString("name");
				String tzhihang=rs.getString("tzhihang");
				String cname=rs.getString("cname");
				int tnumber = rs.getInt("tnumber");
				String tname = rs.getString("tname");
				String ttime = rs.getString("ttime");
				String gcnum = rs.getString("gcnum");
				int tip = rs.getInt("tip");
				Gentity gy = new Gentity(name,ttime, tnumber, tzhihang,  tname,  cname , gcnum ,tip);
				//使用list取出	
				list.add(gy);
					
				}
				rs.close();
				ps.close();
				
			}  catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			 
			 
			
	
		 
		return list;
		 
		 
	 }
	 
	 
	 
	
	
}
