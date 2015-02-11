package org.beatific.daram.jstat;

public class JstatResult {

	private String server;
	private String s0;
	private String s1;
	private String e;
	private String o;
	private String p;
	private String ygc;
	private String ygct;
	private String fgc;
	private String fgct;
	private String gct;
	
	
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	public String getS0() {
		return s0;
	}
	public void setS0(String s0) {
		this.s0 = s0;
	}
	public String getS1() {
		return s1;
	}
	public void setS1(String s1) {
		this.s1 = s1;
	}
	public String getE() {
		return e;
	}
	public void setE(String e) {
		this.e = e;
	}
	public String getO() {
		return o;
	}
	public void setO(String o) {
		this.o = o;
	}
	public String getP() {
		return p;
	}
	public void setP(String p) {
		this.p = p;
	}
	public String getYgc() {
		return ygc;
	}
	public void setYgc(String ygc) {
		this.ygc = ygc;
	}
	public String getYgct() {
		return ygct;
	}
	public void setYgct(String ygct) {
		this.ygct = ygct;
	}
	public String getFgc() {
		return fgc;
	}
	public void setFgc(String fgc) {
		this.fgc = fgc;
	}
	public String getFgct() {
		return fgct;
	}
	public void setFgct(String fgct) {
		this.fgct = fgct;
	}
	public String getGct() {
		return gct;
	}
	public void setGct(String gct) {
		this.gct = gct;
	}
	@Override
	public String toString() {
		return "JstatResult [server=" + server + ", s0=" + s0 + ", s1=" + s1
				+ ", e=" + e + ", o=" + o + ", p=" + p + ", ygc=" + ygc
				+ ", ygct=" + ygct + ", fgc=" + fgc + ", fgct=" + fgct
				+ ", gct=" + gct + "]";
	}
	
}
