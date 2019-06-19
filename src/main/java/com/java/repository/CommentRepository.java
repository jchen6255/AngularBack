package com.java.repository;

import java.util.List;

import com.java.dto.Comment;

public interface CommentRepository {
	public List<Comment> getComments(int postId);
	public void insertComment(Comment comment);
}
