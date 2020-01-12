package ex;

import java.io.File;

public class Task {
	public static void task1(String sourcePath, String targetPath){
		IOStream stream1 = new IOStream(sourcePath);
		stream1.read();
		stream1.getData();
		
		File file = new File(targetPath);
		if(file.exists()){
			System.out.println("The file already exists");
		} else {
			System.out.printf("The file %s will be created%n", targetPath);
		}
		IOStream stream2 = new IOStream(file.getAbsolutePath());
		stream2.write(stream1.getData());
	}
	
	public static int task2(String sourcePath, String key){
		IOStream stream = new IOStream(sourcePath);
		stream.read();
		String content = stream.getData();
		int index =0;
		int counter =0;
		while ((index = content.indexOf(key)) != -1) {
			content = content.substring(index + key.length());
			counter++;
		}
		return counter;
		
	}
}
