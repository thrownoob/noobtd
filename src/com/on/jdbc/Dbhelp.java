package com.on.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.mysql.cj.jdbc.Driver;

public class Dbhelp {
    
//	 private static String url = "jdbc:mysql://localhost/yx?useSSL = false&serverTimezone = UTC&";
//	 private static String userName ="root";
//	 private static String password ="123456";
//	 private static Connection conn= null;
//	 
//	 
//	 public static Connection getConnection() {
//		 
//		 if (null==conn) {
//			 
//			 try {
//				 
//				 Class.forName("com.mysql.cj.jdbc.Driver");
//				 conn=DriverManager.getConnection(url,userName,password);
//				 
//				
//			} catch (Exception e) {
//				// TODO: handle exception
//				e.printStackTrace();
//			}
//		 }
//		return conn;
//		 
//		 
//	 }
	 
//	 public static void main(String[] args) {
//		System.err.println(getConnection());
//	}

		private static Connection conn;
		private static PreparedStatement ps;
		private static ResultSet rs;
		//静态代码块
		static {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (Exception e) {
				System.out.println("忘记导入mysql的jar包");
				e.printStackTrace();
			}
		}
		


		//获得Connection  
		public static Connection getConnection(){
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost/haocai?useSSL = false&serverTimezone = UTC&characterEncoding=utf8", "root", "123456");
			} catch (Exception e) {
				System.out.println("获得Connection失败...（数据库忘记改名称了)");
				e.printStackTrace();
			}
			return conn;
		}
		public ResultSet execQuery(final String strSQL,final Object[] params){
			getConnection();
			System.out.println("SQL:>"+strSQL);
			try{
				ps=conn.prepareStatement(strSQL);
				for(int i=0;i<params.length;i++){
					ps.setObject(i+1, params[i]);
				}
				rs=ps.executeQuery();
				return rs;
			} catch(SQLException e){
				e.printStackTrace();

				return null;
				}
			
		}
		//获取预编译对象
		public static PreparedStatement getPs(String sql,Object...args){
			try {
				conn =getConnection();
				ps = conn.prepareStatement(sql);
				if(args!=null){//证明sql有？占位符  需要ps.setObject();
					for(int i=1;i<=args.length;i++){
						System.out.println("可变参数是：："+args[i-1]);
						ps.setObject(i, args[i-1]);
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return ps;
		} 
		//查询集合
		public static <T> List<T> getList(Class<T> clazz,String sql,Object...args){
			List<T> list = new ArrayList<T>();
			T t=null;
			
			try {
				System.out.println(args);
				System.out.println(sql);
				ps=getPs(sql,args);
				rs = ps.executeQuery();
				//获取此ResultSet 对象的列的编号、类型和属性
				ResultSetMetaData metaData=rs.getMetaData();
				// 返回此ResultSet 对象中的列数
				int count= metaData.getColumnCount();			
				while(rs.next()){
					t=clazz.newInstance();//通过反射创建对象 想给t对象赋值 只能通过set方法赋值
					for(int i=1;i<=count;i++){
						//获得每一列的名称 metaData.getColumnName(i)
						String name = metaData.getColumnName(i);
						//System.out.println(name);
						//String dddd = (String)rs.getObject(i);
						
						BeanUtils.copyProperty(t, name, rs.getObject(i));
					}
					list.add(t);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				close(rs, ps, conn);
			}		
			return list;
		}
		//增加 删除 修改
		public static int executeSql(String sql,Object... args){
			int i=0;
			try {
				ps=getPs(sql,args);
				i = ps.executeUpdate();
				for (Object sssString : args) {
					System.out.println(sssString);
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally{
				close(rs, ps, conn);
			}			
			return i;
		}

		//查询条数
			public static int getConut(String sql,Object... args){
				int count=0;
				try {
					ps = getPs(sql, args);
					rs = ps.executeQuery();
					if(rs.next()){
						count=rs.getInt(1);
					}
				} catch (Exception e) {
					System.out.println("查询条数失败。。。。");
					e.printStackTrace();
				} finally{
					close(rs, ps, conn);
				}
				return count;
			}
			
		//根据用户名查找企业号
		public static String queryForField(String sql,Object... args) {
			
			String field = null;
			try {
				ps=getPs(sql,args);
				rs = ps.executeQuery();
				//获取此ResultSet 对象的列的编号、类型和属性
				ResultSetMetaData metaData=rs.getMetaData();
				// 返回此ResultSet 对象中的列数
				int count= metaData.getColumnCount();
				while (rs.next()){
					field = (String)rs.getObject(count);;
				}
				

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally{
				close(rs, ps, conn);
			}			
			return field;
		}
		
		//通过条件查询对象
		public static <T> T getObject(Class<T> clazz,String sql,Object... args){
			T t=null;		
			try {
				ps=getPs(sql,args);
				rs = ps.executeQuery();
				//获取此ResultSet 对象的列的编号、类型和属性
				ResultSetMetaData metaData=rs.getMetaData();
				// 返回此ResultSet 对象中的列数
				int count= metaData.getColumnCount();			
				while(rs.next()){
					t=clazz.newInstance();//通过反射创建对象 想给t对象赋值 只能通过set方法赋值
					for(int i=1;i<=count;i++){
						//获得每一列的名称 metaData.getColumnName(i)
						String name = metaData.getColumnName(i);
						//System.out.println(name);
						BeanUtils.copyProperty(t, name, rs.getObject(i));
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				close(rs, ps, conn);
			}		
			return t;
		} 
		
		//关闭连接
		public static void close(ResultSet rs,PreparedStatement ps,Connection conn){
			try {
				if(rs!=null){
					rs.close();
				}if(ps!=null){
					ps.close();
				}if(conn!=null){
					conn.close();
				}
				
			} catch (Exception e) {
				System.out.println("关闭连接失败...");
				e.printStackTrace();
			}
		}
		

}
  
