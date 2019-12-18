package com.j7arsen.filenavigator.base;

import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import moxy.MvpAppCompatActivity;

public abstract class BaseActivity extends MvpAppCompatActivity {

    private Unbinder unbinder;

    protected abstract int getLayout();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
