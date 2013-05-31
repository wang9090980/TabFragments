package net.libsoftigran.tabfragments.demo;

import net.libsoftigran.tabfragments.TabContainer;
import net.libsoftigran.tabfragments.demo.fragments.Tab0Fragment0;
import net.libsoftigran.tabfragments.demo.fragments.Tab1Fragment0;
import net.libsoftigran.tabfragments.demo.fragments.Tab2Fragment0;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends TabContainer {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        containerId = R.id.tabs_container;
        setContentView(R.layout.activity_main);
        addTab(findViewById(R.id.tab0), new Tab0Fragment0());
        addTab(findViewById(R.id.tab1), new Tab1Fragment0());
        addTab(findViewById(R.id.tab2), new Tab2Fragment0());
        addTabClickListeners();

        switchTab(0);
    }

    @Override
    protected void onFirstFragmentBackPressed() {

        Toaster.showToast(this, "OVERRIDED pressed back on first fragment", Toast.LENGTH_LONG);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
