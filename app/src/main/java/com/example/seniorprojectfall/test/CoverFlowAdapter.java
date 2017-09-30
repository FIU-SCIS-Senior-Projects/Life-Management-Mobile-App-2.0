package com.example.seniorprojectfall.test;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import java.util.ArrayList;

/**
 * Created by lazaro on 9/25/17.
 */

    public class CoverFlowAdapter extends BaseAdapter{

        private ArrayList<Joy2> data;  //contains all the activities
        private AppCompatActivity activity;
        private ArrayList<String> n = new ArrayList<>(); //contains the activities the user will selects
        int positionSaver = 0;
        static int counter = 0;



        public CoverFlowAdapter(AppCompatActivity context, ArrayList<Joy2> objects) {
            this.activity = context;
            this.data = objects;

        }

        @Override
        public int getCount() {

            return data.size();
        }


        @Override
        public Joy2 getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder;

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item_flow_view, null, false);

                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.gameImage.setImageResource(data.get(position).getImageSource());
            viewHolder.gameName.setText(data.get(position).getName());

            convertView.setOnClickListener(onClickListener(position));

            return convertView;
        }

        private View.OnClickListener onClickListener(final int position) {
            return new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    final Dialog dialog = new Dialog(activity);
                    dialog.setContentView(R.layout.dialog_joy_info);
                    dialog.setCancelable(true); // dismiss when touching outside
                    dialog.setTitle("Details");

                    ++counter;

                    n.add(data.get(position).getName().toString());
                    //System.out.println("TESTING2 " + n.size() + " ---- " + n.get(0).toString());

                    if(counter == 2){

                        counter = 0;
                        TextView text2 = (TextView) dialog.findViewById(R.id.name2);
                        text2.setText(getItem(position).getName());
                        ImageView image2 = (ImageView) dialog.findViewById(R.id.image2);
                        image2.setImageResource(getItem(position).getImageSource());

                        TextView text1 = (TextView) dialog.findViewById(R.id.name);
                        text1.setText(getItem(positionSaver).getName());
                        ImageView image1 = (ImageView) dialog.findViewById(R.id.image);
                        image1.setImageResource(getItem(positionSaver).getImageSource());

                        dialog.show();

                        Button continueBtn = dialog.findViewById(R.id.continueButtonjoyDialog);

                        continueBtn.setOnClickListener(new View.OnClickListener() {

                            public void onClick(View v) {
                                //Toast.makeText(MainActivity.this, "It works",Toast.LENGTH_LONG).show();
                                dialog.dismiss();
                            }
                        });

                    }


                    positionSaver = position;

                }
            };
        }


        private static class ViewHolder {
            private TextView gameName;
            private ImageView gameImage;

            public ViewHolder(View v) {
                gameImage = (ImageView) v.findViewById(R.id.image);
                gameName = (TextView) v.findViewById(R.id.name);
            }
        }

}

