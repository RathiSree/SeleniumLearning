package selenium.workout;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Myntra {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver/chromedriver.exe");
		ChromeDriver driver= new ChromeDriver();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-popup-blocking");
		
				//1) Open https://www.myntra.com/
		driver.get("https://www.myntra.com");
		driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	    
	    		//2) Mouse over on WOMEN
	    		//3) Click Jackets & Coats
	    WebElement women= driver.findElementByXPath("//a[text()='Women'][1]");
	    WebElement jackets= driver.findElementByXPath("//a[text()='Jackets & Coats']");
	    Actions action = new Actions(driver);
	    action.moveToElement(women).perform();
	    Thread.sleep(2000);
	    action.click(jackets).perform();
	   
	    		//4) Find the total count of item (top)
        String totalCount=driver.findElementByXPath("//div[@class='title-container']//span").getText();
        String splitTotalCount=totalCount.replaceAll("\\D", "");
        int totCount=Integer.parseInt(splitTotalCount);
        System.out.println("Total Items Count : "+totCount);
        
        String jackets1=driver.findElementByXPath("(//span[@class='categories-num'])[1]").getText();
        String splitJackets=jackets1.replaceAll("\\D", "");
        int jacketsCount=Integer.parseInt(splitJackets);
        System.out.println("Total Jackets Count : "+jacketsCount);
         
        		//5) Validate the sum of categories count matches
        String coats1=driver.findElementByXPath("(//span[@class='categories-num'])[2]").getText();
        String splitCoats=coats1.replaceAll("\\D", "");
        int coatsCount=Integer.parseInt(splitCoats);
        System.out.println("Total Coats Count : "+coatsCount);
        int categoriesCount=   jacketsCount +    coatsCount;
        
        if(categoriesCount == totCount) {
        	System.out.println("The total count matches with categories count");
         }else {
        	System.out.println("The total count doesn't matches with categories count");
         }
          Thread.sleep(1000);
          
          		//6) Check Coats
          driver.findElementByXPath("(//div[@class='common-checkboxIndicator'])[2]").click();
          
          		//7) Click + More option under BRAND	
          driver.findElementByXPath("//div[@class='brand-more']").click();
          
          		//8)Type MANGO and click checkbox
          driver.findElementByClassName("FilterDirectory-searchInput").sendKeys("MANGO");
          driver.findElementByXPath("//ul[@class='FilterDirectory-list']/li[2]").click();
          
          		//9) Close the pop-up x
          driver.findElementByXPath("//span[@class='myntraweb-sprite FilterDirectory-close sprites-remove']").click();
          Thread.sleep(2000);
          
          		//10) Confirm all the Coats are of brand MANGO
          List<WebElement> eachProductName = driver.findElementsByXPath("//h3[@class='product-brand']");
		  for (WebElement each : eachProductName) {
			  
		  if (each.getText().equalsIgnoreCase("MANGO")) {
			System.out.println("All products are related to the Brand - MANGO");
		   } else {
			System.out.println("TC's Fail. Product Brand mis match..!");
		  break;

				}
				//11) Sort by Better Discount
		  action.moveToElement(driver.findElementByXPath("//div[@class='sort-sortBy']")).perform();
		  action.moveToElement(driver.findElementByXPath("//label[text()='Better Discount']")).click().build().perform();
				
		        //12) Find the price of first displayed item
		  List <WebElement> productPrice=driver.findElementsByXPath("//span[@class='product-discountedPrice']");
		  WebElement price=productPrice.get(0);
		  String p=price.getText();
		  System.out.println("The first product price is " +p);
				 
				//13) Mouse over on size of the first item
		  action.moveToElement(driver.findElementByXPath("(//div[@class='product-productMetaInfo'])[1]")).perform();
		  Thread.sleep(2000); 	
		        //14) Click on WishList Now
		  driver.findElementByXPath("(//span[@class='product-actionsButton product-wishlist '])[1]").click();
		  Thread.sleep(2000);       		
		        //15) Close Browser
		  driver.close();
		  } 
        	}
}

