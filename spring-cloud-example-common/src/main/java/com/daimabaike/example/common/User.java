package com.daimabaike.example.common;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9186856726922329372L;

	private String name;
	
	private int age;
	
	private String signature;
	
}
