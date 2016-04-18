package controllers;

import java.util.ArrayList;
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
		  User user = Accounts.getLoggedInUser();
		  Post post = new Post (title, content);
		  
		  //LocalDate dateNow = LocalDate.now();
		  //post.date = dateNow;
		  
		  post.save();
		  user.posts.add(post);
		  user.save();
    
		  Logger.info ("title:" + title + " content:" + content);
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