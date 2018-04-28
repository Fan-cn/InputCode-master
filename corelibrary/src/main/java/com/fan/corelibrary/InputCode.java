package com.fan.corelibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * <pre>
 *     author: Fan
 *     time  : 2018/4/22 下午4:21
 *     desc  : 自定义验证码输入框
 * </pre>
 */
public class InputCode extends ViewGroup {

    /**
     * InputType
     */
    public static final String NUMBER = "number";
    public static final String TEXT = "text";
    public static final String PASSWORD = "password";
    public static final String PHONE = "phone";
    /**
     * 输入框的数量
     */
    protected int mBoxCount;
    /**
     * 输入框的宽度(单位：dp)
     */
    protected int mBoxWidth;
    /**
     * 输入框的高度(单位：dp)
     */
    protected int mBoxHeight;
    /**
     * 输入框与上边和下边的间距(paddingTop与paddingBottom同时生成, 单位：dp)
     */
    protected int mBoxVPadding;
    /**
     * 输入框之间的横向间距(单位：dp)
     */
    protected int mBoxHPadding;
    /**
     * 输入框未选中时的背景
     */
    protected Drawable mBoxBgFocus;
    /**
     * 输入框选中时的背景
     */
    protected Drawable mBoxBgNormal;
    /**
     * 输入后显示的类型
     */
    protected String mCodeInputType = NUMBER;
    /**
     * 输入文字的颜色
     */
    protected int mTextColor = Color.BLACK;
    /**
     * 输入文字的颜色
     */
    protected float mTextSize;
    /**
     * 成功回调
     */
    protected OnInputCompleteListener listener;
    /**
     * 是否显示自定义背景(默认显示)
     */
    protected boolean mIsShowBack;

    public void setListener(OnInputCompleteListener listener) {
        this.listener = listener;
    }

    public InputCode(Context context, AttributeSet attrs) {
        super(context, attrs);
        initDefValue();
        initAttrs(attrs);
        init();
    }

    /**
     * 初始化默认值
     */
    protected void initDefValue() {
        mBoxCount = 4;
        mBoxWidth = 120;
        mBoxHeight = 120;
        mBoxVPadding = 14;
        mBoxHPadding = 14;
        mTextColor = Color.BLACK;
        mTextSize = 20;
        mIsShowBack = true;
    }

