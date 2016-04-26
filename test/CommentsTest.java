import java.util.ArrayList;
import java.util.List;

import models.Comment;
import models.Post;
import models.User;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

public class CommentsTest extends UnitTest
{
  private User bob;
  private Comment comment1, comment2;
  private Post post1, post2;

  @BeforeClass
  public static void loadDB()
  {
    Fixtures.deleteAllModels();
  }

  @Before
  public void setup()
  {
    bob   = new User("bob", "jones", "bob@jones.com", "secret", 20, "irish");
    post1 = new Post("Post Title 1", "This is the first post content");
    post2 = new Post("Post Title 2", "This is the second post content");
    comment1 = new Comment("This is the first comment");
    comment2 = new Comment("This is the second comment");
    bob.save();
    post1.save();
    post2.save();
    comment1.save();
    comment2.save();
  }

  @After
  public void teardown()
  {
    bob.delete();
    post1.delete();
    post2.delete();
    comment1.delete();
    comment2.delete();
  }

  @Test
  public void testCreateComment()
  {
	post1.comments.add(comment1); 	// Add comment1 to post1
	post1.save();
    bob.posts.add(post1);			// Add post1 to posts
    bob.save();

    User user = User.findByEmail("bob@jones.com");
    List<Post> posts = user.posts;
    Post post = posts.get(0);
    
    assertEquals(1, post.comments.size());
    assertEquals(post1.comments.get(0).content, "This is the first comment");
  }

  @Test
  public void testCreateMultipleComments()
  {
	post1.comments.add(comment1); 
	post1.comments.add(comment2);
	post1.save();
	
    bob.posts.add(post1);	// Post1 should have 2 comments
    bob.posts.add(post2);	// Post2 should have 0 comments
    bob.save();

    User user = User.findByEmail("bob@jones.com");
    List<Post> posts = user.posts;
    assertEquals(2, posts.size());
    
    Post posta = posts.get(0);
    assertEquals(2, posta.comments.size());
    assertEquals(posta.comments.get(0).content, "This is the first comment");
    assertEquals(posta.comments.get(1).content, "This is the second comment");

    Post postb = posts.get(1);
    assertEquals(0, postb.comments.size());
  }
  
  @Test
  public void testDeleteComment() {
	  Post post3 = new Post("Post title 3", "This is the third post content");
	  post3.save();
	  Comment comment3 = new Comment("This is the third comment");
	  comment3.save();
	  
	  post3.comments.add(comment3);
	  bob.posts.add(post3);
	  bob.save();

	  User user = User.findByEmail("bob@jones.com");
	  Post post = user.posts.get(0);
	  assertEquals(1, user.posts.size());
	  assertEquals(1, post.comments.size()); 

	  user.posts.remove(0);
	  user.save();
	  post3.delete();

	  User anotherUser = User.findByEmail("bob@jones.com");
	  assertEquals(0, anotherUser.posts.size());
  }
}