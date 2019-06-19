package com.java.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.java.dto.Like;

@Repository
public class LikeRepositoryImpl implements LikeRespository{

	@Autowired 
	@Qualifier("sessionFactory")
	SessionFactory sf;
	
	@Override
	public void insertLike(Like like) {
		Session s=sf.openSession();
		Transaction tx = s.beginTransaction();
		s.save(like);
		tx.commit();
		s.close();
	}

	@Override
	public void deleteLike(Like like) {
		Session s=sf.openSession();
		Transaction tx = s.beginTransaction();
		s.delete(like);
		tx.commit();
		s.close();
	}

	@Override
	public int likesbyPost(int postId) {
		Session s = sf.openSession();
		Query<Like> q = s.createQuery("From Like Where postId = :pId", Like.class);
		q.setParameter("pId", postId);
		List<Like> list = q.list();
		s.close();
		return list.size();
	}

	@Override
	public List<Like> userLikes(int userId) {
		Session s = sf.openSession();
		Query<Like> q = s.createQuery("From Like Where userId = :uId", Like.class);
		q.setParameter("uId", userId);
		List<Like> list = q.list();
		s.close();
		return list;
	}

}
