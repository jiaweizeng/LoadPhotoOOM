package cn.hzjk.loadphotooom;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Bitmap mBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageView iv = findViewById(R.id.iv_load);
        Button btn = findViewById(R.id.btn_load);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*mBitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().
                        getPath() + "/IMG_2509.JPG");*/
                mBitmap = getMySampleBitmap(Environment.getExternalStorageDirectory().
                        getPath() + "/IMG_2509.JPG",iv.getWidth(),iv.getHeight());
                iv.setImageBitmap(mBitmap);

            }
        });
}
    /**   设置采样率，防止图片太大，解析OOM
     *
     * @param file_path 图片路径
     * @return
     */
    public  Bitmap getMySampleBitmap(String file_path, int width, int height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file_path, options);
        options.inSampleSize = getMySampleSize(width, height, options);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(file_path, options);
    }
    private  int getMySampleSize(int reqWidth, int reqHeight, BitmapFactory.Options options) {
        int inSampleSize = 1;
        if (options.outWidth > reqWidth || options.outHeight > reqHeight) {
            int widthRatio = Math.round((float) options.outWidth / (float) reqWidth);
            int heightRatio = Math.round((float) options.outHeight / (float) reqHeight);
            inSampleSize = Math.min(widthRatio, heightRatio);
        }
        return inSampleSize;
    }
}
