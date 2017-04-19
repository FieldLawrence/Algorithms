import java.util.Arrays;

/**
 * Created by Lawrence Field on 4/19/2017.
 */
public class BigONotation {

    private int[] theArray;
    private int arraySize;
    private int itemsInArray = 0;
    private static long startTime;
    private static long endTime;

    public static void main(String[] args) {
        //Add Item to Array Test
        BigONotation testAlgo = new BigONotation(10);
        testAlgo.addItemToArray((int)(Math.random()*1000));
        testAlgo.addItemToArray((int)(Math.random()*1000));
        testAlgo.addItemToArray((int)(Math.random()*1000));
        testAlgo.addItemToArray((int)(Math.random()*1000));
        testAlgo.addItemToArray((int)(Math.random()*1000));
        testAlgo.addItemToArray((int)(Math.random()*1000));
        testAlgo.addItemToArray((int)(Math.random()*1000));
        testAlgo.addItemToArray((int)(Math.random()*1000));
        testAlgo.addItemToArray((int)(Math.random()*1000));
        testAlgo.addItemToArray((int)(Math.random()*1000));
        System.out.println(Arrays.toString(testAlgo.theArray));

        //Generate Random Arrays for Tests 2-5
        BigONotation testAlgo2 = new BigONotation(100000);
        testAlgo2.generateRandomArray();
        BigONotation testAlgo3 = new BigONotation(200000);
        testAlgo3.generateRandomArray();
        BigONotation testAlgo4 = new BigONotation(300000);
        testAlgo4.generateRandomArray();
        BigONotation testAlgo5 = new BigONotation(400000);
        testAlgo5.generateRandomArray();

        //Linear Search Test
        testAlgo2.linearSearch((int)(Math.random()*1000));
        testAlgo3.linearSearch((int)(Math.random()*1000));
        testAlgo4.linearSearch((int)(Math.random()*1000));
        testAlgo5.linearSearch((int)(Math.random()*1000));

        //Generate Arrays for Tests 6-9
        BigONotation testAlgo6 = new BigONotation(10000);
        testAlgo6.generateRandomArray();
        BigONotation testAlgo7 = new BigONotation(20000);
        testAlgo7.generateRandomArray();
        BigONotation testAlgo8 = new BigONotation(30000);
        testAlgo8.generateRandomArray();
        BigONotation testAlgo9 = new BigONotation(40000);
        testAlgo9.generateRandomArray();

        //Bubble Sort Test
        testAlgo6.bubbleSort();
        testAlgo7.bubbleSort();
        testAlgo8.bubbleSort();
        testAlgo9.bubbleSort();

        //Binary Search Test
        testAlgo6.binarySearch((int)(Math.random()*1000));
        testAlgo7.binarySearch((int)(Math.random()*1000));
        testAlgo8.binarySearch((int)(Math.random()*1000));
        testAlgo9.binarySearch((int)(Math.random()*1000));

        //Simple Quick Sort Test
        startTime = System.currentTimeMillis();
        testAlgo2.quickSort(0, testAlgo2.itemsInArray);
        endTime = System.currentTimeMillis();
        System.out.println("Quick Sort took " + (endTime - startTime));

        startTime = System.currentTimeMillis();
        testAlgo3.quickSort(0, testAlgo3.itemsInArray);
        endTime = System.currentTimeMillis();
        System.out.println("Quick Sort took " + (endTime - startTime));

        startTime = System.currentTimeMillis();
        testAlgo4.quickSort(0, testAlgo4.itemsInArray);
        endTime = System.currentTimeMillis();
        System.out.println("Quick Sort took " + (endTime - startTime));

        startTime = System.currentTimeMillis();
        testAlgo5.quickSort(0, testAlgo5.itemsInArray);
        endTime = System.currentTimeMillis();
        System.out.println("Quick Sort took " + (endTime - startTime));
    }
    // O(1)
    private void addItemToArray (int newItem) {
        theArray [itemsInArray++] = newItem;
    }
    // O(N)
    private void linearSearch(int value) {

        boolean valueInArray = false;
        String indexsWithValue = "";
        startTime = System.currentTimeMillis();

        for (int i = 0; i < arraySize; i++){
            if (theArray[i] == value){
                valueInArray= true;
                indexsWithValue += i + " ";
            }
        }
        System.out.println("Value Fount: " +valueInArray);
        endTime = System.currentTimeMillis();
        System.out.println("Linear Search Took: " + (endTime-startTime));
    }
    // O(N^2)
    private void bubbleSort(){

        startTime = System.currentTimeMillis();

        for (int i = arraySize -1 ; i > 1; i--) {
            for (int j = 0; j < i; j++){
                if (theArray[j] > theArray[j+1]) {
                    swapValues( j , j+1);
                }
            }
        }
        endTime = System.currentTimeMillis();
        System.out.println("Bubble Sort Took: " + (endTime-startTime));
    }
    // O(logN)
    private void binarySearch(int value){

        startTime = System.currentTimeMillis();
        int lowIndex = 0;
        int highIndex = arraySize - 1;
        int timesThrough = 0;

        while (lowIndex <= highIndex){
            int middleIndex = (highIndex + lowIndex) / 2;

            if (theArray[middleIndex] < value)
                lowIndex = middleIndex + 1;
                else if(theArray[middleIndex] > value)
                    highIndex = middleIndex - 1;
                else {
                    System.out.println("Found a match in index " + middleIndex);
                    lowIndex = highIndex + 1;
            }
            timesThrough++;
        }
        endTime = System.currentTimeMillis();
        System.out.println("Binary Search Took: " + (endTime-startTime) + " milliseconds");
        System.out.println("Number of guesses : " + timesThrough);
    }
    // O(N*logN)
    private void quickSort(int left, int right){

        if(right - left <=0)
            return;

        else {
            int pivot = theArray[right];
            int pivotLoation = partitionArray(left,right,pivot);
            quickSort(left, pivotLoation - 1);
            quickSort(pivotLoation + 1, right);
        }
    }
    //Create the partition array to sort through
    private int partitionArray(int left,int right,int pivot) {
        int leftPointer = left - 1;
        int rightPointer = right;

        while (true) {
            while (theArray[++leftPointer] < pivot);

            while (rightPointer > 0 && theArray[--rightPointer] > pivot);

            if (leftPointer >= rightPointer){
                break;
            }
            else {
                swapValues(leftPointer, rightPointer);
            }
        }

        swapValues(leftPointer, right);
        return leftPointer;
    }
    // Constructor to create a new array
    BigONotation(int size) {
        arraySize = size;
        theArray = new int[size];
    }
    //Method to generate a random array
    private void generateRandomArray() {
        for (int i = 0; i < arraySize; i++) {

            theArray[i] = (int) (Math.random() * 1000) + 10;
        }

        itemsInArray = arraySize - 1;
    }
    //Swapping values for sorting
    private void swapValues (int indexOne, int indexTwo) {
        int temp = theArray[indexOne];
        theArray [indexOne] = theArray[indexTwo];
        theArray[indexTwo] = temp;
    }
}