package com.java.service;

import java.util.List;

import com.java.dto.Like;

public interface LikeService {
	public void insertLike(Like like);

	public void deleteLike(Like like);

	public int likesbyPost(int postId);

	public List<Like> userLikes(int userId);
}
