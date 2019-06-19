package com.java.main;

import java.security.MessageDigest;

import org.apache.log4j.Logger;

import com.java.config.Project2Config;
import com.java.controller.MD5;

public class Project2 {
	static Logger logger= Logger.getLogger(Project2.class);
	public static void main(String[] args) {
		logger.info("logger");
		//MessageDigest md= new MessageDigest();
		MD5 md5= new MD5();
		System.out.println(md5.encrypt("password"));
		}

}
