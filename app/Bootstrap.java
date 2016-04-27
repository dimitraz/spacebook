import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import play.*;
import play.db.jpa.Blob;
import play.jobs.*;
import play.libs.MimeTypes;
import play.test.*;
 
import models.*;
 
@OnApplicationStart
public class Bootstrap extends Job 
{ 
  public void doJob() throws FileNotFoundException 
  {
	  Fixtures.deleteDatabase();
      Fixtures.loadModels("data.yml");
      
      List<User> users = User.findAll();
      Logger.info("Blah" + users.get(2).posts);
      
      String photoName1 = "lisa.jpg";
      String photoName2 = "lisacover.jpg";
      Blob blob1 = new Blob();
      Blob blob2 = new Blob();
      blob1.set(new FileInputStream(photoName1), MimeTypes.getContentType(photoName1));
      blob2.set(new FileInputStream(photoName2), MimeTypes.getContentType(photoName2));
      User lisa = User.findByEmail("lisa@simpson.com");
      lisa.profilePicture = blob1;
      lisa.coverPicture = blob2;
      lisa.save();
      
      String photoName3 = "marge.png";
      String photoName4 = "margecover.jpg";
      Blob blob3 = new Blob();
      Blob blob4 = new Blob();
      blob3.set(new FileInputStream(photoName3), MimeTypes.getContentType(photoName3));
      blob4.set(new FileInputStream(photoName4), MimeTypes.getContentType(photoName4));
      User marge = User.findByEmail("marge@simpson.com");
      marge.profilePicture = blob3;
      marge.coverPicture = blob4;
      marge.save();  
      
      String photoName5 = "bart.jpg";
      String photoName6 = "bartcover.jpg";
      Blob blob5 = new Blob();
      Blob blob6 = new Blob();
      blob5.set(new FileInputStream(photoName5), MimeTypes.getContentType(photoName5));
      blob6.set(new FileInputStream(photoName6), MimeTypes.getContentType(photoName6));
      User bart = User.findByEmail("bart@simpson.com");
      bart.profilePicture = blob5;
      bart.coverPicture = blob6;
      bart.save();  
      
      String photoName7 = "maggie.png";
      String photoName8 = "maggiecover.jpg";
      Blob blob7 = new Blob();
      Blob blob8 = new Blob();
      blob7.set(new FileInputStream(photoName7), MimeTypes.getContentType(photoName7));
      blob8.set(new FileInputStream(photoName8), MimeTypes.getContentType(photoName8));
      User maggie = User.findByEmail("maggie@simpson.com");
      maggie.profilePicture = blob7;
      maggie.coverPicture = blob8;
      maggie.save();  
      
      String photoName9 = "homer.png";
      String photoName10 = "homercover.jpg";
      Blob blob9 = new Blob();
      Blob blob10 = new Blob();
      blob9.set(new FileInputStream(photoName9), MimeTypes.getContentType(photoName9));
      blob10.set(new FileInputStream(photoName10), MimeTypes.getContentType(photoName10));
      User homer = User.findByEmail("homer@simpson.com");
      homer.profilePicture = blob9;
      homer.coverPicture = blob10;
      homer.save(); 
  }
}