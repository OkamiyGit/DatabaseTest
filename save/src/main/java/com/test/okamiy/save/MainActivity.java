package com.test.okamiy.save;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private EditText mText;
    private Button mSave, mGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mText = (EditText) findViewById(R.id.et_input);
        mSave = (Button) findViewById(R.id.save);
        mGet = (Button) findViewById(R.id.get);

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * name ：文件名，不存在创建
                 * mode ：指定操作模式，MODE_PRIVATE表示只有当前应用才可以进行操作
                 */
                SharedPreferences.Editor editor = getSharedPreferences("save", Context.MODE_PRIVATE).edit();
                editor.putBoolean("isSave", true);
                editor.putInt("int", 100);
                editor.putString("string", "四季发财，");

                editor.commit();

                Spinner spinner = null;
                SpinnerAdapter apsAdapter = spinner.getAdapter();
                int k = apsAdapter.getCount();
                for (int i = 0; i < k; i++) {
                    if ("洋洋老师".equals(apsAdapter.getItem(i).toString())) {
                        spinner.setSelection(i, true);
                        break;
                    }
                }
            }
        });

        mGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getSharedPreferences("save", Context.MODE_PRIVATE);
                boolean is = pref.getBoolean("isSave", false);
                int num = pref.getInt("int", 0);
                String msg = pref.getString("string", "");
                Log.i(TAG, "onClick: " + is + "," + num + "," + msg);

            }
        });

        String history = loadContent();

        if (!TextUtils.isEmpty(history)) {
            mText.setText(history);
            mText.setSelection(history.length());
            Toast.makeText(this, "Restoring successQ", Toast.LENGTH_LONG).show();
        }


    }

    private String loadContent() {
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();

        /**
         * 从文件读取内容
         */
        try {
            in = openFileInput("my_data");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != reader) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content.toString();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        String content = mText.getText().toString();
        if (!TextUtils.isEmpty(content)) {
            saveFile(content);
        }
    }

    //保存Edittext输入的内容
    private void saveFile(String content) {

        FileOutputStream out = null;
        BufferedWriter writer = null;

        /**
         * 保存到文件
         */
        try {
            out = openFileOutput("my_data", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != writer) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
