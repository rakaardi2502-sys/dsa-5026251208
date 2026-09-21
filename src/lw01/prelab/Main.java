package lw01.prelab;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        List<PrintJob> printJobs = new ArrayList<>();

        Scanner scanner = new Scanner(Main.class.getResourceAsStream("jobs.txt"));

        while (scanner.hasNext()){
            String type = scanner.next();
            String id = scanner.next();
            int pages = scanner.nextInt();

            if (pages < 0){
                throw new IllegalArgumentException();
            }

            if (type.equals("MONO")){
                printJobs.add(new MonoPrint(id, pages));
            } else{
                printJobs.add(new ColourPrint(id, pages));
            }
        }

        for (PrintJob job : printJobs){
            System.out.println(job.summary());
        }

        scanner.close();
    }
}
