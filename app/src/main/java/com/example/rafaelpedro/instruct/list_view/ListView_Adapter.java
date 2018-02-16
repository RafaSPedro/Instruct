package com.example.rafaelpedro.instruct.list_view;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.rafaelpedro.instruct.R;
import com.example.rafaelpedro.instruct.models.User_ListView;

import java.util.ArrayList;

/**
 * Created by RafaelPedro on 15/02/2018.
 */

public class ListView_Adapter extends ArrayAdapter<User_ListView> {
    private ArrayList<User_ListView> userArray;
    private Context contexto;

    public ListView_Adapter(Context context, int resource, ArrayList<User_ListView> userArray){
        super(context, resource, userArray);

        this.contexto = context;
        this.userArray = userArray;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        ViewHolder holder = null;
        int type = getItemViewType(position);
        if(convertView==null){
            LayoutInflater inflater = ((Activity) contexto).getLayoutInflater();
            convertView = inflater.inflate(R.layout.layout_itemlistview, parent, false);

            holder = new ViewHolder();
            holder.username = (TextView) convertView.findViewById(R.id.tv_username);
            holder.website = (TextView) convertView.findViewById(R.id.tv_website);
            holder.email = (TextView) convertView.findViewById(R.id.tv_email);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.email.setVisibility(View.GONE);

        User_ListView u = userArray.get(position);
        holder.username.setText(u.getUsername());
        holder.website.setText(u.getWebsite());
        Log.i("Teste", String.format("%s", u.getEmail()));

        if(u.getEmail() != null) {
            holder.email.setText(u.getEmail());
            holder.email.setVisibility(View.VISIBLE);
        }

        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }


    static class ViewHolder{
        TextView username;
        TextView website;
        TextView email;
    }
}
