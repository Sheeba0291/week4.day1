package week4.day1;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LeafGround_Window {

	public static void main(String[] args) {
		// Setup WebDriverManager
		WebDriverManager.chromedriver().setup();

		// Create the object for ChromeDriver
		ChromeDriver driver = new ChromeDriver();

		// Launch the LeafGround_Window HTML
		driver.get("http://www.leafground.com/pages/Window.html");

		// Maximize the window
		driver.manage().window().maximize();

		// Click button to open home page in New Window
		driver.findElement(By.id("home")).click();

		// Find the number of opened windows
		driver.findElement(By.xpath("//button[text()='Open Multiple Windows']")).click();

		// Get the set of window handles and convert to list
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> list = new ArrayList<String>(windowHandles);

		// Print the count of windows opened.
		System.out.println("Number of Opened windows : "+windowHandles.size());
		
		// Store the parentWindow 
		String parentWindow = list.get(0);
		
		// Click on Do Not close me button.
		driver.findElement(By.xpath("//button[text()='Do not close me ']")).click();
		
		windowHandles = driver.getWindowHandles();
		list = new ArrayList<String>(windowHandles);
		
		// Close all windows except parentWindow
		for (int i =1; i<list.size(); i++) 
		{
			driver.switchTo().window(list.get(i));
			System.out.println("Title of the Page : "+driver.getTitle());
			driver.close();
			System.out.println("Closed");
		}
		
		// switch to Parent Window.
		driver.switchTo().window(parentWindow);
		
		// Click on the webElement "Wait for 5 seconds" button.
		driver.findElement(By.xpath("//button[text()='Wait for 5 seconds']")).click();
		
		// Wait until 2 new windows are opened.
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.numberOfWindowsToBe(3));
		
		System.out.println("2 New Windows are Opened");
		
	}

}
