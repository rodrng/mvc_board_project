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
	
	public BDto contentView(String strId) {
		
		upHit(strId);//조회수를 +1 해주는 함수 호출
		
//		ArrayList<BDto> dtos = new ArrayList<BDto>();
		BDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultset = null;
		
		try {
			conn = datasource.getConnection();
			String query = "select * from mvc_board where bid = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, strId);
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
				
				dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);								
				
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
		
		return dto;
	}
	
	public void modify(String bid, String bname, String btitle, String bcontent) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = datasource.getConnection();
			String query = "update mvc_board set bname = ?, btitle = ?, bcontent = ? where bid = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bname);
			pstmt.setString(2, btitle);
			pstmt.setString(3, bcontent);
			pstmt.setInt(4, Integer.parseInt(bid));
			int ret = pstmt.executeUpdate();//데이터 수정이 성공하면 1 반환
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
	
	private void upHit(String bId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = datasource.getConnection();
			String query = "update mvc_board set bhit = bhit + 1 where bid=?";
			pstmt = conn.prepareStatement(query);			
			pstmt.setString(1, bId);
			int ret = pstmt.executeUpdate();//데이터 수정이 성공하면 1 반환
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
	
	public void delete(String bId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = datasource.getConnection();
			String query = "delete from mvc_board where bid=?";
			pstmt = conn.prepareStatement(query);			
			pstmt.setString(1, bId);
			int ret = pstmt.executeUpdate();//데이터 수정이 성공하면 1 반환
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
	
	public BDto reply_view(String strId) {
	
			BDto dto = null;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet resultset = null;
			
			try {
				conn = datasource.getConnection();
				String query = "select * from mvc_board where bid = ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, strId);
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
					
					dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
					
//					System.out.println(bGroup);
//					System.out.println(bStep);
//					System.out.println(bIndent);
					
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
			
			return dto;
		}

	public void reply(String bid, String bname, String btitle, String bcontent, String bgroup, String bstep, String bindent) {
		
		replyShape(bgroup, bstep);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		System.out.println(bgroup);
		System.out.println(bstep);
		System.out.println(bindent);
		
		try {
			conn = datasource.getConnection();
			String query = "insert into mvc_board (bid, bname, btitle, bcontent, bgroup, bstep, bindent) values (mvc_board_seq.nextval, ?, ? ,?, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bname);
			pstmt.setString(2, btitle);
			pstmt.setString(3, bcontent);			
			pstmt.setInt(4, Integer.parseInt(bgroup));//덧글을 단 글의 bid
			pstmt.setInt(5, Integer.parseInt(bstep)+1);//덧글을 단 글의 bstep+1
			pstmt.setInt(6, Integer.parseInt(bindent)+1);//덧글을 단 글의 bindent+1			
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

	public void replyShape(String strGroup, String strStep) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = datasource.getConnection();
			String query = "update mvc_board set bstep = bstep + 1 where bgroup = ? and bstep > ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(strGroup));
			pstmt.setInt(2, Integer.parseInt(strStep));			
			
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

}