import com.sun.source.tree.Tree;

import java.io.*;
import java.util.*;

public class HW2 {
        static String userName;
        static int userNumber;
        static int itemNumber;
        public static ArrayList<TreeSet<Integer>> getList() {
             Scanner sc = new Scanner(System.in);
             String fileName;
             File file;
             BufferedReader br = null;
             ArrayList<TreeSet<Integer>> list = new ArrayList<>();

             fileName = sc.next();
             userName = sc.next();
             userNumber = Integer.parseInt(sc.next());
             itemNumber = Integer.parseInt(sc.next());

                try {
                        br = new BufferedReader(new FileReader(new File(fileName)));
                        String s;

                        while((s = br.readLine())!= null) {
                                TreeSet<Integer> user = new TreeSet<>();

                                if(!s.isEmpty()) {
                                        StringTokenizer st = new StringTokenizer(s, " ");
                                        int n = st.countTokens();
                                        st.nextToken();

                                        for(int i = 1; i < n; i++) {
                                                user.add(Integer.parseInt(st.nextToken()));
                                        }

                                        list.add(user);
                                }
                        }
                        br.close();

                } catch(FileNotFoundException e) {
                        e.printStackTrace();
                } catch(IOException e) {
                        e.printStackTrace();
                }

                sc.close();

             return list;
        }
        public static void main(String args[]) {
            ArrayList<TreeSet<Integer>> list = getList();
            TreeMap<Integer, Float> jaccard = new TreeMap<>();


        }


}
