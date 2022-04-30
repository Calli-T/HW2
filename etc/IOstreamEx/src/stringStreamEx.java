import java.io.*;

public class stringStreamEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileReader fin = null;
		
		try {
			fin = new FileReader("3주차.txt");//("C:\\학업\\토익\\3주차.txt");
			int c;
			while((c = fin.read()) != -1){
				System.out.print((char)c);
			}
		}
		catch(IOException e) {
			System.out.println("입출력 오류");
		}
		
		try {
			fin.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
