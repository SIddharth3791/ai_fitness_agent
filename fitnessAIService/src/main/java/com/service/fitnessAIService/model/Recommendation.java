package com.service.fitnessAIService.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.service.fitnessAIService.model.gemini.ContentRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "recommendations")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Recommendation {
	
	@Id
	private String id;
	
	private String activityId;
	private String userId;
	private String activityType;
	private String recommendation;
	private List<Imporvement>imporvements;
	private List<Suggestion> suggestions;
	private List<String> safety;
	
	@CreatedDate
	private LocalDateTime createdAt;

	
    @Data
    @Builder
    @Getter
    @Setter
    public static class Imporvement {
        private String area;
        private String recommendation;
    }
    
	
    @Data
    @Builder
    @Getter
    @Setter
    public static class Suggestion {
        private String workout;
        private String description;
    }
}
