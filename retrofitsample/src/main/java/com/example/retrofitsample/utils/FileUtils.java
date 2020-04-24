package com.example.retrofitsample.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileUtils {

    /**
     * 检查外部存储是否可用
     *
     * @return
     */
    private static   boolean isExternaStrongeWriteAble() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /**
     * 向外部存储写入字符串
     */

    public static void writeStringToFile(Context context, String folderName, String fileName, String content) {
        if (!isExternaStrongeWriteAble()) {
            return;
        }

        File dir = context.getExternalFilesDir(folderName);
        Log.e("yulh","===="+dir.getAbsolutePath());

        if (!dir.exists()) {
            dir.mkdir();
        }

        File file = new File(dir, fileName);
        if (file.exists()) {
            file.delete();
        }

        FileOutputStream fos = null;
        BufferedOutputStream bos = null;

        try {
            file.createNewFile();
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);

            bos.write(content.getBytes());

            bos.close();
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }

                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 向外部存储写入图片数据
     *
     * @param context    上下文对象
     * @param folderName 文件夹名称
     * @param fileName   文件名
     * @param bitmap     图片
     */
    public static void writerBitmapToFile(Context context, String folderName, String fileName, Bitmap bitmap) {
        if (!isExternaStrongeWriteAble()) {
            return;
        }

        File dir = context.getExternalFilesDir(folderName);

        if (!dir.exists()) {
            dir.mkdir();
        }

        File file = new File(dir, fileName);
        if (file.exists()) {
            file.delete();
        }

        FileOutputStream fos = null;
        BufferedOutputStream bos = null;


        try {
            file.createNewFile();

            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);

            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);

            bos.flush();

            bos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }

                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 向外部存储读取文本
     *
     * @param context  上下文对像
     * @param fileName 文件名
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String readStringToFile(Context context, String fileName) {
        if (!isExternaStrongeWriteAble()) {
            return null;
        }

        File dir = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);

        if (!dir.exists()) {
            return null;
        }

        File file = new File(dir, fileName);

        if (!file.exists()) {
            return null;
        }

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;


        StringBuffer sb = new StringBuffer();

        try {
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);

            sb.append(br.readLine());

            while (br.readLine() != null) {
                sb.append("\n" + br.readLine());
            }

            br.close();
            isr.close();
            fis.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (isr != null) {
                    isr.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }

    /**
     * 向外部存储读取图片
     * @param context  上下文对象
     * @param fileName  文件名
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static Bitmap readBitmapToFile(Context context, String fileName) {
        if (!isExternaStrongeWriteAble()) {
            return null;
        }

        File dir = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);

        if (!dir.exists()) {
            return null;
        }

        File file = new File(dir, fileName);

        if (!file.exists()) {
            return null;
        }

        FileInputStream fis = null;
        BufferedInputStream bis = null;
        Bitmap bitmap = null;

        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);

            bitmap = BitmapFactory.decodeStream(bis);

            bis.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(bis!=null){
                    bis.close();
                }
                if(fis!=null){
                    fis.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        return bitmap;
    }


    public static String readToFile(Context context,String type,String fileName) throws IOException {
        StringBuilder sb=new StringBuilder();

        File file=new File(context.getExternalFilesDir(type)+"/"+fileName);

        //打开文件输入流
        FileInputStream fis=new FileInputStream(file);
        byte[] bytes=new byte[1024];
        int len=fis.read(bytes);

        while (len>0){
            sb.append(new String(bytes,0,len));

            //继续将数据放到bytes
            len=fis.read(bytes);
        }

        fis.close();
        return sb.toString();
    }
}
