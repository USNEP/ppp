package main.kyp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import global.Constants;
import main.R;
import reports.IndvTxnsReport;

public class IndvReportActivity extends AppCompatActivity {
    private Bundle bundle;
    private  String[] arrayArgs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arrayArgs = getIntent().getExtras().getStringArray(Constants.ARG_ARRAY_REPORT);
        addFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch ((id)) {
            case android.R.id.home:
                super. onBackPressed();
                break;
        }
        return true;
    }
    @Override
    public void setTitle(CharSequence title) {
        setTitle(title);
    }
    public void addFragment(){
        Fragment fragment =new IndvTxnsReport();
        bundle = new Bundle();
        bundle.putStringArray(Constants.ARG_FRGMT, arrayArgs);
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(android.R.id.content,fragment).commit();
    }
}