package com.example.projecttestwebmaps.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projecttestwebmaps.R;
import com.example.projecttestwebmaps.model.Registration;

import java.util.ArrayList;

public class ListRegistrationAdapter extends RecyclerView.Adapter<ListRegistrationAdapter.RegistrationViewHolder> {

    ArrayList<Registration> listRegistration;

    public ListRegistrationAdapter(ArrayList<Registration> listRegistration) {
        this.listRegistration = listRegistration;
    }

    @NonNull
    @Override
    public RegistrationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_records, null, false);
        return new RegistrationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RegistrationViewHolder holder, int position) {
        holder.tvUser.setText(String.valueOf(listRegistration.get(position).getUsuario()));
        holder.tvCountry.setText(listRegistration.get(position).getCountry());
        holder.tvState.setText(listRegistration.get(position).getState());
        holder.tvGender.setText(listRegistration.get(position).getGender());
    }

    @Override
    public int getItemCount() {
        return listRegistration.size();
    }

    public class RegistrationViewHolder extends RecyclerView.ViewHolder {

        TextView tvUser, tvCountry, tvState, tvGender;

        public RegistrationViewHolder(@NonNull View itemView) {
            super(itemView);

            tvUser = itemView.findViewById(R.id.tvUser);
            tvCountry = itemView.findViewById(R.id.tvCountry);
            tvState = itemView.findViewById(R.id.tvState);
            tvGender = itemView.findViewById(R.id.tvGender);
        }
    }
}
