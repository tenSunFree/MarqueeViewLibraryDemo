package com.tensun.marqueeviewlibrarydemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.gongwen.marqueen.MarqueeFactory;

public class ComplexViewMF extends MarqueeFactory<RelativeLayout, ComplexItemEntity> {
    private LayoutInflater inflater;

    public ComplexViewMF(Context mContext) {
        super(mContext);
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RelativeLayout generateMarqueeItemView(ComplexItemEntity data) {
        RelativeLayout mView = (RelativeLayout) inflater.inflate(R.layout.complex_view, null);

        ((ImageView) mView.findViewById(R.id.image)).setImageResource(data.getImageId());
        return mView;
    }
}