package com.bagelplay.flowerfish.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bagelplay.flowerfish.R;

import static android.R.attr.scaleHeight;
import static android.R.attr.scaleWidth;
import static android.R.attr.width;

/**
 * Created by zhangtianjie on 2017/7/28.
 */

public class FlowerView extends FrameLayout {
    String Tag = "FlowerView";

    private ImageView iv1, iv2, iv3, iv4, iv5;


    Bitmap[] mFlowerBitmaps;


    ImageView[] mFlowerImageViews;


    int mFlowerBitmapsSelect[];

    int flowerSize = 5;


    public FlowerView(Context context) {
        super(context);
    }

    public FlowerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.flower_view_layout, this, true);

        mFlowerBitmaps = new Bitmap[flowerSize];
        mFlowerBitmapsSelect = new int[flowerSize];
        mFlowerImageViews = new ImageView[flowerSize];


        iv1 = (ImageView) findViewById(R.id.im_flower1);
        iv2 = (ImageView) findViewById(R.id.im_flower2);
        iv3 = (ImageView) findViewById(R.id.im_flower3);
        iv4 = (ImageView) findViewById(R.id.im_flower4);
        iv5 = (ImageView) findViewById(R.id.im_flower5);

        mFlowerImageViews[0] = iv1;
        mFlowerImageViews[1] = iv2;
        mFlowerImageViews[2] = iv3;
        mFlowerImageViews[3] = iv4;
        mFlowerImageViews[4] = iv5;

        mFlowerBitmapsSelect[0] = R.drawable.newflower_1_1;
        mFlowerBitmapsSelect[1] = R.drawable.newflower_2_1;
        mFlowerBitmapsSelect[2] = R.drawable.newflower_3_1;
        mFlowerBitmapsSelect[3] = R.drawable.newflower_4_1;
        mFlowerBitmapsSelect[4] = R.drawable.newflower_5_1;

        for (int i = 0; i < mFlowerImageViews.length; i++) {


            Bitmap resizedBitmap = getDecodeBitmap(context, mFlowerBitmapsSelect[i]);


            mFlowerBitmaps[i] = resizedBitmap;


        }


        iv1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                // TODO Auto-generated method stub


                for (int i = 0; i < mFlowerBitmaps.length; i++) {

                    if (mFlowerBitmaps[i].getPixel((int) (arg1.getX()), ((int) arg1.getY())) == 0) {
                        Log.i(Tag, "图" + i + "透明区域");


                        continue;

                    } else {

                        Log.i(Tag, "图" + i + "实体区域");

                        mFlowerImageViews[i].setImageResource(mFlowerBitmapsSelect[i]);


                        return true;
                    }


                }


                return true;


            }
        });
    }

    private Bitmap getDecodeBitmap(Context context, int id) {
        Bitmap bitmapOrg = BitmapFactory.decodeResource(getResources(),
                id);

        int width = bitmapOrg.getWidth();
        int height = bitmapOrg.getHeight();


        int w = getResources().getDimensionPixelSize(R.dimen.FlowerImangeViewHeight);

        int h = getResources().getDimensionPixelSize(R.dimen.FlowerImangeViewWidth);

        // Log.d(Tag,w+"~"+h);

        w = Px2Dp(context, w);

        h = Px2Dp(context, h);


        int newWidth = dip2px(context, w);
        int newHeight = dip2px(context, h);


        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        return Bitmap.createBitmap(bitmapOrg, 0, 0,
                width, height, matrix, true);
    }


    public int Px2Dp(Context context, float px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    public FlowerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FlowerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


}
