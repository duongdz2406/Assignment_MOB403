package duongddtph24297.fpoly.assignment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    Context context;
    ArrayList<Product> list;


    public ProductAdapter(Context context, ArrayList<Product> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtName.setText(list.get(position).getName());
        holder.txtPrice.setText(String.valueOf(list.get(position).getPrice()));
        holder.txtLoai.setText(list.get(position).getLoai());
        holder.txtSoluong.setText(String.valueOf(list.get(position).getSoluong()));
        Picasso.get().load(list.get(position).img).into(holder.img);
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Delete(list.get(holder.getAdapterPosition()).getId());
                Log.d("123", "id: "+list.get(holder.getAdapterPosition()).getId());
                notifyDataSetChanged();

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img,imgEdit,imgDelete;
        TextView txtName,txtPrice,txtLoai,txtSoluong;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            imgEdit = itemView.findViewById(R.id.imgEdit);
            imgDelete = itemView.findViewById(R.id.imgDelete);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtName = itemView.findViewById(R.id.txtName);
            txtLoai = itemView.findViewById(R.id.txtLoai);
            txtSoluong = itemView.findViewById(R.id.txtSoluong);
        }
    }
    private void Delete(int id){
        RequestQueue queue = Volley.newRequestQueue(context);
        String url ="http://192.168.20.109/thaiduong_ph24297/delete.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("Success")){
                            Toast.makeText(context, "delete thanh cong", Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                            Log.d("res", "onResponse: "+response);
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
                paramV.put("id_product", String.valueOf(id));
                return paramV;
            }
        };
        queue.add(stringRequest);
    }



}
