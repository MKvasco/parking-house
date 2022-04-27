package sk.stuba.fei.uim.vsa.pr1b;

import sk.stuba.fei.uim.vsa.pr1b.entities.*;

import java.util.Date;

public class Project1B {

    public static void main(String[] args) {
        startCLI();
    }

    public static void startCLI(){
            System.out.println("###########################################");
        System.out.println("        Welcome to parking system!");
            System.out.println("###########################################");
        while(true){
            System.out.println("Please pick a method!");
            System.out.println(
                    "1. CREATE\n"+
                    "2. GET\n" +
                    "3. DELETE\n" +
                    "4. QUIT"
            );
            break;
        }
    }

}
