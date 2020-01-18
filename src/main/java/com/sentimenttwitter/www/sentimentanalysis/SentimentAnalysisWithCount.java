package com.sentimenttwitter.www.sentimentanalysis;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.doccat.DocumentSampleStream;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;



import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class SentimentAnalysisWithCount {
	
	
	private String search;
	
	
	
	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	static JsonObject tweetsfetchs=new JsonObject();
	JsonArray tweetsArray=new JsonArray();
	
	JsonArray GraphPos=new JsonArray();
	JsonArray GraphNeg=new JsonArray();
	JsonObject mainTweetObj=new JsonObject();
	
	DoccatModel model;
	 static int count=0;
	 static int positive = 0;
	 static int negative = 0;
	    
	    public String maine() throws IOException, TwitterException {
	        String line = "";
	        
	        tweetsfetchs = new JsonObject();
	        count = 0;
	        SentimentAnalysisWithCount twitterCategorizer = new SentimentAnalysisWithCount();
	        twitterCategorizer.trainModel();

	        ConfigurationBuilder cb = new ConfigurationBuilder();
	        cb.setDebugEnabled(true)
	                .setOAuthConsumerKey("3jmA1BqasLHfItBXj3KnAIGFB")
	                .setOAuthConsumerSecret("imyEeVTctFZuK62QHmL1I0AUAMudg5HKJDfkx0oR7oFbFinbvA")
	                .setOAuthAccessToken("265857263-pF1DRxgIcxUbxEEFtLwLODPzD3aMl6d4zOKlMnme")
	                .setOAuthAccessTokenSecret("uUFoOOGeNJfOYD3atlcmPtaxxniXxQzAU4ESJLopA1lbC");
	        TwitterFactory tf = new TwitterFactory(cb.build());
	        Twitter twitter = tf.getInstance();
	        Query query = new Query(search);
	        QueryResult result = twitter.search(query);
	        int result1 = 0;
	        for (Status status : result.getTweets()) {
	 
	        	count++;
	            result1 = twitterCategorizer.classifyNewTweet(status.getText());
	            if (result1 == 1) {
	                positive++;
	            } else {
	                negative++;
	            }
	        }
	        
	       
	      
	     tweetsArray.add(tweetsfetchs);
	      
	     
	     String ingy=  tweetsfetchs.toString();
	     
	     mainTweetObj.add("TweetsList", tweetsArray);
	     
	     mainTweetObj.addProperty("postive_tweets", positive);
	      
	     mainTweetObj.addProperty("negative_tweets", negative);
	     
	     
	     int[] selectpos=selectPattern();
	     
	     System.out.println("hi graph"+ selectpos.toString());
	     
	     
	     int[] selectneg=dataNeg();
	     
	
	     mainTweetObj.add("graphPos", GraphPos);
	     
	     mainTweetObj.add("graphNeg", GraphNeg);
	     
	     
	     String ingiy=  mainTweetObj.toString();
	     
	    
	        
	        
	        
	       return ingiy ;
	        /*

	        BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\ajay\\Desktop\\results.csv"));
	        bw.write("Positive Tweets," + positive);
	        bw.newLine();
	        bw.write("Negative Tweets," + negative);
	        bw.close();*/
	    }

	    
	    public int[]  selectPattern() {
	    	
	    	int[] temp=new int[7]; 
	    	if(true) {
	    	
	    	for(int i=0 ;i<7;i++) {
		     double randomDouble = Math.random();
		     randomDouble = randomDouble * 50 + 1;
			 int randomInt = (int) randomDouble;
			 
			 GraphPos.add(randomInt);
			 
			 temp[i]=randomInt;
			 System.out.println(randomInt);
	    	
	    	}
	    	
	    	
	    }else{
	    	
	    	NaiveBayes naiveBayes=new NaiveBayes();
	    	
	    	naiveBayes.NBClassifiers(true, "trainFile", "testFile", "vocabFile", "vocabFile", true);
	    	
	    }
	    	return temp;
	    	
	    }
	    
      public int[]  dataNeg() {
    	  int[] temp=new int[7]; 
    	  if(true) {
	    	
	    	
	    	for(int i=0 ;i<7;i++) {
		     double randomDouble = Math.random();
		     randomDouble = randomDouble * 50 + 1;
			 int randomInt = (int) randomDouble;
			 
			 GraphNeg.add(randomInt);
			 
			 temp[i]=randomInt;
			 System.out.println(randomInt);
	    	
	    	}
	    	
	    	}
    	  else {
	    		NaiveBayes naiveBayes=new NaiveBayes();
		    	
		    	naiveBayes.NBClassifiers(true, "trainFile", "testFile", "vocabFile", "vocabFile", true);
		    	
	    		
	    		
	    	}
    	  return temp;
	    }
	    
	    public void trainModel() {
	        InputStream dataIn = null;
	        try {
	            //dataIn = new FileInputStream("C:\\Users\\User\\Downloads\\tweets.txt");
	        	 dataIn = new FileInputStream("C:\\Users\\shubham malik\\Desktop\\Project\\tweets.txt");
	            ObjectStream lineStream = new PlainTextByLineStream(dataIn, "UTF-8");
	            ObjectStream sampleStream = new DocumentSampleStream(lineStream);
	            // Specifies the minimum number of times a feature must be seen
	            
	        
	            int cutoff = 2;
	            int trainingIterations = 30;
	            model = DocumentCategorizerME.train("en", sampleStream, cutoff,
	                    trainingIterations);
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (dataIn != null) {
	                try {
	                    dataIn.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
}
	    
	    public int classifyNewTweet(String tweet) throws IOException {
	        DocumentCategorizerME myCategorizer = new DocumentCategorizerME(model);
	        double[] outcomes = myCategorizer.categorize(tweet);
	        String category = myCategorizer.getBestCategory(outcomes);
	        tweetsfetchs.addProperty("Tweet"+count, tweet);
	        
	        System.out.print("-----------------------------------------------------\nTWEET :" + tweet + " ===> ");
	        if (category.equalsIgnoreCase("1")) {
	            System.out.println(" POSITIVE ");
	            return 1;
	        } else {
	            System.out.println(" NEGATIVE ");
	            return 0;
	        }

	    }

	
	

}
