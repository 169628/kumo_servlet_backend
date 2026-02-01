package web.campaign.dao;

import java.util.List;

import web.campaign.bean.Campaign;

public interface CampaignDao {

	List<Campaign> selectAllWithLike(String keyword);
}
