package com.service.fitnessAIService;

public class PromptConstants {
	
	public final static String RECOMMENDATION_PROMPT = """ 
			analyze this fitness activity and provided detail recommedation to imporve upon the activity. following is the Recommendation formate needed -
			{
				"analysis": "Overall analysis here" + "Calories burned analysis here" + "Heart rate analysis here",
				"imporvements":[
						{
						
							"area": "Area Name",
							"recommendation" : "Detailed recommendation",
						}
						
					],
				"suggestions":[
						{
							"workout": "Workout Name",
							"description": "Detailed Workout description"
						}
					],
				"safety" : [
					"safety point 1"
					"safety point 2"
				]
			}
			
			Analyze below Activiy:
			Activity Type: %s
			Duration: %d minutes
			Calories Burned: %d
			Additional Metrics: %s
			
			Provide detailed Analysis focused on performance, imporvement, next workout suggestion, and safety guidelines.
			Ensure the response follows the EXACT JSON format show above.
			""";

}
