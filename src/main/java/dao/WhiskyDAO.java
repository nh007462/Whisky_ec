package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Whisky;

public class WhiskyDAO {
	//IDでウヰスキー検索
	public Whisky select(Integer id) throws SQLException {

		DBUtil db = new DBUtil();
		Connection con = null;

		try {
			/* STEP 1:データベースの接続 */
			con = db.getConnection();

			/* STEP 2:SQL送信処理 */
			String sql = "SELECT * FROM WHISKY WHERE ID =?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			Whisky whisky = null;
			if (rs.next()) {
				whisky = new Whisky(rs.getInt("ID"), rs.getString("NAME"), rs.getInt("PRICE"), rs.getInt("Stock"), rs.getInt("DISTID"), rs.getString("DISCRIPTION"));
			}
			pstmt.close();
			return whisky;

		} finally {
			/* STEP 3:データベースとの接続を切断 */
			db.closeConnection(con);
		}

	}
	//名前でウヰスキー検索
	public List<Whisky> selectByName(String name) throws SQLException {
		DBUtil db = new DBUtil();
		Connection con = null;

		try {
			/* STEP 1:データベースの接続 */
			con = db.getConnection();
			/* STEP 2:SQL送信処理 */
			String sql = "SELECT * FROM WHISKY WHERE UPPER(NAME) LIKE UPPER(?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");
			ResultSet rs = pstmt.executeQuery();
			List<Whisky> list = new ArrayList<Whisky>();
			while (rs.next()) {
				Whisky whisky = new Whisky(rs.getInt("ID"), rs.getString("NAME"), rs.getInt("PRICE"), rs.getInt("Stock"), rs.getInt("DISTID"), rs.getString("DISCRIPTION"));
				list.add(whisky);
			}
			pstmt.close();
			return list;
		} finally {
			/* STEP 3:データベースとの接続を切断 */
			db.closeConnection(con);
		}
	}
	//データベースに新しいデータ追加
	public int insert(Whisky whisky) throws SQLException {
		DBUtil db = new DBUtil();
		Connection con = null;
		
		try {
			/* STEP 1:データベースの接続 */
			con = db.getConnection();
			/* STEP 2:SQL送信処理 */
			String sql = "INSERT INTO WHISKY(ID, NAME, PRICE, STOCK, DISTID, DISCRIPTION) VALUES(?, ?, ?, ?, ?. ?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, whisky.getId());
			pstmt.setString(2, whisky.getName());
			pstmt.setInt(3, whisky.getPrice());
			pstmt.setInt(4, whisky.getStock());
			pstmt.setInt(5, whisky.getDistid());
			pstmt.setString(6, whisky.getDiscription());
			int r = pstmt.executeUpdate();
			pstmt.close();
			return r;
		} finally {
			/* STEP 3:データベースとの接続を切断 */
			db.closeConnection(con);
		}
	}
	
	

}
