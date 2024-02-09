package com.xadmin.ibtfees.bean;

public class Fee {
	private int fsn;
	private int acsn;
	private float fee_amt;
	private String fee_type_id;
	
	public Fee(int fsn, int acsn, float fee_amt, String fee_type_id) {
		super();
		this.fsn = fsn;
		this.acsn = acsn;
		this.fee_amt = fee_amt;
		this.fee_type_id = fee_type_id;
	}
	public Fee(int acsn, float fee_amt, String fee_type_id) {
		super();
		this.acsn = acsn;
		this.fee_amt = fee_amt;
		this.fee_type_id = fee_type_id;
	}
	
	
	public int getFsn() {
		return fsn;
	}
	public void setFsn(int fsn) {
		this.fsn = fsn;
	}
	public int getAcsn() {
		return acsn;
	}
	public void setAcsn(int acsn) {
		this.acsn = acsn;
	}
	public float getFee_amt() {
		return fee_amt;
	}
	public void setFee_amt(float fee_amt) {
		this.fee_amt = fee_amt;
	}
	public String getFee_type_id() {
		return fee_type_id;
	}
	public void setFee_type_id(String fee_type_id) {
		this.fee_type_id = fee_type_id;
	}
	
	
}
