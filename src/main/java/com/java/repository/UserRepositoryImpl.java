package com.java.repository;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.java.controller.MD5;
import com.java.dto.User;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	@Qualifier("sessionFactory")
	SessionFactory sf;

	@Override
	public User getUserByUsername(String username/* , String hashedPassword */) {
		Session s = sf.openSession();
		// Query<User> q = s.createQuery("From User Where username = :uName And password
		// = :uPass", User.class);
		Query<User> q = s.createQuery("From User Where username = :uName", User.class);
		q.setParameter("uName", username);
		// q.setParameter("uPass", hashedPassword);
		User currUser = q.uniqueResult();
		s.close();
		return currUser;
	}

	@Override
	public User getUserById(int userId) {
		Session s = sf.openSession();
		Query<User> q = s.createQuery("From User Where id = :uId", User.class);
		q.setParameter("uId", userId);
		User currUser = q.uniqueResult();
		s.close();
		return currUser;
	}

	@Override
	public User registerUser(User newUser) {
		Session s = sf.openSession();
		Transaction tx = s.beginTransaction();
		// encrypt before save
		MD5 encryption = new MD5();
		newUser.setPassword(encryption.encrypt(newUser.getPassword()));
		s.save(newUser);
		try {
			tx.commit();
		} catch (PersistenceException e) {
			System.out.println(e.getMessage());
			s.close();
			return null;
		}
		s.close();
		return newUser;
	}

	@Override
	public User updateUser(User currUser) {		
		Session s=sf.openSession();
		Transaction tx = s.beginTransaction();
		Query<User> q1 = s.createQuery("From User Where id = :uId", User.class);
		q1.setParameter("uId", currUser.getId());
		User user = q1.uniqueResult();	
		if(currUser.getEmail() != null) {
			Query<User> q2 = s.createQuery("From User Where email = :uEmail", User.class);
			q2.setParameter("uEmail", currUser.getEmail());
			User existingUser = q2.uniqueResult();
			if(existingUser == null)
				user.setEmail(currUser.getEmail());
		}
		if(currUser.getFirstName() != null)
			user.setFirstName(currUser.getFirstName());
		if (currUser.getLastName() != null)
			user.setLastName(currUser.getLastName());
		if (currUser.getProfilePic() != null)
			user.setProfilePic(currUser.getProfilePic());
		if(currUser.getUsername() != null) {
			Query<User> q3 = s.createQuery("From User Where username = :uUsername", User.class);
			q3.setParameter("uUsername", currUser.getUsername());
			User existingUser = q3.uniqueResult();
			if(existingUser == null)
				user.setUsername(currUser.getUsername());
		}
		if (currUser.getPassword() != null) {
			MD5 encryption = new MD5();
			user.setPassword(encryption.encrypt(currUser.getPassword()));
		}
		// s.merge(currUser);
		s.update(user);
		tx.commit();
		Query<User> q4 = s.createQuery("From User Where id = :uId", User.class);
		q4.setParameter("uId", currUser.getId());
		User userReturn = q4.uniqueResult();
		s.close();
		return userReturn;
	}

	@Override
	public List<User> getUsers() {
		Session s = sf.openSession();
		Query<User> q = s.createQuery("From User", User.class);
		List<User> list = q.list();
		s.close();
		return list;
	}

	@Override
	public List<User> getUserslikeUsername(String usrname) {
		Session s = sf.openSession();
		Query<User> q = s.createQuery("From User Where Username Like :uName", User.class);
		q.setParameter("uName", "%" + usrname + "%");
		List<User> list = q.list();
		s.close();
		return list;

	}
}
