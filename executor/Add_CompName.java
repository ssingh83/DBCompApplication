package executor;
import pageobjects.Page;

public class Add_CompName {

	

	public static void main(String[] args) throws InterruptedException {
		
		Page page = new Page();
		
	
		//Add the computer name
		System.out.println("Check for the add computer name");
		page.addComputer("MacComp");
		page.addComputer("123456789");
		page.addComputer("^&!@#$*()&");
		page.addComputer("Mac123");
		page.addComputer("NewComp", "RCA");
		page.addComputer("Apple", "1982-05-09", "2002-02-01");
		page.addComputer("Andriod", "1982-05-09", "2002-02-01","IBM");
		
		System.out.println("Check for the madatory field");
		page.addComputer(" ");
		
		System.out.println("Check for the date wrong format");
		page.addComputer("NewComp", "05-09", "2002-02-01");
		page.addComputer("NewComp1", "1982-05-09", "2002-02");
		
		System.out.println("Check for the cancel option on add computer name");
		page.cancelAddComp("MacComp");
		
		// Edit the computer name
		
		System.out.println("Check for the edit computer name");
		page.editComputer("MacComp");
		page.editComputer("123456789");
		page.editComputer("^&!@#$*()&");
		page.editComputer("Mac123");
		page.editComputer("NewComp", "RCA");
		page.editComputer("Andriod", "1982-05-09", "2002-02-01");
		page.editComputer("Andriod", "1982-05-09", "2002-02-01","IBM");
		
		System.out.println("Check for the madatory field\n");
		page.editComputer(" ");
		

		System.out.println("Check for the cancel option on edit computer name\n");
		page.cancelEditComp("Mac123");
		
		//Search the computer name
		
		page.searchComputer("Apple");
		page.searchComputer(" ");
		
		////Delete the computer name
		
		page.deleteComputer("NewComp");
	
				
		}

}
