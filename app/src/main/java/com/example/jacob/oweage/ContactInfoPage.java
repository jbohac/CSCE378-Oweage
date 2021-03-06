package com.example.jacob.oweage;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;


public class ContactInfoPage extends ActionBarActivity {

    String name = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info_page);

        //LinearLayout contactInfo = (LinearLayout) findViewById(R.id.contactInfo);


        ScrollView scroll = (ScrollView) findViewById(R.id.scrollView);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        String s = getIntent().getExtras().getString("name");
        this.name = s;

        Contact c1 = new Contact("c1");



        TextView nameTV = new TextView(this);
        TextView relationTV = new TextView(this);

        TextView name = (TextView) findViewById(R.id.contactName);
        TextView relation = (TextView) findViewById(R.id.contactRelation);

        for (Contact c : MainActivity.contactList) {
            if (s.equals(c.getName())) {
                c1 = c;
            }
        }



        name.setText(c1.getName());
        relation.setText(c1.getRelation());

        //Are the next two lines needed?
        //nameTV.setText(c1.getName());
        //relationTV.setText(c1.getRelation());

        TextView balanceTV = (TextView) findViewById(R.id.currentBalance);



        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String moneyString = formatter.format(c1.getBalance());

        balanceTV.setText(moneyString);

        if (c1.getBalance() < 0) {
            balanceTV.setTextColor(Color.RED);
        } else {
            balanceTV.setTextColor(Color.GREEN);
        }

        ArrayList<TransactionEntry> history = c1.getHistory();


        for (int i = history.size()-1; i >= 0; i--) {

            TextView t = new TextView(this);


            //Button b = new Button(this);
            t.setWidth(100);
            t.setHeight(125);
            t.setTextSize(16);


            int color = 0;

            if (history.get(i).getAmount() < 0) {

                color = Color.RED;
                //t.setTextColor(Color.RED);
            } else {

                color = Color.GREEN;
                //t.setTextColor(Color.GREEN);
            }

            String amountString = formatter.format(history.get(i).getAmount());

            Spannable part1 = new SpannableString( String.format("%-15s", history.get(i).getDate() ));
            Spannable part2 = new SpannableString( String.format("%-28s", history.get(i).getDescription()));
            Spannable part3 = new SpannableString( String.format("%-20s", amountString));

            part1.setSpan(new ForegroundColorSpan(Color.WHITE), 0, part1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            part2.setSpan(new ForegroundColorSpan(Color.WHITE), 0, part2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            part3.setSpan(new ForegroundColorSpan(color), 0, part3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            t.setText(part1);
            t.append(part2);
            t.append(part3);

            /*t.setText(history.get(i).getDate() +
                    "\t\t" + history.get(i).getDescription() +
                    "\t\t" + history.get(i).getAmount());*/
            t.setId(100 + i);

            linearLayout.addView(t);

             /*Added below to adjust color NOT WORKING YET- HAS NO EFFECT*/
            View ruler = new View(this); ruler.setBackgroundColor(0x3ebaa9);//0x3ebaa9
            linearLayout.addView(ruler,
                    new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, 4));
            /*Added above to adjust color */
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
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        System.out.println("--->" + view.getTag());

        // This passes a 0 for IOU event
        //               1 for payback event
        intent.putExtra("key", view.getTag().toString());
        intent.putExtra("name", this.name);

        startActivity(intent);
    }

    public void goHome(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void goContacts(View view) {
        Intent intent = new Intent(this, ContactsPage.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void goSettings(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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
