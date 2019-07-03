/**
 * 
 */
package test.utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;

/**
 * Utility class for reading test data from Excel and writing result back in
 * same file
 * 
 * @author PD
 *
 */
public class ExcelUtils {

	private static ExcelUtils excelUtilInstance;

	// private constructor.
	private ExcelUtils() {
		// Prevent form the reflection api.
		if (excelUtilInstance != null) {
			throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
		}
	}

	public synchronized static ExcelUtils getInstance() {
		// if there is no instance available... create new one
		if (excelUtilInstance == null) {
			excelUtilInstance = new ExcelUtils();
		}
		return excelUtilInstance;
	}

	/**
	 * 
	 * @param rowNum
	 * @param colNum
	 * @param SheetName
	 * @return
	 * @throws IOException
	 */
	public String getCellData(int rowNum, int colNum, String sheetName) throws IOException {
		FileInputStream inputStream = new FileInputStream(Constant.Path_TestData + Constant.File_eBotTestData);
		XSSFWorkbook excelWBook = new XSSFWorkbook(inputStream);
		XSSFSheet excelWSheet = excelWBook.getSheet(sheetName);
		XSSFCell cellData = excelWSheet.getRow(rowNum).getCell(colNum, MissingCellPolicy.CREATE_NULL_AS_BLANK);
		excelWBook.close();
		return cellData.toString();
	}

	/**
	 * 
	 * @param valueToWrite
	 * @param rowNum
	 * @param colNum
	 * @param sheetName
	 * @throws IOException
	 */
	public void setCellData(String valueToWrite, int rowNum, int colNum, String sheetName) throws IOException {
		FileInputStream inputStream = new FileInputStream(Constant.Path_TestData + Constant.File_eBotTestData);
		XSSFWorkbook excelWBook = new XSSFWorkbook(inputStream);
		XSSFSheet excelWSheet = excelWBook.getSheet(sheetName);

		XSSFRow row = excelWSheet.getRow(rowNum);
		XSSFCell cell = row.getCell(colNum, MissingCellPolicy.RETURN_BLANK_AS_NULL);
		if (cell == null) {
			cell = row.createCell(colNum);
			cell.setCellValue(valueToWrite);

		} else {
			cell.setCellValue(valueToWrite);
		}

		FileOutputStream fileOut = new FileOutputStream(Constant.Path_TestData + Constant.File_eBotTestData);
		excelWBook.write(fileOut);
		excelWBook.close();
		fileOut.flush();
		fileOut.close();
	}

	/**
	 * 
	 * @param valueToWrite
	 * @param rowNum
	 * @param colNum
	 * @param sheetName
	 * @throws IOException
	 */
	public void setQuotes(List<String> listOfQuotes, String sheetName) throws IOException {
		FileInputStream inputStream = new FileInputStream(Constant.Path_TestData + Constant.File_Quotes);
		XSSFWorkbook excelWBook = new XSSFWorkbook(inputStream);
		int index = excelWBook.getSheetIndex(sheetName);
		XSSFSheet excelWSheet = excelWBook.getSheetAt(index);// (sheetName);

		int rowNum = 1;
		for (String valueToWrite : listOfQuotes) {

			XSSFRow row = excelWSheet.createRow(rowNum);

			XSSFCell cell = row.getCell(0, MissingCellPolicy.RETURN_BLANK_AS_NULL);
			if (cell == null) {
				cell = row.createCell(0);
				cell.setCellValue("Gideon");

				cell = row.createCell(1);
				cell.setCellValue(valueToWrite);

			} else {
				cell.setCellValue(valueToWrite);
			}
			rowNum++;
		}

		FileOutputStream fileOut = new FileOutputStream(Constant.Path_TestData + Constant.File_Quotes);
		excelWBook.write(fileOut);
		excelWBook.close();
		fileOut.flush();
		fileOut.close();
	}

	public List<String> getQuotes(String sheetName) throws IOException {
		FileInputStream inputStream = new FileInputStream(Constant.Path_TestData + Constant.File_Quotes);
		XSSFWorkbook excelWBook = new XSSFWorkbook(inputStream);
		int index = excelWBook.getSheetIndex(sheetName);
		XSSFSheet excelWSheet = excelWBook.getSheetAt(index);// (sheetName);

		List<String> listOfQuotes = new ArrayList<>();

		Iterator<Row> rowIterator = excelWSheet.rowIterator();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();

			String quote = row.getCell(1, MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

			listOfQuotes.add(quote);
		}

		excelWBook.close();
		return listOfQuotes;

	}
}
