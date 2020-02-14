package com.komurdzhiev.task2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class FilmCellAdapter extends BaseAdapter {

    private Context context;
    private Films filmsPage;

    public FilmCellAdapter(Context context, Films filmPage) {
        this.context = context;
        this.filmsPage = filmPage;
    }

    public int getCount() {
        return filmsPage.getResults().size();
    }

    public Object getItem(int position) {
        return filmsPage.getResults().get(position);
    }

    public String getItemTitle(int position) {
        return filmsPage.getResults().get(position).getTitle();
    }

    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        String URL = filmsPage.getResults().get(position).getPoster().getImage();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.film_cell, parent, false);

            holder = new ViewHolder();
            holder.textView = (TextView) convertView.findViewById(R.id.txt_part);
            holder.imageView = (ImageView) convertView.findViewById(R.id.img_part);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textView.setText(filmsPage.getResults().get(position).getTitle());

        if (holder.imageView != null) {
            //new DownloadImage(holder.imageView).execute(URL);
            Picasso.get().load(URL).into(holder.imageView);
        }

        return convertView;
    }

    static class ViewHolder {
        TextView textView;
        ImageView imageView;
    }

}