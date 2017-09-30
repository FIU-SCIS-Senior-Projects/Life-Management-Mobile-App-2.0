package com.example.seniorprojectfall.test;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.sccomponents.gauges.ScArcGauge;
import com.sccomponents.gauges.ScCopier;
import com.sccomponents.gauges.ScGauge;
import com.sccomponents.gauges.ScNotches;
import com.sccomponents.gauges.ScPointer;
import com.sccomponents.gauges.ScWriter;

public class gaugeMain extends AppCompatActivity {

        private final static int NOTCHES_COUNT = 40;
        private final static int NOTCHES_WIDTH = 10;
        private final static int NOTCHES_LENGTH = 18;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.gauge);

            // Find the components
            final TextView counter = (TextView) gaugeMain.this.findViewById(R.id.counter);
            ScArcGauge gauge = (ScArcGauge) this.findViewById(R.id.gauge);

            // If you set the value from the xml that not produce an event so I will change the
            // value from code.
            gauge.setHighValue(60);

            // Each time I will change the value I must write it inside the counter text.
            gauge.setOnEventListener(new ScGauge.OnEventListener() {
                @Override
                public void onValueChange(float lowValue, float highValue) {
                    counter.setText((int) highValue + "%");
                }
            });
        }
    }

