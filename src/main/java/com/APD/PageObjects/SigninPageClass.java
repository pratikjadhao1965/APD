package com.APD.PageObjects;

import com.taf.core.TestContext;
import com.taf.core.TestPage;
import com.taf.impl.selenium.SeleniumTestContext;
import com.APD.ElementProviders.SigninPageElementProvider;

/**
 * The Class SigninPageClass.
 */
public class SigninPageClass extends TestPage {

    /** The selenium test context. */
    private final SeleniumTestContext seleniumTestContext;

    /** The sign up page element provider. */
    private SigninPageElementProvider signinPageElementProvider;

    /**
     * Instantiates a new sign up page.
     *
     * @param context
     *            the context
     */
    public SigninPageClass(final TestContext context){
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
        signinPageElementProvider = new SigninPageElementProvider(seleniumTestContext);
    }
    
    
          
    public void clickEmail(){
		    seleniumTestContext.clickButton(signinPageElementProvider.getEmail());
		}
		
         
    public void enterEmail(final String text){
		    seleniumTestContext.enterTextIn(signinPageElementProvider.getEmail(),text);
		}
		
         
    public void enterPasswd(final String text){
		    seleniumTestContext.enterTextIn(signinPageElementProvider.getPasswd(),text);
		}
		
         
    public void clickSubmitLogingtspan(){
		    seleniumTestContext.clickButton(signinPageElementProvider.getSubmitLogingtspan());
		}
		
         
    public String verifyTextPinfoaccount(){
		    return seleniumTestContext.getText(signinPageElementProvider.getPinfoaccount());
		}
		
         
    public void clickSignout(){
		    seleniumTestContext.clickButton(signinPageElementProvider.getSignout());
		}
		
        
        
    public void SF(String email,String passwd){
		    		    clickEmail();
		    		    enterEmail(email);
		    		    enterPasswd(passwd);
		    		    clickSubmitLogingtspan();
		    		}
		
      }
    