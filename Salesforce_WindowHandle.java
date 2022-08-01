package week4.day1;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Salesforce_WindowHandle {

	/*
	 *  1.Launch the browser
	 *  2.Load the url as " https://login.salesforce.com/ "
	 *  3.Enter the username as " ramkumar.ramaiah@testleaf.com "
	 *  4. Enter the password as " Password$123 "
	 *  5.click on the login button
	 *  6.click on the learn more option in the Mobile publisher
	 *  7.Switch to the next window using Windowhandles.
	 *  8.click on the confirm button in the redirecting page
	 *  9.Get the title
	 *  10.Get back to the parent window
	 *  11.close the browser
	 */
	public static void main(String[] args)
	{
		// Setup WebDriverManager
		WebDriverManager.chromedriver().setup();

		// Create the chromedriver object named driver
		ChromeDriver driver = new ChromeDriver();

		// Launch the SalesForce URL
		driver.get("https://login.salesforce.com/");

		// Maximize the window
		driver.manage().window().maximize();

		// Enter the username as " ramkumar.ramaiah@testleaf.com "
		driver.findElement(By.id("username")).sendKeys("ramkumar.ramaiah@testleaf.com");

		// Enter the password as " Password$123 "
		driver.findElement(By.id("password")).sendKeys("Password$123");

		// click on the login button
		driver.findElement(By.id("Login")).click();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		// click on the learn more option in the Mobile publisher
		driver.findElement(By.xpath("(//span[@class=' label bBody'])[2]")).click();
		
		// Switch to the next window using Windowhandles.
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> listOfWindowHandles = new ArrayList<String>(windowHandles);
		
		driver.switchTo().window(listOfWindowHandles.get(1));
		
		// click on the confirm button in the redirecting page
		driver.findElement(By.xpath("//button[text()='Confirm']")).click();
		
		// Get the title
		System.out.println("Title of the Page :   "+driver.getTitle());
		
		// Get back to the parent window
		driver.switchTo().window(listOfWindowHandles.get(0));
		
		// close the browser (closing the active browser alone)
		//driver.close();
		
		// close the browser (closing all the browsers)
		driver.quit();

		// Print a message to the user in the console.
		System.out.println("***** ALL THE STEPS WERE EXECUTED SUCCESSFULLY *****");

	}

}
