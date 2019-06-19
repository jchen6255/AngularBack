package com.java.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.java.dto.Comment;

//@Repository
public class CommentRepositoryImpl implements CommentRepository{

	//@Autowired 
	@Qualifier("sessionFactory")
	SessionFactory sf;
	
	@Override
	public List<Comment> getComments(int postId) {
		Session s = sf.openSession();
		Query<Comment> q = s.createQuery("From Comment Where postId = :pId Order By commentDate ASC", Comment.class);
		q.setParameter("pId", postId);
		List<Comment> list = q.list();
		s.close();
		return list;
	}

	@Override
	public void insertComment(Comment comment) {
		Session s=sf.openSession();
		Transaction tx = s.beginTransaction();
		s.save(comment);
		tx.commit();
		s.close();
		
	}
	
}
