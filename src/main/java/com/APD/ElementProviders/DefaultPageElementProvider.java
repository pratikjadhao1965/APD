package com.APD.ElementProviders;

import com.taf.core.PageElementProvider;
import com.taf.core.TestContext;
import com.taf.impl.selenium.SeleniumElementProvider;
import com.taf.impl.selenium.SeleniumTestContext;
import org.openqa.selenium.By;

/**
 * The Class DefaultPageElementProvider.
 */
public class DefaultPageElementProvider extends PageElementProvider {

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
    public DefaultPageElementProvider(final TestContext context) {
        super(context);
        seleniumTestContext = (SeleniumTestContext) context;
        seleniumElementProvider = new SeleniumElementProvider(seleniumTestContext);
    }

					
		public Object getSignin(){
		        seleniumTestContext.waitForElementToDisplayUsingBy(By.linkText("Sign in"));
		        return seleniumElementProvider.getElementByLinkText("Sign in");
	    	}
	    	
    	}