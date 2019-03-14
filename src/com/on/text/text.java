package com.on.text;

import java.util.ArrayList;

import com.on.dao.Consumables;
import com.on.dao.Gentity;
import com.on.dao.Snamedao;


public class text {
   
	public static void main(String[] args) {
	
	Consumables cons=new Consumables();
	Gentity gt=new Gentity();
	gt.setName("晓");
    gt.setTnumber(15);
   
    
    cons.add(gt);
}
}