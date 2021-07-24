package com.authorization.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JwtResponse {

	private String userid;

	private String username;

	private boolean isValid;
	
}
