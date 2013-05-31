package net.libsoftigran.tabfragments.demo.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import net.libsoftigran.tabfragments.TabFragment;
import net.libsoftigran.tabfragments.demo.R;


public class Tab0Fragment0 extends TabFragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.init(inflater, container, R.layout.tab0_fragment0);

        ((Button) findViewById(R.id.next_fragment)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(new Tab0Fragment1());
            }
        });

        return content;
    }

    public void update(Bundle params)
    {
        String text = params.getString("text");
        ((TextView) findViewById(R.id.data_text)).setText(text);
    }
}
