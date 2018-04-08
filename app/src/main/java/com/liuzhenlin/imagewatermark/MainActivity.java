package com.liuzhenlin.imagewatermark;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import static com.liuzhenlin.imagewatermark.DensityUtil.dp2px;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = getApplicationContext();

        Bitmap srcBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        Bitmap stretchedSrcBitmap = ImageUtil.scaleWithWH(srcBitmap,
                dp2px(context, 200), dp2px(context, 200));
        Bitmap pictureWatermarkBitmap = ImageUtil.createWatermarkCenter(stretchedSrcBitmap,srcBitmap);
        Bitmap textWatermarkBitmap = ImageUtil.drawTextToRightBottom(context,
                stretchedSrcBitmap, "ImageWatermark", 16, Color.WHITE, 25, 25);

        ImageView pictureWatermarkImage = findViewById(R.id.image_pictureWatermark);
        ImageView textWatermarkImage = findViewById(R.id.image_textWatermark);
        pictureWatermarkImage.setImageBitmap(pictureWatermarkBitmap);
        textWatermarkImage.setImageBitmap(textWatermarkBitmap);
    }
}
