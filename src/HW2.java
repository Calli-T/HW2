import java.io.*;
import java.util.*;

public class HW2 {
        static String userName;
        static int originNumber;
        static int userNumber;
        static int itemNumber;
        public static ArrayList<HashSet<Integer>> getList() {
             Scanner sc = new Scanner(System.in);
             String fileName;
             File file;
             BufferedReader br = null;
             ArrayList<HashSet<Integer>> list = new ArrayList<>();

             fileName = sc.next();
             userName = sc.next();
             userNumber = Integer.parseInt(sc.next());
             itemNumber = Integer.parseInt(sc.next());

             originNumber = Integer.parseInt(userName.split("u")[1]);

                try {
                        br = new BufferedReader(new FileReader(new File(fileName)));
                        String s;

                        while((s = br.readLine())!= null) {
                            HashSet<Integer> user = new HashSet<>();

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

        public static TreeMap<Float, Integer> getJaccard(ArrayList<HashSet<Integer>> list, int userNumber) {
            TreeMap<Float, Integer> jaccard = new TreeMap<>(Collections.reverseOrder());
            HashSet<Integer> origin = list.get(userNumber - 1);
            int size = origin.size();
            float jacc = 0;

            for(int i = 0; i < list.size(); i++) {
                if(userNumber == (i + 1)) continue;

                int intersection = 0;
                HashSet<Integer> comparator = list.get(i);
                Iterator<Integer> it = origin.iterator();
                while(it.hasNext())
                    if(comparator.contains(it.next()))
                        intersection++;

                jacc = intersection / (float)(size + comparator.size() - intersection);
                jaccard.put(jacc, i + 1);
            }
            return jaccard;
        }
        public static void main(String args[]) {

            ArrayList<HashSet<Integer>> list = getList();
            TreeMap<Float, Integer> jaccard = getJaccard(list, originNumber);

            Iterator<Float> keys = jaccard.keySet().iterator();
            Iterator<Integer> values = jaccard.values().iterator();
            while(keys.hasNext()){
                float key = keys.next();
                int value = values.next();
                System.out.println(key + " " + value);
            }

        }


}
