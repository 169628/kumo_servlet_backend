package web.campaign.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import web.campaign.bean.Campaign;
import web.campaign.dao.CampaignDao;


public class CampaignDaoImpl implements CampaignDao {
	private DataSource ds;
	
	public CampaignDaoImpl() throws NamingException {
		ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/kumo");
	}

	@Override
	public List<Campaign> selectAllWithLike(String keyword) {
		StringBuilder sql = new StringBuilder("select");
		sql.append(" no, campaign_id, brand, model, sv, tv, file, file_size, is_test_mode, test_list, d.content as download_by, is_enabled, create_at, update_at");
		sql.append(" from campaigns c");
		sql.append(" join download_by_ref d");
		sql.append(" on c.download_by_id = d.download_by_id");
		sql.append(" where is_deleted = 0");
		
		if(keyword != null && !keyword.isEmpty()) {
			sql.append(" and");
			sql.append(" ( brand like ? or model like ? or no like ? )");
		}
		
		sql.append(" order by update_at desc, no desc");
	
		try(
				Connection conn = ds.getConnection();				
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) {
			
			if(keyword != null && !keyword.isEmpty()) {
				pstmt.setString(1,"%" + keyword + "%");
				pstmt.setString(2,"%" + keyword + "%");
				pstmt.setString(3,"%" + keyword + "%");
			}
			
			try (ResultSet rs = pstmt.executeQuery()) {
				List<Campaign> list = new ArrayList<>();
				while (rs.next()) {
					Campaign campaign = new Campaign();
					campaign.setNo(rs.getInt("no"));
					campaign.setCampaignId(rs.getLong("campaign_id"));
					campaign.setBrand(rs.getString("brand"));
					campaign.setModel(rs.getString("model"));
					campaign.setSv(rs.getString("sv"));
					campaign.setTv(rs.getString("tv"));
					campaign.setFile(rs.getString("file"));
					campaign.setFileSize(rs.getInt("file_size"));
					campaign.setIsTestMode(rs.getBoolean("is_test_mode"));
					campaign.setTestList(rs.getString("test_list"));
					campaign.setDownloadBy(rs.getString("download_by"));
					campaign.setIsEnabled(rs.getBoolean("is_enabled"));
					campaign.setCreateAt(rs.getTimestamp("create_at"));
					campaign.setUpdateAt(rs.getTimestamp("update_at"));

					list.add(campaign);
				}
				return list;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	

	
	

}
