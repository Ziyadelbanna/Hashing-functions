import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class universalHashing {

    int keyListLength ;

    public universalHashing(int keyListLength) {
        this.keyListLength = keyListLength ;
    }

    public int[][] generateUniversalHashFunc(){
        int u = (int) (Math.log(keyListLength * keyListLength) / Math.log(2));
        if (keyListLength == 1 )
            u = 1 ;
        Random rand = new Random();
        int[][] randomHashingFunc = new int[u][32];
        for (int i = 0 ; i < u ; i ++){
            for (int j = 0 ; j < 32 ; j++)
            {
                randomHashingFunc[i][j] =rand.nextInt(2);
            }
        }
        return randomHashingFunc ;
    }
}
