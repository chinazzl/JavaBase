package langer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Mather {
    public static void main(String[] args) {
        String regex = ",";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher("水水水水水,");
        System.out.println(matcher.find());

    }


}
