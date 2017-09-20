package com.wq.mvpframe.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wq.mvpframe.R;

public class WaitDialog extends Dialog{
    public WaitDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getRootLayout());
    }

    private LinearLayout getRootLayout(){
        LinearLayout root = new LinearLayout(getContext());
        root.setOrientation(LinearLayout.VERTICAL);

        ProgressBar progressBar = new ProgressBar(getContext());
        TextView tips = new TextView(getContext());

        tips.setText(R.string.progress_login);

        root.addView(progressBar);
        root.addView(tips);
        return root;
    }
}
