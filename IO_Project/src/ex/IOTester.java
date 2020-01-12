package ex;

public class IOTester {

	public static void main(String[] args) {
		//                              D:\temp\io
		IOStream stream = new IOStream("D:\\temp\\io\\readme.txt");
		stream.read();
		System.out.println(stream.getData());
		
		stream.write("Two");
		stream.read();
		
		System.out.println(stream.getData());
		
		
		System.out.println("TASK #1");
		Task.task1("D:\\temp\\io\\CONTENT.txt", "D:\\temp\\io\\page.html");
		System.out.println("END");
		
		
		IOStream stream3 = new IOStream("resources\\try.txt");
		stream3.read();
		System.out.println(stream3.getData());
		
		System.out.println("*******APPEND*********");
		
		IOStream ios = new IOStream("D:\\temp\\io\\readme.txt");
		ios.read();
		
		IOStream ios2 = new IOStream("resources\\try.txt", true);
		ios2.write(ios.getData());
		
		ios2.read();
		System.out.println(ios2.getData());
		
		System.out.println("END");
		
		System.out.println("********Task2********");
		int counter =Task.task2("resources\\source2.txt", "*");
		System.out.println(counter);
		System.out.println("END");

		

	}

}
