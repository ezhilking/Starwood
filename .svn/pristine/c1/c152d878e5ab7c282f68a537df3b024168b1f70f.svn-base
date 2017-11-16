package functions;
/* Purpose		:Excel related utility class.
 * Developed By	:Ezhilarasan.S
 * Modified By	:Ezhilarasan.S
 * Modified Date:30-Mar-2016
 * Reviewed By	:
 * Reviewed Date:
 */
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;



public class ExcelUtil {
	Workbook wb;
	Sheet sheet;
	Cell cell;
	FileInputStream inputStream;
	Logger log = Logger.getLogger(ExcelUtil.class.getName());
	String fileLocation;
	int defaultColumn = 1;
	protected static ExcelUtil ORLocator;
	protected static ExcelUtil TestData;
	private final static String ORLocatorPath = "S:\\Automation_Selenium\\Data";
	//Load Excel object
	static void LoadExcel(){
		ORLocator = new ExcelUtil(ORLocatorPath+"\\ORLocator.xlsx",Environment.ORDataSheet);
		TestData = new ExcelUtil(ORLocatorPath+"\\TestData.xls", Environment.TestDataSheet);
	}

	ExcelUtil(){//Default Constructor

	}

	ExcelUtil(String fileLocation,String Sheetname){
		try{
			this.fileLocation = fileLocation;
			this.inputStream = new FileInputStream(this.fileLocation);
			//			Workbook wb = new HSSFWorkbook(inputStream);
			this.wb = WorkbookFactory.create(inputStream);
			if(!Sheetname.isEmpty()){
				this.sheet = this.wb.getSheet(Sheetname);
			}else{
				this.sheet = this.wb.getSheet(this.wb.getSheetName(0));// For all other excel sheet 1st sheet as default. 
			}
			if(this.sheet==null){
				Environment.loger.log(Level.FATAL,"'"+Sheetname+"' sheet is not present in '"+fileLocation+"'");
				System.exit(1);
				//TODO log info
			}
			//			sheet = this.wb.getSheetAt(Sheetname);
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Unable to read the Excel from Shared folder. "+e.getMessage());
		}
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	int GetColIndex(int SearchInWhichRow,String ColumnName){
		int totalCol = ColumnCount();
		for(int col=1;col<=totalCol;col++){
			if(GetData(SearchInWhichRow ,col).trim().compareTo(ColumnName)==0){
				return col;
			}
		}
		return -1;
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	int GetRowIndex(int SearchiInWhichColumn, String ContenttoSearch){
		try{
			int totalRows = RowCount();
			for(int row=1;row<=totalRows;row++){
				if(GetData(row, SearchiInWhichColumn).trim().compareTo(ContenttoSearch)==0){
					return row;
				}
			}
		}catch(Exception e){
			return -1;
			//			Environment.loger.log(Level.ERROR, "Exception occured");
		}
		return -1;
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	public String GetData(int row, int col){
		//		CellStyle style =this.wb.createCellStyle() ;
		//		style.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
		//		style.setFillPattern(CellStyle.ALIGN_FILL); 
		return CellToString(this.sheet.getRow(row-1).getCell(col-1));
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	int ColumnCount(){
		return this.sheet.getRow(0).getLastCellNum();
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	int RowCount(){
		int Count=0;
		try{
			for (Row row : this.sheet) {
				for (Cell cell : row) {
					if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {  
						//		            if (cell.getCellType() != Cell.CELL_ ||  cell.getStringCellValue().length() > 0) {  
						Count++;  
						break;  
					}
				} 
			}  
			//			sheet.getPhysicalNumberOfRows();
			return Count;

		}catch(Exception e){
			return -1;
		}
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	void SetSheet(String SheetName){
		this.sheet = this.wb.getSheet(SheetName);
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	private void Write(int column, int row,String Text){
		try {
			this.sheet.getRow(row-1).getCell(column-1).setCellValue(Text);
			this.inputStream.close();

			FileOutputStream outputStream = new FileOutputStream(this.fileLocation);
			this.wb.write(outputStream);
			outputStream.close();
			//			this.wb.close();
		} catch (Exception e) {
			Environment.loger.log(Level.ERROR, e);
		}
	}

	void CreateCellWrite(int column, int row,String[] TexIDAndValue){
		try {
			Row row1 = this.sheet.createRow(row-1);
			row1.createCell(column-1).setCellValue(TexIDAndValue[0]);
			row1.createCell(GetColIndex(1, Environment.getRunEnvironment().toUpperCase())-1).setCellValue(TexIDAndValue[1]);
//			for(String SingleValue:ListOfVlaues){
//				row1.createCell(column-1).setCellValue(SingleValue);
//				column++;
//			}
			this.inputStream.close();
			FileOutputStream outputStream = new FileOutputStream(this.fileLocation);
			this.wb.write(outputStream);
			outputStream.close();
			//			this.wb.close();
		} catch (Exception e) {
			Environment.loger.log(Level.ERROR, "Exception occured",e);
		}
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	String CellToString (Cell cell){
		try{
			//			CellStyle style = this.wb.createCellStyle();
			//			style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
			//			style.setFillPattern(CellStyle.BIG_SPOTS);
			//			row.setRowStyle(style);
			//			
			int type;
			String  result;
			cell.setCellType(Cell.CELL_TYPE_STRING);
			type = cell.getCellType();
			switch(type){
			case 1: //string value in excel
				result = cell.getStringCellValue();
				break;
			case 3:	
				result ="";
			default:
				Environment.loger.log(Level.ERROR, "Unsupported for this type of cell");
				throw new RuntimeException("Unsupported for this type of cell");
			}
			return result;
		}catch(NullPointerException e){
			Environment.loger.log(Level.ERROR, "EMPTY CELL");
			return "";
		}
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	void CloseSheet(){
		try {	
			this.inputStream.close();
			this.wb.close();
		} catch (Exception e){}
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	//Write in TestData first and second column only.
	void WriteToTestData(String TextID,String TextToWrite){
		int row = this.GetRowIndex(defaultColumn, TextID);
		if(row==-1){
			String[] TexIDAndValue = new String[2];
			TexIDAndValue[0] = TextID;
			TexIDAndValue[1] = TextToWrite;
			Environment.loger.log(Level.INFO, "TextID '"+TextID+"' is NOT present in TestData!! So adding new row!!");
			int RowToAdd = ExcelUtil.TestData.RowCount()+1;
			this.CreateCellWrite(defaultColumn, RowToAdd, TexIDAndValue);
		}else{
			Environment.loger.log(Level.INFO, "TextID '"+TextID+"' is present in TestData!! So updating corresponding column");
			this.Write(GetColIndex(defaultColumn, Environment.getRunEnvironment().toUpperCase()), row, TextToWrite);
		}
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	String GetTestData(String TextID){
		int ColumnIndex = GetColIndex(defaultColumn, Environment.getRunEnvironment().toUpperCase());
		int RowIndex = GetRowIndex(defaultColumn, TextID);
		//this.defaultColumn = this.GetRowIndex(1, "");
		if(RowIndex!=-1){
			return GetData(RowIndex, ColumnIndex);
		}else{
			Environment.loger.log(Level.ERROR,"Data not found for the TextID - '"+TextID+"'");
			return "Data not found for the TextID - '"+TextID+"'";
		}
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	String GetLocator(String ObjectName){//To get 3rd and 4th column value

		int row = this.GetRowIndex(defaultColumn, ObjectName);
		//this.defaultColumn = this.GetRowIndex(1, "");
		if(row!=-1){
			return (GetData(row, 3))+"|"+GetData(row, 4);
			//			return cellToString(this.sheet.getRow(row-1).getCell(2))+"|"+cellToString(this.sheet.getRow(row-1).getCell(3));
		}else{
			Environment.loger.log(Level.ERROR,"Locator not found for the Object - '"+ObjectName+"'");
			return "Locator not found for the Object-'"+ObjectName+"' in ORLocator.xlsx";
		}
	}
	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	String GetXPath(String ObjectName){//To get 4th column value

		int row = this.GetRowIndex(defaultColumn, ObjectName);
		if(row!=-1){
			String xpath = GetData(row, 4);
			if(xpath.startsWith("/") || xpath.startsWith("(")){
				return xpath;
			}else{
				Environment.loger.log(Level.ERROR,ObjectName+" is not XPATH identifier");
				return ObjectName+" is not XPATH identifier";
			}
		}else{
			Environment.loger.log(Level.ERROR,"Locator not found for the Object - '"+ObjectName+"'");
			return "Locator not found for the Object - '"+ObjectName+"'";
		}
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*Email related Excel sheet*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	String GetEmailTestData(String TestCaseName,String TextID){
		SetSheet("GCEmailValidation");
		int row = GetRowIndex(defaultColumn, TestCaseName);
		int col = GetColIndex(defaultColumn, TextID);
		//this.defaultColumn = this.GetRowIndex(1, "");
		if(row!=-1){
			return GetData(row, col);
		}else{
			Environment.loger.log(Level.ERROR,"Data not found for the TextID - '"+TextID+"'");
			return "Data not found for the TextID - '"+TextID+"'";
		}
	}

	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	void WriteToEmailTestData(String TestCaseName, String TextID,String TextToWrite){
		SetSheet("GCEmailValidation");
		int row = this.GetRowIndex(defaultColumn, TestCaseName);
		int col = this.GetColIndex(defaultColumn, TextID);
		if(row==-1 &&  col==-1){
			Environment.loger.log(Level.ERROR, "TestCase name '"+TestCaseName+"' is not present in TestData!!");
			SetSheet("CRM");
		}else{
			Environment.loger.log(Level.INFO, "TestCase name '"+TestCaseName+"' is present in TestData!! So updating corresponding column");
			this.Write(col, row, TextToWrite);
			SetSheet("CRM");
		} 
	}

}