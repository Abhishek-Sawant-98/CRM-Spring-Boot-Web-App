package com.crm.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.crm.model.Case;
import com.crm.model.User;
import com.crm.queryconstants.AccountConstants;
import com.crm.queryconstants.CaseConstants;
import com.crm.queryconstants.ContactConstants;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor // Generates constructor for final fields at compile time
@Repository
public class CaseDaoImpl implements CaseDao {

	// Using final fields and a constructor is generally considered best practise
	// for autowiring dependencies
	private final JdbcTemplate jdbcTemplate;

	@Override
	public boolean addCase(Case caseObj, User user) {
		String insertQuery = CaseConstants.INSERT_CASE;
		String contactKeyQuery = ContactConstants.CONTACT_KEY;
		String accountKeyQuery = AccountConstants.ACCOUNT_KEY;

		caseObj.setCaseOwner(user.getUserId());

		caseObj.setIdContact(jdbcTemplate.queryForObject(contactKeyQuery,
				new Object[] { caseObj.getContactMobile(), caseObj.getCaseOwner() }, Integer.class));

		caseObj.setIdAccount(jdbcTemplate.queryForObject(accountKeyQuery,
				new Object[] { caseObj.getAccountName(), caseObj.getCaseOwner() }, Integer.class));

		// If the case isn't registered, insert the case details in database and return
		// true, else return false
		if (!isCaseRegistered(caseObj)) {

			jdbcTemplate.update(insertQuery, caseObj.getCaseOwner(), caseObj.getIdContact(), caseObj.getIdAccount(),
					caseObj.getEmailId(), caseObj.getStatus(), caseObj.getType(), caseObj.getCaseOrigin(),
					caseObj.getCaseReason(), caseObj.getPriority(), caseObj.getSubject(), caseObj.getDescription(),
					caseObj.getInternalComments());

			return true;
		}
		return false;
	}

	@Override
	public boolean isCaseRegistered(Case caseObj) {
		String caseOccurrencesQuery = CaseConstants.CASE_OCCURRENCES;

		return jdbcTemplate.queryForObject(caseOccurrencesQuery,
				new Object[] { caseObj.getCaseOwner(), caseObj.getSubject() }, Integer.class) == 1;
	}

	@Override
	public List<Case> getCasesByUserId(String userId) {
		String fetchCasesQuery = CaseConstants.FETCH_CASES;

		return jdbcTemplate.query(fetchCasesQuery, new Object[] { userId }, rs -> {
			List<Case> caseList = new ArrayList<>();
			while (rs.next()) {
				Case caseObj = new Case();
				caseObj.setContactName(rs.getString("Contact_Name"));
				caseObj.setContactMobile(rs.getString("mobile"));
				caseObj.setAccountName(rs.getString("Account_Name"));
				caseObj.setEmailId(rs.getString("email_id"));
				caseObj.setSubject(rs.getString("subject"));
				caseObj.setStatus(rs.getString("status"));
				caseObj.setType(rs.getString("type"));
				caseObj.setCaseOrigin(rs.getString("case_origin"));
				caseObj.setCaseReason(rs.getString("case_reason"));
				caseObj.setPriority(rs.getString("case_priority"));
				caseObj.setDescription(rs.getString("description"));

				caseList.add(caseObj);
			}
			return caseList;
		});
	}

}
