package com.blog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.exception.CommentException;
import com.blog.exception.LoginException;
import com.blog.exception.PostException;
import com.blog.model.Comment;
import com.blog.model.Login;
import com.blog.model.Post;
import com.blog.repository.CommentRepo;
import com.blog.repository.LoginRepo;
import com.blog.repository.PostRepo;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private LoginRepo loginRepo;

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private CommentRepo commentRepo;

	// checking user login validation
	public boolean checkLoginStatus() {
		List<Login> loginList = loginRepo.findAll();
		if (loginList.isEmpty())
			return false;

		return true;
	}

	@Override
	public List<Comment> getAllComment(Integer postId) throws PostException, CommentException, LoginException {
		// TODO Auto-generated method stub

		if (checkLoginStatus() == false)
			throw new LoginException("User login required!");

		Optional<Post> postOpt = postRepo.findById(postId);
		if (postOpt.isEmpty())
			throw new PostException("Invalid post id");

		List<Comment> commentList = postOpt.get().getCommentList();
		if (commentList.isEmpty())
			throw new CommentException("Empty comment list!");

		return commentList;
	}

	@Override
	public Comment getCommentById(Integer postId, Integer commentId)
			throws PostException, LoginException, CommentException {
		// TODO Auto-generated method stub

		if (checkLoginStatus() == false)
			throw new LoginException("User login required!");

		Optional<Post> postOpt = postRepo.findById(postId);
		if (postOpt.isEmpty())
			throw new PostException("Invalid post id");

		List<Comment> commentList = postOpt.get().getCommentList();
		if (commentList.isEmpty())
			throw new CommentException("Empty comment list!");

		Optional<Comment> commentOpt = commentRepo.findById(commentId);
		if (commentOpt.isEmpty())
			throw new CommentException("Comment not found with " + commentId);

		return commentOpt.get();

	}

	@Override
	public Comment createNewComment(Integer postId, Comment comment)
			throws PostException, LoginException, CommentException {
		// TODO Auto-generated method stub

		if (checkLoginStatus() == false)
			throw new LoginException("User login required!");

		Optional<Post> postOpt = postRepo.findById(postId);
		if (postOpt.isEmpty())
			throw new PostException("Invalid post id");

		return null;
	}

	@Override
	public Comment updateComment(Integer postId, Comment comment, Integer commentId)
			throws PostException, LoginException, CommentException {
		// TODO Auto-generated method stub

		if (checkLoginStatus() == false)
			throw new LoginException("User login required!");

		Optional<Post> postOpt = postRepo.findById(postId);
		if (postOpt.isEmpty())
			throw new PostException("Invalid post id");

		return null;
	}

	@Override
	public Comment deleteCommentById(Integer postId, Integer commentId) throws PostException, LoginException {
		// TODO Auto-generated method stub

		if (checkLoginStatus() == false)
			throw new LoginException("User login required!");

		Optional<Post> postOpt = postRepo.findById(postId);
		if (postOpt.isEmpty())
			throw new PostException("Invalid post id");

		return null;
	}

}
