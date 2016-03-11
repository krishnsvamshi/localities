package models;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.avaje.ebean.Model;

@Entity
public class GitCommit extends Model {
	@Id
	public Long id;
	public String commitId;
	public String message;
	public String commitUrl;
	public Date committedAt;
	public String committerName;
	public String committerEmail;
	public String userName;
	public static Model.Finder<Long, GitCommit> find = new Model.Finder<Long, GitCommit>(GitCommit.class);

}
