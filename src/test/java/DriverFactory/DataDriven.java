package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import CommonFunLibrary.ERP_Functions;
import Utilities.ExcelFileUtil;


public class DataDriven {
	WebDriver driver;
	@BeforeTest
	public void setUp() throws Throwable{
		String launch=ERP_Functions.launchApp("http://webapp.qedge.com/");
		Reporter.log(launch,true);
		String login=ERP_Functions.verifyLogin("admin", "master");
	}
	
	@Test
	public void supplierCreation()throws Throwable{
		//accessing util method
		ExcelFileUtil xl=new ExcelFileUtil();
		//row count
		int rc=xl.rowCount("Supplier");
		int cc=xl.colCount("Supplier");
		Reporter.log("No of rows are::"+rc+"  "+"No of columns are::"+cc,true);
		for(int i=1;i<=rc;i++){
			String sname=xl.getCellData("Supplier", i, 0);
			String address=xl.getCellData("Supplier", i, 1);
			String city=xl.getCellData("Supplier", i, 2);
			String country=xl.getCellData("Supplier", i, 3);
			String cperson=xl.getCellData("Supplier", i, 4);
			String pnumber=xl.getCellData("Supplier", i, 5);
			String email=xl.getCellData("Supplier", i, 6);
			String mnumber=xl.getCellData("Supplier", i, 7);
			String notes=xl.getCellData("Supplier", i, 8);
			//cell supplier creation method
			String Results=ERP_Functions.verifySupplier(sname, address, city, country, cperson, pnumber, email, mnumber, notes);
			Reporter.log(Results,true);
			xl.setCellData("Supplier", i, 9, Results);
		}
	}
	
	@AfterTest
	public void tearDown(){
		ERP_Functions.verifylogout();
	}

} 
