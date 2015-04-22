package com.example.jacob.oweage;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;


public class ContactInfoPage extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info_page);

        LinearLayout contactInfo = (LinearLayout) findViewById(R.id.contactInfo);


        ScrollView scroll = (ScrollView)findViewById(R.id.scrollView);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        String s = getIntent().getExtras().getString("name");

        ArrayList<Contact> list = MainActivity.contactList;
        Contact c1 = new Contact("c1");

        System.out.println("list size = " + list.size());

        for(Contact c : list){
            System.err.println("\n\n" + s + "     " + c.getName() + "\n\n");
            if(s.equals(c.getName())){
                c1 = c;
                System.out.println(c1.getRelation());

            }
        }

        TextView nameTV = new TextView(this);
        TextView relationTV = new TextView(this);
        nameTV.setText(c1.getName());
        relationTV.setText(c1.getRelation());
        contactInfo.addView(nameTV);
        contactInfo.addView(relationTV);

        TextView balanceTV = (TextView) findViewById(R.id.currentBalance);
        balanceTV.setText(Double.toString(c1.getBalance()));

        ArrayList<TransactionEntry> history = c1.getHistory();


        for (int i = 0; i < history.size(); i++) {

            TextView t = new TextView(this);


            //Button b = new Button(this);
            t.setWidth(1000);
            t.setHeight(125);
            t.setTextColor(Color.CYAN);
            t.setText(history.get(i).getDate() +
                    "\t\t" + history.get(i).getDescription() +
                    "\t\t" + history.get(i).getAmount());
            t.setId(100 + i);

            linearLayout.addView(t);
        }

        scroll.addView(linearLayout);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact_info_page, menu);
        return true;
    }

    public void startEvent(View view) {
        Intent intent = new Intent(this, EventPage.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
