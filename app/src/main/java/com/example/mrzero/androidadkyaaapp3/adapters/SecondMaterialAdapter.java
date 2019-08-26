package com.example.mrzero.androidadkyaaapp3.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mrzero.androidadkyaaapp3.R;
import com.example.mrzero.androidadkyaaapp3.listener.MyItemListener;
import com.example.mrzero.androidadkyaaapp3.model.getSecondMaterial.SecondMaterialData;
import com.example.mrzero.androidadkyaaapp3.model.getSecondMaterial.Section;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SecondMaterialAdapter extends RecyclerView.Adapter<SecondMaterialAdapter.SecondMaterialViewHolder> implements MyItemListener {



    ArrayList<SecondMaterialData> mListMaterial=  new ArrayList<>();
    private Context mContext;

    public SecondMaterialAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public ArrayList<SecondMaterialData> getmListMaterial() {
        return mListMaterial;
    }

    public void setmListMaterial(ArrayList<SecondMaterialData> mListMaterial) {
        this.mListMaterial = mListMaterial;
    }


    @NonNull
    @Override
    public SecondMaterialViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vertical_material_item_rec_layout, parent, false);
        return new SecondMaterialViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SecondMaterialViewHolder holder, int position) {

        holder.tv_title.setText(mListMaterial.get(position).getName());
     SectionMaterialAdapter   adapterSection = new SectionMaterialAdapter(mContext );
     adapterSection.setMyItemListener(this);

        holder.recyle_inner_horizental_material.setHasFixedSize(true);
        holder.recyle_inner_horizental_material.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));

        adapterSection.setmList( mListMaterial.get(position).getSections());
        holder.recyle_inner_horizental_material.setAdapter(adapterSection);


        holder.tv_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "click from second adapter", Toast.LENGTH_SHORT).show();
            }
        });

    }
     @Override
    public int getItemCount() {
        return(null != mListMaterial ? mListMaterial.size() : 0);
    }

    @Override
    public void onClickItem(int position) {
        Toast.makeText(mContext, "click from section adapter", Toast.LENGTH_SHORT).show();

    }


    public class SecondMaterialViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title;
        RecyclerView recyle_inner_horizental_material;

        public SecondMaterialViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title=itemView.findViewById(R.id.tv_title);
            recyle_inner_horizental_material=itemView.findViewById(R.id.recyle_inner_horizental_material);
        }
    }
}
