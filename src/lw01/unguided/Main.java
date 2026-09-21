package lw01.unguided;

import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        Rental[] rentals = new Rental[4];

        Scanner sc = new Scanner(Main.class.getResourceAsStream("rental.txt"));

        int testCase = sc.nextInt();

        for (int i = 0; i < testCase; i++){

            String type = sc.next();
            String id = sc.next();
            int days = sc.nextInt();
            int units = sc.nextInt();

            if (type.equals("LAPTOP")){
                rentals[i] = new LaptopRental(id, days, units);
            } else if (type.equals("PROJECTOR")){
                rentals[i] = new ProjectorRental(id, days, units);
            }
            
        }

        for (Rental rental : rentals){
            System.out.println(rental.summary());
        }
    }
}
