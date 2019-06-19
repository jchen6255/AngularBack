package com.java.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.java.dto.Post;
import com.java.dto.User;

@Repository
public class PostRepositoryImpl implements PostRepository{

	@Autowired 
	@Qualifier("sessionFactory")
	SessionFactory sf;
	@Override
	public Post getPostById(int postId) {
		Session s = sf.openSession();
		Query<Post> q = s.createQuery("From Post Where pId = :pId", Post.class);
		q.setParameter("pId", postId);
		Post currPost = q.uniqueResult();
		s.close();
		System.out.println(currPost);
		System.out.println("author = " + currPost.getAuthor());
		return currPost;
	}

	@Override
	public List<Post> getPostsOfUser(int userId) {
		Session s = sf.openSession();
		Query<Post> q = s.createQuery("From Post Where author.id = :uId Order By pId DESC", Post.class);
		q.setParameter("uId", userId);
		List<Post> posts = q.list();
		s.close();
		return posts;
	}

	//@Transactional
	@Override
	public void updatePost(Post currPost) {
		Session s=sf.openSession();
		Transaction tx = s.beginTransaction();
		
		s.update(currPost);
		tx.commit();
		s.close();
	}

	@Override
	public User insertPost(Post currPost, int userId) {
		Session s=sf.openSession();
		Transaction tx = s.beginTransaction();
		Query<User> q = s.createQuery("From User Where id = :uId", User.class);
		q.setParameter("uId", userId);
		User currUser = q.uniqueResult();
		//currPost.setAuthorId(userId);
		currPost.setAuthor(currUser);
		s.save(currPost);
		tx.commit();
		//Query<Post> q2 = s.createQuery("From Post Order By pId DESC LIMIT 1", Post.class);
		//Post newPost = q2.uniqueResult();
		s.close();
		
		s=sf.openSession();
		Query<User> q2 = s.createQuery("From User Where id = :uId", User.class);
		q2.setParameter("uId", userId);
		User updatedUser = q2.uniqueResult();
		s.close();
		
		System.out.println("This is updated user: " + updatedUser);
		return updatedUser;
	}

	@Override
	public List<Post> getPosts() {
		Session s = sf.openSession();
		Query<Post> q = s.createQuery("From Post Order By pId DESC", Post.class);
		List<Post> posts = q.list();
		s.close();
		return posts;
	}

}
