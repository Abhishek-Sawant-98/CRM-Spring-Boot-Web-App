package com.crm.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.crm.model.User;
import com.crm.queryconstants.UserConstants;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // Generates constructor for final fields at compile time
@Repository
public class UserDaoImpl implements UserDao {

	// Using final fields and a constructor is generally considered best practise
	// for autowiring dependencies
	private final JdbcTemplate jdbcTemplate;

	@Override
	public boolean signUp(User user) {
		String insertQuery = UserConstants.INSERT_USER;

		if (!isUserAlreadyRegistered(user.getUserId())) {

			jdbcTemplate.update(insertQuery, user.getUserId(), user.getPassword(), user.getFirstName(),
					user.getLastName(), user.getDob(), user.getGender(), user.getContactNumber(), user.getEmailId(),
					user.getJobTitle(), user.getCompany(), user.getEmployeeCount(), user.getCountry());

			return true;
		}
		return false;
	}

	// Checking if user credentials are uniquely present in the database
	@Override
	public boolean isUserAuthentic(User user) {
		String userOccurrencesQuery = UserConstants.FIND_USER_OCCURRENCES;

		return jdbcTemplate.queryForObject(userOccurrencesQuery, new Object[] { user.getUserId(), user.getPassword() },
				Integer.class) == 1;
	}

	// Checking if user id is already present in database
	@Override
	public boolean isUserAlreadyRegistered(String userId) {
		String userIdOccurrencesQuery = UserConstants.FIND_USER_ID_OCCURRENCES;

		return jdbcTemplate.queryForObject(userIdOccurrencesQuery, new Object[] { userId }, Integer.class) == 1;
	}

	// Fetching the user object by user_id
	@Override
	public User getUserById(String userId) {
		String fetchUserQuery = UserConstants.FETCH_USER_BY_ID;

		return jdbcTemplate.query(fetchUserQuery, new Object[] { userId }, rs -> {
			List<User> userList = new ArrayList<User>();
			while (rs.next()) {
				User user = new User();
				user.setUserId(rs.getString("user_id"));
				user.setPassword(rs.getString("password"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setDob(rs.getString("date_of_birth"));
				user.setGender(rs.getString("gender"));
				user.setContactNumber(rs.getString("contact_number"));
				user.setEmailId(rs.getString("email_id"));
				user.setJobTitle(rs.getString("job_title"));
				user.setCompany(rs.getString("company"));
				user.setEmployeeCount(rs.getString("employee_count"));
				user.setCountry(rs.getString("country"));
				userList.add(user);
			}
			return userList.get(0);
		});
	}

}
