/**
 * 
 */
package utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author PD
 *
 */
public class ExcelUtils {
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static XSSFRow Row;
	
	
	public static void setExcelFile(String Path, String SheetName) throws Exception{
		try {
			
			FileInputStream ExcelFile = new FileInputStream(Path);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
		}catch (Exception e) {
			throw (e);
		}
		
	}
	
	public static String getCellData(int rowNum, int colNum) throws Exception{
		try {
			Cell = ExcelWSheet.getRow(rowNum).getCell(colNum);
			String CellData = Cell.getStringCellValue();
			return CellData;
		}catch (Exception e){
			return"";
		}
	}
	
	public static void setCellData(String Result, int rowNum, int colNum) throws Exception{
		try {
			Row = ExcelWSheet.getRow(rowNum);
			Cell = Row.getCell(colNum, MissingCellPolicy.RETURN_BLANK_AS_NULL);
			if (Cell == null) {
				Cell = Row.createCell(colNum);
				Cell.setCellValue(Result);
				
			}else {
				
			}	Cell.setCellValue(Result);
			
			FileOutputStream fileOut = new FileOutputStream(Constant.Path_TestData + Constant.File_eBotTestData);
			ExcelWBook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		}catch (Exception e) {
			throw(e);
		}
	}
}
