package co.mongde.board.vo;

import java.sql.Date;

public class BoardVO {
	private int bid;
	private String bname;
	private String btitle;
	private String bcontent;
	private Date bdate;
	private int bhit;
	private int bgroup;
	private int bstep;
	private int bident;
	
	public BoardVO() {
		// TODO Auto-generated constructor stub
	}

	public int getBid() {return bid;}
	public void setBid(int bid) {this.bid = bid;}
	public String getBname() {return bname;}
	public void setBname(String bname) {this.bname = bname;}
	public String getBtitle() {return btitle;}
	public void setBtitle(String btitle) {this.btitle = btitle;}
	public String getBcontent() {return bcontent;}
	public void setBcontent(String bcontent) {this.bcontent = bcontent;}
	public Date getBdate() {return bdate;}
	public void setBdate(Date bdate) {this.bdate = bdate;}
	public int getBhit() {return bhit;}
	public void setBhit(int bhit) {this.bhit = bhit;}
	public int getBgroup() {return bgroup;}
	public void setBgroup(int bgroup) {this.bgroup = bgroup;}
	public int getBstep() {return bstep;}
	public void setBstep(int bstep) {this.bstep = bstep;}
	public int getBident() {return bident;}
	public void setBident(int bident) {this.bident = bident;}
	
	
}
