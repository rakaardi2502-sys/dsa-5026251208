package lw01.prelab;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        List<PrintJob> printJobs = new ArrayList<>();

        Scanner sc = new Scanner(Main.class.getResourceAsStream("jobs.txt"));

        while (sc.hasNext()){
            String type = sc.next();
            String id = sc.next();
            int pages = sc.nextInt();

            if (type.equals("MONO")){
                printJobs.add(new MonoPrint(id, pages));
            } else{
                printJobs.add(new ColourPrint(id, pages));
            }
        }

        for (PrintJob job : printJobs){
            System.out.println(job.summary());
        }
    }
}
