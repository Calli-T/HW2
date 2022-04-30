import java.io.*;
import java.util.*;

public class FileWriterEx {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		FileWriter fout = null;
		int c;
		
		try {
			fout = new FileWriter("C:\\공부용자료\\자바\\입출력테스트.txt");
			
			while(true) {
				String line = scanner.nextLine();
				if(line.length() == 0)
					break;
				fout.write(line, 0, line.length());
				fout.write("\r\n", 0, 2);
			}
			fout.close();
			
		}
		catch(IOException e){
			System.out.println("입출력 오류");
		}
		scanner.close();
		
	}

}
