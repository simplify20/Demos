package com.example.yjj.databinding.utils;

/**
 * @author:YJJ
 * @date:2016/3/8
 * @email:yangjianjun@117go.com
 */
public class StringUtil {
    /**
     * 首字母大写
     * @param word
     * @return
     */
    public static String capitalize(final String word) {
        if (word.length() > 1) {
            return String.valueOf(word.charAt(0)).toUpperCase() + word.substring(1);
        }
        return word;
    }
}
