package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dto.Like;
import com.java.repository.LikeRespository;

@Service
public class LikeServiceImpl implements LikeService{

	@Autowired LikeRespository rep;
	
	@Override
	public void insertLike(Like like) {
		rep.insertLike(like);
	}

	@Override
	public void deleteLike(Like like) {
		rep.deleteLike(like);
	}

	@Override
	public int likesbyPost(int postId) {
		return rep.likesbyPost(postId);
	}

	@Override
	public List<Like> userLikes(int userId) {
		return rep.userLikes(userId);
	}

}
