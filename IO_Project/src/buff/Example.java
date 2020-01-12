package buff;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Example {
	public static void main(String[] args) {
		String sourcePath = "resources\\source.txt";
		String targetPath = "resources\\target.txt";
		
		BufferedReader reader = null;
		BufferedWriter writer = null;
		
		try {
			reader = new BufferedReader(new FileReader(sourcePath));
			writer = new BufferedWriter(new FileWriter(targetPath));

			String line = null;
			while((line = reader.readLine()) !=null){
				writer.write((line.toUpperCase()));
				writer.newLine();
			};
			System.out.println("Stop");
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				writer.close();
			} catch (IOException e) {
				// TODO: handle exception
			}
		}

		
	}
}
