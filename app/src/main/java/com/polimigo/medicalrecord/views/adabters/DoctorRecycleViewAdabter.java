package com.polimigo.medicalrecord.views.adabters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.polimigo.medicalrecord.BR;
import com.polimigo.medicalrecord.R;
import com.polimigo.medicalrecord.databinding.DoctorItemRowBinding;
import com.polimigo.medicalrecord.models.DoctorModel;
import com.polimigo.medicalrecord.views.events.CustomClickListener;

import java.util.List;


public class DoctorRecycleViewAdabter extends RecyclerView.Adapter<DoctorRecycleViewAdabter.ViewHolder> implements CustomClickListener {

    private List<DoctorModel> dataModelList;
    private Context context;

    public DoctorRecycleViewAdabter(List<DoctorModel> dataModelList, Context ctx) {
        this.dataModelList = dataModelList;
        context = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        DoctorItemRowBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.doctor_item_row, parent, false);
        return new ViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DoctorModel dataModel = dataModelList.get(position);
        holder.itemRowBinding.setModel(dataModel);
        holder.bind(dataModel);
        holder.itemRowBinding.setItemClickListener(this);
    }


    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public DoctorItemRowBinding itemRowBinding;

        public ViewHolder(DoctorItemRowBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.itemRowBinding = itemRowBinding;
        }

        public void bind(Object obj) {
            itemRowBinding.setVariable(BR.model, obj);
            itemRowBinding.executePendingBindings();
        }

    }

    public void cardClicked(DoctorModel f) {

    }

}


