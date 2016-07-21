package com.example.admin.housekeeper.db;

import java.io.File;

/**
 * Created by admin on 2016/7/14.
 */
public class DBReader {
    public static File telFile;
    static {
        String packageName = "data/data/com.example.admin.housekeeper";
        telFile = new File(packageName,"commonnum.db");
    }

    public static boolean isExsitDBFile(){
        if (!telFile.exists() || telFile.length()<= 0){
            return false;
        }
        return true;
    }


}
