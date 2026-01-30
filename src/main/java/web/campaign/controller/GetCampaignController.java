package web.campaign.controller;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.campaign.dao.CampaignDao;
import web.campaign.dao.impl.CampaignDaoImpl;


@WebServlet("/campaign")
public class GetCampaignController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("campaign");
		
		try {
			CampaignDao campaignDao = new CampaignDaoImpl();
		
			System.out.println(campaignDao.insert());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
