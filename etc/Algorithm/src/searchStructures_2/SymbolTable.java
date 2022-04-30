package searchStructures_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

class TestClient {
	
	public static SequentialSearchST<String, Integer> st1 = new SequentialSearchST<String, Integer>();
	public static BinarySearchST<String, Integer> st2 = new BinarySearchST<String, Integer>();
	public static File file;
	public static SequentialSearchST<String, Integer> minlenST = new SequentialSearchST<String, Integer>();
	public static BinarySearchST<String, Integer> minlenBinST = new BinarySearchST<String, Integer>();
	
	public static void getFrequency(int minlen) {
		Scanner sc = null;
		Scanner sc2 = null;
		
		try { 
			sc = new Scanner(file);
			sc2 = new Scanner(file);
			
			//-------------------------------------linked list
			long time = System.currentTimeMillis();
			
			while(sc.hasNext()) {
				String word = sc.next();
				if(word.length() < minlen) continue;
				
				if(!minlenST.contains(word)) {
					minlenST.put(word, 1);
				}
				else {
					minlenST.put(word, minlenST.get(word) + 1);
				}
			}		
			String maxKey =""; int maxValue = 0;
			for(String word: minlenST.keys()) {
				if(minlenST.get(word)>maxValue) {
					maxValue = minlenST.get(word);
					maxKey = word;
				}
			}
			
			time = System.currentTimeMillis() - time;
			System.out.println("Linked List");
			System.out.println(maxKey + " " + maxValue);
			System.out.println("소요 시간  = " + time + "ms");
			
			System.out.println();
			
			//-------------------------------------array
			time = System.currentTimeMillis();
			while(sc2.hasNext()) {
				String word = sc2.next();
				if(word.length() < minlen) continue;
				
				if(!minlenBinST.contains(word)) {
					minlenBinST.put(word, 1);
				}
				else {
					minlenBinST.put(word, minlenBinST.get(word) + 1);
				}
			}		
			maxKey =""; maxValue = 0;
			for(String word: minlenBinST.keys()) {
				if(minlenBinST.get(word) > maxValue) {
					maxValue = minlenBinST.get(word);
					maxKey = word;
				}
			}
			
			time = System.currentTimeMillis() - time;
			System.out.println("Array");
			System.out.println(maxKey + " " + maxValue);
			System.out.println("소요 시간  = " + time + "ms");
			
			
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		sc.close();
		sc2.close();
	}
	
	public static void getFile() {
		file = fileTaker.FileTaker.getFile();
	}
	
	public static void getText() {
		
		//두 개의 심볼테이블에 저장
		Scanner sc = null;
		try {
			sc = new Scanner(file);
			for(int i = 0; sc.hasNext(); i++) {
				String key = sc.next();
				st1.put(key, i);
				st2.put(key, i);
			}
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//스캐너 닫기
		if(sc !=null) sc.close();
		
		//System.out.println(st1.size());
	}
	
}

public class SymbolTable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestClient.getFile();
		//TestClient.getText();
		TestClient.getFrequency(4);
	}

}
