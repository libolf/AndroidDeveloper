package com.libok.androiddeveloper.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.libok.androiddeveloper.R;
import com.libok.androiddeveloper.util.L;
import com.libok.androiddeveloper.util.T;
import com.xiaomi.market.sdk.UpdateResponse;
import com.xiaomi.market.sdk.XiaomiUpdateAgent;
import com.xiaomi.market.sdk.XiaomiUpdateListener;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        XiaomiUpdateAgent.update(this, true);
        XiaomiUpdateAgent.setUpdateAutoPopup(true);
        XiaomiUpdateAgent.setUpdateListener(new XiaomiUpdateListener() {
            @Override
            public void onUpdateReturned(int i, UpdateResponse updateResponse) {
                Log.e(TAG, "onUpdateReturned: " + i + " " + updateResponse.versionCode);
            }
        });
    }
}
