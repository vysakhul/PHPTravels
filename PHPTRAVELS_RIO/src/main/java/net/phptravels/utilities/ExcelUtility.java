package net.phptravels.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtility {
	
	private static XSSFWorkbook excelWbook;
	private static XSSFSheet excelWsheet;
	
	public static String getCellData(String sheetName, int RowNum, int ColNum) throws IOException, InterruptedException
	{
		FileInputStream exl = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources" + "/TestData.xlsx");
		excelWbook = new XSSFWorkbook(exl);
		excelWsheet = excelWbook.getSheet(sheetName);
		Thread.sleep(500);
		return excelWsheet.getRow(RowNum).getCell(ColNum).getStringCellValue();
	}

}
