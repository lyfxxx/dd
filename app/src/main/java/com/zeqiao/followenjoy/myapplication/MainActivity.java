package com.zeqiao.followenjoy.myapplication;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化存储
        sp = getSharedPreferences("config", MODE_PRIVATE);
        long lastUseTime = sp.getLong("last_use", 0l);
        showDialog(lastUseTime);


    }
    /**
    *
    * 展示弹窗
    *
    * */
    public void showDialog(long lastUseTime){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.view_message);
        Window window = dialog.getWindow();
        window.setLayout(WRAP_CONTENT,WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        dialog.findViewById(R.id.bt_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        TextView tvContext = dialog.findViewById(R.id.tv_context);
        if (lastUseTime>0l) {
            long l = System.currentTimeMillis();
            if (l-lastUseTime>=1000*60*60*24*3) {
                tvContext.setText("好久不见，欢迎再次使用");
            }else {
                tvContext.setText("欢迎经常使用");
                //Toast.makeText(this,,Toast.LENGTH_SHORT).show();
            }
        }else{
            tvContext.setText("欢迎初次使用");
        }
        dialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        long l = System.currentTimeMillis();
        sp.edit().putLong("last_use",l).apply();
    }
}
