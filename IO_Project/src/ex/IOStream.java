package ex;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOStream {
	private String path;
	private InputStream in;
	private OutputStream out;
	private StringBuilder builder;
	private boolean append;
	
	public IOStream(String path) {
		this.path = path;
		builder = new StringBuilder(128);
	}
	
	public IOStream(String path, boolean append) {
		this(path);
		this.append = append;
		
	}
	
	public String getData(){
		return builder.toString();
	}
	
	public void read(){
		try {
			in = new FileInputStream(path);
			// 
			int data = in.read();
			char ch;
			
			while (data != -1){
				builder.append((char)data);
				//ch = (char)data;
				data = in.read();
				//System.out.print(Character.toUpperCase(ch) + " ");
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	public void write(String data){
		builder.setLength(0); // clear
		if(data != null){
			try {
				out = new FileOutputStream(path, append);
				out.write(data.getBytes());
				out.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
}
