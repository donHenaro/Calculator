package my.solver;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Roman {
    public final static String strPattern="^M{0,3}(CM|CD|D?C{0,3})?(XC|XL|L?X{0,3})?(IX|IV|V?I{0,3})?$";
    private final static Pattern pattern=Pattern.compile(strPattern);
    private static Map<String, Integer> map = new HashMap<>();
    static {
         map.put("I", 1); map.put("II", 2);map.put("III", 3);map.put("IV", 4);map.put("V", 5);
         map.put("VI", 6);map.put("VII", 7);map.put("VIII", 8);map.put("IX", 9);

         map.put("X", 10); map.put("XX", 20);map.put("XXX", 30);map.put("XL", 40);map.put("L", 50);
         map.put("LX", 60); map.put("LXX", 70); map.put("LXXX", 80); map.put("XC", 90);

         map.put("C", 100); map.put("CC", 200); map.put("CCC",300); map.put("CD",400); map.put("D",500);
         map.put("DC",600); map.put("DCC",700); map.put("DCCC",800); map.put("CM",900);

         map.put("M",1000); map.put("MM",2000); map.put("MMM",3000);
    }

    private String strValue;
    private int intValue;

    public Roman(String strValue) {
        setStrValue(strValue);
    }

    public Roman(int intValue) {
        setIntValue(intValue);
    }

    public int getIntValue() {
        return intValue;
    }

    public void setStrValue(String strValue) {
        this.strValue = strValue;
        Matcher match = pattern.matcher(strValue);
        int intTemp=0;
        if(match.matches()) {
            for (int i = 1; i < match.groupCount()+1; i++) {
                intTemp+=map.get(match.group(i))==null?0:map.get(match.group(i));
            }
        }
        this.intValue=intTemp;
    }

    public void setIntValue(int intValue) {
        boolean subzero=false;
        this.intValue = intValue;
        if(intValue<0) {
            subzero=true;
            intValue*=-1;
        }

        StringBuilder sb=new StringBuilder();
        for (int intExt = 1;intValue>0;intValue/=10, intExt*=10){
            for (Map.Entry<String, Integer> val:map.entrySet()) {
                if((intValue%10)*intExt==val.getValue()){
                    sb.insert(0,val.getKey());
                    break;
                }
            }
         }
        if(subzero) sb.insert(0,"-");

        this.strValue=sb.toString();
    }

    @Override
    public String toString() {
        return strValue;
    }

    @Override
    public int hashCode() {
        return intValue;
     }

}


