package lw04.prelab;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner (Main.class.getResourceAsStream("branches.txt"));

        int branches = sc.nextInt();
        int[] numList = new int[branches];
        for (int i = 0; i < branches; i++){
            numList[i] = sc.nextInt();
        }
        
        int targets = sc.nextInt();
        int[] targetList = new int[targets];
        for (int i = 0; i < targets; i++){
            targetList[i] = sc.nextInt();
        }

        int maxSort = 4;
        for (int i = 0; i < maxSort - 1; i++){
            for (int j = i + 1; j < maxSort; j++){
                if (numList[i] < numList[j]){
                    int temp = numList[i];
                    numList[i] = numList[j];
                    numList[j] = temp;
                }
            }
        }

        for (int n : numList){
            System.out.print(n + " ");
        }
        
        System.out.println();

        for (int target : targetList){
            boolean isFound = false;
            for (int i = 0; i < numList.length; i++){
                if (target == numList[i]){
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
}
