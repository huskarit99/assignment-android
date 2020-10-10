package com.example.librarymanagement.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.librarymanagement.R;
import com.example.librarymanagement.models.Book;
import com.example.librarymanagement.models.BorrowedBook;
import com.example.librarymanagement.networks.Server;
import com.example.librarymanagement.networks.SessionManager;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.lang.String.valueOf;

public class BorrowedBookActivity extends AppCompatActivity {

    SessionManager sessionManager;
    ListView listView;
    Toolbar toolbar;

    public int idUser;
    public ArrayList<BorrowedBook> listBorrowedBook;
    public ArrayList<Book> listBook;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrowed_book);

        mapping();

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Trang chủ");

        Intent intent = getIntent();
        listBook = (ArrayList<Book>) intent.getSerializableExtra(HomeActivity.BOOK);

        sessionManager = new SessionManager(this);
        idUser = sessionManager.getUser();

        requestQueue = Volley.newRequestQueue(BorrowedBookActivity.this);
        getListBorrowedBook();

        CustomAdapterBook customAdapterBook = new BorrowedBookActivity.CustomAdapterBook();
        listView.setAdapter(customAdapterBook);
    }

    private void getListBorrowedBook() {
        listBorrowedBook = new ArrayList<BorrowedBook>();
        String url = Server.GETBORROWEDBOOK + "?id=" + valueOf(idUser);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                listBorrowedBook.add(new BorrowedBook(
                                        jsonObject.getInt("bill_id"),
                                        jsonObject.getInt("user_id"),
                                        jsonObject.getInt("book_id"),
                                        jsonObject.getString("dateOfBorrowed"),
                                        jsonObject.getString("dateOfPurchase"),
                                        jsonObject.getInt("state")
                                ));
                                for (int j = 0; j < listBook.size(); j++){
                                    if (listBook.get(j).getBook_id() == listBorrowedBook.get(i).getBook_id()){
                                        listBorrowedBook.get(i).setName(listBook.get(j).getName());
                                        listBorrowedBook.get(i).setImage(listBook.get(j).getImage());
                                    }
                                }

                                System.out.println(listBorrowedBook.get(i).getImage());
                                System.out.println(listBorrowedBook.get(i).getName());
                                System.out.println(listBorrowedBook.get(i).getBill_id());
                                System.out.println(listBorrowedBook.get(i).getBook_id());
                                System.out.println(listBorrowedBook.get(i).getUser_id());
                                System.out.println(listBorrowedBook.get(i).getDateOfBorrowed());
                                System.out.println(listBorrowedBook.get(i).getDateOfPurchase());
                                System.out.println(listBorrowedBook.get(i).getState());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(BorrowedBookActivity.this,"Không lấy được dữ kiệu người dùng",Toast.LENGTH_SHORT).show();
                    }
                });
    