import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class squareHashing {
    universalHashing hashTable;
    int[] keyList  ;
    int[][] randomHashingFunc;
    int[][] binaryKey = new int[32][1];
    int[] hashList ;
    boolean rehash = true ;
    int repeat = 0 ;
    int counter = 0;


    public squareHashing(int[] keyList ){
        this.keyList = keyList ;
        this.hashTable = new universalHashing(this.keyList.length) ;
    }

    public int[] getHashList() {
        return hashList;
    }
    public boolean find(int key){
        generateBinaryKey(key);
        int index = getIndex(MultiplyOut());
        if (hashList[index] == key)
            return true;
        return false;
    }

    public void generateHashTable(){

        while (rehash == true){
            rehash = false ;
            this.randomHashingFunc = hashTable.generateUniversalHashFunc();
            int index  ;
            hashList = new int[keyList.length * keyList.length];
            for (int i = 0 ; i < keyList.length ; i ++){
                generateBinaryKey(keyList[i]);
                index = getIndex(MultiplyOut());
                if (hashList[index] == 0){
                    hashList[index] = keyList[i] ;
                    counter ++ ;
                }else {
                    rehash = true;
                    repeat++;
                    break;
                }
            }
        }

    }
    private void generateBinaryKey(int key){
        String temp = intToBinary(key ,32);
        for (int i = 0 ; i < 32 ; i++){
            if (temp.charAt(i) == '0')
                this.binaryKey[i][0] = 0 ;
            else
                this.binaryKey[i][0] = 1 ;
        }
    }
    public  String intToBinary (int n, int numOfBits) {
        String binary = "";
        for(int i = 0; i < numOfBits; ++i, n/=2) {
            switch (n % 2) {
                case 0:
                    binary = "0" + binary;
                    break;
                case 1:
                    binary = "1" + binary;
                    break;
            }
        }

        return binary;
    }
    private int[][] MultiplyOut(){
        int sum = 0 ;
        int[][] hashFunc = new int[randomHashingFunc.length][1];
        for (int i = 0 ; i < randomHashingFunc.length ; i ++)
        {
            sum = 0 ;
            for (int j = 0 ; j < 32 ; j++){
                sum += randomHashingFunc[i][j] * binaryKey[j][0];
            }
            sum = sum % 2 ;
            hashFunc[i][0] = sum ;

        }
        return hashFunc;

    }
    private int getIndex(int[][] binaryIndex){
        String temp = "";
        for (int i = 0 ; i < binaryIndex.length ; i ++){
            temp += binaryIndex[i][0];
        }
        int decimalIndex = Math.floorMod(Integer.parseInt(temp , 2) , this.hashList.length) ;
        return decimalIndex ;
    }

}
