package com.on.text;

import java.util.ArrayList;
import java.util.List;

import com.on.dao.Consumables;
import com.on.dao.Gentity;
import com.on.dao.Snamedao;


public class text {
   
	public static void main(String[] args) {
	
	Consumables cons=new Consumables();
	Gentity gt=new Gentity();
	gt.setName("3108");
      List<Gentity> ls= cons.axne(gt);
    for (Gentity gentity : ls) {
		int a= gentity.getCnumber();
		System.out.println(a);
	}
}
}