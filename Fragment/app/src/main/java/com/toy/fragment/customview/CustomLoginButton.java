package com.toy.fragment.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.toy.fragment.R;

public class CustomLoginButton extends LinearLayout {
    private LinearLayout bg;
    private ImageView symbol;
    private TextView text;

    public CustomLoginButton(Context context) {
        super(context);

        initView();
    }



    public CustomLoginButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
        getAttrs(attrs);
    }



    public CustomLoginButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        getAttrs(attrs,defStyleAttr);

    }

    private void initView() {
        String infServiec = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater)getContext().getSystemService(infServiec);
        View v = li.inflate(R.layout.welcome_login_button, this ,false);
        addView(v);

        bg= (LinearLayout) findViewById(R.id.bg);
        symbol = (ImageView) findViewById(R.id.symbol);
        text = (TextView) findViewById(R.id.text);

    }
    private void getAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.LoginButton);
        setTypeArray(typedArray);

    }
    private void getAttrs(AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.LoginButton, defStyleAttr,0);
        setTypeArray(typedArray);
    }

    private void setTypeArray(TypedArray typedArray) {
        int bg_resID = typedArray.getResourceId(R.styleable.LoginButton_bg, R.drawable.yello_background);
        bg.setBackgroundResource(bg_resID);

        int symbol_resID = typedArray.getResourceId(R.styleable.LoginButton_symbol,R.drawable.kakao_logo);
        symbol.setImageResource(symbol_resID);

        int textColor = typedArray.getColor(R.styleable.LoginButton_textColor,0);
        text.setTextColor(textColor);

        String text_string = typedArray.getString(R.styleable.LoginButton_text);
        text.setText(text_string);

        typedArray.recycle();
    }
    void setBg(int bg_resID) { bg.setBackgroundResource(bg_resID); }
    void setSymbol(int symbol_resID) { symbol.setImageResource(symbol_resID); }
    void setTextColor(int color) { text.setTextColor(color); }
    void setText(String text_string) { text.setText(text_string); }
    void setText(int text_resID) { text.setText(text_resID); }


}
