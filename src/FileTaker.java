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

/*
Scanner sc = null;

		try {
			sc = new Scanner(file);

			long time = System.currentTimeMillis();
			while(sc.hasNext()) {
				String word = sc.next();
				if(word.length() < minlen) continue;

				if(!frequencyTest.contain(word)) {
					frequencyTest.put(word, 1);
				}
				else {
					frequencyTest.put(word, frequencyTest.get(word) + 1);
				}
			}
			String maxKey = ""; int maxValue = 0;
			for(String word: frequencyTest.keys()) {
				if(frequencyTest.get(word) > maxValue) {
					maxValue = frequencyTest.get(word);
					maxKey = word;
				}
			}

			time = System.currentTimeMillis() - time;
			System.out.println(maxKey + " " + maxValue);
			System.out.println("소요 시간  = " + time + "ms");


		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}

		sc.close();
 */
