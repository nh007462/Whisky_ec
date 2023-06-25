package dto;

public class OrderDetail {
	private final Integer orderid;
	private final Integer itemid;
	private final int price;
	private final int amount;
	
	public OrderDetail(Integer orderid, Integer itemid, int price, int amount) {
		super();
		this.orderid = orderid;
		this.itemid = itemid;
		this.price = price;
		this.amount = amount;
	}

	public Integer getOrderId() {
		return orderid;
	}

	public Integer getItemId() {
		return itemid;
	}

	public int getPrice() {
		return price;
	}

	public int getAmount() {
		return amount;
	}
	
	
	
}
