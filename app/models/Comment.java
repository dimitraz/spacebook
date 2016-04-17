package models;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class Comment extends Model
{
  public String author;
  @Lob
  public String content;

  public Comment(String author, String content)
  {
    this.author = author;
    this.content = content;
  }

  public String toString()
  {
	  return  "Author name: " + author + "\n" + "Comment:\n" + content;
  } 
}