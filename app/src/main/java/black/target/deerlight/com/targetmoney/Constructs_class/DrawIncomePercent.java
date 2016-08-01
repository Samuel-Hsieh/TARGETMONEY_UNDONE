package black.target.deerlight.com.targetmoney.Constructs_class;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.View;

import black.target.deerlight.com.targetmoney.R;

/**
 * Created by samuel_hsieh on 2015/10/22.
 */
public class DrawIncomePercent extends View {

    private Paint[] ArrayPaint;
    double startPosition = 0;
    double endPostion = 0;
    String mColors[];
    double[] ArrayPersent;
    int i,k,round=0;
    RectF rect;
    /*** 跟背景顏色一樣＝＝ 隱藏用*/
    int myColor = getResources().getColor(R.color.AuditAccountColor);
    int mColor_item;
    int width,height;

    public DrawIncomePercent(Context context) {
        super(context);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        i=0;
        /******* 一開始清除圓餅圖（用圖蓋圖）*******/
        Paint eraser = new Paint();
        eraser.setColor(myColor);
        canvas.drawPaint(eraser);
        /******* 畫圓餅圖 ********/
        /**建立畫筆*/
        mColors = getResources().getStringArray(R.array.PieColorlist);
        ArrayPaint = new Paint[ArrayPersent.length];
        while(i < ArrayPersent.length) {
            if(i >= mColors.length){
                round=1;
            }
            for(k = round;k < mColors.length;k++){
                if(i != ArrayPersent.length){
                    ArrayPaint[i] = new Paint();
                    mColor_item = Color.parseColor(mColors[k]);
                    ArrayPaint[i].setColor(mColor_item);
                    i++;
                }
                else {
                    break;
                }
            }
        }
        /**開始畫圓*/
        rect = new RectF(0, 0, width, height);
        for (i=0;i<ArrayPersent.length;i++) {
            endPostion = ArrayPersent[i]*3.6;
            endPostion = (double)(Math.round(endPostion *100))/100;
            canvas.drawArc(rect,
                    (float) startPosition,  //開始角度
                    (float) endPostion, //結束角度
                    true, //是否使用中心
                    ArrayPaint[i]);
            startPosition = startPosition + endPostion;
        }
        /******* 畫中心空洞 *******/
        Paint paint = new Paint();
        paint.setColor(myColor);
        canvas.drawCircle(width/2,height/2,width/4,paint);
    }
    public void setArrayPersent(double[] arrayPersent) {
        ArrayPersent = arrayPersent;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
