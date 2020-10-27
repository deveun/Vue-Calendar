
package com.kt.calendar.vo;

public class Calendar {
	private int seqNo;
	private String title;
	private String content;
	private String creator;
	private String team;
	private String startDt;
	private String endDt;
	private String cretDt;
	
	public Calendar(int seq_no, String title, String content, String creator, String team, String startDt, String endDt,
			String cretDt) {
		super();
		this.seqNo = seq_no;
		this.title = title;
		this.content = content;
		this.creator = creator;
		this.team = team;
		this.startDt = startDt;
		this.endDt = endDt;
		this.cretDt = cretDt;
	}
	
	public int getSeq_no() {
		return seqNo;
	}
	public void setSeq_no(int seq_no) {
		this.seqNo = seq_no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getcontent() {
		return content;
	}
	public void setcontent(String content) {
		this.content = content;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public String getstartDt() {
		return startDt;
	}
	public void setstartDt(String startDt) {
		this.startDt = startDt;
	}
	public String getendDt() {
		return endDt;
	}
	public void setendDt(String endDt) {
		this.endDt = endDt;
	}
	public String getcretDt() {
		return cretDt;
	}
	public void setcretDt(String cretDt) {
		this.cretDt = cretDt;
	}
	
	
}
