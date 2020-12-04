package com.APD.ElementProviders;

import com.taf.core.PageElementProvider;
import com.taf.core.TestContext;
import com.taf.impl.selenium.SeleniumElementProvider;
import com.taf.impl.selenium.SeleniumTestContext;
import org.openqa.selenium.By;

/**
 * The Class SigninPageElementProvider.
 */
public class SigninPageElementProvider extends PageElementProvider {

    /** The selenium test context. */
    protected SeleniumTestContext seleniumTestContext;

    /** The selenium element provider. */
    protected SeleniumElementProvider seleniumElementProvider;

    /**
     * Instantiates a new base element provider.
     *
     * @param context
     *            the context
     */
    public SigninPageElementProvider(final TestContext context) {
        super(context);
        seleniumTestContext = (SeleniumTestContext) context;
        seleniumElementProvider = new SeleniumElementProvider(seleniumTestContext);
    }

			
		public Object getEmail(){
		        seleniumTestContext.waitForElementToDisplayUsingBy(By.id("email"));
		        return seleniumElementProvider.getElementByID("email");
	    	}
	    	
    			
		public Object getPasswd(){
		        seleniumTestContext.waitForElementToDisplayUsingBy(By.id("passwd"));
		        return seleniumElementProvider.getElementByID("passwd");
	    	}
	    	
    			
		public Object getSubmitLogingtspan(){
		        seleniumTestContext.waitForElementToDisplayUsingBy(By.cssSelector("#SubmitLogin"));
		        return seleniumElementProvider.getElementByCSSSelector("#SubmitLogin");
	    	}
	    	
    			
		public Object getPinfoaccount(){
		        seleniumTestContext.waitForElementToDisplayUsingBy(By.cssSelector("p.info-account"));
		        return seleniumElementProvider.getElementByCSSSelector("p.info-account");
	    	}
	    	
    			
		public Object getSignout(){
		        seleniumTestContext.waitForElementToDisplayUsingBy(By.linkText("Sign out"));
		        return seleniumElementProvider.getElementByLinkText("Sign out");
	    	}
	    	
    	}