package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Utility {

	public static RequestSpecification req;
	public  RequestSpecification requestSpecification() throws IOException
	{
		if(req==null)
		{
		PrintStream print = new PrintStream(new FileOutputStream("Logging.txt"));
		 
			
		 req = new RequestSpecBuilder().setBaseUri(getProperty("baseUrl")).addQueryParam("key", "qaclick123")
				 .addFilter(RequestLoggingFilter.logRequestTo(print))
				 .addFilter(ResponseLoggingFilter.logResponseTo(print))
			  .setContentType(ContentType.JSON).build();
		 return req;
		}
		 return req;
	}
	
	public static String getProperty(String key) throws IOException
	{
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream("D:\\Project\\com.CucumberAutomation\\src\\test\\java\\resources\\global.properties");
		prop.load(file);
		return prop.getProperty(key);
		
	}
}