    /**
     * 设置自定义属性
     */
    protected void initAttrs(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.InputCode);
        for (int i = 0; i < ta.getIndexCount(); i++) {
            int index = ta.getIndex(i);
            if (index == R.styleable.InputCode_code_box)
                mBoxCount = ta.getInt(R.styleable.InputCode_code_box, 4);
            else if (index == R.styleable.InputCode_code_box_width)
                mBoxWidth = (int) ta.getDimension(R.styleable.InputCode_code_box_width, 60);
            else if (index == R.styleable.InputCode_code_box_height)
                mBoxHeight = (int) ta.getDimension(R.styleable.InputCode_code_box_height, 60);
            else if (index == R.styleable.InputCode_code_box_v_padding)
                mBoxVPadding = (int) ta.getDimension(R.styleable.InputCode_code_box_v_padding, 14);
            else if (index == R.styleable.InputCode_code_box_h_padding)
                mBoxHPadding = (int) ta.getDimension(R.styleable.InputCode_code_box_h_padding, 14);
            else if (index == R.styleable.InputCode_code_box_bg_normal)
                mBoxBgNormal = ta.getDrawable(R.styleable.InputCode_code_box_bg_normal);
            else if (index == R.styleable.InputCode_code_box_bg_focus)
                mBoxBgNormal = ta.getDrawable(R.styleable.InputCode_code_box_bg_focus);
            else if (index == R.styleable.InputCode_code_text_color)
                mTextColor = ta.getColor(R.styleable.InputCode_code_text_color, Color.BLACK);
            else if (index == R.styleable.InputCode_code_text_size)
                mTextSize = ta.getDimensionPixelSize(R.styleable.InputCode_code_text_size, 15);
            else if (index == R.styleable.InputCode_code_input_type)
                mCodeInputType = ta.getString(R.styleable.InputCode_code_input_type);
            else if (index == R.styleable.InputCode_code_is_show_back)
                mIsShowBack = ta.getBoolean(R.styleable.InputCode_code_is_show_back, true);
        }
        ta.recycle();
    }

    /**
     * 初始化AppCompatEditText
     */
    protected void init() {
        for (int i = 0; i < mBoxCount; i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mBoxWidth, mBoxHeight);
            params.leftMargin = mBoxHPadding;
            params.topMargin = mBoxVPadding;
            params.rightMargin = mBoxHPadding;
            params.bottomMargin = mBoxVPadding;
            params.gravity = Gravity.CENTER;

            AppCompatEditText editText = new AppCompatEditText(getContext());
            //对每个EditText添加返回按钮的监听
            editText.setOnKeyListener(onKeyListener);
            //添加背景
            setBg(editText, false);
            //设置输入文字的颜色
            editText.setTextColor(mTextColor);
            //设置输入文字的大小
            editText.setTextSize(mTextSize);
            editText.setLayoutParams(params);
            //设置文字居中显示
            editText.setGravity(Gravity.CENTER);
            //设置EditText最多只能输入1个字符
            editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(1)});

            //文字的输入类型
            switch (mCodeInputType){
                case NUMBER:
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    break;
                case PASSWORD:
                    editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    break;
                case TEXT:
                    editText.setInputType(InputType.TYPE_CLASS_TEXT);
                    break;
                case PHONE:
                    editText.setInputType(InputType.TYPE_CLASS_PHONE);
                    break;
            }

            if (i == 0) {
                setBg(editText, true);
                editText.setEnabled(true);
            } else {
                setBg(editText, false);
                editText.setEnabled(false);
            }
            editText.setId(i);
            //设置文字显示为一个M字符的宽度
            //Ems (equal E的复数)
            editText.setEms(1);
            //添加输入文字变化的监听
            editText.addTextChangedListener(textWatcher);
            addView(editText, i);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);
        }

        if (childCount > 0){
            View child = getChildAt(0);
            int measuredHeight = child.getMeasuredHeight();
            int measuredWidth = child.getMeasuredWidth();
            int maxH = measuredHeight + mBoxVPadding * 2;
            int maxW = (measuredWidth + mBoxHPadding) * mBoxCount - mBoxHPadding;
            setMeasuredDimension(resolveSize(maxW, widthMeasureSpec),
                    resolveSize(maxH, heightMeasureSpec));
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int count = getChildCount();

        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            child.setVisibility(VISIBLE);
            int measuredHeight = child.getMeasuredHeight();
            int measuredWidth = child.getMeasuredWidth();

            int cL = i * (measuredWidth + mBoxHPadding);
            int cT = mBoxVPadding;
            int cR = cL + measuredWidth;
            int cB = cT + measuredHeight;

            child.layout(cL, cT, cR, cB);//设置每个AppCompatEditText显示的位置
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LinearLayout.LayoutParams(getContext(), attrs);
    }

    @Override
    public void setEnabled(boolean enabled) {
        int childCount = getChildCount();

        for (int i = 0; i < childCount; i++) {
            getChildAt(i).setEnabled(enabled);
        }
    }

    protected void setBg(AppCompatEditText editText, boolean focus) {
        if (mIsShowBack){
            if (mBoxBgNormal == null){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mBoxBgNormal = getResources().getDrawable(
                            R.drawable.verification_edit_bg_normal, null);
                }else {
                    mBoxBgNormal = getResources().getDrawable(
                            R.drawable.verification_edit_bg_normal);
                }
            }
            if (mBoxBgFocus == null){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mBoxBgFocus = getResources().getDrawable(
                            R.drawable.verification_edit_bg_focus, null);
                }else {
                    mBoxBgFocus = getResources().getDrawable(
                            R.drawable.verification_edit_bg_focus);
                }
            }
        }

        if (mBoxBgNormal != null && !focus){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                editText.setBackground(mBoxBgNormal);
            }else {
                editText.setBackgroundDrawable(mBoxBgNormal);
            }
        }else if (mBoxBgFocus != null && focus){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                editText.setBackground(mBoxBgFocus);
            }else {
                editText.setBackgroundDrawable(mBoxBgFocus);
            }
        }
    }

    protected TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() > 0){
                focus();
                inputComplete();
            }
        }
    };

    protected void inputComplete() {
        int count = getChildCount();
        StringBuilder sb = new StringBuilder();
        boolean isComplete = true;
        for (int i = 0; i < count; i++) {
            String str = ((AppCompatEditText) getChildAt(i)).getText().toString();
            if (TextUtils.isEmpty(str)) {
                isComplete = false;
                break;
            } else sb.append(str);
        }

        if (isComplete) {
//            setEnabled(false);//设置控件不可用
            if (listener != null) listener.onComplete(sb.toString());
        }
    }

    protected void focus() {
        int count = getChildCount();
        AppCompatEditText editText;
        for (int i = 0; i < count; i++) {
            editText = (AppCompatEditText) getChildAt(i);
            editText.setEnabled(false);
            if (editText.getText().length() == 0){
                editText.requestFocus();//改变焦点
                setBg(editText, true);//修改背景
                editText.setEnabled(true);
                break;
            }
        }
    }

    protected OnKeyListener onKeyListener = new OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            //监听删除键
            if (keyCode == KeyEvent.KEYCODE_DEL){
                int count = getChildCount();
                AppCompatEditText editText;
                for (int i = count - 1; i >= 0; i--) {
                    editText = (AppCompatEditText) getChildAt(i);
                    if (editText.getText().length() == 0 && i > 0){
                        setBg(editText, false);//修改背景
                        editText.setEnabled(false);
                    }if (editText.getText().length() == 1){
                        editText.setEnabled(true);
                        editText.requestFocus();//改变焦点
                        editText.setSelection(1);//设置光标
                        break;
                    }
                }
            }
            return false;
        }
    };

    public interface OnInputCompleteListener{
        void onComplete(String code);
    }
}
