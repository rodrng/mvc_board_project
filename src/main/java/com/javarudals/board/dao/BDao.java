package com.javarudals.board.dao;

import java.sql.*;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


import com.javarudals.board.dto.*;

public class BDao {
	DataSource datasource;

	public BDao() {		
		try {
			Context context = new InitialContext();
			datasource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void write(String bname, String btitle, String bcontent) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = datasource.getConnection();
			String query = "insert into mvc_board (bid, bname, btitle, bcontent, bhit, bgroup, bstep, bindent) values (mvc_board_seq.nextval, ?, ? ,?, 0, mvc_board_seq.currval, 0, 0)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bname);
			pstmt.setString(2, btitle);
			pstmt.setString(3, bcontent);
			int ret = pstmt.executeUpdate();//데이터 삽입이 성공하면 1 반환
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public ArrayList<BDto> list() {
		
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultset = null;
		
		try {
			conn = datasource.getConnection();
			String query = "select * from mvc_board order by bgroup desc, bstep asc";
			pstmt = conn.prepareStatement(query);
			resultset = pstmt.executeQuery();
			
			while(resultset.next()) {
				int bId = resultset.getInt("bid");
				String bName = resultset.getString("bname");
				String bTitle = resultset.getString("btitle");
				String bContent = resultset.getString("bcontent");
				Timestamp bDate = resultset.getTimestamp("bdate");
				int bHit = resultset.getInt("bhit");
				int bGroup = resultset.getInt("bgroup");
				int bStep = resultset.getInt("bstep");
				int bIndent = resultset.getInt("bindent");
				
				BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
				dtos.add(dto);
				
			}
						
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(resultset != null) {
					resultset.close();
				}
				
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		return dtos;
		
	}
	
	
}