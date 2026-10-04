package lw03.prelab;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Problem 1
        List<String> playList = new LinkedList<>();
        List<String> filteredPlayList = new LinkedList<>();
        Scanner sc = new Scanner(Main.class.getResourceAsStream("playlist.txt"));

        while (sc.hasNextLine()) {
            String songDetail = sc.nextLine();
            playList.add(songDetail);
        }

        sc.close();

        for (int i = 0; i < playList.size(); i++){
            String[] songSplit = playList.get(i).split(" ");
            String opr = songSplit[0];
            if (opr.equals("ADD")){
                filteredPlayList.add(songSplit[1]);
            } else if (opr.equals("REMOVE")){
                filteredPlayList.remove(songSplit[1]);
            } else if (opr.equals("INSERT")){
                int index = Integer.parseInt(songSplit[1]);
                String songName = playList.get(i).substring((opr.length() + String.valueOf(index).length()) + 2);
                filteredPlayList.add(index, songName);
            }
        }

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + filteredPlayList.size());
        for (int i = 1; i <= filteredPlayList.size(); i++){
            System.out.println(i + ": " + filteredPlayList.get(i - 1));
        }

        // Problem 2
        Set<String> participants = new LinkedHashSet<>();
        int duplicateParticipant = 0;
        Scanner sc1 = new Scanner (Main.class.getResourceAsStream("participants.txt"));

        while (sc1.hasNext()) {
            String name = sc1.next();

            if (participants.contains(name)){
                duplicateParticipant++;
            } else {
                participants.add(name);
            }
        }
        sc1.close();

        System.out.println();
        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants: " + participants.size());
        int index = 1;
        for(String particapant : participants){
            System.out.println(index + ". " + particapant);
            index++;
        }
        System.out.println("Duplicate registrations: " + duplicateParticipant);

        // Problem 3
        Map<String, Integer> inventories = new LinkedHashMap<>();
        int failedSales = 0;
        Scanner sc2 = new Scanner (Main.class.getResourceAsStream("inventory.txt"));

        while (sc2.hasNext()) {
            String type = sc2.next();
            String product = sc2.next();
            int quantity = sc2.nextInt();

            if (type.equals("ADD")){
                if (inventories.containsKey(product)){
                    int newQuantity = inventories.get(product) + quantity;
                    inventories.put(product, newQuantity);
                } else {
                    inventories.put(product, quantity);
                }
            } else if (type.equals("SELL")){
                if (inventories.containsKey(product) && inventories.get(product) >= quantity){
                    int newQuantity = inventories.get(product) - quantity;
                    inventories.put(product, newQuantity);
                } else {
                    failedSales++;
                }
            }
        }
        sc2.close();

        System.out.println();
        System.out.println("===== Problem 3 =====");
        for (String inventory : inventories.keySet()){
            System.out.println(inventory + ": " + inventories.get(inventory));
        }
        System.out.println("Failed sales: " + failedSales);

    }
}
