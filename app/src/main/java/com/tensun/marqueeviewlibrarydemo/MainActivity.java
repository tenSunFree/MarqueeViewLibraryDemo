package com.tensun.marqueeviewlibrarydemo;

import android.os.Bundle;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gongwen.marqueen.MarqueeFactory;
import com.gongwen.marqueen.MarqueeView;
import com.gongwen.marqueen.SimpleMF;
import com.gongwen.marqueen.SimpleMarqueeView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageView yellowSpeaker;
    private SimpleMarqueeView marqueeView3;
    private MarqueeView marqueeView4;

    private int[] imageIds = {                                                                      // 自定義的圖片數組
            R.drawable.a_01,
            R.drawable.b_01,
            R.drawable.c_01,
            R.drawable.d_01,
            R.drawable.e_05,
            R.drawable.f_01,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        yellowSpeaker = (ImageView) findViewById(R.id.yellowSpeaker);
        marqueeView3 = (SimpleMarqueeView) findViewById(R.id.marqueeView3);
        marqueeView4 = (MarqueeView) findViewById(R.id.marqueeView4);

        /**
         * 目的: 將yellowSpeaker 變換顏色, 如果有想要讓圖片呈現對比效果的話, 也可以使用此方法改變顏色來呈現對比
         * setTint(): 对 drawable 对象着色处理
         * wrap(): 为了在不同的系统 API 上使用setTint(), 必须使用这个方法处理现有的 drawable 对象
         * mutate(): 如果沒有使用mutate(), 应用中所有使用到该图片资源的地方，都会显示成被着色处理过的样式
         */
        DrawableCompat.setTint(                                                                       // 進行著色處理
                DrawableCompat.wrap(yellowSpeaker.getDrawable().mutate()),                          // 取得yellowSpeaker的Drawable對象, 再透過mutate() 設定成唯一修改, 再透過wrap() 解決不同系統API的問題
                getResources().getColor(R.color.yellow)                                              // 想要變換的顏色
        );

        initMarqueeView3();
        initMarqueeView4();
    }

    /** Html.fromHtml(): 可以使用HTML的語法 來呈現想要的內容 */
    private void initMarqueeView3() {
        SimpleMF<Spanned> marqueeFactory3 = new SimpleMF<>(MainActivity.this);
        List<Spanned> datas3 = new ArrayList<>();                                                   // Spanned類型, 才可以接受Html.fromHtml() 的產物
        datas3.add(Html.fromHtml("<em><b><font color=\"#2A3457\"> 新世紀福音戰士 </font></b></em>"));
        datas3.add(Html.fromHtml("<em><b><font color=\"#16235A\"> 化物語 </font></b></em>"));
        datas3.add(Html.fromHtml("<em><b><font color=\"#2A3457\"> 機動戰士高達 0079 </font></b></em>"));
        datas3.add(Html.fromHtml("<em><b><font color=\"#16235A\"> 機動戰士高達 Seed Destiny </font></b></em>"));
        datas3.add(Html.fromHtml("<em><b><font color=\"#2A3457\"> 魔法少女小圓 </font></b></em>"));
        datas3.add(Html.fromHtml("<em><b><font color=\"#16235A\"> 涼宮春日的憂鬱 </font></b></em>"));

        marqueeFactory3.setData(datas3);                                                            // 把自定義的資料集合, 放入marqueeFactory2
        marqueeView3.setMarqueeFactory(marqueeFactory3);
        marqueeFactory3.setOnItemClickListener(                                                     // 對marqueeView3 點擊監聽
                new MarqueeFactory.OnItemClickListener<TextView, Spanned>() {
            @Override
            public void onItemClickListener(MarqueeFactory.ViewHolder<TextView, Spanned> holder) {
                Toast.makeText(MainActivity.this, holder.data, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initMarqueeView4() {
        List<ComplexItemEntity> complexDatas = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            complexDatas.add(new ComplexItemEntity(imageIds[i]));
        }
        MarqueeFactory<RelativeLayout, ComplexItemEntity> marqueeFactory = new ComplexViewMF(MainActivity.this);
        marqueeFactory.setOnItemClickListener(                                                      // // 對marqueeView4 點擊監聽
                new MarqueeFactory.OnItemClickListener<RelativeLayout, ComplexItemEntity>() {
            @Override
            public void onItemClickListener(MarqueeFactory.ViewHolder<RelativeLayout, ComplexItemEntity> holder) {
                Toast.makeText(MainActivity.this, holder.data.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        marqueeFactory.setData(complexDatas);
        marqueeView4.setInAndOutAnim(R.anim.in_left, R.anim.out_right);                                 // 設置進場 出場的動畫
        marqueeView4.setAnimDuration(3000);                                                         // 設置动画执行时间
        marqueeView4.setMarqueeFactory(marqueeFactory);
    }

    /**
     * startFlipping(), 表示 开始浏览
     */
    @Override
    public void onStart() {
        super.onStart();
        Log.v("more", "onStart()");
        marqueeView3.startFlipping();
        marqueeView4.startFlipping();
    }

    /**
     * stopFlipping(), 表示 停止浏览
     */
    @Override
    public void onStop() {
        super.onStop();
        Log.v("more", "onStop()");
        marqueeView3.stopFlipping();
        marqueeView4.stopFlipping();
    }
}
