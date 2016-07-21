package com.example.admin.housekeeper.dao;

import android.app.ActivityManager;
import android.content.Context;
import android.hardware.Camera;
import android.os.Build;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/7/20.
 */
public class GetPhoneInfoDao {
    public Context  mContext;
    public String   mBrand;
    public String   mVersion;
    public String[] mCpuName;
    public int      mWidthPixels;
    public int      mHeightPixels;
    public String   mRadioVersion;
    public int      mCpuNumber;
    public long     mMemSize;
    public String   mTotalMemSize;
    public long     mAvailMem;
    public String   mAvailMemSize;
    private static final String TAG = "GetPhoneInfoDao";

    public GetPhoneInfoDao(Context context) {
        mContext = context;
    }

    //获取设备名称和系统版本号
    public void getDeviceInfo(){
        mBrand = Build.BRAND;
        mVersion = Build.VERSION.RELEASE;

    }

    //获取运行总内存和空闲内存
    public void getMemory(){
        try {
            FileReader reader = new FileReader("/proc/meminfo");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String text = bufferedReader.readLine();
            String[] split = text.split("\\s+");
            mMemSize = Long.valueOf(split[1]) * 1024;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mTotalMemSize = Formatter.formatFileSize(mContext, mMemSize);

        ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
        ActivityManager am = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        am.getMemoryInfo(info);
        mAvailMem = info.availMem;
        mAvailMemSize = Formatter.formatFileSize(mContext, mAvailMem);

    }


    //获取CPU的名称及个数
    public void getCpu(){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            mCpuName = Build.SUPPORTED_ABIS;
            Log.d(TAG, "getCpu: " + Arrays.toString(mCpuName));
        }
        try {
            File dir = new File("/sys/devices/system/cpu/");
            File[] files = dir.listFiles(new CpuFilter());
            mCpuNumber = files.length;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //获取手机分辨率
    public void getMetricx() {
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        mWidthPixels = dm.widthPixels;
        mHeightPixels = dm.heightPixels;

    }

    //获取相机分辨率
    public String getCameraMetrics() {
        Camera camera = Camera.open();
        Camera.Parameters parameters = camera.getParameters();
        List<Camera.Size> sizes = parameters.getSupportedPictureSizes();
        Camera.Size s = null;
        for (Camera.Size size : sizes) {
            if(s == null){
                s = size;
            } else if(s.width * s.height < size.width * size.height){
                s = size;
            }
        }
        camera.release();
        return s.width + "*" + s.height;
    }

    //获取基带版本
    public void getRadio() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
//            mRadioVersion = Build.getRadioVersion();
//            Log.d(TAG, "getRadio: " + mRadioVersion);
//        }
//       mRadioVersion = Build.RADIO;
        try {

            Class cl = Class.forName("android.os.SystemProperties");

            Object invoker = cl.newInstance();

            Method m = cl.getMethod("get", new Class[] { String.class,String.class });

            mRadioVersion = (String) m.invoke(invoker, new Object[]{"gsm.version.baseband", "no message"});



        } catch (Exception e) {

        }
    }

    //是否root
    //如果root了，则会生成一个单独的文件   system/bin/su  system/xbin/su
    public boolean getIsRoot(){
        boolean isRoot = false;
        isRoot = !(!new File("system/bin/su").exists() && !new File("system/xbin/su").exists());
        return isRoot;
    }

    //文件过滤器
    class CpuFilter implements FileFilter {
        public boolean accept(File pathname) {
            if (Pattern.matches("cpu[0-9]", pathname.getName())) {
                return true;
            }
            return false;
        }
    }


}
