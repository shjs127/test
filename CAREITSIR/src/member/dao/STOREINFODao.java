package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import jdbc.JdbcUtil;
import member.model.STOREINFO;

public class STOREINFODao {

	public static STOREINFO selectById(Connection conn, String manageNo) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"select * from STOREINFO where manageNo = ?");
			pstmt.setString(1, manageNo);
			rs = pstmt.executeQuery();
			STOREINFO storeinfo = null;
			if (rs.next()) {
				storeinfo = new STOREINFO(
						rs.getInt("storeNo"),
						rs.getString("storeName"), 
						rs.getString("storePic"),
						rs.getString("address"), 
						rs.getString("hours"), 
						rs.getString("closedDay"),
						rs.getString("callNumber"),
						rs.getString("manageNo"));
			}
			return storeinfo;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}



	public int insert(Connection conn, STOREINFO storeinfo) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		
		try(PreparedStatement pstmt = 
				conn.prepareStatement("insert into STOREINFO"
						+ " values(STORENUM.NEXTVAL,?,?,?,?,?,?,?)")) {

			pstmt.setString(1, storeinfo.getStoreName());
			pstmt.setString(2, storeinfo.getStorePic());
			pstmt.setString(3, storeinfo.getAddress());
			pstmt.setString(4, storeinfo.getHours());
			pstmt.setString(5, storeinfo.getClosedDay());
			pstmt.setString(6, storeinfo.getCallNumber());
			pstmt.setString(7, storeinfo.getManageNo());
			//pstmt.setString(7, storeinfo.getStoreName());
			int insertedCount = pstmt.executeUpdate();
			
			if (insertedCount > 0) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select STORENUM.currval from dual");
				if (rs.next()) {
					Integer newNo = rs.getInt(1);
					return newNo;
				}
			}
			return 0;
		}
}

	public int selectCount(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from storeinfo");
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}
	
	public List<STOREINFO> select(Connection conn, int startRow, int size) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from article " +
					"order by article_no desc limit ?, ?");
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			List<STOREINFO> result = new ArrayList<>();
			while (rs.next()) {
				result.add(convertArticle(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	private STOREINFO convertArticle(ResultSet rs) throws SQLException {
		return new STOREINFO(rs.getInt("storeNo"),
				rs.getString("storeName"),
				rs.getString("storePic"),
				rs.getString("address"),
				rs.getString("hours"),
				rs.getString("closedDays"),
				rs.getString("callNumber"),
				rs.getString("manageNo"));

	}

	/*
	 * public void update(Connection conn, STOREINFO storeinfo) throws SQLException
	 * { try (PreparedStatement pstmt = conn.prepareStatement(
	 * "update storeinfo set USERNAME = ?, PASSWORD = ? where USERID = ?")) {
	 * pstmt.setString(1, storeinfo.getName()); pstmt.setString(2,
	 * storeinfo.getPassword()); pstmt.setString(3, storeinfo.getUserId());
	 * pstmt.executeUpdate(); } }
	 */
}





