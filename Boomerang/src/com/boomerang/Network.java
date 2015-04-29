package com.boomerang;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Network extends Activity {
    static String news;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.network);
        String[] a={"Connect","disconnect"};
        ListView o=(ListView)findViewById(R.id.lvnetwork);
        ArrayAdapter<String> b=new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,a);
        o.setAdapter(b);

}
}