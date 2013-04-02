package com.sample.bsf;

import java.util.ArrayList;
import java.util.List;

import org.apache.bsf.BSFEngine;
import org.apache.bsf.BSFException;
import org.apache.bsf.BSFManager;

/**
 * BSF£¨Bean Scripting Framework£©test£¨array and list£©
 * 
 * @author archie
 * 
 */
public class BSFTestArray {

	public static void main(String[] args) {

		// Script
		// String
		// script="function print(){str=bsf.lookupBean('user');return 'BSF array¡¾1¡¿:'+str[1].address;}";
		String script = "function print(){str=bsf.lookupBean('userList');return 'BSF array¡¾1¡¿:'+str.get(1).address;}";
		try {
			// BSF Manager
			BSFManager bsfManager = new BSFManager();

			// BSF Engine
			BSFEngine bsfEngine = bsfManager.loadScriptingEngine("javascript");

			/**
			 * Bean
			 */
			User user1 = new User();
			user1.setId(1);
			user1.setUname("archie");
			user1.setAddress("ShangHai");

			User user2 = new User();
			user2.setId(2);
			user2.setUname("archie2010");
			user2.setAddress("Peking");

			// Object array
			User[] user = new User[] { user1, user2 };

			List<User> userList = new ArrayList<User>();
			userList.add(user1);
			userList.add(user2);

			// execute the script
			bsfEngine.eval("javascript", 0, 0, script);

			// register a bean(array) named 'user' via BSF Manager
			bsfManager.registerBean("user", user);
			// register a list named 'userList' via BSF Manager
			bsfManager.registerBean("userList", userList);

			// execute the specified method of the script and return the result
			Object result = bsfEngine.eval("javascript", 0, 0, "print();");

			System.out.println("Result is:\n" + result.toString());
		} catch (BSFException e) {
			e.printStackTrace();
		}
	}

}
