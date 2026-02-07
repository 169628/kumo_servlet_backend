package web.campaign.service.impl;

import java.util.List;

import javax.naming.NamingException;

import web.campaign.vo.Campaign;
import web.campaign.dao.CampaignDao;
import web.campaign.dao.impl.CampaignDaoImpl;
import web.campaign.service.CampaignService;

public class CampaignServiceImpl implements CampaignService{
	private CampaignDao campaignDao;
	
	public CampaignServiceImpl() throws NamingException {
		campaignDao = new CampaignDaoImpl();
	}

	@Override
	public List<Campaign> getCampaign() {
		
		return campaignDao.selectAll();
		
	}



}
