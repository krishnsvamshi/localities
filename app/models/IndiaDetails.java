package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.avaje.ebean.Model;
@Entity
public class IndiaDetails extends Model{
	@Id
	public Long id;
	public String state;
	public String district;
	public String city;
	public String locality;
	  public static Model.Finder<Long, IndiaDetails> find = new Finder<Long, IndiaDetails>(
			  IndiaDetails.class);

}
