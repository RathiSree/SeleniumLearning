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
		
		driver.get("https://www.myntra.com");
		driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	    WebElement women= driver.findElementByXPath("//a[text()='Women'][1]");
	    WebElement jackets= driver.findElementByXPath("//a[text()='Jackets & Coats']");
	    Actions action = new Actions(driver);
	    action.moveToElement(women).perform();
	    Thread.sleep(2000);
	    action.click(jackets).perform();
	    
        String totalCount=driver.findElementByXPath("//div[@class='title-container']//span").getText();
        String splitTotalCount=totalCount.replaceAll("\\D", "");
        int totCount=Integer.parseInt(splitTotalCount);
        System.out.println("Total Items Count : "+totCount);
        
        String jackets1=driver.findElementByXPath("(//span[@class='categories-num'])[1]").getText();
        String splitJackets=jackets1.replaceAll("\\D", "");
        int jacketsCount=Integer.parseInt(splitJackets);
        System.out.println("Total Jackets Count : "+jacketsCount);
                
        String coats1=driver.findElementByXPath("(//span[@class='categories-num'])[2]").getText();
        String splitCoats=coats1.replaceAll("\\D", "");
        int coatsCount=Integer.parseInt(splitCoats);
        System.out.println("Total Coats Count : "+coatsCount);
        
         int categoriesCount=   jacketsCount +    coatsCount;
         
         if(categoriesCount == totCount) {
        	 
        	 System.out.println();
         }else {
        	 System.out.println();
         }
        
          WebDriverWait wait= new WebDriverWait(driver,10);
          driver.findElementByXPath("(//div[@class='common-checkboxIndicator'])[2]").click();
          driver.findElementByXPath("//div[@class='brand-more']").click();
          driver.findElementByClassName("FilterDirectory-searchInput").sendKeys("MANGO");
          driver.findElementByXPath("//ul[@class='FilterDirectory-list']/li[2]").click();
          driver.findElementByXPath("//span[@class='myntraweb-sprite FilterDirectory-close sprites-remove']").click();
          Thread.sleep(2000);
          List<WebElement> eachProductName = driver.findElementsByXPath("//h3[@class='product-brand']");
			for (WebElement each : eachProductName) {

				if (each.getText().equalsIgnoreCase("MANGO")) {
					System.out.println("All products are related to the Brand - MANGO");
				} else {
					System.out.println("TC's Fail. Product Brand mis match..!");
					break;

				}

			}
			
         action.moveToElement(driver.findElementByXPath("//div[@class='sort-sortBy']")).perform();
         action.moveToElement(driver.findElementByXPath("//label[text()='Better Discount']")).click().build().perform();
         driver.findElementByXPath("(//label[@class='sort-label '])[3]").click();
		 action.moveToElement(driver.findElementByXPath("//ul[@class='filter-summary-filterList']")).perform();
       
	}
}
