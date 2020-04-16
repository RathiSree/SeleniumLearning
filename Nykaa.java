package selenium.workout;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class Nykaa {
public static void main(String[] args) throws InterruptedException {
System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver/chromedriver.exe");
ChromeOptions ops = new ChromeOptions ();
ops.addArguments("--disable-Notifications");
ChromeDriver driver=new ChromeDriver(ops);

//Go to https://www.nykaa.com/
driver.get("https://www.nykaa.com");
driver.manage().window().maximize();
driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);

//Mouseover on Brands and Mouseover on Popular
WebElement brands= driver.findElementByXPath("//a[text()='brands']");
Actions action = new Actions(driver);
action.moveToElement(brands).perform();
WebElement popular= driver.findElementByXPath("//a[text()='Popular']");
Actions action1 = new Actions(driver);
action1.moveToElement(popular).perform();
Thread.sleep(2000);

//Click L'Oreal Paris
driver.findElementByXPath("//img[@src='https://adn-static2.nykaa.com/media/wysiwyg/2019/lorealparis.png']").click();;
Thread.sleep(2000);

//Go to the newly opened window and check the title contains L'Oreal Paris
Set<String> allWindows = driver.getWindowHandles();
List<String> ls = new ArrayList<String>(allWindows);
String secwindow=ls.get(1);
driver.switchTo().window(secwindow);
String title = driver.getTitle();
System.out.println(" The title of the Page is "+title);
if(title.contains("L'Oreal Paris")) {
System.out.println("The page title contains L'Oreal Paris");
}else {
System.out.println("The page doesn't title contains L'Oreal Paris");
}

//Click sort By and select customer top rated
driver.findElementByXPath("//i[@class='fa fa-angle-down']").click();
driver.findElementByXPath("//input[@id='3']/following-sibling::div").click();
Thread.sleep(1000);

//Click Category and click Shampoo
driver.findElementByXPath("(//div[@class='filter-sidebar__filter-title pull-left'])[1]").click();
driver.findElementByXPath("//label[@for='chk_Shampoo_undefined']/div").click();

//check whether the Filter is applied with Shampoo
String filter= driver.findElementByXPath("//ul[@class='pull-left applied-filter-lists']").getText();
if(filter.contains("Shampoo") ) {
System.out.println("Shampoo filter is applied");
}
else {
System.out.println("Shampoo filter is not applied");
}

//clik on L'Oreal Paris Colour Protect Shampoo
driver.findElementByXPath("(//div[@class='m-content__product-list__title']//span)[4]").click();

//GO to the new window and select size as 175ml
Set<String> allWindows1 = driver.getWindowHandles();
List<String> ls1 = new ArrayList<String>(allWindows1);
String secwindow1=ls1.get(2);
driver.switchTo().window(secwindow1);
driver.findElementByXPath("//span[text()='175ml']").click();

//Print the MRP of the product
String price=driver.findElementByXPath("//span[@class='post-card__content-price-offer']").getText();
String splitPrice=price.replaceAll("\\D", "");
int price1=Integer.parseInt(splitPrice);
System.out.println("The price of the product is " +price1);

//Click on ADD to BAG
driver.findElementByXPath("(//button[text()='ADD TO BAG'])[1]").click();

//Go to Shopping Bag
driver.findElementByXPath("//div[@class='AddBagIcon']").click();

//Print the Grand Total amount
String grandTotal=driver.findElementByXPath("(//div[@class='value'])[4]").getText();
String splitTotal=grandTotal.replaceAll("\\D", "");
int price2=Integer.parseInt(splitTotal);
System.out.println("The Grand Total amount is "+price2);

//Click Proceed
driver.findElementByXPath("//span[text()='Proceed']").click();

//Click on Continue as Guest
driver.findElementByXPath("//button[text()='CONTINUE AS GUEST']").click();

//Print the warning message (delay in shipment)
String message=driver.findElementByXPath("//div[@class='message']").getText();
System.out.println("Warning message is : "+message);
//Close all windows
driver.quit();
}
}

