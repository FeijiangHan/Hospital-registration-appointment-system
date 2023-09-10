package com.asiait.yygh;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.asiait.yygh.until.ConfigApplication;
import com.hb.dialog.myDialog.MyAlertDialog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Main_grzx extends Fragment {
    TextView nickname;
    LinearLayout grxx,tc,xgmm,jzr;
    RelativeLayout bj;
    public static Main_grzx newInstance(String tag) {
        Bundle args = new Bundle();
        Main_grzx fragment = new Main_grzx();
        args.putString("title",tag);
        fragment.setArguments(args);
        return fragment;
    }
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.grzx, container, false);
        jzr=v.findViewById(R.id.jzr);

        jzr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),Jzr.class));
            }
        });


        grxx=v.findViewById(R.id.grxx);
        grxx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ConfigApplication.getU().getType().equals("患者")) {
                    startActivity(new Intent(getActivity(), UserUpdate_hz.class));
                }
                else {
                    startActivity(new Intent(getActivity(), UserUpdate_ys.class));

                }
            }
        });
        xgmm=v.findViewById(R.id.xgmm);
        xgmm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),PassUpdate.class));
            }
        });

        tc=v.findViewById(R.id.tc);
        tc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyAlertDialog myAlertDialog = new MyAlertDialog(getActivity()).builder()
                        .setTitle("确认吗？")
                        .setMsg("退出账号")
                        .setPositiveButton("确认", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(getActivity(),LoginActivity.class));
                                Toast.makeText(getActivity(), "退出成功", Toast.LENGTH_SHORT).show();
                            }
                        }).setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        });
                myAlertDialog.show();
            }
        });

        return v;
    }
}
