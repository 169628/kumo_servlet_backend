package web.campaign.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import web.campaign.dao.CampaignDao;

public class CampaignDaoImpl implements CampaignDao {
	private DataSource ds;
	
	public CampaignDaoImpl() throws NamingException {
		ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/kumo");
	}

	@Override
	public int insert() {
		String sql = "select * from campaigns where no = 15";
		try (
				Connection conn = ds.getConnection();				
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()
				){
			
			if (rs.next()) {
				
				System.out.println(rs.getString("brand"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}

	
	

}
