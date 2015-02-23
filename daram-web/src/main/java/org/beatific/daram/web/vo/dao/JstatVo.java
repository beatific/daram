package org.beatific.daram.web.vo.dao;

import java.util.Date;

public class JstatVo {

	private String server;
	private Double s0;
	private Double s1;
	private Double e;
	private Double o;
	private Double p;
	private Double ygc;
	private Double ygct;
	private Double fgc;
	private Double fgct;
	private Double gct;
	private Date jstatTime;
	private String jstatId;
	private String fromTime; 
	private String toTime;
	
	public String getFromTime() {
		return fromTime;
	}
	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}
	public String getToTime() {
		return toTime;
	}
	public void setToTime(String toTime) {
		this.toTime = toTime;
	}
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	public Double getS0() {
		return s0;
	}
	public void setS0(Double s0) {
		this.s0 = s0;
	}
	public Double getS1() {
		return s1;
	}
	public void setS1(Double s1) {
		this.s1 = s1;
	}
	public Double getE() {
		return e;
	}
	public void setE(Double e) {
		this.e = e;
	}
	public Double getO() {
		return o;
	}
	public void setO(Double o) {
		this.o = o;
	}
	public Double getP() {
		return p;
	}
	public void setP(Double p) {
		this.p = p;
	}
	public Double getYgc() {
		return ygc;
	}
	public void setYgc(Double ygc) {
		this.ygc = ygc;
	}
	public Double getYgct() {
		return ygct;
	}
	public void setYgct(Double ygct) {
		this.ygct = ygct;
	}
	public Double getFgc() {
		return fgc;
	}
	public void setFgc(Double fgc) {
		this.fgc = fgc;
	}
	public Double getFgct() {
		return fgct;
	}
	public void setFgct(Double fgct) {
		this.fgct = fgct;
	}
	public Double getGct() {
		return gct;
	}
	public void setGct(Double gct) {
		this.gct = gct;
	}
	public Date getJstatTime() {
		return jstatTime;
	}
	public void setJstatTime(Date jstatTime) {
		this.jstatTime = jstatTime;
	}
	public String getJstatId() {
		return jstatId;
	}
	public void setJstatId(String jstatId) {
		this.jstatId = jstatId;
	}
	
}
