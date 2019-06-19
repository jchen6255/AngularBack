package com.java.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.dto.Like;
import com.java.dto.User;
import com.java.service.LikeService;

import lombok.Data;
import lombok.NoArgsConstructor;

@RestController
@CrossOrigin(origins="*")
public class LikeController {

	@Autowired LikeService service;
	
	@PostMapping("/like.do")
	public void insertLike(@RequestBody Like like, HttpServletRequest request) {
		/*User user = (User) request.getSession().getAttribute("User");
		if(user != null) {
			Like like = new Like();
			like.setUserId(user.getId());
			like.setPostId(postId.postId);
			service.insertLike(like);
		}*/
		service.insertLike(like);
	}

	@DeleteMapping("/unlike.do")
	public void deleteLike(@RequestBody Like like, HttpServletRequest request) {
		/*User user = (User) request.getSession().getAttribute("User");
		if(user != null) {
			Like like = new Like();
			like.setUserId(user.getId());
			like.setPostId(postId.postId);
			service.deleteLike(like);
		}*/
		service.deleteLike(like);
	}

	public int likesbyPost(int postId) {
		return service.likesbyPost(postId);
	}

	public List<Like> userLikes(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("User");
		return service.userLikes(user.getId());
	}
}

@Data
@NoArgsConstructor
class PostId{
	int postId;
}
