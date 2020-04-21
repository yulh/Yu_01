package com.example.yu_01.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.yu_01.R;
import com.example.yu_01.entity.AgeEntity;

import org.xutils.DbManager;
import org.xutils.common.util.KeyValue;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.io.File;

public class DbActivity extends AppCompatActivity implements View.OnClickListener {
    public Button buttonSearch;
    public Button buttonAdd;
    public Button buttonUpdate;
    public Button buttonDelete;

    private DbManager.DaoConfig daoConfig;
    private DbManager dbManager;


    public void applyPermissions(Context context, String permission) {
        if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{permission}, 1);
        } else {

        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        applyPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        findId();
        setListener();

        daoConfig = new DbManager.DaoConfig()
                .setDbName("shujuku.db")
                .setDbVersion(1)
//                .setDbDir(new File("/storage/emulated/0"))
                .setDbOpenListener(new DbManager.DbOpenListener() {
                    @Override
                    public void onDbOpened(DbManager db) {
                        db.getDatabase().enableWriteAheadLogging();
                    }
                });
        dbManager = x.getDb(daoConfig);
    }

    private void findId() {
        buttonSearch = (Button) findViewById(R.id.button_search);
        buttonAdd = (Button) findViewById(R.id.button_add);
        buttonUpdate = (Button) findViewById(R.id.button_update);
        buttonDelete = (Button) findViewById(R.id.button_delete);
    }

    private void setListener() {
        buttonSearch.setOnClickListener(this);
        buttonAdd.setOnClickListener(this);
        buttonUpdate.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_search:
                AgeEntity ageEntity1 = null;
                try {
                    ageEntity1 = dbManager.selector(AgeEntity.class).where("name","==", "李丽").findFirst();
                } catch (DbException e) {
                    e.printStackTrace();
                }
                    Toast.makeText(DbActivity.this,ageEntity1!=null? ageEntity1.toString():"数据见底了", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_add:
                AgeEntity ageEntity = new AgeEntity( "李丽",2);
                try {
                    dbManager.save(ageEntity);
                } catch (DbException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.button_update:
                try {
                  int i=  dbManager.update(AgeEntity.class, WhereBuilder.b("name", "=", "李丽").and("age", "=", 2), new KeyValue("age", 28));
                  Toast.makeText(DbActivity.this,i==1?"修改成功":"修改失败",Toast.LENGTH_SHORT).show();

                } catch (DbException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.button_delete:
                try {
                    dbManager.delete(AgeEntity.class, WhereBuilder.b("name", "=", "李丽"));
                } catch (DbException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
