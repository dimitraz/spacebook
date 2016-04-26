package models;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class Comment extends Model {
	@ManyToOne
	public User fromUser;
	@Lob
	public String content;
	public String currentDate;

	public Comment(String content) {
		this.content = content;
	}

	public String toString() {
	  return  "Author name: " + fromUser.firstName + "\n" + "Comment:\n" + content + "\n" + "Date: " + currentDate;
	} 
}