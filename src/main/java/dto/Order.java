package dto;

import java.util.Date;


public class Order {
	private final Integer id;
	private final Integer memberid;
	private final int postage;
	private final int total;
	private final Date date;
	private String status;
	
	public Order(Integer id, Integer memberid, int postage, int total, Date date, String status) {
		super();
		this.id = id;
		this.memberid = memberid;
		this.postage = postage;
		this.total = total;
		this.date = date;
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public Integer getMemberId() {
		return memberid;
	}

	public int getPostage() {
		return postage;
	}

	public int getTotal() {
		return total;
	}

	public Date getDate() {
		return date;
	}
	
	
	

}
