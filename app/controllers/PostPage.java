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
import java.util.List;
	  
public class PostPage extends Controller {
	
	  public static void index(Long id, Long postid) {
		  User user = User.findById(id);
		  Post post = Post.findById(postid);
		  render(post, user);
	  }
	  
	  public static void newComment(Long id, Long postid, String commentContent) {
		  if(Accounts.getLoggedInUser() != null) {
			  Comment comment = new Comment(commentContent);
			
			  // Create Date
			  SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
			  Date now = new Date();
			  comment.currentDate = formatDate.format(now);
			  
			  // Get User
			  User currentUser = Accounts.getLoggedInUser();
			  comment.fromUser = currentUser;
			  comment.save();
			  
			  // Add and save comment to post
			  Post currentPost = Post.findById(postid);
			  currentPost.newComment(comment);
			  currentPost.save();
	
			  index(id, postid);
		  } 
		  else {
			  Accounts.index();
		  }
	  }

}
