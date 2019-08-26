package com.example.mrzero.androidadkyaaapp3.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mrzero.androidadkyaaapp3.R;
import com.example.mrzero.androidadkyaaapp3.listener.MyItemListener;
import com.example.mrzero.androidadkyaaapp3.model.getSecondMaterial.Section;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class SectionMaterialAdapter extends RecyclerView.Adapter<SectionMaterialAdapter.SectionViewHolder> {


    public ArrayList<Section> getmList() {
        return mSectionsList;
    }

    public void setmList(ArrayList<Section> mList) {
        if (mList!=null){
            this.mSectionsList = mList;

        }else {
            this.mSectionsList = new ArrayList<>();

        }
        this.mSectionsList = mList;
    }

    ArrayList<Section> mSectionsList= new ArrayList<>();

    public MyItemListener getMyItemListener() {
        return myItemListener;
    }

    public void setMyItemListener(MyItemListener myItemListener) {
        this.myItemListener = myItemListener;
    }

    private MyItemListener myItemListener;
    private Context mContext;

    public SectionMaterialAdapter(Context mContext) {
        this.mContext = mContext;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public SectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizantal_section_item_rec_layout, parent, false);
        return new SectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SectionViewHolder holder, int position) {
        holder.tv_description.setText(mSectionsList.get(position).getName());

    }

    @Override
    public int getItemCount() {
      return(null != mSectionsList ? mSectionsList.size() : 0);
    }

    public class SectionViewHolder  extends RecyclerView.ViewHolder{
        TextView tv_description;
        ImageView img_check;
        ImageView img_item;
        ConstraintLayout constraintLayout;

        public SectionViewHolder(@NonNull View itemView) {
            super(itemView);
            img_check=itemView.findViewById(R.id.imageView19);
            img_item=itemView.findViewById(R.id.imageView18);
            tv_description=itemView.findViewById(R.id.textView29);
            constraintLayout=itemView.findViewById(R.id.container_constrain);
            constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myItemListener.onClickItem(getAdapterPosition());
                }
            });


        }
    }
}
