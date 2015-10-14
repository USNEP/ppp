package main.kyp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import global.Constants;
import main.R;
import setting.EditType;

public class EditTypeActivity extends AppCompatActivity {
    private Bundle bundle;
    private String head;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        head = getIntent().getExtras().getString(Constants.ARG_SECTION_NUMBER);
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
        Fragment fragment =new EditType();
        bundle = new Bundle();
        bundle.putString(Constants.ARG_FRGMT, head);
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(android.R.id.content,fragment).commit();
    }
}
