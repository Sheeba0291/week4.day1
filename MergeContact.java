package week4.day1;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContact {

	/*
	 * //Pseudo Code

	 * 1. Launch URL "http://leaftaps.com/opentaps/control/login"
	 * 2. Enter UserName and Password Using Id Locator
	 * 3. Click on Login Button using Class Locator
	 * 4. Click on CRM/SFA Link
	 * 5. Click on contacts Button	
	 * 6. Click on Merge Contacts using Xpath Locator
	 * 7. Click on Widget of From Contact
	 * 8. Click on First Resulting Contact
	 * 9. Click on Widget of To Contact
	 * 10. Click on Second Resulting Contact
	 * 11. Click on Merge button using Xpath Locator
	 * 12. Accept the Alert
	 * 13. Verify the title of the page
	 */

	public static void main(String[] args) {

		// Setup WebDriverManager
		WebDriverManager.chromedriver().setup();

		// Create the chromedriver object named driver
		ChromeDriver driver = new ChromeDriver();

		// Launch the Leaftaps URL
		driver.get("http://leaftaps.com/opentaps/control/main");

		// Maximize the window
		driver.manage().window().maximize();

		// Enter UserName Using Id Locator
		driver.findElement(By.id("username")).sendKeys("demosalesmanager");

		// Enter Password Using Id Locator
		driver.findElement(By.id("password")).sendKeys("crmsfa");

		// Click on Login Button using Class Locator
		driver.findElement(By.className("decorativeSubmit")).click();

		// Click on CRM/SFA Link
		driver.findElement(By.linkText("CRM/SFA")).click();

		// Click on contacts Button
		driver.findElement(By.linkText("Contacts")).click();

		// Click on Merge Contacts using Xpath Locator
		driver.findElement(By.xpath("//a[text()='Merge Contacts']")).click();

		// Click on Widget of From Contact
		driver.findElement(By.xpath("//table[@id='widget_ComboBox_partyIdFrom']/following::a/img")).click();

		// Get the list of windows
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> listOfWindows = new ArrayList<String>(windowHandles);

		// Switch to the new window 
		driver.switchTo().window(listOfWindows.get(1));

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		// Click on First Resulting Contact
		driver.findElement(By.xpath("(//table[@class='x-grid3-row-table']//a)[1]")).click();

		// Switch to parent window
		driver.switchTo().window(listOfWindows.get(0));

		// Click on Widget of To Contact
		driver.findElement(By.xpath("(//table[@id='widget_ComboBox_partyIdFrom']/following::a/img)[2]")).click();

		// Get the list of windows
		windowHandles = driver.getWindowHandles();
		listOfWindows = new ArrayList<String>(windowHandles);

		// Switch to the new window 
		driver.switchTo().window(listOfWindows.get(1));

		// Click on Second Resulting Contact
		driver.findElement(By.xpath("(//td[@class='x-grid3-col x-grid3-cell x-grid3-td-partyId x-grid3-cell-first '])[2]//a")).click();

		// Switch to parent window
		driver.switchTo().window(listOfWindows.get(0));

		// Click on Merge button using Xpath Locator
		driver.findElement(By.xpath("//a[text()='Merge']")).click();

		// Switch to the Alert
		Alert alert = driver.switchTo().alert();

		// Accept the Alert
		alert.accept();

		// Verify the title of the page
		if(driver.getTitle().equals("View Contact | opentaps CRM"))
		{
			System.out.println("Page Title is Verified as : "+driver.getTitle());
		}
		else
		{
			System.out.println("Merge Contact is Unsuccessful");
		}

	}

}
