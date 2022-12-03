package com.blog.service;

import java.util.List;

import com.blog.exception.CommentException;
import com.blog.exception.LoginException;
import com.blog.exception.PostException;
import com.blog.model.Comment;

public interface CommentService {

	public List<Comment> getAllComment(Integer postId) throws PostException, CommentException, LoginException;

	public Comment getCommentById(Integer postId, Integer commentId)
			throws PostException, LoginException, CommentException;

	public Comment createNewComment(Integer postId, Comment comment)
			throws PostException, LoginException, CommentException;

	public Comment updateComment(Integer postId, Comment comment, Integer commentId)
			throws PostException, LoginException, CommentException;

	public Comment deleteCommentById(Integer postId, Integer commentId) throws PostException, LoginException;

}
