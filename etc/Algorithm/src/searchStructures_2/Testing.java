package searchStructures_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Testing {

	static File file;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		file = fileTaker.FileTaker.getFile();
		
		// 삽입 시간 테스트
		/*
		long time = System.currentTimeMillis();
		getText();
		time = System.currentTimeMillis() - time;
		
		System.out.println("소요시간: " + time + "ms");
		*/
		
		// 빈도수 테스트
		//getFrequency(4);
		
		
		BST<String, Integer> test1 = new BST<String, Integer>();
		AVLTree<String, Integer> test2 = new AVLTree<String, Integer>();
		
		BSTFrequency(test1, 4);
		AVLTreeFrequency(test2, 4);
	
	}

	public static void getText(BST<String, Integer> test) {
		
		//두 개의 심볼테이블에 저장
		Scanner sc = null;
		try {
			sc = new Scanner(file);
			for(int i = 0; sc.hasNext(); i++) {
				String key = sc.next();
				test.put(key, i);
			}
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//스캐너 닫기
		if(sc !=null) sc.close();
	}
	
	public static void BSTFrequency(BST<String, Integer> frequencyTest, int minlen) {
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
	}
	
	public static void AVLTreeFrequency(AVLTree<String, Integer> frequencyTest, int minlen) {
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
	}
	
}