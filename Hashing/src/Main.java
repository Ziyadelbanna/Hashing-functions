import java.io.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("/home/sami/IdeaProjects/Hash/src/test.txt");
        parseFile test = new parseFile(file);
   
//        squareHashing hash = new squareHashing(test.getKeyList());
//        hash.generateHashTable();
//        for (int i = 1 ; i < 200 ; i ++){
//            System.out.println(hash.find(i));
//        }

        linearHashing hash2 = new linearHashing(test.getKeyList());
        hash2.generateSquareHashTable();
        for (int i = 1 ; i < 200 ; i ++){
            System.out.println(hash2.find(i));
        }
    }

}
