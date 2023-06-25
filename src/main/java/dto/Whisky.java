package dto;

public class Whisky {
	//フィールドの生成
	private final Integer id;
	private final String name;
	private int price;
	private int stock;
	private final Integer distid;
	private String discription;
	
	public Whisky(Integer id, String name, int price, int stock, Integer distid, String discription) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.distid = distid;
		this.discription = discription;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getDistid() {
		return distid;
	}
	
	
	

}
