package controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.OneToMany;

import models.Comment;
import models.Message;
import models.Post;
import models.User;
import play.Logger;
import play.mvc.Controller;

public class Blog  extends Controller {
	  
	public static void index() {
		User user = Accounts.getLoggedInUser();
		render(user);
	}
	  
	public static void visit(Long id) {
		User user = User.findById(id);
		render(user);
	}
	    
	public static void newPost(String title, String content) {
		// Create Post and add to posts List
		User user = Accounts.getLoggedInUser();
		Post post = new Post (title, content);

		post.save();
		user.posts.add(post);
		user.save();	  
		  
		// Create Date
		SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
		Date now = new Date();
		post.currentDate = formatDate.format(now);
		post.save();
		  
		// Log and redirect to blog index
		Logger.info ("title:" + title + " content:" + content + " date:" + post.currentDate);
		index();
	}
	  
	public static void deletePost(Long postid) {
		User user = Accounts.getLoggedInUser();
		Post post = Post.findById(postid);
		user.posts.remove(post);
		user.save();
		post.delete();
		
		index();
	}
 
}
	   