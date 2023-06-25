package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.OrderDetail;

public class OrderDetailDAO {

	//注文IDから注文詳細を検索してクラスに格納してリターン
	public OrderDetail select(Integer id) throws SQLException {

		DBUtil db = new DBUtil();
		Connection con = null;

		try {
			/* STEP 1:データベースの接続 */
			con = db.getConnection();

			/* STEP 2:SQL送信処理 */
			String sql = "SELECT * FROM ORDERDETAIL WHERE ORDERID =?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			OrderDetail orderdetail = new OrderDetail(0, 0, 0, 0);
			while (rs.next()) {
				orderdetail = new OrderDetail(rs.getInt("ORDERID"), rs.getInt("MEMBERID"), rs.getInt("PRICE"), rs.getInt("AMOUNT"));
			}
			pstmt.close();
			return orderdetail;

		} finally {
			/* STEP 3:データベースとの接続を切断 */
			db.closeConnection(con);
		}

	}

	

	//データベースに新しいデータ追加
	public int insert(OrderDetail orderdetail) throws SQLException {
		DBUtil db = new DBUtil();
		Connection con = null;

		try {
			/* STEP 1:データベースの接続 */
			con = db.getConnection();
			/* STEP 2:SQL送信処理 */
			String sql = "INSERT INTO ORDERDETAIL (ORDERID, MEMBERID, PRICE, AMOUNT) VALUES(?, ?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, orderdetail.getOrderId());
			pstmt.setInt(2, orderdetail.getItemId());
			pstmt.setInt(3, orderdetail.getPrice());
			pstmt.setInt(4, orderdetail.getAmount());
			int r = pstmt.executeUpdate();
			pstmt.close();
			return r;
		} finally {
			/* STEP 3:データベースとの接続を切断 */
			db.closeConnection(con);
		}
	}

}
