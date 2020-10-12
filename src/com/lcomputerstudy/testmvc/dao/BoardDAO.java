package com.lcomputerstudy.testmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.lcomputerstudy.testmvc.database.DBConnection;
import com.lcomputerstudy.testmvc.vo.Board;

public class BoardDAO {
	private static BoardDAO dao = null;
	private BoardDAO() {
	
	}
	
	public static BoardDAO getInstance() {
		if(dao == null) {
			dao = new BoardDAO();
		}
		return dao;
	}
	public ArrayList<Board> getBoards(int page){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Board> list = null;
		int pageNum = (page-1)*3;
		
		try {
			conn = DBConnection.getConnection();
			String query = new StringBuilder()
					.append("SELECT  @ROWNUM := @ROWNUM -1 AS ROWNUM,\n")
					.append("   ta.*\n")
					.append("FROM   board ta,\n")
					.append("    (SELECT @rownum := (SELECT COUNT(*)-?+1 FROM board ta)) tb\n")
					.append("LIMIT   ?,3\n")
					.toString();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, pageNum);
			pstmt.setInt(2,pageNum);
			rs = pstmt.executeQuery();
			list = new ArrayList<Board>();
			
			while(rs.next()) {
				Board board = new Board();
				board.setRownum(rs.getInt("ROWNUM"));
				board.setB_idx(rs.getInt("b_idx"));
				board.setB_title(rs.getString("b_title"));
				board.setB_content(rs.getString("b_content"));
				board.setB_writer(rs.getString("b_writer"));
				list.add(board);
			   }
					} catch (Exception e) {
						
					} finally {
						try {
						if(rs != null)	rs.close();
						if(pstmt != null)pstmt.close();
						if(conn != null)	conn.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					
					return list;
				}
	public int getBoardsCount() {
		Connection conn =null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		int count = 0;
		
		try {
			conn =DBConnection.getConnection();
			String query = "SELECT COUNT(*) count FROM board";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				count = rs.getInt("count");
			}
		}catch (Exception e) {
		
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	
}
