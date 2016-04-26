package controllers;

import play.*;
import play.db.jpa.Blob;
import play.mvc.*;

import java.util.*;

import models.*;

public class Home extends Controller {
	
	public static void index() {
		User user = Accounts.getLoggedInUser();
		render(user);
	}

	public static void drop(Long id) {
		User user   = Accounts.getLoggedInUser();    
		User friend = User.findById(id);
		user.unfriend(friend);
		Logger.info("Dropping " + friend.email);
		index();
	}  
  
	public static void changeStatus(String statusText) {
		User user = Accounts.getLoggedInUser();
		user.statusText = statusText;
		user.save();
		Logger.info("Status changed to " + statusText);
		index();
	}
  
	public static void uploadPicture(Long id, Blob picture) {
		User user = User.findById(id);
		user.profilePicture = picture;
		user.save();
		Logger.info("saving picture");
		index();
	} 
  
  	public static void uploadCover(Long id, Blob picture) {
  		User user = User.findById(id);
  		user.coverPicture = picture;
  		user.save();
  		index();
  	} 
}