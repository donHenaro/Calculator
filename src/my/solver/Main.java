package my.solver;

import java.util.Scanner;
import static my.solver.Solver.*;

public class Main {

    public static void main(String[] args) {
        String str;
        while(true){
            System.out.print("Ведите выражение: ");
            str = new Scanner(System.in).nextLine();
            if(str.equals("exit")) break;
            str=str.replaceAll("\\s+","");
            System.out.println(solve(str));
        }
    }
}
