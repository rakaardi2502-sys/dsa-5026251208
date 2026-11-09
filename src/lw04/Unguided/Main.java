package lw04.Unguided;

import java.util.Scanner;

public class Main {

    public static void sortCards (int[] arr, int passes){
        int n = arr.length;
        for (int i = 0; i < passes; i++){
            int min_numIdx = i;
            for (int j = i + 1; j < n; j++){
                if (arr[min_numIdx] > arr[j]){
                    min_numIdx = j;
                }
            }
            int temp = arr[min_numIdx];
            arr[min_numIdx] = arr[i];
            arr[i] = temp;
        }
    }

    public static void findCards (int[] arr, int target){
        for (int t : arr){
            boolean isFound = false;
            for (int i = 0; i < numList.length; i++){
                if (t == numList[i]){
                    System.out.println(target + ": FOUND at index " + i);
                    isFound = true;
                    break;
                } else {
                    isFound = false;
                }
            }
            
            if (!isFound){
                System.out.println(target + ": NOT FOUND");
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner (Main.class.getResourceAsStream("cards2.txt"));

        int total_nums = sc.nextInt();
        int[] numList = new int[total_nums];
        for (int i = 0; i < total_nums; i++){
            numList[i] = sc.nextInt();
        }
        
        int targets = sc.nextInt();
        int[] targetList = new int[targets];
        for (int i = 0; i < targets; i++){
            targetList[i] = sc.nextInt();
        }

        sortCards(numList, 2);

        for (int n : numList){
            System.out.print(n + " ");
        }
        
        System.out.println();

        findCards(numList, targets);
    }
}
