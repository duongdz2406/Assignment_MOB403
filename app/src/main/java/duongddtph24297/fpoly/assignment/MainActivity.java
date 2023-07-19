package duongddtph24297.fpoly.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    String jsonRes;
    Product product;
    ProductAdapter productAdapter;
    FloatingActionButton floadAdd;
    ArrayList<Product> list = new ArrayList<>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recy);
        floadAdd = findViewById(R.id.floatAddProduct);

        loadData();
        floadAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            ShowDialog();
            }
        });
    }

    private void loadData(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.168.20.109/thaiduong_ph24297/read.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            jsonRes="";
                            JSONArray jsonArray = new JSONArray(response);
                            for(int i =0;i<jsonArray.length();i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Integer id = Integer.parseInt(jsonObject.getString("id_product"));
                                String name = jsonObject.getString("name_product");
                                Integer price = Integer.parseInt(jsonObject.getString("price_product"));
                                String img = jsonObject.getString("img_product");
                                Integer soluong = Integer.parseInt(jsonObject.getString("soluong_product"));
                                String loai = jsonObject.getString("categorie_product");
                                product = new Product(id,name,price,img,soluong,loai);
                                list.add(product);
                            }
                            productAdapter = new ProductAdapter(MainActivity.this, list);
                            recyclerView.setAdapter(productAdapter);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error",error.getLocalizedMessage());
            }
        }){
//            protected Map<String, String> getParams(){
//                Map<String, String> paramV = new HashMap<>();
//                paramV.put("param", "abc");
//                return paramV;
//            }
        };
        queue.add(stringRequest);
    }
    private void AddProduct(String name,int price,String img,int soluong,String loai){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.168.20.109/thaiduong_ph24297/create.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("Success")){
                            Toast.makeText(MainActivity.this, "add thanh cong", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this, "khong thanh cong", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error",error.getLocalizedMessage());
            }
        }){
            protected Map<String, String> getParams(){
                Map<String, String> paramV = new HashMap<>();

                paramV.put("name_product", name);
                paramV.put("price_product", String.valueOf(price));
                paramV.put("img_product", img);
                paramV.put("soluong_product", String.valueOf(soluong));
                paramV.put("categorie_product", loai);
                return paramV;
            }
        };
        queue.add(stringRequest);
    }
    private void ShowDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_themsanpham,null);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) EditText edThemTen = view.findViewById(R.id.edThemTenSP);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) EditText edThemGia = view.findViewById(R.id.edThemGiaSP);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) EditText edThemLoai = view.findViewById(R.id.edThemLoaiSP);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) EditText edThemSoLuong = view.findViewById(R.id.edThemSoLuongSP);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) EditText edThemImg = view.findViewById(R.id.edThemImg);



        builder.setView(view);
        builder.setPositiveButton("Them", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = edThemTen.getText().toString();
                String loai = edThemLoai.getText().toString();
                String img = edThemImg.getText().toString();
                int price = Integer.parseInt(edThemGia.getText().toString());
                int soluong = Integer.parseInt(edThemSoLuong.getText().toString());
                AddProduct(name,price,img,soluong,loai);

                recreate();


            }
        });
        builder.setNegativeButton("Huy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });


        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.setCancelable(false);
    }
    //https://sieuthihoaba.com.vn/wp-content/uploads/2020/08/nuoc-ngot-7-up-vi-chanh-330ml-201905301056157261-768x576.jpg

}
