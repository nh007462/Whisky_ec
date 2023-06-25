package dto;

public class CartItem {
	private Whisky whisky;
	private int amount;
	
	public CartItem(Whisky whisky, int amount) {
		this.whisky = whisky;
		this.amount += amount;
	}

	public Whisky getWhisky() {
		return whisky;
	}

	public void setWhisky(Whisky whisky) {
		this.whisky = whisky;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount += amount;
	}
	
	

}
