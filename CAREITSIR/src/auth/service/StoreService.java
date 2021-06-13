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
			
			STOREINFO storeinfo =toStore(storeReq);
			STOREINFO savedStore = storeinfoDao.insert(conn, storeinfo);
			if (savedStore != null) {
				JdbcUtil.rollback(conn);
				throw new RuntimeException("fail to insert storeinfo");
			}
			//storeinfo의 storeno을 받아서 새로운 detailinfo 생성
			DETAILINFO detailInfo=new DETAILINFO(
					savedStore.getStoreNo(),
					detailReq.getTotalSeat(),
					detailReq.getSocketSeat(),
					detailReq.getDessertSales(),
					detailReq.getTerrace(),
					detailReq.getRoofTop(),
					detailReq.getWifi(),
					detailReq.getCompanionDog(),
					detailReq.getParkingSpace(),
					detailReq.getNokidsZone(),
					detailReq.getSmokingArea()
					);
			DETAILINFO savedDetail=detailinfoDao.insert(conn, detailInfo);
			if (savedDetail == null) {
				throw new RuntimeException("fail to insert detailinfo");
			}
			
			conn.commit();

			return savedStore.getStoreNo();
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
