package web.campaign.service;

import java.util.List;

import web.campaign.bean.Campaign;

public interface CampaignService {
	List<Campaign> getCampaign(String keyword);
}
