package org.faqrobot.armynewspaper.viewclickbinder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Annotate annotate = new Annotate();
        annotate.OnClick(this);

//        findViewById(R.id.tv_show_one).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.e("AA", "我点击了");
//            }
//        });





    }


    @IOnClick({R.id.tv_show_one, R.id.tv_show_two})
    public void button(View view) {
        switch (view.getId()) {
            case R.id.tv_show_one:
                Toast.makeText(this, "点击了以一", Toast.LENGTH_LONG).show();
                break;
            case R.id.tv_show_two:
                Toast.makeText(this, "点击了以二", Toast.LENGTH_LONG).show();
                break;
        }

    }
}
