package com.cognizant.crm.repository;

import com.cognizant.crm.model.User;
import com.cognizant.crm.queryconstants.AccountConstants;
import com.cognizant.crm.queryconstants.ContactConstants;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.cognizant.crm.model.Contact;

import lombok.RequiredArgsConstructor;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor // Generates constructor for final fields at compile time
@Repository
public class ContactDaoImpl implements ContactDao {

	// Using final fields and a constructor is generally considered best practise
	// for autowiring dependencies
	private final JdbcTemplate jdbcTemplate;

	@Override
	public boolean addContact(Contact contact, User user) {

		String insertQuery = ContactConstants.INSERT_CONTACT;
		String insertNameQuery = ContactConstants.INSERT_CONTACT_NAME;
		String insertMailingAddressQuery = ContactConstants.INSERT_CONTACT_MAILING_ADDRESS;
		String insertOtherAddressQuery = ContactConstants.INSERT_CONTACT_OTHER_ADDRESS;
		String accountKeyQuery = AccountConstants.ACCOUNT_KEY;

		contact.setContactOwner(user.getUserId());
		contact.setIdAccount(jdbcTemplate.queryForObject(accountKeyQuery,
				new Object[] { contact.getAccountName(), contact.getContactOwner() }, Integer.class));

		// If the contact isn't registered, insert the contact details in database and
		// return true, else return false
		if (!isContactRegistered(contact)) {

			// GeneratedKeyHolder is used to capture the specified auto-generated primary
			// key from MySQL upon successful insertion into the specified table
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(con -> {
				PreparedStatement ps = con.prepareStatement(insertQuery, new String[] { "idContact" });
				ps.setString(1, contact.getContactOwner());
				ps.setInt(2, contact.getIdAccount());
				ps.setString(3, contact.getMobile());
				ps.setString(4, contact.getEmailId());
				ps.setString(5, contact.getTitle());
				ps.setString(6, contact.getFax());
				ps.setString(7, contact.getDepartment());
				ps.setString(8, contact.getHomePhone());
				ps.setString(9, contact.getOtherPhone());
				ps.setString(10, contact.getLeadSource());
				ps.setString(11, contact.getDob());
				ps.setString(12, contact.getAssistant());										
				ps.setString(13, contact.getAssistantPhone());
				ps.setString(14, contact.getDescription());
				return ps;
			}, keyHolder);

			/*
			 * This primary key of the inserted contact will be used for inserting its
			 * address (Mailing address and Other address) and Name
			 */
			contact.setIdContact(Objects.requireNonNull(keyHolder.getKey()).intValue());

			// Inserts the Name
			jdbcTemplate.update(insertNameQuery, contact.getSalutation(), contact.getFirstName(), contact.getLastName(),
					contact.getIdContact());

			// Inserts the Mailing address
			jdbcTemplate.update(insertMailingAddressQuery, contact.getMailingStreet(), contact.getMailingCity(),
					contact.getMailingStateProvince(), contact.getMailingZipPostal(), contact.getMailingCountry(),
					contact.getIdContact());

			// Inserts the Other address
			jdbcTemplate.update(insertOtherAddressQuery, contact.getOtherStreet(), contact.getOtherCity(),
					contact.getOtherStateProvince(), contact.getOtherZipPostal(), contact.getOtherCountry(),
					contact.getIdContact());

			return true;
		}
		return false;

	}

	@Override
	public boolean isContactRegistered(Contact contact) {
		String contactOccurrenceQuery = ContactConstants.CONTACT_OCCURRENCES;

		return jdbcTemplate.queryForObject(contactOccurrenceQuery,
				new Object[] { contact.getContactOwner(), contact.getMobile() }, Integer.class) == 1;
	}

	@Override
	public List<Contact> getContactsByUserId(String userId) {
		String fetchContactsQuery = ContactConstants.FETCH_CONTACTS;

		return jdbcTemplate.query(fetchContactsQuery, new Object[] { userId }, rs -> {
			List<Contact> contactList = new ArrayList<Contact>();
			while (rs.next()) {
				Contact contact = new Contact();
				contact.setContactName(rs.getString("Contact_Name"));
				contact.setAccountName(rs.getString("account_name"));
				contact.setMobile(rs.getString("mobile"));
				contact.setEmailId(rs.getString("email_id"));
				contact.setDob(rs.getString("birth_date"));
				contact.setTitle(rs.getString("title"));
				contact.setFax(rs.getString("fax"));
				contact.setDepartment(rs.getString("department"));
				contact.setLeadSource(rs.getString("lead_source"));
				contact.setAssistantPhone(rs.getString("assistant_phone"));
				contact.setMailingAddress(rs.getString("Mailing_Address"));
				contact.setOtherAddress(rs.getString("Other_Address"));

				contactList.add(contact);
			}
			return contactList;
		});
	}

}
