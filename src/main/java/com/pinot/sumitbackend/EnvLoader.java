/**
 * 
 */
package com.pinot.sumitbackend;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * Environment variables loading class
 */
public class EnvLoader {
	
	/**
	 * Check if /app directory exists (production environment)
	 * otherwise ignore error
	 */
	public static void loadEnv() {
//		Dotenv dotenv = Dotenv.configure().directory("/app").ignoreIfMissing().load();
		Dotenv dotenv = Dotenv.load();
		dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
	}

}
