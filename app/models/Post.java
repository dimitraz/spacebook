package models;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import controllers.Accounts;
import play.Logger;
import play.db.jpa.Model;
import models.Comment;

@Entity
public class Post extends Model 
{
	public String title;
	@Lob
	public String content;
	public ArrayList<Comment> comments;
  
	public Post() {
		comments = new ArrayList<Comment>();
	}
	
	public Post(String title, String content) {
		this.title = title;
  		this.content = content;
  		comments = new ArrayList<Comment>();
	}
  
	public void addComment(String commentTitle, String commentContent) {
		Comment comment = new Comment(commentTitle, commentContent);
		comment.save();
		comments.add(comment);
	}
	
	public void newComment(Comment comment) {
		comment.save();
	  	comments.add(comment);
  }
  
	public String toString() {
  		return title;
	} 
}