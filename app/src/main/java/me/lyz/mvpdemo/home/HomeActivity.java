package me.lyz.mvpdemo.home;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import me.lyz.mvpdemo.R;

public class HomeActivity extends AppCompatActivity implements IHomeContract.View {

    private static final String TAG = "HomeActivity";

    private TextInputEditText mNameEt;
    private TextInputEditText mPsdEt;
    private Button mRegisterBtn;
    private Button mLoginBtn;


    private IHomeContract.Presenter mPresenter;

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
        }
    };

    private static class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mPresenter = new HomePresenter(this);
        initView();
//        mRegisterBtn.performClick();


        new Thread(){}.notify();
    }

    private void initView() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mNameEt = findViewById(R.id.user_name_et);
        mPsdEt = findViewById(R.id.user_psd_et);
        mRegisterBtn = findViewById(R.id.register_btn);
        mLoginBtn = findViewById(R.id.login_btn);

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onRegister(mNameEt.getText().toString(),
                        mPsdEt.getText().toString());
//                try {
//                    Thread.sleep(60000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                sendBroadcast(ACTION_SLEEP);
                sendBroadcast(ACTION_SLEEP);
            }
        });

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onLogin(mNameEt.getText().toString(),
                        mPsdEt.getText().toString());
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent: 啊");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLoginSuccess() {
        Toast.makeText(this, "onLoginSuccess", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginFailed() {
        Toast.makeText(this, "onLoginFailed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRegisterSuccess() {
        Toast.makeText(this, "onRegisterSuccess", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRegisterFailed() {
        Toast.makeText(this, "onRegisterFailed", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerBroadcastReceiver();
        registerBroadcastReceiver();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mBroadcastReceiver);
    }

    public static final String ACTION_SLEEP = "ACTION_SLEEP";

    private void registerBroadcastReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_SLEEP);
        registerReceiver(mBroadcastReceiver, intentFilter);
    }

    private void sendBroadcast(String action) {
        Intent intent = new Intent(action);
        Log.d(TAG, "sendBroadcast: processName:" + getProcessName(this));
        sendBroadcast(intent);
    }

    public static String getProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> infos = manager.getRunningAppProcesses();
        if (infos != null) {
            for (ActivityManager.RunningAppProcessInfo processInfo : infos) {
                if (processInfo.pid == pid) {
                    return processInfo.processName;
                }
            }
        }
        return null;
    }

    private MyBroadcastReceiver mBroadcastReceiver = new MyBroadcastReceiver();

    private class MyBroadcastReceiver extends BroadcastReceiver {

        private static final String TAG = "MyBroadcastReceiver";

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceive: " + intent.getAction());
            Log.d(TAG, "onReceive: processName:" + getProcessName(context));
            switch (intent.getAction()) {
                case ACTION_SLEEP:
                    for (; ; ) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
//                    Log.d(TAG, "onReceive: " + "end");
//                    break;
            }
        }


    }
}
