package com.example.police;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchCriminalbyname extends AppCompatActivity {
    // List View object
    ListView listView;

    // Define array adapter for ListView
    ArrayAdapter<String> adapter;

    // Define array List for List View data
    ArrayList<String> mylist;
    TestAdapter testAdapter;
    Context context=this;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_criminalbyname);

        listView = findViewById(R.id.listviewwww);

       /* mylist = new ArrayList<>();
        mylist.add("C");
        mylist.add("C++");
        mylist.add("C#");
        mylist.add("Java");
        mylist.add("Advanced java");
        mylist.add("Interview prep with c++");
        mylist.add("Interview prep with java");
        mylist.add("data structures with c");
        mylist.add("data structures with java");

        // Set adapter to ListView
        adapter
                = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                mylist);
        listView.setAdapter(adapter);*/

        try {
            testAdapter = new TestAdapter(this);
            testAdapter.createDatabase();
            testAdapter.open();

            addcriminalname();



            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    final ProgressDialog dialog =
                            new ProgressDialog(SearchCriminalbyname.this);
                    dialog.setIcon(R.drawable.alert);
                    dialog.setTitle("SEARCH");
                    dialog.setMessage("Please wait Searching Criminal Records...");
                    dialog.show();

                    final Runnable progressRunnable = new Runnable() {
                        @Override
                        public void run() {
                            dialog.cancel();
                            String mpass = null;


                            name = listView.getItemAtPosition(i).toString();

                            Toast.makeText(getApplicationContext(), "You Selected...." + name, Toast.LENGTH_SHORT).show();
                            AlertDialog.Builder alertbuilder = new AlertDialog.Builder(context);
                            TableLayout layout = new TableLayout(context);
                            layout.setGravity(Gravity.CENTER);


                            Cursor c = testAdapter.getcrimedtat(name);
                            while (c.moveToNext()) {




                                final TableRow tableRow = new TableRow(context);
                                tableRow.setBackgroundResource(R.drawable.row);
                                layout.addView(tableRow);


                                final TableRow r = new TableRow(context);
                                TextView tv = new TextView(context);
                                tv.setText("No");
                                tv.setTextSize(20);
                                tv.setTextColor(Color.BLACK);
                                //r.setBackgroundResource(R.drawable.row);
                                r.addView(tv);


                                final TextView tv1 = new TextView(context);
                                tv1.setText("       " + c.getString(0));
                                final String num = ("     " + c.getString(0));
                                tv1.setTextSize(20);
                                tv1.setPadding(0, 20, 0, 0);
                                tv1.setTextColor(Color.BLACK);
                                r.addView(tv1);
                                layout.addView(r);


                                final TableRow tableRow5 = new TableRow(context);
                                tableRow5.setBackgroundResource(R.drawable.line);
                                layout.addView(tableRow5);

                                final TableRow r2 = new TableRow(context);
                                TextView tv2 = new TextView(context);
                                tv2.setText("Case Type");
                                tv2.setTextSize(20);
                                tv2.setTextColor(Color.BLACK);
                                // r2.setBackgroundResource(R.drawable.rrow);
                                r2.addView(tv2);

                                final TextView ttv1 = new TextView(context);
                                ttv1.setText("     " + c.getString(1));
                                ttv1.setTextSize(20);
                                ttv1.setPadding(0, 20, 0, 0);
                                ttv1.setTextColor(Color.BLACK);
                                r2.addView(ttv1);
                                layout.addView(r2);


                                final TableRow tableRow6 = new TableRow(context);
                                tableRow6.setBackgroundResource(R.drawable.line);
                                layout.addView(tableRow6);

                                final TableRow r3 = new TableRow(context);
                                TextView tv3 = new TextView(context);
                                tv3.setText("Crime Date");
                                tv3.setTextSize(20);
                                tv3.setTextColor(Color.BLACK);
                                //r3.setBackgroundResource(R.drawable.row);
                                r3.addView(tv3);

                                final TextView ttv2 = new TextView(context);
                                ttv2.setText("     " + c.getString(2));
                                ttv2.setTextSize(20);
                                ttv2.setPadding(0, 20, 0, 0);
                                ttv2.setTextColor(Color.BLACK);
                                r3.addView(ttv2);
                                layout.addView(r3);


                                final TableRow tableRow7 = new TableRow(context);
                                tableRow7.setBackgroundResource(R.drawable.line);
                                layout.addView(tableRow7);


                                final TableRow r4 = new TableRow(context);
                                TextView tv4 = new TextView(context);
                                tv4.setText("Name");
                                tv4.setTextColor(Color.BLACK);
                                tv4.setTextSize(20);
                                //r4.setBackgroundResource(R.drawable.anay);
                                r4.addView(tv4);

                                final TextView ttv4 = new TextView(context);
                                ttv4.setText("     " + c.getString(3));
                                ttv4.setTextSize(20);
                                ttv4.setPadding(0, 20, 0, 0);
                                ttv4.setTextColor(Color.BLACK);
                                r4.addView(ttv4);
                                layout.addView(r4);


                                final TableRow tableRow8 = new TableRow(context);
                                tableRow8.setBackgroundResource(R.drawable.line);
                                layout.addView(tableRow8);

                                final TableRow rp4 = new TableRow(context);
                                TextView tvp4 = new TextView(context);
                                tvp4.setText("Parent");
                                tvp4.setTextColor(Color.BLACK);
                                tvp4.setTextSize(20);
                                //r4.setBackgroundResource(R.drawable.anay);
                                rp4.addView(tvp4);

                                final TextView ttvp4 = new TextView(context);
                                ttvp4.setText("     " + c.getString(5));
                                ttvp4.setTextSize(20);
                                ttvp4.setPadding(0, 20, 0, 0);
                                ttvp4.setTextColor(Color.BLACK);
                                rp4.addView(ttvp4);
                                layout.addView(rp4);


                                final TableRow tableRowp8 = new TableRow(context);
                                tableRowp8.setBackgroundResource(R.drawable.line);
                                layout.addView(tableRowp8);



                                final TableRow rrr4 = new TableRow(context);
                                TextView tvr4 = new TextView(context);
                                tvr4.setText("Address");
                                tvr4.setTextColor(Color.BLACK);
                                tvr4.setTextSize(20);
                                //r4.setBackgroundResource(R.drawable.anay);
                                rrr4.addView(tvr4);

                                final TextView ttvr4 = new TextView(context);
                                ttvr4.setText("     " + c.getString(4));
                                ttvr4.setTextSize(20);
                                ttvr4.setPadding(0, 20, 0, 0);
                                ttvr4.setTextColor(Color.BLACK);
                                rrr4.addView(ttvr4);
                                layout.addView(rrr4);


                                final TableRow tableRowr8 = new TableRow(context);
                                tableRowr8.setBackgroundResource(R.drawable.line);
                                layout.addView(tableRowr8);

                                final TableRow r5 = new TableRow(context);
                                TextView tv5 = new TextView(context);
                                tv5.setText("Gender");
                                tv5.setTextColor(Color.BLACK);
                                tv5.setTextSize(20);
                                //r5.setBackgroundResource(R.drawable.row);
                                r5.addView(tv5);


                                final TextView ttv5 = new TextView(context);
                                ttv5.setText("     " + c.getString(6));
                                ttv5.setTextSize(20);
                                ttv5.setPadding(0, 20, 0, 0);
                                ttv5.setTextColor(Color.BLACK);
                                r5.addView(ttv5);
                                layout.addView(r5);


                                final TableRow tableRow9 = new TableRow(context);
                                tableRow9.setBackgroundResource(R.drawable.line);
                                layout.addView(tableRow9);

                                final TableRow r6 = new TableRow(context);
                                TextView tv6 = new TextView(context);
                                tv6.setText("Age");
                                tv6.setTextSize(20);
                                tv6.setTextColor(Color.BLACK);
                                //r6.setBackgroundResource(R.drawable.rrow);
                                r6.addView(tv6);

                                final TextView ttv6 = new TextView(context);
                                ttv6.setText("     " + c.getString(7));
                                ttv6.setTextSize(20);
                                ttv6.setPadding(0, 20, 0, 0);
                                ttv6.setTextColor(Color.BLACK);
                                r6.addView(ttv6);
                                layout.addView(r6);


                                final TableRow tableRow1 = new TableRow(context);
                                tableRow1.setBackgroundResource(R.drawable.line);
                                layout.addView(tableRow1);

                                final TableRow r7 = new TableRow(context);
                                TextView tv7 = new TextView(context);
                                tv7.setText("Mobile");
                                tv7.setTextSize(20);
                                tv7.setTextColor(Color.BLACK);
                                //r7.setBackgroundResource(R.drawable.row);
                                r7.addView(tv7);

                                final TextView ttv7 = new TextView(context);
                                ttv7.setText("     " + c.getString(8));
                                ttv7.setTextSize(20);
                                ttv7.setPadding(0, 20, 0, 0);
                                ttv7.setTextColor(Color.BLACK);
                                r7.addView(ttv7);
                                layout.addView(r7);


                                final TableRow tableRow2 = new TableRow(context);
                                tableRow2.setBackgroundResource(R.drawable.line);
                                layout.addView(tableRow2);


                                final TableRow r9 = new TableRow(context);
                                TextView tv9 = new TextView(context);
                                tv9.setText("P.Station");
                                tv9.setTextSize(20);
                                tv9.setTextColor(Color.BLACK);
                                //r7.setBackgroundResource(R.drawable.row);
                                r9.addView(tv9);

                                final TextView ttv9 = new TextView(context);
                                ttv9.setText("     " + c.getString(9));
                                ttv9.setTextSize(20);
                                ttv9.setPadding(0, 20, 0, 0);
                                ttv9.setTextColor(Color.BLACK);
                                r9.addView(ttv9);
                                layout.addView(r9);

                                final TableRow tableR = new TableRow(context);
                                tableR.setBackgroundResource(R.drawable.line);
                                layout.addView(tableR);

                                final TableRow r10 = new TableRow(context);
                                TextView tv10 = new TextView(context);
                                tv10.setText("FIR No");
                                tv10.setTextSize(20);
                                tv10.setTextColor(Color.BLACK);
                                //r7.setBackgroundResource(R.drawable.row);
                                r10.addView(tv10);

                                final TextView ttv10 = new TextView(context);
                                ttv10.setText("     " + c.getString(10));
                                ttv10.setTextSize(20);
                                ttv10.setPadding(0, 20, 0, 0);
                                ttv10.setTextColor(Color.BLACK);
                                r10.addView(ttv10);
                                layout.addView(r10);

                                final TableRow tableRr = new TableRow(context);
                                tableRr.setBackgroundResource(R.drawable.line);
                                layout.addView(tableRr);

                                final TableRow r11 = new TableRow(context);
                                TextView tv11 = new TextView(context);
                                tv11.setText("Section");
                                tv11.setTextSize(20);
                                tv11.setTextColor(Color.BLACK);
                                //r7.setBackgroundResource(R.drawable.row);
                                r11.addView(tv11);

                                final TextView ttv11 = new TextView(context);
                                ttv11.setText("     " + c.getString(13));
                                ttv11.setTextSize(20);
                                ttv11.setPadding(0, 20, 0, 0);
                                ttv11.setTextColor(Color.BLACK);
                                r11.addView(ttv11);
                                layout.addView(r11);


                                final TableRow tableRrr = new TableRow(context);
                                tableRrr.setBackgroundResource(R.drawable.line);
                                layout.addView(tableRrr);

                                final TableRow r114 = new TableRow(context);
                                TextView tv114 = new TextView(context);
                                tv114.setText("Description");
                                tv114.setTextSize(20);
                                tv114.setTextColor(Color.BLACK);
                                //r7.setBackgroundResource(R.drawable.row);
                                r114.addView(tv114);
                                layout.addView(r114);



                                final TableRow tableRrrrr = new TableRow(context);
                                //tableRrrrr.setBackgroundResource(R.drawable.line);
                                //layout.addView(tableRrrrr);

                                final TextView ttv114 = new TextView(context);
                                ttv114.setText("     " + c.getString(14));
                                ttv114.setTextSize(20);
                                ttv114.setPadding(0, 20, 0, 0);
                                ttv114.setTextColor(Color.BLACK);
                                tableRrrrr.addView(ttv114);
                                layout.addView(tableRrrrr);


                                final TableRow tableRrr4 = new TableRow(context);
                                tableRrr4.setBackgroundResource(R.drawable.line);
                                layout.addView(tableRrr4);                                alertbuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // TODO Auto-generated method stub

                                    }
                                });


                                alertbuilder.setView(layout);

                            }


                            AlertDialog alertDialog = alertbuilder.create();
                            alertDialog.show();

                        }
                    };
                    Handler pdCanceller = new Handler();
                    pdCanceller.postDelayed(progressRunnable, 6000);

                }


            });


        }catch (Exception e){}




    }

    private void addcriminalname() {
        Cursor c=testAdapter.selectCriminal();
        mylist=new ArrayList<String>();
        while(c.moveToNext())
        {

            mylist.add(c.getString(3).toString());

        }

        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mylist);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate menu with items using MenuInflator
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        // Initialise menu item search bar
        // with id and take its object
        MenuItem searchViewItem
                = menu.findItem(R.id.search_bar);
        SearchView searchView
                = (SearchView) MenuItemCompat
                .getActionView(searchViewItem);

        // attach setOnQueryTextListener
        // to search view defined above
        searchView.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {

                    // Override onQueryTextSubmit method
                    // which is call
                    // when submitquery is searched

                    @Override
                    public boolean onQueryTextSubmit(String query)
                    {
                        // If the list contains the search query
                        // than filter the adapter
                        // using the filter method
                        // with the query as its argument
                        if (mylist.contains(query)) {
                            adapter.getFilter().filter(query);
                        }
                        else {
                            // Search query not found in List View
                            final ProgressDialog dialog =
                                    new ProgressDialog(SearchCriminalbyname.this);
                            dialog.setIcon(R.drawable.alert);
                            dialog.setTitle("SEARCH");
                            dialog.setMessage("Please wait Searching Criminal Records...");
                            dialog.show();

                            final Runnable progressRunnable = new Runnable() {
                                @Override
                                public void run() {
                                    dialog.cancel();
                                    String mpass = null;
                                    // Intent i = new Intent(Login.this, PoliceHomePage.class);
                                    //startActivity(i);
                                    Toast.makeText(getApplicationContext(), "Record Not Found..." , Toast.LENGTH_SHORT).show();


                                }
                            };


                            Handler pdCanceller = new Handler();
                            pdCanceller.postDelayed(progressRunnable, 6000);

                        }

                        return false;
                    }

                    // This method is overridden to filter
                    // the adapter according to a search query
                    // when the user is typing search
                    @Override
                    public boolean onQueryTextChange(String newText)
                    {
                        adapter.getFilter().filter(newText);
                        return false;
                    }
                });

        return super.onCreateOptionsMenu(menu);
    }
}