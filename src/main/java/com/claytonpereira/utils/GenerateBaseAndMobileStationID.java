package com.claytonpereira.utils;

import java.util.ArrayList;
import java.util.List;

public class GenerateBaseAndMobileStationID {

    public static List<String> generateBaseStationID(long CountValue) {
        List<String> baseIDS = new ArrayList<>();
        for(long i = 0;i<= CountValue; i++) {
            baseIDS.add("b" + 1);
        }
        return baseIDS;
    }

    public static List<String> generateMobileStationID(long CountValue) {
        List<String> MobileIDS = new ArrayList<>();
        for(long i = 1;i<= CountValue; i++) {
            MobileIDS.add("m" + i);
        }
        return MobileIDS;
    }
}
