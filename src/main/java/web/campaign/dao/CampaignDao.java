package web.campaign.dao;

import java.util.List;

import web.campaign.vo.Campaign;

public interface CampaignDao {

	List<Campaign> selectAll();
}
