package com.blog.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class PostUpdateDTO {

	private Integer id;
	private String title;
	private String description;
	
	@JsonIgnore
	private LocalDateTime date;
	
	@JsonIgnore
	private Integer authorId;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	List<Comment> commentList = new ArrayList<>();
}
