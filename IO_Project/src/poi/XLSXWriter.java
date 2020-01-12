package poi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.Client;

public class XLSXWriter {
	
	private String filePath;

	public XLSXWriter(String filePath) {
		this.filePath = filePath;
	}

	public List<Client> setClients(List<Client> cl) throws IOException {

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Clients sheet");
      
		List<Client> list = cl;
		 
	        int rownum = 0;
	        Cell cell;
	        Row row;
	        
	        row = sheet.createRow(rownum);
	 
	        // Number
	        cell = row.createCell(0, CellType.STRING);
	        cell.setCellValue("Number");

	        // First Name
	        cell = row.createCell(1, CellType.STRING);
	        cell.setCellValue("First Name");
	        // Last Name
	        cell = row.createCell(2, CellType.STRING);
	        cell.setCellValue("Last Name");
	        // Gender
	        cell = row.createCell(3, CellType.STRING);
	        cell.setCellValue("Gender");
	        // Country
	        cell = row.createCell(4, CellType.STRING);
	        cell.setCellValue("Country");
	        // Age
	        cell = row.createCell(5, CellType.STRING);
	        cell.setCellValue("Age");
	        // Data
	        cell = row.createCell(6, CellType.STRING);
	        cell.setCellValue("Data");
	        // ID
	        cell = row.createCell(7, CellType.STRING);
	        cell.setCellValue("ID");
	        
	        //Data
	        for (Client clients : list) {
				rownum++;
				row = sheet.createRow(rownum);
				
				 // Number (A)
	            cell = row.createCell(0, CellType.NUMERIC);
	            cell.setCellValue(clients.getNumber());
	            // First Name (B)
	            cell = row.createCell(1, CellType.STRING);
	            cell.setCellValue(clients.getFirstName());
	            // Last Name (C)
	            cell = row.createCell(2, CellType.STRING);
	            cell.setCellValue(clients.getLastName());
	            // Gender (D)
	            cell = row.createCell(3, CellType.STRING);
	            cell.setCellValue(clients.getGender());
	            // Country (E)
	            cell = row.createCell(4, CellType.STRING);
	            cell.setCellValue(clients.getCountry());
	            // Age (F)
	            cell = row.createCell(5, CellType.NUMERIC);
	            cell.setCellValue(clients.getAge());
	            // Data (G)
	            cell = row.createCell(6, CellType.NUMERIC);
	            clients.getDate();
				cell.setCellValue(new SimpleDateFormat("yyyy.MM.dd").format(clients.getDate()));
	            // ID (H)
	            cell = row.createCell(7, CellType.NUMERIC);
	            cell.setCellValue(clients.getId());
			}
	        File file = new File(filePath);
	        file.getParentFile().mkdirs();
	 
	        FileOutputStream outFile = new FileOutputStream(file);
	        workbook.write(outFile);
	        System.out.println("Created file: " + file.getAbsolutePath());
	        workbook.close();
			return list;
	        
		}
		

	}

