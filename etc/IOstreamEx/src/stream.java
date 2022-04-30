import java.io.*;

public class stream {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputStreamReader in = null;
		FileInputStream fin = null;
		
		try {
			fin = new FileInputStream("C:\\공부용자료\\토익\\4주차.txt");
			in = new InputStreamReader(fin, "UTF-8");
			int c;
			
			System.out.println("인코딩 문자 집합은 " + in.getEncoding());
			while((c = in.read()) != -1){
				System.out.print((char)c);
			}
			
			in.close();
			fin.close();
		}
		catch(IOException e) {
			System.out.println("입출력 오류");
		}
		
	}

}
