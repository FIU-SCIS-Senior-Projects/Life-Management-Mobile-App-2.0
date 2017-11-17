package com.example.natalia.lifemanagementfirst;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.content.Intent;
        import android.support.annotation.Nullable;
        import android.support.v4.view.GravityCompat;
        import android.support.v4.widget.DrawerLayout;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;
        import android.widget.Button;

public class cycleActivity extends AppCompatActivity {

    static String element_static;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cycle);

        Intent in = getIntent();
        element_static = in.getExtras().getString("theelement");
        String[] s = element_static.split(" ");

        System.out.println("queahira element " + element_static);

        String s6detail = "";
        String s7detail = "";

        if(s[6].length()==7){
            s6detail = "0" + s[6].substring(1,2) + "/" + s[6].substring(2,4) + "/" + s[6].substring(6);
        }else{
            s6detail = s[6].substring(0,2) + "/" + s[6].substring(2,4) + "/" + s[6].substring(6);
        }


        if(s[2].length()==7){
            s7detail = "0" + s[2].substring(1,2) + "/" + s[2].substring(2,4) + "/" + s[2].substring(6);
        }else{
            s7detail = s[2].substring(0,2) + "/" + s[2].substring(2,4) + "/" + s[2].substring(6);
        }


        TextView g = (TextView) findViewById(R.id.textView3);

        g.setText("\n\n"
                + " Activity Score: " + "\t " + s[0] + "\n"
                + " Actual Points:  " + "\t " + s[1] + "\n"
                + " Activity Name: " + "\t"+ s[3] + "\n"
                + " Target Points:  " + "\t "+ s[5] + "\n"
                + " Starting Date:  " + "\t "+ s7detail + "\n"
                + " Ending Date:   " + "\t  "+ s6detail);

        Button btn = (Button) findViewById(R.id.buttonleave);
        btn.setText("Return");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();


            }
        });
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();

    }

}
