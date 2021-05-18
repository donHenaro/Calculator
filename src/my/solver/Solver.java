package my.solver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solver {

    final private static String strPattRom="([M{0,3}(CM|CD|D?C{0,3})?(XC|XL|L?X{0,3})?(IX|IV|V?I{0,3})?]+)";
    final private static String strPattArab="([0-9]+)";
    final private static String strOper="([\\*\\/\\+\\-]{1})";
    static Pattern patternRoman = Pattern.compile(strPattRom+strOper+strPattRom);
    static Pattern patternArab=Pattern.compile(strPattArab+strOper+strPattArab);

    public static String solve(String strToSolve) {
        String ret;
        Matcher matchA = patternArab.matcher(strToSolve);
        if (matchA.matches()) {
            int r1 = Integer.parseInt(matchA.group(1));
            int r2 = Integer.parseInt(matchA.group(3));
                if(r1>10||r2>10) wrongArgument();
            ret = Solver.solve(r1, matchA.group(2), r2)+"";
            //System.out.println(r1 + matchA.group(2) + r2 + "=" + ret);
            return ret;
        }
        Matcher matchR = patternRoman.matcher(strToSolve);
        if (matchR.matches()) {
            int r1 = new Roman(matchR.group(1)).getIntValue();
            int r2 = new Roman(matchR.group(3)).getIntValue();
                if(r1>10||r2>10) wrongArgument();
            int r3 = Solver.solve(r1, matchR.group(2), r2);
            ret = new Roman(r3).toString();
            //System.out.println(r1 + matchR.group(2) + r2 + "=" + r3);
            return ret;
        }

            throw new IllegalArgumentException("Ошибка во введенном выражении");
    }

    private static void wrongArgument(){
        throw new IllegalArgumentException("Аргумент превышает допустимый уровень (>10)." +
                "\n К сожалению постановщики задачи оганичили допустимый размер аргументов");
    }

    private static int solve(int a, String operation,  int b){
        int ret=0;
            switch (operation){
                case ("+"):
                  ret=a+b;
                break;
                case ("-"):
                    ret=a-b;
                break;
                case ("*"):
                    ret=a*b;
                break;
                case ("/"):
                    ret=a/b;
                break;
            }
        return ret;
    }

}
