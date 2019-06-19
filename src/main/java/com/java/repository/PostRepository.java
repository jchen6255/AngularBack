package com.java.repository;

import java.util.List;

import com.java.dto.Post;
import com.java.dto.User;

public interface PostRepository {
	public Post getPostById(int postId);
	public List<Post> getPostsOfUser(int userId);
	public List<Post> getPosts();
	public void updatePost(Post currPost);
	public User insertPost(Post currPost, int userId);
	//public void deletePost(int postId);
}
