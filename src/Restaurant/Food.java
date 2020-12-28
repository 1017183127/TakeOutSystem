package Restaurant;

import java.awt.Image;

public class Food {
	private int fid;
	private String fname;
	private double fprice;
	private String rname;
	private String fpicture;
	private int rid;

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getFpicture() {
		return fpicture;
	}

	public void setFpicture(String fpicture) {
		this.fpicture = fpicture;
	}

	public double getFprice() {
		return fprice;
	}

	public void setFprice(double fprice) {
		this.fprice = fprice;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}
}
