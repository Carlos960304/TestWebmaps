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
import java.util.List;
import java.util.stream.Collectors;

public class ListRegistrationAdapter extends RecyclerView.Adapter<ListRegistrationAdapter.RegistrationViewHolder> {

    ArrayList<Registration> listRegistration;
    ArrayList<Registration> listOriginal;

    public ListRegistrationAdapter(ArrayList<Registration> listRegistration) {
        this.listRegistration = listRegistration;
        listOriginal = new ArrayList<>();
        listOriginal.addAll(listRegistration);
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

    public void filterCountry(String svSearchCountry) {
        int longitud = svSearchCountry.length();
        if(longitud == 0) {
            listRegistration.clear();
            listRegistration.addAll(listOriginal);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Registration> coleccion = listRegistration.stream()
                        .filter(i -> i.getCountry().toLowerCase().contains(svSearchCountry.toLowerCase()))
                        .collect(Collectors.toList());
                listRegistration.clear();
                listRegistration.addAll(coleccion);
            } else {
                for (Registration r: listOriginal) {
                    if(r.getCountry().toLowerCase().contains(svSearchCountry.toLowerCase())) {
                        listRegistration.add(r);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    public void filterState(String svSearchState) {
        int longitud = svSearchState.length();
        if(longitud == 0) {
            listRegistration.clear();
            listRegistration.addAll(listOriginal);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Registration> coleccion = listRegistration.stream()
                        .filter(i -> i.getState().toLowerCase().contains(svSearchState.toLowerCase()))
                        .collect(Collectors.toList());
                listRegistration.clear();
                listRegistration.addAll(coleccion);
            } else {
                for (Registration r: listOriginal) {
                    if(r.getState().toLowerCase().contains(svSearchState.toLowerCase())) {
                        listRegistration.add(r);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    public void filterGender(String svSearchGender) {
        int longitud = svSearchGender.length();
        if(longitud == 0) {
            listRegistration.clear();
            listRegistration.addAll(listOriginal);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Registration> coleccion = listRegistration.stream()
                        .filter(i -> i.getGender().toLowerCase().contains(svSearchGender.toLowerCase()))
                        .collect(Collectors.toList());
                listRegistration.clear();
                listRegistration.addAll(coleccion);
            } else {
                for (Registration r: listOriginal) {
                    if(r.getGender().toLowerCase().contains(svSearchGender.toLowerCase())) {
                        listRegistration.add(r);
                    }
                }
            }
        }
        notifyDataSetChanged();
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
