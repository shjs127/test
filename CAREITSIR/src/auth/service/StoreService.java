package auth.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import javax.xml.crypto.Data;


import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.dao.STOREINFODao;
import member.model.STOREINFO;
import member.model.USERINFO;
import member.dao.DETAILINFODao;
import member.model.DETAILINFO;

public class StoreService {

	private STOREINFODao storeinfoDao = new STOREINFODao();
	private DETAILINFODao detailinfoDao = new DETAILINFODao();
	
	
	public int store(StoreRequest storeReq, DetailRequest detailReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			System.out.println("storeReq="+storeReq);
			
			STOREINFO storeSel = STOREINFODao.selectById(conn, storeReq.getManageNo());
			System.out.println("storeSel="+storeSel);
			
			if (storeSel != null) {
				JdbcUtil.rollback(conn);
				throw new DuplicateIdException();
			}
			STOREINFO storeinfo =toStore(storeReq);
			int savedStoreNo = storeinfoDao.insert(conn, storeinfo);
			if (savedStoreNo == 0) {
				JdbcUtil.rollback(conn);
				throw new RuntimeException("fail to insert storeinfo");
			}
			//storeinfo의 storeno을 받아서 새로운 detailinfo 생성
			
			
			  DETAILINFO detailInfo=new DETAILINFO( savedStoreNo, detailReq.getTotalSeat(),
			  detailReq.getSocketSeat(), detailReq.getDessertSales(),
			  detailReq.getTerrace(), detailReq.getRoofTop(), detailReq.getWifi(),
			  detailReq.getCompanionDog(), detailReq.getParkingSpace(),
			  detailReq.getNokidsZone(), detailReq.getSmokingArea() ); 
			  
			  DETAILINFO savedDetail=detailinfoDao.insert(conn, detailInfo); 
			  
			  if (savedDetail == null)
			  { throw new RuntimeException("fail to insert detailinfo"); }
			 
			
			
			conn.commit();
			
			System.out.println("savedStoreNo="+savedStoreNo);

			return savedStoreNo;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
	private STOREINFO toStore(StoreRequest req) {
		return new STOREINFO(0, req.getStoreName(), 
				req.getStorePic(), req.getAddress(), req.getHours(), 
				req.getClosedDays(), req.getCallNumber(),null);
	}

}
