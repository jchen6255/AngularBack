package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dto.Post;
import com.java.dto.User;
import com.java.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService{

	@Autowired PostRepository rep;
	
	@Override
	public Post getPost(int id) {
		return rep.getPostById(id);
	}

	@Override
	public List<Post> getPosts() {
		return rep.getPosts();
	}

	@Override
	public User addPost(Post post, int userId) {
		return rep.insertPost(post, userId);
	}

	@Override
	public List<Post> getPostsFromListUser(int id) {
		return rep.getPostsOfUser(id);
	}

	@Override
	public void updatePost() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void likePost() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unLikePost() {
		// TODO Auto-generated method stub
		
	}

}
