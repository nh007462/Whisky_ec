package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.Member;

public class MemberDAO {
	
	//IDとパスワード合致検索
		public Member login(Integer id, String password) throws SQLException {

			DBUtil db = new DBUtil();
			Connection con = null;

			try {
				/* STEP 1:データベースの接続 */
				con = db.getConnection();

				/* STEP 2:SQL送信処理 */
				String sql = "SELECT * FROM MEMBER WHERE ID =? AND PASSWORD =?";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, id);
				pstmt.setString(2,  password);
				ResultSet rs = pstmt.executeQuery();
				Member member = null;
				if (rs.next()) {
					member = new Member(rs.getInt("ID"), rs.getString("PASSWORD"), rs.getString("NAME"), rs.getString("GENDER"), rs.getString("ZIPCODE"), rs.getString("ADDRESS1"), rs.getString("ADDRESS2"), rs.getString("BUILDING"), rs.getString("PHONE"), rs.getString("MAIL"), rs.getDate("DATE"));
				}
				pstmt.close();
				return member;

			} finally {
				/* STEP 3:データベースとの接続を切断 */
				db.closeConnection(con);
			}

		}
		
		//IDからユーザー情報を検索
				public Member select(Integer id) throws SQLException {

					DBUtil db = new DBUtil();
					Connection con = null;

					try {
						/* STEP 1:データベースの接続 */
						con = db.getConnection();

						/* STEP 2:SQL送信処理 */
						String sql = "SELECT * FROM MEMBER WHERE ID =?";
						PreparedStatement pstmt = con.prepareStatement(sql);
						pstmt.setInt(1, id);
						ResultSet rs = pstmt.executeQuery();
						Member member = null;
						if (rs.next()) {
							member = new Member(rs.getInt("ID"), rs.getString("PASSWORD"), rs.getString("NAME"), rs.getString("GENDER"), rs.getString("ZIPCODE"), rs.getString("ADDRESS1"), rs.getString("ADDRESS2"), rs.getString("BUILDING"), rs.getString("PHONE"), rs.getString("MAIL"), rs.getDate("DATE"));
						}
						pstmt.close();
						return member;

					} finally {
						/* STEP 3:データベースとの接続を切断 */
						db.closeConnection(con);
					}

				}
		
		//データベースに新しいデータ追加
		public int insert(Member member) throws SQLException {
			DBUtil db = new DBUtil();
			Connection con = null;
			
			try {
				/* STEP 1:データベースの接続 */
				con = db.getConnection();
				/* STEP 2:SQL送信処理 */
				String sql = "INSERT INTO MEMBER(ID, PASSWORD, NAME, GENDER, ZIPCODE, ADDRESS1, ADDRESS2, BUILDING, PHONE, MAIL, DATE) VALUES(?, ?, ?, ?, ?. ?, ?, ?, ?, ?, ?)";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, member.getId());
				pstmt.setString(2, member.getPassword());
				pstmt.setString(3, member.getName());
				pstmt.setString(4, member.getGender());
				pstmt.setString(5, member.getZipcode());
				pstmt.setString(6, member.getAddress1());
				pstmt.setString(7, member.getAddress2());
				pstmt.setString(8, member.getBuilding());
				pstmt.setString(9, member.getPhone());
				pstmt.setString(10, member.getMail());
				pstmt.setDate(11, (Date) member.getDate());
				int r = pstmt.executeUpdate();
				pstmt.close();
				return r;
			} finally {
				/* STEP 3:データベースとの接続を切断 */
				db.closeConnection(con);
			}
		}

}
