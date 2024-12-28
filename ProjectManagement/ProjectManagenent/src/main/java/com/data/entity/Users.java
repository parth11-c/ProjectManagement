package com.data.entity;

 import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

 import lombok.Data;

@Data
@Document(collection = "projectmanagement")
public class Users {

	@Id
 	private String id;
	private String name;
	private String email;
	private String Username;
	private String password;

	
	
}
