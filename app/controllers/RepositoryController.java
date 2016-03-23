package controllers;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;

import com.fasterxml.jackson.databind.JsonNode;

import models.GitCommit;
import models.GitNotification;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class RepositoryController extends Controller {

	
	
	private static Map<String, String> searchQuery = new HashMap<String, String>();
	private Git git;
public Result getRepos() throws IOException, GitAPIException{
	
	GitHubClient client = new GitHubClient();
	 client.setCredentials("vamshikrishnam", "");
     RepositoryService service = new RepositoryService(client);
     
     List<Repository> repo = service.getRepositories();
     System.out.println(">>>>>>>>> "+repo.toString());
     for(Repository rep : repo){
    	/* System.out.println(">>>>>>"+rep.getName());
    	 List<RepositoryBranch> branches = service.getBranches(rep);*/
    	 /*for(RepositoryBranch branch : branches){
    		 System.out.println("branch name - "+branch.getName());
    		 TypedResource  tr = branch.getCommit();
    		 
    		 System.out.println("last commit "+branch.getCommit());
    	 }*/
    	 org.eclipse.jgit.lib.Repository repository = new FileRepository(rep.getGitUrl().toString());
    	 Logger.info("repositiry "+repository.getBranch());
    	 Git git = new Git(repository);
    	 RevWalk walk = new RevWalk(repository);
    	 List<Ref> branches = git.branchList().call();
    	 Logger.info("branches"+branches.toString());
    	 
    	   for (Ref branch : branches) {
    	        String branchName = branch.getName();

    	        System.out.println("Commits of branch: " + branchName);
    	        System.out.println("-------------------------------------");

    	        Iterable<RevCommit> commits = git.log().all().call();
    	       int  count = 0;
    	       
    	        for (RevCommit rev : commits) {
    	            System.out.println("Commit: " + rev /* + ", name: " + rev.getName() + ", id: " + rev.getId().getName() */);
    	            count++;
    	        }
    	        System.out.println("Had " + count + " commits overall on test-branch");
    	   }
     }
     
	return ok();
}

	public Result getWebHookJson(Long id){
		Logger.info("json recieved------------------------ : "+id);
		JsonNode json = request().body().asJson();
		JsonNode ref = json.findPath("ref");
		JsonNode repository = json.findPath("repository");
		Logger.info("repository "+repository.findPath("name").asText());
		Logger.info(" branch name "+ref.asText());
		JsonNode commitsList = json.findValue("commits");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssX");
		Logger.info("webhook called >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		GitNotification gitNotification = new GitNotification();
		gitNotification.notificationTitle="BB8";
		gitNotification.repositoryBranch=ref.asText().replace("refs/heads/", "");
		gitNotification.repository = json.findPath("repository").findValue("name").asText();
		gitNotification.originJson = json.toString();
		Iterator<JsonNode> i= commitsList.elements();
		while(i.hasNext()){
			GitCommit commit = new GitCommit();
			JsonNode jsonCommit = i.next();
			commit.commitId = jsonCommit.findValue("id").asText();
			commit.message=jsonCommit.findValue("message").asText();
			commit.commitUrl =  jsonCommit.findValue("url").asText();
			String commitTime = jsonCommit.findValue("timestamp").asText();
			commit.committerName=jsonCommit.findValue("committer").findValue("name").asText();
			commit.committerEmail =  jsonCommit.findValue("committer").findValue("email").asText();
			commit.userName =  jsonCommit.findValue("committer").findValue("username").asText();
			try {
				Logger.info("String date  "+commitTime.replace("T", " ").replace("+", " "));
				commit.committedAt = sdf.parse(commitTime.replace("T", " "));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			gitNotification.commitsList.add(commit);
					
		}
		gitNotification.noOfCommits = gitNotification.commitsList.size();
		gitNotification.committedBy = json.findValue("pusher").findValue("name").asText();
		gitNotification.pusherEmail = json.findValue("pusher").findValue("email").asText();
		gitNotification.save();
		
		Logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>> calling gitNotify page");
	   String messagesPage = views.html.GitNotification.render(gitNotification).toString();
	   Logger.info(messagesPage);
		 return ok();
		
	}
	public Result chatRoom(Long id){
		Logger.info("dsjkfhjksdf    >>"+id);
		Logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>> from chatRoom method");
		Logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>> from chatRoom method");
		Logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>> from chatRoom method");
		return ok("");
	}
	public Result chatRoom1(Long id){
		Logger.info("dsjkfhjksdf    >>"+id);
		Logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>> from chatRoom method");
		Logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>> from chatRoom method");
		Logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>> from chatRoom method");
		return ok("");
	}
	public Result chatRoom2(Long id){
		Logger.info("dsjkfhjksdf    >>"+id);
		Logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>> from chatRoom method");
		Logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>> from chatRoom method");
		Logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>> from chatRoom method");
		return ok("");
	}


}
