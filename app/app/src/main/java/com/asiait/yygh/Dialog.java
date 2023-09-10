package com.asiait.yygh;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

public class Dialog extends android.app.Dialog implements View.OnClickListener{
    private View mView;
    private Context mContext;
    //    弹出框整体
    private LinearLayout mBgLl;
    //    标题文字
    private TextView mTitleTv;
    //    输入框
    //    返回按钮
    private Button mNegBtn;
    //    确认按钮
    private Button mPosBtn;

    EditText msg;

    public Dialog(Context context) {
        this(context, 0, null);
    }

    public Dialog(Context context, int theme, View contentView) {
        super(context, theme == 0 ? R.style.MyDialogStyle : theme);

        this.mView = contentView;
        this.mContext = context;

        if (mView == null) {
            mView = View.inflate(mContext, R.layout.editdialog, null);
        }
        init();
        initView();
        initData();
        initListener();

    }

    private void init() {
        this.setContentView(mView);
    }

    private void initView() {
        mBgLl = (LinearLayout) mView.findViewById(R.id.lLayout_bg);
        mTitleTv = (TextView) mView.findViewById(R.id.txt_title);
        mNegBtn = (Button) mView.findViewById(R.id.btn_neg);
        mPosBtn = (Button) mView.findViewById(R.id.btn_pos);
        msg = (EditText) mView.findViewById(R.id.msg);

    }

    private void initData() {
        //设置背景是屏幕的0.85
        mBgLl.setLayoutParams(new FrameLayout.LayoutParams((int) (getMobileWidth(mContext) * 0.85), Toolbar.LayoutParams.WRAP_CONTENT));
        //设置默认为10000
    }

    private void initListener() {
        mNegBtn.setOnClickListener(this);
        mPosBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_neg:  //取消,
                if(onPosNegClickListener != null) {

                        onPosNegClickListener.negCliclListener();
                }

                this.dismiss();
                break;

            case R.id.btn_pos:  //确认
                if(onPosNegClickListener != null) {
                        //                  if (mEtValue.length() > 8 || mEtValue.length() < 4 || Integer.parseInt(mEtValue) <= 0) {
                        //                      //TODO 这里处理条件
                        //                  }
                        onPosNegClickListener.posClickListener();
                }
                this.dismiss();
                break;
        }
    }

    private OnPosNegClickListener onPosNegClickListener;

    public void setOnPosNegClickListener (OnPosNegClickListener onPosNegClickListener) {
        this.onPosNegClickListener = onPosNegClickListener;
    }

    public interface OnPosNegClickListener {
        void posClickListener();
        void negCliclListener();
    }



    /**
     * 工具类
     * @param context
     * @return
     */
    public static int getMobileWidth(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels; // 得到宽度
        return width;
    }
}
