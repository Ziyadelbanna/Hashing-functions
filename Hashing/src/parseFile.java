import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class parseFile {
    int[] keyList  ;

    public parseFile(File file) throws FileNotFoundException {
        HashSet<Integer> collection = new HashSet<>();
        Scanner scanner = new Scanner(file) ;
        scanner.useDelimiter(",");

        while (scanner.hasNextInt())
            collection.add(scanner.nextInt()) ;
        scanner.close();
        buildKeysArray(collection);

    }
    private void buildKeysArray(HashSet collection){
        Iterator iterator = collection.iterator();
        int size = collection.size();
        keyList = new int[size];
        int i = 0 ;
        while (iterator.hasNext()){
            keyList[i] = (int)iterator.next();
            i++;
        }
    }

    public int[] getKeyList() {
        return keyList;
    }
}
