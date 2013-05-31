package net.libsoftigran.tabfragments.demo.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import net.libsoftigran.tabfragments.TabFragment;
import net.libsoftigran.tabfragments.demo.R;

public class Tab1Fragment2 extends TabFragment{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.init(inflater, container, R.layout.tab1_fragment2);

        ((Button) findViewById(R.id.next_fragment)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(new Tab1Fragment3());
            }
        });

        return content;
    }

    @Override
    public Bundle getReturnData() {
        Bundle data = new Bundle();
        data.putString("text", ((TextView) findViewById(R.id.pass_back_input)).getText().toString());
        return data;
    }

    public void update(Bundle params)
    {
        String text = params.getString("text");
        ((TextView) findViewById(R.id.data_text)).setText(text);
    }
}
