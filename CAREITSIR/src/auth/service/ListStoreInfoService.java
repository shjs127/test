package auth.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.catalina.Store;

import jdbc.connection.ConnectionProvider;
import member.dao.STOREINFODao;
import member.model.STOREINFO;

public class ListStoreInfoService {

	private STOREINFODao storeDao = new STOREINFODao();
	private int size = 10;

	public STOREINFOPage getArticlePage(int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int total = storeDao.selectCount(conn);
			List<STOREINFO> content = storeDao.select(
					conn, (pageNum - 1) * size, size);
			return new STOREINFOPage(total, pageNum, size, content);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
