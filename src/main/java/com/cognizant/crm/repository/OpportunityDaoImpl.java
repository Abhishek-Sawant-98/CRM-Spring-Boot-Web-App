package com.cognizant.crm.repository;

import com.cognizant.crm.model.Opportunity;
import com.cognizant.crm.model.User;
import com.cognizant.crm.queryconstants.AccountConstants;
import com.cognizant.crm.queryconstants.OpportunityConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor // Generates constructor for final fields at compile time
@Repository
public class OpportunityDaoImpl implements OpportunityDao {

	// Using final fields and a constructor is generally considered best practise
	// for autowiring dependencies
	private final JdbcTemplate jdbcTemplate;

	@Override
	public boolean addOpportunity(Opportunity opportunity, User user) {
		String insertQuery = OpportunityConstants.INSERT_OPPORTUNITY;
		String accountKeyQuery = AccountConstants.ACCOUNT_KEY;

		opportunity.setOpportunityOwner(user.getUserId());
		opportunity.setIdAccount(jdbcTemplate.queryForObject(accountKeyQuery,
				new Object[] { opportunity.getAccountName(), opportunity.getOpportunityOwner() }, Integer.class));

		if (!isOpportunityRegistered(opportunity)) {

			jdbcTemplate.update(insertQuery, opportunity.getOpportunityOwner(), opportunity.getIdAccount(),
					opportunity.getOpportunityName(), opportunity.getType(), opportunity.getStage(),
					opportunity.getProbability(), opportunity.getCloseDate(), opportunity.getLeadSource(),
					opportunity.getAmount(), opportunity.getDescription(), opportunity.getNextStep());
			return true;
		}
		return false;
	}

	@Override
	public boolean isOpportunityRegistered(Opportunity opportunity) {
		String opportunityOccurrencesQuery = OpportunityConstants.OPPORTUNITY_OCCURRENCES;

		return jdbcTemplate.queryForObject(opportunityOccurrencesQuery,
				new Object[] { opportunity.getOpportunityOwner(), opportunity.getOpportunityName() },
				Integer.class) == 1;
	}

	@Override
	public List<Opportunity> getOpportunitiesByUserId(String userId) {
		String fetchOpportunitiesQuery = OpportunityConstants.FETCH_OPPORTUNITIES;

		return jdbcTemplate.query(fetchOpportunitiesQuery, new Object[] { userId }, rs -> {
			List<Opportunity> opportunityList = new ArrayList<Opportunity>();
			while (rs.next()) {
				Opportunity opportunity = new Opportunity();
				opportunity.setOpportunityName(rs.getString("opportunity_name"));
				opportunity.setAccountName(rs.getString("RelatedTo"));
				opportunity.setType(rs.getString("type"));
				opportunity.setStage(rs.getString("stage"));
				opportunity.setProbability(rs.getString("probability"));
				opportunity.setCloseDate(rs.getString("close_date"));
				opportunity.setLeadSource(rs.getString("lead_source"));
				opportunity.setAmount(rs.getString("amount"));
				opportunity.setDescription(rs.getString("description"));
				opportunity.setNextStep(rs.getString("next_step"));

				opportunityList.add(opportunity);
			}
			return opportunityList;
		});
	}

}
