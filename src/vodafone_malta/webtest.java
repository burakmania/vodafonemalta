package vodafone_malta;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
 
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.exec.util.StringUtils;
import org.junit.Assert;

public class webtest {

	public static void main(String[] args) {

		// Create an instance of the driver
		WebDriver driver = new ChromeDriver();

		// Navigate to a web page
		driver.get("https://www.vodafone.com.mt/home");
		
		 // Perform login actions
		WebElement usernameElement = driver.findElement(By.id("ctl00_ctl00_cphParent_ResponsiveQuickLinks_ResponsiveLogin_txtUsername_txtInput"));
		WebElement passwordElement = driver.findElement(By.id("ctl00_ctl00_cphParent_ResponsiveQuickLinks_ResponsiveLogin_txtPassword_txtInput"));
		WebElement formElement     = driver.findElement(By.id("ctl00_ctl00_cphParent_ResponsiveQuickLinks_ResponsiveLogin_btnLogin"));

		
	    usernameElement.sendKeys("testqa2");
	    passwordElement.sendKeys("Voda1234");
	    formElement.click();
	    
	    //check redirected page
	    String actualTitle=driver.getCurrentUrl();
        try {
           Assert.assertEquals (actualTitle, "https://www.vodafone.com.mt/myVodafone-postpaid");
        } catch (AssertionError e) {
            System.out.println("Test execution error for case 1:Page you are redirected is wrong");
            throw e;
        }
        System.out.println("Test case 1 :Page you are redirected is correct");
		
        //check "My Pending Balance is :" 
        WebElement e1=driver.findElement(By.xpath("//div[@class='prepaidLandingInflate']/p"));
        
        if (e1.getText().contains("My Pending Balance is :")) {

            System.out.println("Test case 2 :Redirected page is correct");
		}else
		{
	        System.out.println("Test execution error for case 2:Page does not contain 'My Pending Balance is :'");
		}
       
        //check the balance is numeric
        String balance = StringUtils.split(e1.getText(), "€")[1];
        if(isNumeric(balance))
        System.out.println("Test case 3 :Balance value is numeric");
        else
            System.out.println("Test execution error for case 3 :Balance value is not numeric");

        driver.close();
		}
	
	public static boolean isNumeric(String strNum) {
	    try {
	        double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException | NullPointerException nfe) 
	    {
	
	        return false;
	    }
	    return true;
	}

}
