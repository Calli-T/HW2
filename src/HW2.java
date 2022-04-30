import java.io.*;
import java.sql.Array;
import java.util.*;

class User implements Comparable<User> {
    int name;
    float jacc;

    public User(float jacc, int name){
        this.jacc = jacc;
        this.name = name;
    }

    @Override
    public int compareTo(User user) {
       if(user.jacc > jacc) {
           return 1;
       } else if(user.jacc < jacc) {
           return -1;
       }
       return 0;
    }
}

class itemPair implements Comparable<itemPair> {
    int name;
    int frequency;

    public itemPair(int frequency, int name){
        this.name = name;
        this.frequency = frequency;
    }

    @Override
    public int compareTo(itemPair it) {
        if(it.frequency > frequency) {
            return 1;
        } else if(it.frequency < frequency) {
            return -1;
        }
        return 0;
    }
}
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

        public static ArrayList<User> getJaccard(ArrayList<HashSet<Integer>> list, int userNumber) {
            ArrayList<User> jaccard = new ArrayList<>();

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
                jaccard.add(new User(jacc, i + 1));
            }
            Collections.sort(jaccard);
            return jaccard;
        }
        public static void main(String args[]) {
            System.out.print("파일 이름, 사용자 이름, 사용자 수, 항목 수? ");

            ArrayList<HashSet<Integer>> list = getList();
            ArrayList<User> jaccard = getJaccard(list, originNumber);

            ArrayList<HashSet<Integer>> top = new ArrayList<>();
            User user;
            Iterator<User> it = jaccard.iterator();
            HashSet<Integer> origin = list.get(originNumber - 1);
            for(int i = 0; i < userNumber; i++) {
                user = it.next();
                HashSet<Integer> temp = list.get(user.name - 1);
                Iterator<Integer> iter = origin.iterator();
                while(iter.hasNext()){
                    temp.remove(iter.next());
                }

                top.add(temp);
            }

            HashMap<Integer, Integer> item = new HashMap<>();
            Iterator<HashSet<Integer>> iter = top.iterator();
            HashSet<Integer> eachList;
            while(iter.hasNext()){
                eachList = iter.next();

                Iterator<Integer> iterator = eachList.iterator();
                int next;
                while(iterator.hasNext()){
                    next =  iterator.next();
                    if(!item.containsKey(next))
                        item.put(next, 1);
                    else {
                        item.put(next, item.get(next) + 1);
                    }
                }
            }

            ArrayList<itemPair> pair = new ArrayList<>();
            Iterator<Integer> keyIt = item.keySet().iterator();
            Iterator<Integer> valueIt = item.values().iterator();
            while(keyIt.hasNext()) {
                pair.add(new itemPair(valueIt.next(), keyIt.next()));
            }
            Collections.sort(pair);

            System.out.print("결과: ");
            Iterator<itemPair> ansIt = pair.iterator();
            while(itemNumber-- != 0){
                itemPair temp = ansIt.next();
                System.out.print(temp.name + "(" + temp.frequency + ") ");
            }
        }

}
