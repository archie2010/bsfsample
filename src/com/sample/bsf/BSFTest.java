package com.sample.bsf;

import org.apache.bsf.BSFEngine;
import org.apache.bsf.BSFException;
import org.apache.bsf.BSFManager;

/**
 * BSF£¨Bean Scripting Framework£© 
 * 1¡¢get bean content via javascript
 * 2¡¢execute specified method of the script
 * 
 * @author archie
 * 
 */
public class BSFTest {

	public static void main(String[] args) {
		// Script
		String script = "function dealBean()"
				+ "{"
				+ " user = bsf.lookupBean('u');"
				+ "return \"ID:\"+user.id+\"UserName:\"+user.uname+\"Adress:\"+user.address+\"======_Call Method:\"+user.toString()+"
				+ "'\t'" + "}";

		try {
			// BSF Manager
			BSFManager bsfManager = new BSFManager();

			// BSF Engine
			BSFEngine bsfEngine = bsfManager.loadScriptingEngine("javascript");

			/**
			 * Bean
			 */
			User u = new User();
			u.setId(1);
			u.setUname("archie");
			u.setAddress("California");

			// execute the script
			bsfEngine.eval("javascript", 0, 0, script);

			// register a bean named 'u' via BSF Manager
			bsfManager.registerBean("u", u);
			// bsfManager.registerBean("u", "archie");

			// execute the specified method of the script and return the result
			Object result = bsfEngine.eval("javascript", 0, 0, "dealBean();");

			System.out.println(result.toString());

		} catch (BSFException e) {
			e.printStackTrace();
		}
	}

}
