package com.qa.keyWordPro.Test;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import com.qa.keyword.engine.keyworkEngine;


public class loginTest {
	public  keyworkEngine kwe ;
	
	@Test()
	public void LoginTest() throws EncryptedDocumentException, IOException {
		kwe = new keyworkEngine();
		
		kwe.startExecution("login");
		
		
		
	}
	

}
