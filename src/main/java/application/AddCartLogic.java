package application;

import java.sql.SQLException;
import java.util.List;

import dao.WhiskyDAO;
import dto.CartItem;
import dto.Whisky;

public class AddCartLogic {
	public List<CartItem> process(Integer id, int amount, List<CartItem> cart) throws SQLException {
		WhiskyDAO dao = new WhiskyDAO();
		Whisky whisky = dao.select(id);
		CartItem cartitem = new CartItem(whisky, amount);
		
		for(CartItem ci : cart) {
			if(id == ci.getWhisky().getId()){
				ci.setAmount(amount);
				return cart;
			}
		}
		cart.add(cartitem);
		
		return cart;
	}

}
