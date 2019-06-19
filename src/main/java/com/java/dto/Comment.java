package com.java.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Comment {
	@Id
	@GeneratedValue
	int id;
	@NotNull
	LocalDateTime commentDate;
	@Size(min=1, max=250)
	String description;
	@NotNull
	int authorId;
	@NotNull
	int postId;
}
