package fileTaker;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class FileTaker {

	//swing���� file�� ����� return���ش�
	public static File getFile() {
		File file;
		final JFileChooser fc = new JFileChooser();
		if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			file = fc.getSelectedFile();
			//System.out.println("���� ���õ�");
			return file;
		}
		else {
			JOptionPane.showMessageDialog(null, "������ �����ϼ���.", "����", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
}
