package Seleniumstart;

/**
 * Created by Prathamesh Zute on 09/09/2019.
 */

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;


public class Aertrip {

	public static void main(String[] args) {

		// Firefox Driver
		//WebDriver driver = new FirefoxDriver();

		// Chrome Driver
		System.setProperty("webdriver.chrome.driver", "C:\\jdk1.8.0_20\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		String URL = "https://aertrip.com/";		
		boolean flag = true;
		String checkinDate;
		String checkoutDate;

		try
		{
			driver.get(URL);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Fail to Load UR" + URL);
			driver.quit();
		}

		if(driver.getTitle().contains("Access Denied"))
		{
			flag = false;
			System.out.println("Flag : " + flag);
		}

		System.out.println("Title : " + driver.getTitle());

		if(flag)
		{
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Check Aertrip Logo visible or not
			driver.findElement(By.xpath("//div[@class='css-logo css-logo-white']")).isDisplayed();

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Check Hotel Menu visible or not
			driver.findElement(By.xpath("//li[@id='hotels-module']")).isDisplayed();

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//Click Hotel Menu
			driver.findElement(By.xpath("//div[@class='navBar']//ul//li[@id='hotels-module']")).click();

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//Check Hotel Menu selected or not
			driver.findElement(By.xpath("//div[@class='navBar']//ul//li[@class='menu-selected' and @id='hotels-module']")).isSelected();

			//Check City, Area and Hotel visible or not
			driver.findElement(By.xpath("//div[@class='hotel-single-location']")).isDisplayed();

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Click in input textbox of city, area and hotel

			driver.findElement(By.xpath("//div[@class='hotel-single-location']//input[@class='ui-autocomplete-input']")).clear();

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			driver.findElement(By.xpath("//div[@class='hotel-single-location']//input[@class='ui-autocomplete-input']")).click();

			//Search Mumbai in city, area and hotel filter
			driver.findElement(By.xpath("//div[@class='hotel-single-location']//input[@class='ui-autocomplete-input']")).sendKeys("Mumbai");

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//Check after search Mumbai results are visible or not
			//	driver.findElement(By.xpath("//ul[@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content ps ps--active-y']")).isEnabled();

			//Select aany 1 city, area and hotel
			driver.findElement(By.xpath("(//ul//li[@class='ui-menu-item'])[1]")).click();

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//Check hotel datepicker calender visible or not
			driver.findElement(By.xpath("//div[@class='hotel-calender']")).isDisplayed();

			//Click and open datepicker option to select date
			driver.findElement(By.xpath("//div[@class='calDiv']")).click();

			//Check after click on datepicker option calender container visible or not
			driver.findElement(By.xpath("//div[@class='datepickerContainer']")).isDisplayed();

			checkinDate = getCheckinDay();
			checkoutDate = getCheckoutDay();

			System.out.println("Checkin Day Number :"+checkinDate);
			System.out.println("Checkout Day Number :"+checkoutDate);

			WebElement dateFrom = driver.findElement(By.xpath("(//div[@class='datepickerContainer']//td//table)[2]//tbody[@class='datepickerDays']"));

			List<WebElement> columns= dateFrom.findElements(By.tagName("td"));

			for(WebElement cell: columns)
			{
				if(cell.getText().equals(checkinDate)){
					cell.click();
					break;
				}		
			}

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			/*	driver.findElement(By.xpath("//div[@class='formBar formBarBot']")).click();

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Click and open datepicker option to select return date
			driver.findElement(By.xpath("//div[@class='retuning-cal']")).click();

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/

			//Check after click on datepicker option calender container visible or not
			driver.findElement(By.xpath("//div[@class='datepickerContainer']")).isDisplayed();

			for(WebElement cell: columns)
			{
				try{
					if(cell.getText().equals(checkoutDate)){
						cell.click();
						break;
					}	}
				catch(Exception e)
				{
					driver.findElement(By.xpath("(//div[@class='datepickerContainer']//td//table)[2]//tbody[@class='datepickerDays']//td//span[contains(text(),'18')]")).click();
					//	e.printStackTrace();
					break;
				}
			}

			// wait for 4 seconds to check checkin and checkout date selected or not

			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//Check Rooms and guests option visible or not
			driver.findElement(By.xpath("//div[@class='formGuest']")).isDisplayed();

			//Click and open Rooms and guests option to select room
			driver.findElement(By.xpath("//div[@class='formGuest']//div[@id='guests']")).click();

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			// Check Adults and Childrens options visible or not
			driver.findElement(By.xpath("//div[@class='roomsGuestBox css-rooms-add dropdown-js rooms-dd-normal-js']")).isDisplayed();

			//Selecting Room 1 : 2 Adults, 1 Child

			if(driver.findElement(By.xpath("//div[@class='roomDiv' and contains(text(),'Room 1')]")).isEnabled())
			{

				// Check 2 Adults selected or not
				driver.findElement(By.xpath("(//div[@class='adultsDiv'])[1]//i[@class='icon icon_adult-selected']")).isSelected();

				// Select 1 Children
				for(int m = 1; m<=1; m++)
				{
					driver.findElement(By.xpath("((//div[@class='childrenDiv'])[1]//i)["+m+"]")).click();
				}

				// Check 1 Children selected or not
				driver.findElement(By.xpath("(//div[@class='childrenDiv'])[1]//i[@class='icon icon_children-selected']")).isSelected();	

			}

			// Adding Room 2
			driver.findElement(By.xpath("//div[@class='css-addRooms addRooms addRooms-js']")).click();


			//Selecting Room 2 : 3 Adults, 2 Child

			if(driver.findElement(By.xpath("//div[@class='roomDiv' and contains(text(),'Room 2')]")).isDisplayed())
			{

				// Select 3 Adults, By default 2 Adults already selected so need to add 1 more adult
				// Select 1 Adult
				driver.findElement(By.xpath("((//div[@class='adultsDiv'])[2]//i)[3]")).click();

				// Check 3 Adults selected or not
				driver.findElement(By.xpath("(//div[@class='adultsDiv'])[2]//i[@class='icon icon_adult-selected']")).isSelected();

				// Select 2 Children
				for(int n = 1; n<=2; n++)
				{
					driver.findElement(By.xpath("((//div[@class='childrenDiv'])[2]//i)["+n+"]")).click();
				}

				// Check 2 Children selected or not
				driver.findElement(By.xpath("(//div[@class='childrenDiv'])[2]//i[@class='icon icon_children-selected']")).isSelected();	

			}

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Check Search Hotel button visible or not
			driver.findElement(By.xpath("//div[@class='buttonDiv hotel-search-button-js']")).isDisplayed();

			//Click on Search Hotel button
			driver.findElement(By.xpath("//div[@class='buttonDiv hotel-search-button-js']//button")).click();

			// wait for 20 seconds after click on search hotel to get results

			try {
				Thread.sleep(25000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			/*JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("document.body.style.zoom='70%'");
			*/
			
			// Check Sort by option visible or not
			driver.findElement(By.xpath("//div[@class='css-hotel-sorting css-sort-wrapper']")).isDisplayed();

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Actions action = new Actions(driver);
			WebElement we = driver.findElement(By.xpath("//div[@class='css-hotel-sorting css-sort-wrapper']"));
			action.moveToElement(we).build().perform();

			//Click on Sort by option
			driver.findElement(By.xpath("//div[@class='css-hotel-sorting css-sort-wrapper']")).click();

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Check after click on sort by option dropdown list visible or not
			driver.findElement(By.xpath("(//div[@class='css-dropdown-list'])[1]")).isDisplayed();

			//Click on Distance nearest sort by option
			driver.findElement(By.xpath("(//div[@class='css-dropdown-list'])[1]//li[contains(text(),'Price - Low to High')]")).click();

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Check near by distance sort option visible or not
			driver.findElement(By.xpath("//span[@class='nearby-tab-js']")).isDisplayed();

			//Click on near by distance sort option
			driver.findElement(By.xpath("//span[@class='nearby-tab-js']")).click();

			// wait for 5 seconds aftr applying sort

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Check near by distance sort option visible or not
			driver.findElement(By.xpath("//div[@id='nearby-hotels-js']")).isDisplayed();

			//Click on 2nd lowest price hotel
			driver.findElement(By.xpath("(//div[@id='nearby-hotels-js']//div[@class='hotel-types-box hotel-list-box'])[2]")).click();

			// wait for 5 sec after click on hotel

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Check Hotel details visible or not
			driver.findElement(By.xpath("//div[@class='detailed-view-js css-hotel-detailed-container']")).isDisplayed();

			//Click to Book Hotel
			driver.findElement(By.xpath("//div[@class='css-hotel-booking-action']//button")).click();

			System.out.println("Hotel Book Successfully");

			driver.quit();
		}
	}

	public static String getCheckinDay()
	{
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

		int checkinDay = calendar.get(Calendar.DAY_OF_MONTH)+3;
		//System.out.println("Checkin Day :"+checkinDay+"\n");

		// Integer to String Conversion
		String checkinDayStr = Integer.toString(checkinDay);
		//System.out.println("Checkin Day String :"+checkinDayStr+"\n");

		return checkinDayStr;
	}

	public static String getCheckoutDay()
	{
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

		int checkoutDay = calendar.get(Calendar.DAY_OF_MONTH)+6;
		//System.out.println("Checkin Day :"+checkoutDay+"\n");

		// Integer to String Conversion
		String checkoutDayStr = Integer.toString(checkoutDay);
		//System.out.println("Checkin Day String :"+checkoutDayStr+"\n");

		return checkoutDayStr;
	}
}