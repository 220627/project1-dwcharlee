package com.revature.models;

public class Reimbursement {

	private int reimb_id;
	private int reimb_amount;
	private int reimb_submitted;
	private int reimb_author;
	private int reimb_resolver;
	private int reimb_status_id;
	private int reimb_type_id;
	
	
	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Reimbursement(int reimb_id, int reimb_amount, int reimb_submitted, 
			int reimb_author, int reimb_resolver, int reimb_status_id, int reimb_type_id) {
		super();
		this.reimb_id = reimb_id;
		this.reimb_amount = reimb_amount;
		this.reimb_submitted = reimb_submitted;
		this.reimb_author = reimb_author;
		this.reimb_resolver = reimb_resolver;
		this.reimb_status_id = reimb_status_id;
		this.reimb_type_id = reimb_type_id;
	}


	@Override
	public String toString() {
		return "Reimbursement [reimb_id=" + reimb_id + ", reimb_amount=" + reimb_amount + ", reimb_submitted="
				+ reimb_submitted + ", reimb_author=" + reimb_author + ", reimb_resolver="
				+ reimb_resolver + ", reimb_status_id=" + reimb_status_id + ", reimb_type_id=" + reimb_type_id + "]";
	}


	public int getReimb_id() {
		return reimb_id;
	}


	public void setReimb_id(int reimb_id) {
		this.reimb_id = reimb_id;
	}


	public int getReimb_amount() {
		return reimb_amount;
	}


	public void setReimb_amount(int reimb_amount) {
		this.reimb_amount = reimb_amount;
	}


	public int getReimb_submitted() {
		return reimb_submitted;
	}


	public void setReimb_submitted(int reimb_submitted) {
		this.reimb_submitted = reimb_submitted;
	}


	public int getReimb_author() {
		return reimb_author;
	}


	public void setReimb_author(int reimb_author) {
		this.reimb_author = reimb_author;
	}


	public int getReimb_resolver() {
		return reimb_resolver;
	}


	public void setReimb_resolver(int reimb_resolver) {
		this.reimb_resolver = reimb_resolver;
	}


	public int getReimb_status_id() {
		return reimb_status_id;
	}


	public void setReimb_status_id(int reimb_status_id) {
		this.reimb_status_id = reimb_status_id;
	}


	public int getReimb_type_id() {
		return reimb_type_id;
	}


	public void setReimb_type_id(int reimb_type_id) {
		this.reimb_type_id = reimb_type_id;
	}
}
