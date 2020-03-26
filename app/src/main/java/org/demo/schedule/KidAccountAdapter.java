package org.demo.schedule;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class KidAccountAdapter extends RecyclerView.Adapter<KidAccountAdapter.ViewHolder> {

   private ArrayList<User> kidList;

    public KidAccountAdapter(ArrayList<User> kidList) {
        this.kidList = kidList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup vg, int viewType) {
        View v = LayoutInflater.from(vg.getContext()).inflate(R.layout.row_kid_account, vg, false);
        return new KidAccountAdapter.ViewHolder(v, new EditLastnameListener(), new EditFirstnameListener(), new EditPhoneListener());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = kidList.get(position);
        holder.firstnameListener.updatePosition(position);
        holder.lastnameListener.updatePosition(position);
        holder.phoneListener.updatePosition(position);
    }

    @Override
    public int getItemCount() {
        return kidList.size();
    }

    public ArrayList<User> getKids() { return kidList; }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        View layout_row;
        EditText et_firstname_kid;
        EditText et_lastname_kid;
        EditText et_phone_kid;
        EditFirstnameListener firstnameListener;
        EditLastnameListener lastnameListener;
        EditPhoneListener phoneListener;

        public ViewHolder(@NonNull View itemView, EditLastnameListener lastnameListener, EditFirstnameListener firstnameListener, EditPhoneListener phoneListener) {
            super(itemView);
            layout_row = itemView.findViewById(R.id.layout_row);
            et_firstname_kid = itemView.findViewById(R.id.et_firstname_kid);
            et_lastname_kid = itemView.findViewById(R.id.et_lastname_kid);
            et_phone_kid = itemView.findViewById(R.id.et_phone_kid);

            this.firstnameListener = firstnameListener;
            this.lastnameListener = lastnameListener;
            this.phoneListener = phoneListener;

            et_firstname_kid.addTextChangedListener(firstnameListener);
            et_lastname_kid.addTextChangedListener(lastnameListener);
            et_phone_kid.addTextChangedListener(phoneListener);
        }
    }

    private class EditLastnameListener implements TextWatcher {
        private int position;

        public void updatePosition(int position) {
            this.position = position;
        }


        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            User user = kidList.get(position);
            user.setLastName(editable.toString());
        }
    }

    private class EditFirstnameListener implements TextWatcher {
        private int position;

        public void updatePosition(int position) {
            this.position = position;
        }


        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            User user = kidList.get(position);
            user.setFirstName(editable.toString());
        }
    }

    private class EditPhoneListener implements TextWatcher {
        private int position;

        public void updatePosition(int position) {
            this.position = position;
        }


        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            User user = kidList.get(position);
            user.setPhoneNumber(editable.toString());
        }
    }
}