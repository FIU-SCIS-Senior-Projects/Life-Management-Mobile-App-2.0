package com.example.natalia.lifemanagementfirst;


        import android.app.Activity;
        import android.support.annotation.NonNull;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.TextView;
        import java.util.List;

public class UserList extends ArrayAdapter<User> {

    private Activity context;
    List<User> artists;

    public UserList(Activity context, List<User> artists) {
        super(context, R.layout.layout_user_list, artists);
        this.context = context;
        this.artists = artists;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_user_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewGenre = (TextView) listViewItem.findViewById(R.id.textViewGenre);

        User artist = artists.get(position);
        textViewName.setText(artist.getUsername());
        textViewGenre.setText(artist.getPassword());

        return listViewItem;
    }
}