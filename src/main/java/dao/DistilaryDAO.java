package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Distilary;

public class DistilaryDAO {
	//IDで蒸留所検索
	public Distilary select(Integer id) throws SQLException {

		DBUtil db = new DBUtil();
		Connection con = null;

		try {
			/* STEP 1:データベースの接続 */
			con = db.getConnection();

			/* STEP 2:SQL送信処理 */
			String sql = "SELECT * FROM DISTILARY WHERE ID =?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			Distilary distilary = null;
			if (rs.next()) {
				distilary = new Distilary(rs.getInt("ID"), rs.getString("NAME"), rs.getString("CATEGORY"));
			}
			pstmt.close();
			return distilary;

		} finally {
			/* STEP 3:データベースとの接続を切断 */
			db.closeConnection(con);
		}

	}

	//名前でウヰスキー検索
	public List<Distilary> selectByName(String name) throws SQLException {
		DBUtil db = new DBUtil();
		Connection con = null;

		try {
			/* STEP 1:データベースの接続 */
			con = db.getConnection();
			/* STEP 2:SQL送信処理 */
			String sql = "SELECT * FROM DISTILARY WHERE UPPER(NAME) LIKE UPPER(?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");
			ResultSet rs = pstmt.executeQuery();
			List<Distilary> list = new ArrayList<Distilary>();
			while (rs.next()) {
				Distilary distilary = new Distilary(rs.getInt("ID"), rs.getString("NAME"), rs.getString("CATEGORY"));
				list.add(distilary);
			}
			pstmt.close();
			return list;
		} finally {
			/* STEP 3:データベースとの接続を切断 */
			db.closeConnection(con);
		}
	}

	//データベースに新しいデータ追加
	public int insert(Distilary distilary) throws SQLException {
		DBUtil db = new DBUtil();
		Connection con = null;

		try {
			/* STEP 1:データベースの接続 */
			con = db.getConnection();
			/* STEP 2:SQL送信処理 */
			String sql = "INSERT INTO DISTILARY(ID, NAME, CATEGORY) VALUES(?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, distilary.getId());
			pstmt.setString(2, distilary.getName());
			pstmt.setString(3, distilary.getCategory());
			int r = pstmt.executeUpdate();
			pstmt.close();
			return r;
		} finally {
			/* STEP 3:データベースとの接続を切断 */
			db.closeConnection(con);
		}
	}
}
