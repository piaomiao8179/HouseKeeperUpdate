package com.example.admin.housekeeper.dao;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by admin on 2016/7/13.
 */
public class AssetsDBManager {
    public static void copyAssetsFileToFile(Context context, String path, File toFile) throws IOException {
        InputStream is = null;
        BufferedOutputStream bos = null;
        BufferedInputStream bis =null;
        is = context.getAssets().open(path);
        bis = new BufferedInputStream(is);
        bos = new BufferedOutputStream(new FileOutputStream(toFile,false));
        byte [] buff = new byte[1024];
        int len = 0;
        while((len = is.read(buff)) != -1){
            bos.write(buff,0,len);
        }
        is.close();
        bis.close();
        bos.close();
    }
}
