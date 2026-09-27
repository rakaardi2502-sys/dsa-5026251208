package lw02.prelab;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Scanner;


public class Main {
    
    public static void main(String[] args) {
        LinkedList<String[]> transactions = new LinkedList<>();
        LinkedList<String[]> customerDatas = new LinkedList<>();
        Queue<String[]> processes = new LinkedList<>();
        Stack<String[]> failTrans = new Stack<>();
        
        Scanner sc = new Scanner(Main.class.getResourceAsStream("transactions.txt"));

        while (sc.hasNext()){
            String name = sc.next();
            String type = sc.next();
            int amount = sc.nextInt();

            String[] transaction = new String[3];
            transaction[0] = name;
            transaction[1] = type;
            transaction[2] = String.valueOf(amount);

            transactions.add(transaction);

            boolean exist = false;
            for (String[] cust : customerDatas){
                if (cust[0].equals(name)){
                    exist = true;
                    break;
                }
            }

            if (!exist){
                String[] custEntry = new String[3];
                custEntry[0] = name;
                custEntry[1] = "BALANCE";
                custEntry[2] = "0";
                customerDatas.add(custEntry);
            }
        }

        while (!transactions.isEmpty()){
            processes.add(transactions.removeFirst());
        }

        for (String[] process : processes){
            for (String[] cust : customerDatas){
                if (cust[0].equals(process[0])){
                    int saldoBaru = 0;
                    int saldoLama = Integer.parseInt(cust[2]);
                    if (process[1].equals("DEPOSIT")){
                        saldoBaru = saldoLama + Integer.parseInt(process[2]);

                        cust[2] = String.valueOf(saldoBaru);
                        process[2] = String.valueOf(saldoBaru);
                    } else if(process[1].equals("WITHDRAW")){
                        if (saldoLama >= Integer.parseInt(process[2])){
                            saldoBaru = saldoLama - Integer.parseInt(process[2]);
                            process[2] = String.valueOf(saldoBaru);
                            cust[2] = String.valueOf(saldoBaru);
                        } else{
                            failTrans.add(0, process);
                        }
                    }
                    break;
                }
            }
        }

        System.out.println("=== Final Balances ===");
        for (String[] data : customerDatas){
            for (String word : data){
                System.out.print(word + " ");
            }
            System.out.println();
        }
        
        System.out.println();
        System.out.println("=== Failed Transactions ===");
        for (String[] failTran : failTrans){
            for (String word : failTran){
                System.out.print(word + " ");
            }
            System.out.println();
        }

        sc.close();
    }
}
