package com.taskgiven;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;





@RestController
public class webservice {
	@Autowired
	   RestTemplate restTemplate;

	   @RequestMapping(value = "/")
	   public String getProductList() throws Exception, JsonProcessingException {
	      HttpHeaders headers = new HttpHeaders();
	        //headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

	      headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
	//      headers.setContentType(MediaType.APPLICATION_JSON);
 headers.set("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzdGFnaW5nMTIzQHlvcG1haWwuY29tIiwiaWF0IjoxNjAwMTYxNTUyLCJpc3MiOiJBRCIsIkFVVEhPUklUSUVTX0tFWSI6IlJPTEVfQURNSU4iLCJleHAiOjE2ODY1NjE1NTJ9.nmfqcDfDbshqxBfnHD7jH_AGBORrsFDLBMQwsYJQCmK6gk0q66ztIbK0FVJ385Y3aLTreasK3zRGd3tTqYvlRg" );
	      HttpEntity <String> entity = new HttpEntity<String>("body",headers);
	      ResponseEntity<String> response= this.restTemplate.exchange("http://13.127.87.149:8080/foxmatrixadminapp/v1/api/finance/unpaidInterviewDetail/unpaidinterviewDetailReports", HttpMethod.GET, entity, String.class);
	      String result = response.getBody();
	      System.out.println("printing values:" + result);
	 
	      ObjectMapper mapper = new ObjectMapper();
	      
	      try {
	    	
	    	      String jsonStr = result;
	    	      

	    	        try {
	    	        	JSONObject output = new JSONObject(jsonStr);
	    	        	System.out.println(output.toString());
	    	            JSONArray docs = output.getJSONArray(jsonStr);

	    	            File file=new File("D:/fromJSON.xls");
	    	            String csv = CDL.toString(docs);
	    	            FileUtils.writeStringToFile(file, csv);
	    	        } catch (JSONException e) {
	    	            e.printStackTrace();
	    	        } catch (IOException e) {
	    	            // TODO Auto-generated catch block
	    	            e.printStackTrace();
	    	        }        
	    	    }

	    	      
	    	      catch (JSONException err){
	    	     
	    	}
	     

	            

	            
	   return result;
	  
	   }
}
