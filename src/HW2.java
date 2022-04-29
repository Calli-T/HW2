import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HW2 {
        static File file;
        public static void getFile() {
                File file;
                final JFileChooser fc = new JFileChooser();
                if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                        file = fc.getSelectedFile();
                } else {
                        JOptionPane.showMessageDialog(null, "파일을 선택하세요.", "오류", JOptionPane.ERROR_MESSAGE);
                }
        }
        public static void getList() {
                Scanner sc = null;

                try {
                        sc = new Scanner(file);

                }catch(FileNotFoundException e) {
                        e.printStackTrace();
                }
        }

        public static void main(String args[]){
                getFile();
                getList();
        }
}
