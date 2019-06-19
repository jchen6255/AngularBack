package com.java.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.java.dto.Post;
import com.java.dto.User;

public interface PostService {
	public Post getPost(int id);

	public List<Post> getPosts();

	public User addPost(Post post, int userId);

	public List<Post> getPostsFromListUser(int id);

	public void updatePost();

	public void likePost();

	public void unLikePost();
}
