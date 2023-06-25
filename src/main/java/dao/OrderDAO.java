package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.Member;
import dto.Order;

public class OrderDAO {

	//IDをリストに格納してリターン
	public List<Integer> idreturn() throws SQLException {

		DBUtil db = new DBUtil();
		Connection con = null;

		try {
			/* STEP 1:データベースの接続 */
			con = db.getConnection();

			/* STEP 2:SQL送信処理 */
			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM ORDERS";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			List<Integer> orderidlist = new ArrayList<>();
			while (rs.next()) {
				orderidlist.add(rs.getInt("ID"));
			}
			pstmt.close();
			return orderidlist;

		} finally {
			/* STEP 3:データベースとの接続を切断 */
			db.closeConnection(con);
		}

	}

	//注文IDから注文情報を検索
	public Order select(Integer id) throws SQLException {

		DBUtil db = new DBUtil();
		Connection con = null;

		try {
			/* STEP 1:データベースの接続 */
			con = db.getConnection();

			/* STEP 2:SQL送信処理 */
			String sql = "SELECT * FROM ORDERS WHERE ID =?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			Order order = null;
			if (rs.next()) {
				order = new Order(rs.getInt("ID"), rs.getInt("MEMBERID"), rs.getInt("POSTAGE"), rs.getInt("TOTAL"),
						rs.getDate("DATE"), rs.getString("STATUS"));
			}
			pstmt.close();
			return order;

		} finally {
			/* STEP 3:データベースとの接続を切断 */
			db.closeConnection(con);
		}

	}

	//会員IDから注文情報を検索してリストに格納してリターン
	public List<Order> selectAll(Integer memberid) throws SQLException {

		DBUtil db = new DBUtil();
		Connection con = null;

		try {
			/* STEP 1:データベースの接続 */
			con = db.getConnection();

			/* STEP 2:SQL送信処理 */
			String sql = "SELECT * FROM ORDERS WHERE MEMBERID =?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, memberid);
			ResultSet rs = pstmt.executeQuery();
			Order order = null;
			List<Order> orderlist = new ArrayList<>();
			while (rs.next()) {
				order = new Order(rs.getInt("ID"), rs.getInt("MEMBERID"), rs.getInt("POSTAGE"), rs.getInt("TOTAL"),
						rs.getDate("DATE"), rs.getString("STATUS"));
				orderlist.add(order);
			}
			pstmt.close();
			return orderlist;

		} finally {
			/* STEP 3:データベースとの接続を切断 */
			db.closeConnection(con);
		}

	}

	//データベースに新しいデータ追加
	public int insert(Member member, Integer id, int total) throws SQLException {
		DBUtil db = new DBUtil();
		Connection con = null;

		try {
			/* STEP 1:データベースの接続 */
			con = db.getConnection();
			/* STEP 2:SQL送信処理 */
			String sql = "INSERT INTO ORDERS (ID, MEMBERID, POSTAGE, TOTAL, DATE, STATUS) VALUES(?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			java.util.Date date = new java.util.Date();
			java.sql.Date sqldate= new java.sql.Date(date.getTime());
			pstmt.setInt(1, id);
			pstmt.setInt(2, member.getId());
			pstmt.setInt(3, 800);
			pstmt.setInt(4, total);
			pstmt.setDate(5, sqldate);
			pstmt.setString(6, "購入受付完了");
			int r = pstmt.executeUpdate();
			pstmt.close();
			return r;
		} finally {
			/* STEP 3:データベースとの接続を切断 */
			db.closeConnection(con);
		}
	}

}
