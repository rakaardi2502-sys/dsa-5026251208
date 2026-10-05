package lw03.Unguided;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Map <String, Integer> enrollments = new LinkedHashMap<>();
        List <String> checkEnroll = new LinkedList<>();
        int rejectedOpr = 0;
        Scanner sc = new Scanner(Main.class.getResourceAsStream("enrollment.txt"));

        while (sc.hasNext()) {
            String type = sc.next();

            if (type.equals("CHECK")){
                String course = sc.next();
                if (!enrollments.containsKey(course)){
                    checkEnroll.add(course + ": Not Found");
                    rejectedOpr++;
                } else {
                    checkEnroll.add(course + " " + enrollments.get(course) + " students");
                }
            } else {
                String course = sc.next();
                int student = sc.nextInt();
                if (type.equals("REGISTER")){
                    if (enrollments.containsKey(course)){
                        int newNum = enrollments.get(course) + student;
                        enrollments.put(course, newNum);
                    } else {
                        enrollments.put(course, student);
                    }
                } else if (type.equals("WITHDRAW")){
                    if (enrollments.containsKey(course) && enrollments.get(course) >= student){
                        int newNum = enrollments.get(course) - student;
                        
                        enrollments.put(course, newNum);
                    } else {
                        rejectedOpr++;
                    }
                }
            }
        }
        sc.close();

        System.out.println("===== Enrollment Checks =====");
        for (String check : checkEnroll){
            System.out.println(check);
        }
    
        System.out.println();
        System.out.println("===== Final Enrollment =====");
        for (String course : enrollments.keySet()){
            System.out.println(course + ": " + enrollments.get(course) + " students");
        }

        System.out.println();
        System.out.println("Rejected operations: " + rejectedOpr);
    }
}
