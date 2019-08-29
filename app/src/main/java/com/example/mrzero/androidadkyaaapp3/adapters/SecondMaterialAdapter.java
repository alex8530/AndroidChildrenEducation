package com.example.mrzero.androidadkyaaapp3.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mrzero.androidadkyaaapp3.R;
import com.example.mrzero.androidadkyaaapp3.listener.MyItemListener;
import com.example.mrzero.androidadkyaaapp3.listener.MyItemListener_nested;
import com.example.mrzero.androidadkyaaapp3.model.getSecondMaterial.SecondMaterialData;
import com.example.mrzero.androidadkyaaapp3.model.getSecondMaterial.Section;
import com.example.mrzero.androidadkyaaapp3.ui.fragments.ChalengeFragment;
import com.example.mrzero.androidadkyaaapp3.utils.Common;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SecondMaterialAdapter extends RecyclerView.Adapter<SecondMaterialAdapter.SecondMaterialViewHolder> implements MyItemListener_nested  {


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
         adapterSection.setMyItemListenerNested(this);

        holder.recyle_inner_horizental_material.setHasFixedSize(true);
        holder.recyle_inner_horizental_material.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));



        //to save position of parent
      ArrayList<Section> list =   mListMaterial.get(position).getSections();
      for (Section section : list){
          section.setParent_position(position);
      }
        adapterSection.setmList(list);
        holder.recyle_inner_horizental_material.setAdapter(adapterSection);



    }
     @Override
    public int getItemCount() {
        return(null != mListMaterial ? mListMaterial.size() : 0);
    }



    public class SecondMaterialViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title;
        RecyclerView recyle_inner_horizental_material;
        LinearLayout linearLayoutHorizenteal;

        public SecondMaterialViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title=itemView.findViewById(R.id.tv_title);
            linearLayoutHorizenteal=itemView.findViewById(R.id.LinearLayoutHorizenteal);
            recyle_inner_horizental_material=itemView.findViewById(R.id.recyle_inner_horizental_material);

        }
    }
    @Override
    public void onClickItemNested(Section section ) {

        //save ids
        Common.CurrentSection=section;
        Common.CurrentSecondMaterial= mListMaterial.get(section.getParent_position());



        Fragment fragment= ChalengeFragment.getInstance();
        FragmentManager fragmentManager =    ((FragmentActivity)mContext).getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.contianer_frame, fragment).commit();
    }
}
