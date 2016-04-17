package controllers;

import play.*;
import play.db.jpa.Blob;
import play.mvc.*;

import java.io.File;
import java.util.*;

import models.*;

public class Profile extends Controller
{
	public static void index() {
	  User user = Accounts.getLoggedInUser();
	  render(user);
	}
  
  public static void changeStatus(String statusText) {
	  User user = Accounts.getLoggedInUser();
	  user.statusText = statusText;
	  user.save();
	  Logger.info("Status changed to " + statusText);
	  index();
  }
  
	public static void getPicture(Long id) {
		String[] images = {"public/images/avatar-1.jpg", "public/images/avatar-2.jpg", "public/images/avatar-3.jpg"};
		int random = new Random().nextInt(images.length);
			
	    User user = User.findById(id);
	    Blob picture = user.profilePicture;
	    
	    if (picture.exists())
	    {
	      response.setContentTypeIfNotSet(picture.type());
	      renderBinary(picture.get());
	    } else {
	    	renderBinary(new File(images[random]));
	    }
	}
  
  public static void uploadPicture(Long id, Blob picture)
  {
    User user = User.findById(id);
    user.profilePicture = picture;
    user.save();
    Logger.info("saving picture");
    index();
  }  
  
  public static void getThumbnail(Long id) 
  {
    User user = User.findById(id);
    Blob picture = user.thumbnailPicture;
    if (picture.exists())
    {
      response.setContentTypeIfNotSet(picture.type());
      renderBinary(picture.get());
    }
  }

  public static void uploadThumbnail(Long id, Blob picture)
  {
    User user = User.findById(id);
    user.thumbnailPicture = picture;
    user.save();
    index();
  } 
}