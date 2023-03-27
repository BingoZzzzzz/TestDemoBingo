package com.bingo.myapplication;

import java.text.Collator;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * User  ：Bingo
 * Time：2023-03-28
 * Annotations：
 */
public class ComparatoreUtils {
    private final String DIGIT_REGEX = "[0-9]+";
    private final String LETTER_REGEX = "[a-zA-Z]+";
    private final String CHINESE_REGEX = "[\u4e00-\u9fa5]";

    private int min;
    private int position;

    public ComparatoreUtils() {
        position = 0;
    }

    public int getValues(String o1, String o2) {
        min =Math.min(o1.length(),o2.length());
        String o1Compare="",o2Compare ="";
        if(position<min){
            o1Compare = o1.charAt(position)+"";
            o2Compare = o2.charAt(position)+"";
        }else{
            return o1.length()>o2.length()?1:-1;
        }

        if(o1Compare.equals(o2Compare)){
            position++;
            return getValues(o1,o2);
        }

        boolean o1Chines = Pattern.compile(CHINESE_REGEX).matcher(o1Compare).matches();
        boolean o2Chines = Pattern.compile(CHINESE_REGEX).matcher(o2Compare).matches();
        if (o1Chines && o2Chines) {
            return Collator.getInstance(Locale.CHINA).compare(o1, o2);
        }

        boolean o1Letter = Pattern.compile(LETTER_REGEX).matcher(o1Compare).matches();
        boolean o2Letter = Pattern.compile(LETTER_REGEX).matcher(o2Compare).matches();
        if (o1Letter && o2Letter) {
            return Collator.getInstance(Locale.CHINA).compare(o1, o2);
        }

        boolean o1Digit = Pattern.compile(DIGIT_REGEX).matcher(o1Compare).matches();
        boolean o2Digit = Pattern.compile(DIGIT_REGEX).matcher(o2Compare).matches();
        if (o1Digit && o2Digit) {
            return Collator.getInstance(Locale.CHINA).compare(o1, o2);
        }

        if (o1Chines) {
            return -1;
        }
        if (o2Chines) {
            return 1;}

        if (o1Letter) {
            return -1;
        }
        if (o2Letter) {
            return 1;
        }
        if (o1Digit) {
            return -1;
        }
        if (o2Digit) {
            return 1;
        }
        return -1;
    }
}
