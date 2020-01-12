package poi;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import model.Client;
import util.HTMLTableGenerator;

public class POIUtils extends Thread {
	
	@Override
	public void run() {
		this.setName("POI_JOB_THREAD");
		System.out.println("Child thread started - " + this.getName()+"/" + this.getId()+"/" + this.getPriority());
		System.out.println("ALL STARTED OK!");
		PerformanceAnalyzer.start("POI CLIENTS JOB");
        
        List<Client> clients = null;
         
        try {
            clients = new XLSXReader(
                    "resources/file_example_XLS_1000.xlsx").getClients();
            generateClientTemplate( clients, "resources/Output_Clients_template.docx");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        HTMLTableGenerator table = new HTMLTableGenerator(clients.size(), 8);

        table.addHeaders("id", "number", "firstName", "lastName", "gender", "country", "age", "date");
        
        for (Client client : clients) {
            table.addRowsData(client.getId() + "",
                    client.getNumber() + "",
                    client.getFirstName(),
                    client.getLastName(),
                    client.getGender(),
                    client.getCountry(),
                    client.getAge() + "",
                    client.getDate().toString());
        }
        
        MailUtils.send("j1819sender@gmail.com", 
                "verishkoalexey@gmail.com", 
                "resources/Output_Clients_template.docx",
                table.build(),
                "NO SPAM");
        
        
        PerformanceAnalyzer.end();
	}
    
    public static final String DATE_FORMAT_PATTERN = "yyyyMMdd"; 
    
    public static void generateClientTemplate(List<Client> clients,
            String path) {
        XWPFDocument template = new XWPFDocument();
        OutputStream os = null;
        
        try {
            os = new FileOutputStream(path);
            XWPFParagraph paragraph = template.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setBold(true);
            SimpleDateFormat simpleDateFormat 
                = new SimpleDateFormat(DATE_FORMAT_PATTERN);
            String dateStr = simpleDateFormat.format(new Date());
            
            run.setText(String.format("CLIENTS REPORT. SINCE - %s", dateStr));
            run.addBreak();
        
            
            Map<String, Integer> data = new TreeMap<>();
            for (Client client : clients) {
                
                LocalDate date = LocalDate.parse(new SimpleDateFormat("YYYY-MM-dd").format(client.getDate()));
                String key = String.valueOf(date.getYear());
                
                if(!data.containsKey(key)) {
                    data.put(key, 1);
                } else {
                    data.put(key, data.get(key) + 1);
                }
            }
            
            for(Entry<String, Integer> entry : data.entrySet()) {
                XWPFRun run2 = paragraph.createRun();
                run2.setBold(false);
                run2.setText(entry.getKey() + " : " + entry.getValue());
                run2.addBreak();
            }
            
            
            
            
            template.write(os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
    	System.out.println("Parent thread started - " + Thread.currentThread().getName()+"/"
				+ Thread.currentThread().getId()+"/" + Thread.currentThread().getPriority());
    	
    	POIUtils th = new POIUtils();
    	th.start();
    	
    	System.out.println("Parent thread finished");
    	
    	
        
        
    }
    
}

