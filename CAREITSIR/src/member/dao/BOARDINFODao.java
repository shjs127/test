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
import member.model.BOARDINFO;

public class BOARDINFODao {

	public BOARDINFO selectById(Connection conn, String boardNo) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"select * from BOARDINFO where BOARDNO = ?");
			pstmt.setString(1, boardNo);
			rs = pstmt.executeQuery();
			BOARDINFO boardinfo = null;
			if (rs.next()) {
				boardinfo = new BOARDINFO(
						rs.getInt("boardNo"),
						rs.getInt("userNo"), 
						rs.getString("boardTitle"),
						rs.getString("boardContents"), 
						rs.getString("boardPic"), 
						rs.getInt("viewCount"),
						toDate(rs.getTimestamp("regdate")));
			}
			return boardinfo;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}


	private Date toDate(Timestamp date) {
		return date == null ? null : new Date(date.getTime());
	}




	public BOARDINFO insert(Connection conn, BOARDINFO boardinfo) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try (PreparedStatement pstmt = 
				conn.prepareStatement("insert into boardinfo values(BOARDNUM.NEXTVAL,?,?,?,?,0,sysdate")) {

			pstmt.setInt(1, boardinfo.getUserNo());
			pstmt.setString(2, boardinfo.getBoardTitle());
			pstmt.setString(3, boardinfo.getBoardContents());
			pstmt.setString(4, boardinfo.getBoardPic());
			/* pstmt.setInt(5, boardinfo.getViewCount()); */
			pstmt.setTimestamp(5, new Timestamp(boardinfo.getBoardDate().getTime()));
			
			int insertedCount = pstmt.executeUpdate();

			if (insertedCount > 0) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select USERNO from BOARDINFO");
				if (rs.next()) {
					Integer newNo = rs.getInt(1);
					return new BOARDINFO(newNo,
							boardinfo.getUserNo(),
							boardinfo.getBoardTitle(),
							boardinfo.getBoardContents(),
							boardinfo.getBoardPic(),0,
							boardinfo.getBoardDate()
							);
				}
			}
			return null;

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);

		}
	}
	public static int selectCount(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from BOARDINFO");
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}
	
	/*
	 * public List<BOARDINFO> select(Connection conn, int startRow, int size) throws
	 * SQLException { PreparedStatement pstmt = null; ResultSet rs = null; try {
	 * pstmt = conn.prepareStatement("select * from BOARDINFO " +
	 * "order by BOARDNO desc limit ?, ?"); pstmt.setInt(1, startRow);
	 * pstmt.setInt(2, size); rs = pstmt.executeQuery(); List<BOARDINFO> result =
	 * new ArrayList<>(); while (rs.next()) {
	 * 
	 * } return result; } finally { JdbcUtil.close(rs); JdbcUtil.close(pstmt); } }
	 */



	 
}





