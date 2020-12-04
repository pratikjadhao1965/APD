package com.APD.TestCases;

import org.testng.annotations.Test;
import com.APD.PageObjects.*;
import com.APD.TestCases.BaseTest;
import org.testng.asserts.SoftAssert;
import org.testng.Assert;

public class APDTestCase extends BaseTest {
		
 	@Test(description="This is Login",groups={"APD","Login"})
    public void testLogin() {
     
          	    		    		    				    	    
    	 SoftAssert softassert=new SoftAssert();
	    
     	 DefaultPageClass defaultpageclass = testApplication.get().getInstance(DefaultPageClass.class);
   	 	 SigninPageClass signinpageclass = testApplication.get().getInstance(SigninPageClass.class);
   	     
        
    	defaultpageclass.clickSignin();
        EXTENTREPORTER.logInfo("Sign in");
        	
       
    	signinpageclass.SF("mayurishimpi7@gmail.com","P@ssword1117");
        EXTENTREPORTER.logInfo("Please enter description.");
        	
       
    	softassert.assertEquals(signinpageclass.verifyTextPinfoaccount(),"Welcome to your account. Here you can manage all of your personal information and orders.");
        EXTENTREPORTER.logInfo("verify");
        	
       
    	signinpageclass.clickSignout();
        EXTENTREPORTER.logInfo("Sign out");
        	
           softassert.assertAll();
        
  	} 
  	}