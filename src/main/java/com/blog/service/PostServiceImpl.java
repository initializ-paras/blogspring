package com.blog.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.exception.LoginException;
import com.blog.exception.PostException;
import com.blog.exception.UserException;
import com.blog.model.Login;
import com.blog.model.Post;
import com.blog.model.PostDTO;
import com.blog.model.PostUpdateDTO;
import com.blog.model.User;
import com.blog.repository.LoginRepo;
import com.blog.repository.PostRepo;
import com.blog.repository.UserRepo;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private LoginRepo loginRepo;

	// checking user login validation
	public boolean checkLoginStatus() {
		List<Login> loginList = loginRepo.findAll();
		if (loginList.isEmpty())
			return false;

		return true;
	}

	// get current logged user id
	public Integer getUserId() throws LoginException, UserException {

		List<Login> loginList = loginRepo.findAll();
		if (loginList.isEmpty())
			throw new LoginException("User login required!");

		User user = userRepo.findByEmail(loginList.get(0).getEmail());
		if (user == null)
			throw new UserException("User is not register!");

		return user.getId();
	}

	@Override
	public List<Post> getAllPost() throws PostException, LoginException {
		// TODO Auto-generated method stub

		if (checkLoginStatus() == false)
			throw new LoginException("User login required!");

		List<Post> postList = postRepo.findAll();
		if (postList.isEmpty())
			throw new PostException("Post list is empty!");

		return postList;
	}

	@Override
	public Post getPostById(Integer postId) throws PostException, LoginException {
		// TODO Auto-generated method stub

		if (checkLoginStatus() == false)
			throw new LoginException("User login required!");

		Optional<Post> postOpt = postRepo.findById(postId);
		if (postOpt.isEmpty())
			throw new PostException("Post not found with this id " + postId);

		return postOpt.get();
	}

	@Override
	public Post createNewPost(PostDTO post) throws PostException, LoginException, UserException {
		// TODO Auto-generated method stub

		if (post == null)
			throw new PostException("Post can't be null!");

		Post newPost = new Post();
		newPost.setTitle(post.getTitle());
		newPost.setDescription(post.getDescription());

		int userId = getUserId();
		newPost.setAuthorId(userId);
		newPost.setDate(LocalDateTime.now());

		return postRepo.save(newPost);
	}

	@Override
	public Post updatePost(PostUpdateDTO post, Integer postId) throws PostException, LoginException, UserException {
		// TODO Auto-generated method stub

		if (checkLoginStatus() == false)
			throw new LoginException("User login required!");

		Optional<Post> postOpt = postRepo.findById(postId);
		if (postOpt.isEmpty())
			throw new PostException("Post not found with this id " + postId);

		if (post == null)
			throw new PostException("Post can't be null!");

		int userId = getUserId();
		Post updatedPost = postOpt.get();
		updatedPost.setAuthorId(userId);
		updatedPost.setDate(LocalDateTime.now());

		return postRepo.save(updatedPost);
	}

	@Override
	public Post deletePostById(Integer postId) throws PostException, LoginException {
		// TODO Auto-generated method stub

		if (checkLoginStatus() == false)
			throw new LoginException("User login required!");

		Optional<Post> postOpt = postRepo.findById(postId);
		if (postOpt.isEmpty())
			throw new PostException("Post not found with this id " + postId);

		Post deletedPost = postOpt.get();
		postRepo.delete(deletedPost);
		return deletedPost;
	}

	@Override
	public List<Post> pagination(Integer pageSize, String sortBy) throws PostException {
		// TODO Auto-generated method stub
		return null;
	}

}
