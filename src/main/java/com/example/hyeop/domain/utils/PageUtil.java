package com.example.hyeop.domain.utils;

import java.util.ArrayList;
import java.util.List;

public class PageUtil {

    public static List<Integer> createPageList(int firstIndex, int lastIndex){
        ArrayList<Integer> result = new ArrayList<>();
        for(int i =firstIndex;i<=lastIndex;i++){
            result.add(i);
        }
        return result;
    }
}
