package com.java.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name="Likes")
public class Like implements Serializable{
	/*@Id
	@GeneratedValue
	int id;*/
	@Id
	int userId;
	@Id
	int postId; 
}
