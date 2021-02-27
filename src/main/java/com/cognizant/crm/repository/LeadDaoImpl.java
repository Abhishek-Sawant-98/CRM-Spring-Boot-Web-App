package com.cognizant.crm.repository;

import com.cognizant.crm.model.Lead;
import com.cognizant.crm.model.User;
import com.cognizant.crm.queryconstants.LeadConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor // Generates constructor for final fields at compile time
@Repository
public class LeadDaoImpl implements LeadDao {

	// Using final fields and a constructor is generally considered best practise
	// for autowiring dependencies
	private final JdbcTemplate jdbcTemplate;

	@Override
	public boolean addLead(Lead lead, User user) {
		String insertQuery = LeadConstants.INSERT_LEAD;
		String insertNameQuery = LeadConstants.INSERT_LEAD_NAME;
		String insertAddressQuery = LeadConstants.INSERT_LEAD_ADDRESS;

		lead.setLeadOwner(user.getUserId());
		/*
		 * Adding name and address to be done with the primary key of the inserted lead.
		 */
		if (!isLeadRegistered(lead)) {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(con -> {
				PreparedStatement ps = con.prepareStatement(insertQuery, new String[] { "idLead" });
				ps.setString(1, lead.getLeadOwner());
				ps.setString(2, lead.getLeadStatus());
				ps.setString(3, lead.getPhone());
				ps.setString(4, lead.getCompany());
				ps.setString(5, lead.getEmailId());
				ps.setString(6, lead.getWebsite());
				ps.setString(7, lead.getLeadSource());
				ps.setString(8, lead.getEmployeeCount());
				ps.setString(9, lead.getAnnualRevenue());
				ps.setString(10, lead.getIndustry());
				ps.setString(11, lead.getDescription());
				return ps;
			}, keyHolder);

			// The primary key obtained here will be used to insert the name and address
			lead.setIdLead(Objects.requireNonNull(keyHolder.getKey()).intValue());

			// This inserts the Name
			jdbcTemplate.update(insertNameQuery, lead.getSalutation(), lead.getFirstName(), lead.getLastName(),
					lead.getIdLead());

			// This inserts the Address
			jdbcTemplate.update(insertAddressQuery, lead.getStreet(), lead.getCity(), lead.getStateProvince(),
					lead.getZipPostal(), lead.getCountry(), lead.getIdLead());

			return true;
		}
		return false;
	}

	@Override
	public boolean isLeadRegistered(Lead lead) {
		String leadOccurrenceQuery = LeadConstants.LEAD_OCCURRENCES;

		return jdbcTemplate.queryForObject(leadOccurrenceQuery, new Object[] { lead.getLeadOwner(), lead.getPhone() },
				Integer.class) == 1;
	}

	@Override
	public List<Lead> getLeadsByUserId(String userId) {
		String fetchLeadsQuery = LeadConstants.FETCH_LEADS;

		return jdbcTemplate.query(fetchLeadsQuery, new Object[] { userId }, rs -> {
			List<Lead> leadList = new ArrayList<>();
			while (rs.next()) {
				Lead lead = new Lead();
				lead.setName(rs.getString("Name"));
				lead.setAddress(rs.getString("Address"));
				lead.setLeadStatus(rs.getString("lead_status"));
				lead.setPhone(rs.getString("phone"));
				lead.setCompany(rs.getString("company"));
				lead.setEmailId(rs.getString("email_id"));
				lead.setWebsite(rs.getString("website"));
				lead.setLeadSource(rs.getString("lead_source"));
				lead.setEmployeeCount(rs.getString("employee_count"));
				lead.setAnnualRevenue(rs.getString("annual_revenue"));
				lead.setIndustry(rs.getString("industry"));

				leadList.add(lead);
			}
			return leadList;
		});
	}
}
