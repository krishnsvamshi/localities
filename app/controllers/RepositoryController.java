package controllers;

import java.io.IOException;
import java.util.HashMap;
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

import play.Logger;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Http.MultipartFormData;
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
		JsonNode j1 = json.findPath("head_commit");
		JsonNode j2 = j1.findPath("committer");
	//	DynamicForm loginData = Form.form().bindFromRequest();
//	
//		String ss =loginData.toString();
		Logger.info(" >>>>>>>>>>  json map size "+j2.findValue("email").asText());
		Logger.info("webhook called >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		return ok(Json.toJson("asdfasd"));
	}


}
