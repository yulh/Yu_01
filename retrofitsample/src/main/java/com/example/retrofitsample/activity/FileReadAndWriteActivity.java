package com.example.retrofitsample.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.retrofitsample.R;
import com.example.retrofitsample.databinding.ActivityFileAndWriteReadBinding;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileReadAndWriteActivity extends AppCompatActivity {
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityFileAndWriteReadBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_file_and_write_read);
        binding.setFileReadActivity(this);

        mContext = this;
    }

    /**
     * 向外不存储写入字符串
     */
    public void writeStringToFile(String str) {
        if (!isExternalStroageWiterable()) {
            return;
        }

        File dir = getExternalFilesDir("text");
        Log.e("writeStringToFile", "writeStringToFile: dir = " + dir.getAbsolutePath());

        if (!dir.exists()) {
            dir.mkdir();
        }

        File file = new File(dir, "str.txt");
        if (file.exists()) {
            file.delete();
        }

        FileOutputStream fos = null;
        BufferedOutputStream bos = null;


        try {
            file.createNewFile();
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);

            bos.write(str.getBytes());

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
     * 向外不存储写入字符串
     */

    public void writeStringToFile(Bitmap bitmap) {
        if (!isExternalStroageWiterable()) {
            return;
        }

        File dir = getExternalFilesDir("text");
        Log.e("writeStringToFile", "===" + dir.getAbsolutePath());

        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(dir, "str.txt");
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


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String readStringFromFile() {
        if (!isExternalStroageWiterable()) {
            return null;
        }

        File dir = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        Log.e("readStringFromFile", "readStringFromFile: dir = " + dir.getAbsolutePath());

        if (!dir.exists()) {
            return null;
        }

        File file = new File(dir, "str.txt");
        if (!file.exists()) {
            return null;
        }

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        StringBuffer sb = new StringBuffer();
        try {
            fis = new FileInputStream(file); //通过字节流获取
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);


            String line;
            sb.append(br.readLine());
            while ((line = br.readLine()) != null) {
                sb.append("\n" + line);
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
     * 外部存储读取图片
     */

    public Bitmap readBitmapFromFile() {

        if (!isExternalStroageWiterable()) {
            return null;
        }

        File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        Log.e("readdBitmapFormFile", "" + dir.getAbsolutePath());
        if (!dir.exists()) {
            return null;
        }

        File file = new File(dir, "ic.png");
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }

                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return bitmap;
    }

    /**
     * 从文件读取数据
     * @param context
     * @return
     * @throws IOException
     */

    public String readFile(Context context) throws IOException {
        StringBuilder sb = new StringBuilder("");
        File file = new File(context.getExternalFilesDir("voice") + "/voive.pcm");
        //打开文件输入流
        FileInputStream inputStream = new FileInputStream(file);
        byte[] buffer = new byte[1024];
        int len = inputStream.read(buffer);
        //读取文件内容

        while (len > 0) {
            sb.append(new String(buffer, 0, len));

            //继续将数据放到buffer中
            len = inputStream.read(buffer);
        }

        inputStream.close();
        return sb.toString();
    }

    /**
     * 检查外部存储状态
     */

    public boolean isExternalStroageWiterable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        Log.e("Retrofit-isStroage", state + "");
        return false;
    }

}
