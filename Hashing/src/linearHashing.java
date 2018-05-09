import java.util.LinkedList;

public class linearHashing {
	universalHashing hashTable;
	squareHashing[] sqareHashTable;
	LinkedList<Integer>[] twoLevelHashTable;
	int[] keyList;
	int[][] randomHashingFunc;
	int[][] binaryKey = new int[32][1];
	int collision;

	public linearHashing(int[] keyList) {
		this.keyList = keyList;
		this.twoLevelHashTable = new LinkedList[keyList.length];
		this.hashTable = new universalHashing(this.keyList.length);
	}

	public void generateSquareHashTable() {
		collision = 3 * keyList.length;
		while (collision > 2 * keyList.length) {
			this.randomHashingFunc = hashTable.generateUniversalHashFunc();
			int index;
			collision = 0;
			for (int i = 0; i < keyList.length; i++) {
				generateBinaryKey(keyList[i]);
				index = getIndex(MultiplyOut());
				if (twoLevelHashTable[index] == null) {
					twoLevelHashTable[index] = new LinkedList<>();
					twoLevelHashTable[index].add(keyList[i]);
				} else {
					twoLevelHashTable[index].add(keyList[i]);
					collision++;
				}
			}
		}

		squareHashing[] table = new squareHashing[twoLevelHashTable.length];
		squareHashing hashArray;
		for (int i = 0; i < twoLevelHashTable.length; i++) {
			if (twoLevelHashTable[i] == null) {
				table[i] = null;
				continue;
			}
			if (twoLevelHashTable[i].size() != 0) {
				int[] keyArray = new int[twoLevelHashTable[i].size()];
				for (int j = 0; j < twoLevelHashTable[i].size(); j++) {
					keyArray[j] = twoLevelHashTable[i].get(j);
				}
				hashArray = new squareHashing(keyArray);
				hashArray.generateHashTable();
				table[i] = hashArray;
			}
		}
		this.sqareHashTable = table;
		for (int i = 0; i < sqareHashTable.length; i++) {
			if (sqareHashTable[i] != null)
				System.out.print(sqareHashTable[i].getHashList().length + "  ");
			else
				System.out.print("0  ");
		}
	}

	public boolean find(int key) {
		generateBinaryKey(key);
		int arrayIndex = getIndex(MultiplyOut());
		if (this.sqareHashTable[arrayIndex] == null)
			return false;
		return this.sqareHashTable[arrayIndex].find(key);
	}

	private void generateBinaryKey(int key) {
		String temp = intToBinary(key, 32);
		for (int i = 0; i < 32; i++) {
			if (temp.charAt(i) == '0')
				this.binaryKey[i][0] = 0;
			else
				this.binaryKey[i][0] = 1;
		}
	}

	public String intToBinary(int n, int numOfBits) {
		String binary = "";
		for (int i = 0; i < numOfBits; ++i, n /= 2) {
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

	private int[][] MultiplyOut() {
		int sum = 0;
		int[][] hashFunc = new int[randomHashingFunc.length][1];
		for (int i = 0; i < randomHashingFunc.length; i++) {
			sum = 0;
			for (int j = 0; j < 32; j++) {
				sum += randomHashingFunc[i][j] * binaryKey[j][0];
			}
			sum = sum % 2;
			hashFunc[i][0] = sum;

		}
		return hashFunc;

	}

	private int getIndex(int[][] binaryIndex) {
		String temp = "";
		for (int i = 0; i < binaryIndex.length; i++) {
			temp += binaryIndex[i][0];
		}
		int decimalIndex = Math.floorMod(Integer.parseInt(temp, 2), this.twoLevelHashTable.length);
		return decimalIndex;
	}

}
