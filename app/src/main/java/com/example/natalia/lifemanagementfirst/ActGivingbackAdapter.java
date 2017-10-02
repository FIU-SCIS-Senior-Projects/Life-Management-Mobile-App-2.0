package com.example.natalia.lifemanagementfirst;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

/**
 * Created by Natalia on 9/28/2017.
 */

public class ActGivingbackAdapter extends BaseAdapter {

    private List<ActGivingback> actGivingbackList;
    private AppCompatActivity activity;
    private int numOfActSelected;
    private Dialog dialog;   //make it final if do not want to permit user to reselect activities
    private TextView text1;
    private TextView text2;
    private ImageView image1;
    private ImageView image2;


    public ActGivingbackAdapter(AppCompatActivity context, List<ActGivingback> actGivingbackList) {
        this.actGivingbackList = actGivingbackList;
        this.activity = context;
        this.numOfActSelected = 0;
        dialog = new Dialog(activity);
        dialog.setContentView(R.layout.dialog_actgivingback_info);
        dialog.setCancelable(true); //dismiss when touching outside
        dialog.setTitle("Activities Selected");
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return actGivingbackList.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public ActGivingback getItem(int position) {
        return actGivingbackList.get(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.layout_item_givingback, null, false);

            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.actGivingbackImage.setImageResource(actGivingbackList.get(position).getImageSource());
        viewHolder.actGivingbackName.setText(actGivingbackList.get(position).getName());

        convertView.setOnClickListener(onClickListener(position));

        return convertView;
    }

    private View.OnClickListener onClickListener(final int position){
        return new View.OnClickListener() {

            @Override
            public void onClick(View v){
                numOfActSelected++;

                if (numOfActSelected == 1) {
                    text1 = (TextView) dialog.findViewById(R.id.label1Givingback);
                    text1.setText(getItem(position).getName());
                    image1 = (ImageView) dialog.findViewById(R.id.image1Givingback);
                    image1.setImageResource(getItem(position).getImageSource());
                }
                else if (numOfActSelected == 2)
                {
                    // Check if the second selected activity is not the same as first selected one
                    text2 = (TextView) dialog.findViewById(R.id.label2Givingback);
                    text2.setText(getItem(position).getName());
                    //TextView text1 = (TextView) dialog.findViewById(R.id.label1Givingback);
                    if (text1.getText().equals(text2.getText()))
                    {
                        Toast.makeText(activity,"Choose activity #1 that is different from activity #2",Toast.LENGTH_LONG).show();
                        numOfActSelected=1;
                    }
                    else {

                        image2 = (ImageView) dialog.findViewById(R.id.image2Givingback);
                        image2.setImageResource(getItem(position).getImageSource());
                        numOfActSelected = 0; // do not use it if do not want allow user reselect activities
                        text1 = null;
                        text2 = null;
                        image1 = null;
                        image2 = null;
                        dialog.show();
                    }
                }


            }
        };
    }


    private static class ViewHolder {
        private TextView actGivingbackName;
        private ImageView actGivingbackImage;

        public ViewHolder(View v){
            actGivingbackImage = (ImageView)v.findViewById(R.id.imageGivingback);
            actGivingbackName = (TextView)v.findViewById(R.id.labelGivingback);
        }
    }
}
