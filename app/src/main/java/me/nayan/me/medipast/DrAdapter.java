package me.nayan.me.medipast;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.nayan.me.medipast.R;

import java.util.ArrayList;

/**
 * Created by User on 21-May-17.
 */

public class DrAdapter extends ArrayAdapter<DrModel> {

    private Context context;
    private ArrayList<DrModel> doctors;
    private UserAuthentication userAuthenticationSharedPreference;

    public DrAdapter(@NonNull Context context, ArrayList<DrModel> drModelArrayList) {
        super(context, R.layout.single_dr_list_row_design ,drModelArrayList);
        this.context=context;
        this.doctors=drModelArrayList;
    }

    class ViewHolder{
        TextView nameTV;
        TextView phoneTV;

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.single_dr_list_row_design,parent,false);
            holder.nameTV = (TextView) convertView.findViewById(R.id.drNameTV);
            holder.phoneTV = (TextView) convertView.findViewById(R.id.drPhoneTV);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }


        holder.nameTV.setText(doctors.get(position).getDrName());
        holder.phoneTV.setText(doctors.get(position).getDrPhone());
        return convertView;
    }
}
