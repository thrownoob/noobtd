package com.on.dao;



public class Gentity {
	
	
	private int cid;
	private String name;
    private int cnumber;
    private String ctime;
    private String tid;
    private String ttime;
    private int tnumber;
    private int tip;
    private String tzhihang;
    private String tname;
    private String cname;
    private String gcnum;

	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCnumber() {
		return cnumber;
	}
	public void setCnumber(int cnumber) {
		this.cnumber = cnumber;
	}
	public String getCtime() {
		return ctime;
	}
	public void setCtime(String ctime) {
		this.ctime = ctime;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getTtime() {
		return ttime;
	}
	public void setTtime(String ttime) {
		this.ttime = ttime;
	}
	public int getTnumber() {
		return tnumber;
	}
	public void setTnumber(int tnumber) {
		this.tnumber = tnumber;
	}
	public int getTip() {
		return tip;
	}
	public void setTip(int tip) {
		this.tip = tip;
	}
	public String getTzhihang() {
		return tzhihang;
	}
	public void setTzhihang(String tzhihang) {
		this.tzhihang = tzhihang;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	@Override
	public String toString() {
		return "Gentity [cid=" + cid + ", name=" + name + ", cnumber=" + cnumber + ", ctime=" + ctime + ", tid=" + tid
				+ ", ttime=" + ttime + ", tnumber=" + tnumber + ", tip=" + tip + ", tzhihang=" + tzhihang + ", tname="
				+ tname + ", cname=" + cname + "]";
	}

	public Gentity(String name, String ttime, int tnumber, String tzhihang, String tname, String cname , String gcnum ,int tip) {
		super();
		this.name = name;
		this.ttime = ttime;
		this.tnumber = tnumber;
		this.tzhihang = tzhihang;
		this.tname = tname;
		this.cname = cname;
		this.gcnum =gcnum;
		this.tip = tip;
		
	}

	public Gentity(String name2, String ctime2, int cnumber2) {
		super();
		this.name=name2;
		this.ctime=ctime2;
		this.cnumber=cnumber2;
		// TODO Auto-generated constructor stub
	}
	public Gentity() {
		// TODO Auto-generated constructor stub
	}
	public String getGcnum() {
		return gcnum;
	}
	public void setGcnum(String gcnum) {
		this.gcnum = gcnum;
	}
	
    
   
}
