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
      
      String photoName4 = "margecover.jpg";
      Blob blob4 = new Blob();
      blob4.set(new FileInputStream(photoName4), MimeTypes.getContentType(photoName4));
      User marge = User.findByEmail("marge@simpson.com");
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
      
      
      String photoName8 = "maggiecover.jpg";
      Blob blob8 = new Blob();
      blob8.set(new FileInputStream(photoName8), MimeTypes.getContentType(photoName8));
      User maggie = User.findByEmail("maggie@simpson.com");
      maggie.coverPicture = blob8;
      maggie.save();  
  }
}