package com.APD.PageObjects;

import com.taf.core.TestContext;
import com.taf.core.TestPage;
import com.taf.impl.selenium.SeleniumTestContext;
import com.APD.ElementProviders.DefaultPageElementProvider;

/**
 * The Class DefaultPageClass.
 */
public class DefaultPageClass extends TestPage {

    /** The selenium test context. */
    private final SeleniumTestContext seleniumTestContext;

    /** The sign up page element provider. */
    private DefaultPageElementProvider defaultPageElementProvider;

    /**
     * Instantiates a new sign up page.
     *
     * @param context
     *            the context
     */
    public DefaultPageClass(final TestContext context){
        super(context);
        seleniumTestContext = (SeleniumTestContext) context;
    }
    
    /*
     * (non-Javadoc)
     *
     * @see com.taf.core.TestPage#intializeElementProvider()
     */
    @Override
    public void intializeElementProvider(){
        defaultPageElementProvider = new DefaultPageElementProvider(seleniumTestContext);
    }
    
    
          
    public void clickSignin(){
		    seleniumTestContext.clickButton(defaultPageElementProvider.getSignin());
		}
		
        
      }
    