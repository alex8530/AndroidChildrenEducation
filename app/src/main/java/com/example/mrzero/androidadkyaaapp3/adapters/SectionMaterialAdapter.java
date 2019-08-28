package com.example.mrzero.androidadkyaaapp3.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mrzero.androidadkyaaapp3.R;
import com.example.mrzero.androidadkyaaapp3.listener.MyItemListener;
import com.example.mrzero.androidadkyaaapp3.listener.MyItemListener_nested;
import com.example.mrzero.androidadkyaaapp3.model.getSecondMaterial.Section;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class SectionMaterialAdapter extends RecyclerView.Adapter<SectionMaterialAdapter.SectionViewHolder> {


    public ArrayList<Section> getmList() {
        return mSectionsList;
    }




    //    boolean isCompleteQuestion=false;

    public void setmList(ArrayList<Section> mList) {
        if (mList!=null){
            this.mSectionsList = mList;

        }else {
            this.mSectionsList = new ArrayList<>();

        }
        this.mSectionsList = mList;
    }

    ArrayList<Section> mSectionsList= new ArrayList<>();


    private MyItemListener_nested myItemListenerNested;

    public MyItemListener_nested getMyItemListenerNested() {
        return myItemListenerNested;
    }

    public void setMyItemListenerNested(MyItemListener_nested myItemListenerNested) {
        this.myItemListenerNested = myItemListenerNested;
    }

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


        if (mSectionsList.get(position).getIs_complete()){
            //show check icon
         holder.img_check.setVisibility(View.VISIBLE);

        }else {
        //  hide icon check
            holder.img_check.setVisibility(View.INVISIBLE);

        }

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
                    //if the section is complete>>
                    if (mSectionsList.get(getAdapterPosition()).getIs_complete()){
                        Toast.makeText(mContext, "لقد تم حل جميع المسائل في هذا المساق", Toast.LENGTH_SHORT).show();
                    }else {
                        myItemListenerNested.onClickItemNested(mSectionsList.get(getAdapterPosition()));
                    }
                }
            });


        }
    }
}
