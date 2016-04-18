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
import java.util.List;
	  
public class PostPage extends Controller {
	
	  public static void index(Long id, Long postid) {
		  User user = User.findById(id);
		  Post post = Post.findById(postid);
		  render(post, user);
	  }
	  
	  public static void newComment(Long id, Long postid, String commentTitle, String commentContent) {
		  Comment comment = new Comment(commentTitle, commentContent);
		  comment.save();
		  Post currentPost = Post.findById(postid);
		  currentPost.newComment(comment);
		  currentPost.save();
		  // Logger.info("Blah " + comment + currentPost.comments);
		  index(id, postid);
	  }

}
