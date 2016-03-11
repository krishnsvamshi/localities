package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.avaje.ebean.Model;
@Entity
public class GitNotification extends Model{
	@Id
	public Long id;
	public String notificationTitle;
	public String repository;
	public String repositoryBranch;
	public Integer noOfCommits;
	public String committedBy;
	public String pusherEmail;
	@OneToMany(cascade=CascadeType.ALL)
	public List<GitCommit> commitsList = new ArrayList<GitCommit>();
	public static Model.Finder<Long, GitNotification> find = new Model.Finder<Long, GitNotification>(GitNotification.class);
}
