package net.libsoftigran.tabfragments.demo.fragments;

import net.libsoftigran.tabfragments.TabFragment;
import net.libsoftigran.tabfragments.demo.R;
import net.libsoftigran.tabfragments.demo.Toaster;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Tab0Fragment2 extends TabFragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.init(inflater, container, R.layout.tab0_fragment2);
        ((Button) findViewById(R.id.next_fragment)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toaster.showToast(activity, "this is the last fragment", Toast.LENGTH_SHORT);
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
    }
}
