package fileTaker;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class FileTaker {

	//swing으로 file을 집어다 return해준다
	public static File getFile() {
		File file;
		final JFileChooser fc = new JFileChooser();
		if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			file = fc.getSelectedFile();
			//System.out.println("파일 선택됨");
			return file;
		}
		else {
			JOptionPane.showMessageDialog(null, "파일을 선택하세요.", "오류", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
}
