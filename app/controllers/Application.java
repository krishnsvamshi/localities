package controllers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import models.IndiaDetails;
import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Application extends Controller {

    public Result index() {
 
        return ok(index.render("Your new application is ready."));
    }
   public Result getSource(String state){
	   String urlString = "";
	   
	   return ok(urlString);
	   
   }
   public Result getCitySource(String state,String district){
	   String urlString = "";
	  /* try
       {
          URL url = new URL("http://1min.in/indiapost/pincode_search/"+state+"/"+district);
          URLConnection urlConnection = url.openConnection();
          HttpURLConnection connection = null;
          if(urlConnection instanceof HttpURLConnection)
          {
             connection = (HttpURLConnection) urlConnection;
          }
          BufferedReader in = new BufferedReader(
          new InputStreamReader(connection.getInputStream()));
         
          String current;
          while((current = in.readLine()) != null)
          {
             urlString += current;
          }
        //  System.out.print(urlString);
       }catch(IOException e)
       {
          e.printStackTrace();
       }*/
	   return ok(urlString);
	   
   }
   public Result getPostSource(String state,String district,String city){
	   String urlString = "";
	  /* try
       {
          URL url = new URL("http://1min.in/indiapost/pincode_search/"+state+"/"+district+"/"+city);
          URLConnection urlConnection = url.openConnection();
          HttpURLConnection connection = null;
          if(urlConnection instanceof HttpURLConnection)
          {
             connection = (HttpURLConnection) urlConnection;
          }
          BufferedReader in = new BufferedReader(
          new InputStreamReader(connection.getInputStream()));
         
          String current;
          while((current = in.readLine()) != null)
          {
             urlString += current;
          }
        //  System.out.print(urlString);
       }catch(IOException e)
       {
          e.printStackTrace();
       }*/
	   return ok(urlString);
	   
   }

   public Result saveLocality(String state,String district,String city,String post) {
	   IndiaDetails details = new IndiaDetails();
	   details.state = state;
	   details.district = district;
	   details.city = city;
	   details.locality = post;
	   details.save();
	   
	   
	   Logger.info("this is test comment from git");
	   
	   
	   Logger.info(" ====> "+state+" "+district+" "+city+" "+post);
	   return ok();
   }

   public Result saveLocality2(String state,String district,String city,String post) {
	   IndiaDetails details = new IndiaDetails();
	   details.state = state;
	   details.district = district;
	   details.city = city;
	   details.locality = post;
	   details.save();
	   
	
	   Logger.info(" ====> "+state+" "+district+" "+city+" "+post);
	   return ok();
   }
  
   public Result saveLocality4(String state,String district,String city,String post) {
	   IndiaDetails details = new IndiaDetails();
	   details.state = state;
	   details.district = district;
	   details.city = city;
	   details.locality = post;
	   details.save();
	   
	   
	   
	   
	   
	   Logger.info(" ====> "+state+" "+district+" "+city+" "+post);
	   return ok();
   }
   
}
