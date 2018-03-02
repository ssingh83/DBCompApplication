package pageobjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Page {
	WebDriver driver;
	
	String addComputer_id = "add";
	String compName_id="name";
	String introducedDate_name = "introduced";
	String discontinuedDate_name = "discontinued";
	String companyName_id = "company";
	String createComputer_xpath = "//input[@value='Create this computer']";
	String cancelBtn_xpath = "//a[text()='Cancel']";
	String updateComputer_xpath = "//input[@value='Save this computer']";

	
	// URL connection
	
	public Page() {

		driver = new ChromeDriver();
		driver.get("http://computer-database.herokuapp.com/computers");
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	// Add computer feature
	
	public void addComputer(String computerName, String introduced, String discontinued, String companyName) {
		driver.findElement(By.id(addComputer_id)).click();
		driver.findElement(By.xpath("//input[@id='name']")).sendKeys(computerName);
		if(null != introduced && !"".equals(introduced)) {
			driver.findElement(By.name(introducedDate_name)).sendKeys(introduced);
		}
		
		if(null != discontinued && !"".equals(discontinued)) {
			driver.findElement(By.name(discontinuedDate_name)).sendKeys(discontinued);
		}
		
		if(null != companyName && !"".equals(companyName)) {
			WebElement companyDdown = driver.findElement(By.id(companyName_id));
			Select sel = new Select(companyDdown);
			sel.selectByVisibleText(companyName);
			
		}
		driver.findElement(By.xpath(createComputer_xpath)).click();
		
		//WebElement addText = driver.findElement(By.xpath("//*[@id=\"main\"]/div[1]"));
		//System.out.println(addText.getText());
		
		//Verification for mandatory field
		if(verifyElement(By.xpath("//*[@id='name']/ancestor::div[@class='clearfix error']"))) {
			System.out.println("You are entering empty value for computer name\n");
			driver.findElement(By.xpath(cancelBtn_xpath)).click();
		}
		
		//Verify the wrong date format
		if(verifyElement(By.xpath("//*[@id='introduced']/ancestor::div[@class='clearfix error']"))) {
			System.out.println("You are entering wrong format of introduced date\n");
			driver.findElement(By.xpath(cancelBtn_xpath)).click();
		}
		
		//Verify the wrong date format
		if(verifyElement(By.xpath("//*[@id='discontinued']/ancestor::div[@class='clearfix error']"))) {
			System.out.println("You are entering wrong format of disintroduced date\n");
			driver.findElement(By.xpath(cancelBtn_xpath)).click();
		}
		
		
	}
	
	public void cancelAddComp(String computerName) {
		driver.findElement(By.id(addComputer_id)).click();
		driver.findElement(By.xpath("//input[@id='name']")).sendKeys(computerName);
		driver.findElement(By.xpath(cancelBtn_xpath)).click();
		System.out.println("Page is navigated on list page after cancel");
	}
	
	//Edit computer feature
	
	public void editComputer(String computerName,String introduced, String discontinued,String companyName) {
		driver.findElement(By.xpath("//*[@id=\"searchbox\"]")).sendKeys(computerName);			
		driver.findElement(By.xpath("//*[@id=\'searchsubmit\']")).click();	
		driver.findElement(By.xpath("//*[@id=\'main\']/table/tbody/tr[1]/td[1]/a")).click();
		driver.findElement(By.xpath("//input[@id='name']")).clear();
		driver.findElement(By.xpath("//input[@id='name']")).sendKeys(computerName);
		
		if(null != introduced && !"".equals(introduced)) {
			driver.findElement(By.name(introducedDate_name)).clear();
			driver.findElement(By.name(introducedDate_name)).sendKeys(introduced);
		}
		
		if(null != discontinued && !"".equals(discontinued)) {
			driver.findElement(By.name(discontinuedDate_name)).clear();
			driver.findElement(By.name(discontinuedDate_name)).sendKeys(discontinued);
		}
		
		if(null != companyName && !"".equals(companyName)) {
			WebElement companyDdown = driver.findElement(By.id(companyName_id));
			Select sel = new Select(companyDdown);
			sel.selectByVisibleText(companyName);
			}

		driver.findElement(By.xpath(updateComputer_xpath)).click();		
		//WebElement editText = driver.findElement(By.xpath("//*[@id=\"main\"]/div[1]"));
		//System.out.println(editText.getText());
		
		//Verification for mandatory field
		if(verifyElement(By.xpath("//*[@id='name']/ancestor::div[@class='clearfix error']"))) {
			System.out.println("You are entering empty value for computer name");
			driver.findElement(By.xpath(cancelBtn_xpath)).click();
			
		}
		
		//Verify the wrong date format
		if(verifyElement(By.xpath("//*[@id='introduced']/ancestor::div[@class='clearfix error']"))) {
			System.out.println("You are entering wrong format of introduced date ");
			driver.findElement(By.xpath(cancelBtn_xpath)).click();
		}
		
		//Verify the wrong date format
		if(verifyElement(By.xpath("//*[@id='discontinued']/ancestor::div[@class='clearfix error']"))) {
			System.out.println("You are entering wrong format of disintroduced date");
			driver.findElement(By.xpath(cancelBtn_xpath)).click();
		}
		
			
	}
	
	public void cancelEditComp(String computerName) {
		driver.findElement(By.xpath(".//*[@id='searchbox']")).sendKeys(computerName);			
		driver.findElement(By.xpath("//*[@id=\'searchsubmit\']")).click();	
		driver.findElement(By.xpath("//*[@id=\'main\']/table/tbody/tr[1]/td[1]/a")).click();
		driver.findElement(By.xpath("//input[@id='name']")).clear();
		driver.findElement(By.xpath("//input[@id='name']")).sendKeys(computerName);
		driver.findElement(By.xpath(cancelBtn_xpath)).click();
		System.out.println("Page is navigated on list page after cancel");
	}
	
	
	//Search the specific computer name from the list
	
	public void searchComputer(String searchStr) {
		System.out.println("Search the computer name");								
		driver.findElement(By.xpath(".//*[@id='searchbox']")).sendKeys(searchStr);			// Search specific name
		driver.findElement(By.xpath("//*[@id=\'searchsubmit\']")).click();	
		WebElement listName = driver.findElement(By.xpath("//*[@id=\"main\"]/h1"));
		System.out.println("Number of computers are found: "+ listName.getText());
		
	}
	

	
	//Delete the selected computer name
	
	public void deleteComputer(String computerName) {
		driver.findElement(By.xpath("//*[@id=\"searchbox\"]")).sendKeys(computerName);			
		driver.findElement(By.xpath("//*[@id=\'searchsubmit\']")).click();	
		driver.findElement(By.xpath("//*[@id=\'main\']/table/tbody/tr[1]/td[1]/a")).click();
		driver.findElement(By.xpath("//*[@id=\'main\']/form[2]/input")).click();
		WebElement DeleText = driver.findElement(By.xpath("//*[@id=\'main\']/div[1]"));
		System.out.println(DeleText.getText());
	}
	
	
		
	
	public void addComputer(String computerName, String introduced, String discontinued) {
		addComputer(computerName, introduced, discontinued, null);
	}
	
	public void addComputer(String computerName) {
		addComputer(computerName, null, null, null);
	}	
	
	public void addComputer(String computerName, String company) {
		addComputer(computerName, null, null, company);
	}
	
	public void editComputer(String computerName, String introduced, String discontinued) {
		
		editComputer(computerName, introduced, discontinued, null);
	}
	
	public void editComputer(String computerName) {
		
		editComputer(computerName, null, null, null);
	}
	
	public void editComputer(String computerName, String company) {
		editComputer(computerName, null, null, company);
	}
	
	public void editComputer() {
		addComputer(null, null, null, null);
	}
	
	

	public boolean verifyElement(By ele) {
		boolean isElementFound = true;
		try {
			driver.findElement(ele);
		}catch(Exception e) {
			isElementFound = false;
		}
		return isElementFound;
	}
	
	
		
}
	

