import java.io.*;
import java.util.Scanner;

public class bufferStreamEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileReader fin = null;
		int c;
		
		try {
			fin = new FileReader("C:\\공부용자료\\자바\\입출력테스트.txt");
			BufferedOutputStream out = new BufferedOutputStream(System.out, 7);
			while((c=fin.read()) != -1) {
				out.write(c);
			}
			
			new Scanner(System.in).nextLine();
			out.flush();
			fin.close();
			out.close();
		} catch(IOException e){
			e.printStackTrace();
		}
	}

}
