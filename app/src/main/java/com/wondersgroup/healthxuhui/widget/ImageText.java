package com.wondersgroup.healthxuhui.widget;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wondersgroup.healthxuhui.R;

/**
 * Created by yangjinxi on 2016/6/2.
 */
public class ImageText extends RelativeLayout {
    public static final String NAMESPACE = "http://schemas.android.com/apk/res-auto";

    private boolean isChecked = false;
    private int imageOnId;
    private int imageOffId;
    private String text;
    private ImageView ivImage;
    private TextView tvText;
    private int colorOn ;
    private int colorOff ;

    public ImageText(Context context) {
        this(context, null);
    }

    public ImageText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImageText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View.inflate(getContext(), R.layout.widget_image_text, this);
        ivImage = (ImageView) findViewById(R.id.iv_image);
        tvText = (TextView) findViewById(R.id.tv_text);
        //拿到数据为：@2130903053，需去掉@
        imageOnId = Integer.parseInt(attrs.getAttributeValue(NAMESPACE, "imageOn").replace("@", ""));
        imageOffId = Integer.parseInt(attrs.getAttributeValue(NAMESPACE, "imageOff").replace("@", ""));
        int colorOnId = Integer.parseInt(attrs.getAttributeValue(NAMESPACE, "colorOn").replace("@", ""));
        int colorOffId = Integer.parseInt(attrs.getAttributeValue(NAMESPACE, "colorOff").replace("@", ""));
        colorOn = ContextCompat.getColor(context, colorOnId);
        colorOff = ContextCompat.getColor(context, colorOffId);
        isChecked = attrs.getAttributeBooleanValue(NAMESPACE, "checked", false);
        text = attrs.getAttributeValue(NAMESPACE, "widgetText");
        tvText.setText(text);
        setWidgetStatus();
    }

    private void setWidgetStatus(){
        if (isChecked) {
            ivImage.setImageResource(imageOnId);
            tvText.setTextColor(colorOn);
        }else{
            ivImage.setImageResource(imageOffId);
            tvText.setTextColor(colorOff);
        }
    }

    public void  setChecked(boolean checked){
        isChecked = checked;
        setWidgetStatus();
    }

    public boolean getChecked(){
        return isChecked;
    }
}
