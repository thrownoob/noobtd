package com.on.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Snamedao {
    
	public String Gentitywhere(Gentity gt) {
		StringBuffer sbf=new StringBuffer();
		if(gt.getName()!=null && gt.getName().trim().length()>0) {
			sbf.append(" and name = ?");
		}
		if (gt.getTzhihang()!=null && gt.getTzhihang().trim().length()>0) {
			sbf.append("and tzhihang=?");
		}
		if(gt.getTname()!=null && gt.getTname().trim().length()>0) {
			sbf.append("and tname=?");
		}
		if(gt.getTtime()!=null && gt.getTtime().trim().length()>0) {
		
			sbf.append("and ttime BETWEEN ? and ?");
		}
		if(gt.getCname()!=null && gt.getCname().trim().length()>0) {
			sbf.append("and cname=?");
		}
		return sbf.toString();

		  
		
		

	}
	
	public void  Gentityget(Gentity gt,PreparedStatement pst)throws SQLException{
		
		int count =1; 
		
		if(gt.getName()!=null && gt.getName().trim().length()>0) {
			
			 pst.setString(count++, gt.getName());
		}
		if (gt.getTzhihang()!=null && gt.getTzhihang().trim().length()>0) {
			pst.setString(count++, gt.getTzhihang());
		}
		if(gt.getTtime()!=null && gt.getTtime().trim().length()>0) {
			List<String> list = Consumables.getDatime(gt.getTtime());
		    
			pst.setString(count++, list.get(0));
			pst.setString(count++, list.get(1));
			
		}
		if(gt.getTname()!=null && gt.getTname().trim().length()>0) {
			pst.setString(count++, gt.getTname());
		}
		if(gt.getCname()!=null && gt.getCname().trim().length()>0){
			pst.setString(count++, gt.getCname());
		}
		
		
	}
	
}
