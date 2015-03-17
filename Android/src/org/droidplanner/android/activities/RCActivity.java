package org.droidplanner.android.activities;

import java.util.Arrays;

import org.droidplanner.R;
import org.droidplanner.android.DroidPlannerApp;
import org.droidplanner.android.rc.joystickcontroller.JoyStickClass;
import org.droidplanner.core.MAVLink.MavLinkRC;
import org.droidplanner.core.model.Drone;
import org.droidplanner.android.helpers.*;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class RCActivity extends Activity {
	RelativeLayout layout_joystick, layout_joystick2;
	ImageView image_joystick, image_border;
	TextView textView1, textView2, textView3, textView4, textView5, textView11, textView22, textView33, textView44, textView55;
	
	private Button armButton;
	private Drone drone;
	
	JoyStickClass js, js2;
	RcOutput ro;
	
	int[] rcOutputs = new int[]{100,200,300,400,500,600,700,800};
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rc);
        
        DroidPlannerApp droidPlannerApp = (DroidPlannerApp) getApplication();
        drone = droidPlannerApp.getDrone();
        
        //ro = new RcOutput(drone, getApplicationContext());
        
        textView1 = (TextView)findViewById(R.id.textView1);
        textView2 = (TextView)findViewById(R.id.textView2);
        textView3 = (TextView)findViewById(R.id.textView3);
        textView4 = (TextView)findViewById(R.id.textView4);
        textView5 = (TextView)findViewById(R.id.textView5);
        
        textView11 = (TextView)findViewById(R.id.textView11);
        textView22 = (TextView)findViewById(R.id.textView22);
        textView33 = (TextView)findViewById(R.id.textView33);
        textView44 = (TextView)findViewById(R.id.textView44);
        textView55 = (TextView)findViewById(R.id.textView55);
        
	    layout_joystick = (RelativeLayout)findViewById(R.id.layout_joystick);
	    layout_joystick2 = (RelativeLayout)findViewById(R.id.layout_joystick2);
	    
	    armButton = (Button)findViewById(R.id.ArmButton);
	
	    armButton.setOnClickListener(new Button.OnClickListener(){ 
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
            	Toast.makeText(v.getContext(), "Arm", Toast.LENGTH_SHORT).show();
            	MavLinkRC.sendRcOverrideMsg(drone, rcOutputs);
            	//MavLinkArm.sendArmMessage(drone, true);
            }         
        });   
	    
        js = new JoyStickClass(getApplicationContext()
        		, layout_joystick, R.drawable.image_button);
	    js.setStickSize(75, 75);
	    js.setLayoutSize(250, 250);
	    js.setLayoutAlpha(75);
	    js.setStickAlpha(50);
	    js.setOffset(40);
	    js.setMinimumDistance(25);

        js2 = new JoyStickClass(getApplicationContext()
        		, layout_joystick2, R.drawable.image_button);
	    js2.setStickSize(75, 75);
	    js2.setLayoutSize(250, 250);
	    js2.setLayoutAlpha(75);
	    js2.setStickAlpha(50);
	    js2.setOffset(40);
	    js2.setMinimumDistance(25);
	    //js2.setY();
	    //js2.draw();

		//textView11.setText("X : " + String.valueOf(js2.getX()));
		//textView22.setText("Y : " + String.valueOf(js2.getY()));
		//textView33.setText("Angle : " + String.valueOf(js2.getAngle()));
		//textView44.setText("Distance : " + String.valueOf(js2.getDistance()));
	    
	    layout_joystick.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				js.drawStick(arg1);
				if(arg1.getAction() == MotionEvent.ACTION_DOWN
						|| arg1.getAction() == MotionEvent.ACTION_MOVE) {
					textView1.setText("X : " + String.valueOf(js.getX()));
					textView2.setText("Y : " + String.valueOf(js.getY()));
					textView3.setText("Angle : " + String.valueOf(js.getAngle()));
					textView4.setText("Distance : " + String.valueOf(js.getDistance()));
					
					int direction = js.get8Direction();
					if(direction == JoyStickClass.STICK_UP) {
						textView5.setText("Direction : Up");
					} else if(direction == JoyStickClass.STICK_UPRIGHT) {
						textView5.setText("Direction : Up Right");
					} else if(direction == JoyStickClass.STICK_RIGHT) {
						textView5.setText("Direction : Right");
					} else if(direction == JoyStickClass.STICK_DOWNRIGHT) {
						textView5.setText("Direction : Down Right");
					} else if(direction == JoyStickClass.STICK_DOWN) {
						textView5.setText("Direction : Down");
					} else if(direction == JoyStickClass.STICK_DOWNLEFT) {
						textView5.setText("Direction : Down Left");
					} else if(direction == JoyStickClass.STICK_LEFT) {
						textView5.setText("Direction : Left");
					} else if(direction == JoyStickClass.STICK_UPLEFT) {
						textView5.setText("Direction : Up Left");
					} else if(direction == JoyStickClass.STICK_NONE) {
						textView5.setText("Direction : Center");
					}
				} else if(arg1.getAction() == MotionEvent.ACTION_UP) {
					textView1.setText("X :");
					textView2.setText("Y :");
					textView3.setText("Angle :");
					textView4.setText("Distance :");
					textView5.setText("Direction :");
				}
				return true;
			}
        });
	    
	    layout_joystick2.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg2, MotionEvent arg3) {
				js2.drawStick(arg3);
				if(arg3.getAction() == MotionEvent.ACTION_DOWN
						|| arg3.getAction() == MotionEvent.ACTION_MOVE) {					
					textView11.setText("X : " + String.valueOf(js2.getX()));
					textView22.setText("Y : " + String.valueOf(js2.getY()));
					textView33.setText("Angle : " + String.valueOf(js2.getAngle()));
					textView44.setText("Distance : " + String.valueOf(js2.getDistance()));
					
					int direction = js2.get8Direction();
					if(direction == JoyStickClass.STICK_UP) {
						textView55.setText("Direction : Up");
					} else if(direction == JoyStickClass.STICK_UPRIGHT) {
						textView55.setText("Direction : Up Right");
					} else if(direction == JoyStickClass.STICK_RIGHT) {
						textView55.setText("Direction : Right");
					} else if(direction == JoyStickClass.STICK_DOWNRIGHT) {
						textView55.setText("Direction : Down Right");
					} else if(direction == JoyStickClass.STICK_DOWN) {
						textView55.setText("Direction : Down");
					} else if(direction == JoyStickClass.STICK_DOWNLEFT) {
						textView55.setText("Direction : Down Left");
					} else if(direction == JoyStickClass.STICK_LEFT) {
						textView55.setText("Direction : Left");
					} else if(direction == JoyStickClass.STICK_UPLEFT) {
						textView55.setText("Direction : Up Left");
					} else if(direction == JoyStickClass.STICK_NONE) {
						textView55.setText("Direction : Center");
					}
				} else if(arg3.getAction() == MotionEvent.ACTION_UP) {
					textView11.setText("X :");
					textView22.setText("Y :");
					textView33.setText("Angle :");
					textView44.setText("Distance :");
					textView55.setText("Direction :");
				}
				return true;
			}
        });
    } 	
}
