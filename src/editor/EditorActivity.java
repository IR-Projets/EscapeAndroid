package editor;

import com.example.escapeandroid.R;

import android.app.Activity;
import android.content.ClipData;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class EditorActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editor);
		setupViews();
	}
	
	/*
	 * FOR DRAG AND DROP IN EDITOR...
	 */
	
	private void setupViews() {
		ImageView i1 = (ImageView) findViewById (R.id.imageView1);
		ImageView i2 = (ImageView) findViewById (R.id.imageView2);
		ImageView i3 = (ImageView) findViewById (R.id.imageView3);
		ImageView i4 = (ImageView) findViewById (R.id.imageView4);
		ImageView i5 = (ImageView) findViewById (R.id.imageView5);
		ImageView i6 = (ImageView) findViewById (R.id.imageView6);
		ImageView i7 = (ImageView) findViewById (R.id.imageView7);
		ImageView i8 = (ImageView) findViewById (R.id.imageView8);
		
		OnDragListener dragListener = new View.OnDragListener() {			
			@Override
			public boolean onDrag(View v, DragEvent event) {
				// Defines a variable to store the action type for the incoming event
		        final int action = event.getAction();

		        // Handles each of the expected events
		        switch(action) {

		            case DragEvent.ACTION_DRAG_STARTED:
		                break;

		            case DragEvent.ACTION_DRAG_ENTERED: 
		            	break;

		            case DragEvent.ACTION_DRAG_LOCATION:
		            	break;

		            case DragEvent.ACTION_DRAG_EXITED:
		            	break;

		           case DragEvent.ACTION_DROP:
		               // Dropped, reassign View to ViewGroup
		               View view = (View) event.getLocalState();
		               ViewGroup owner = (ViewGroup) view.getParent();
		               owner.removeView(view);
		               LinearLayout container = (LinearLayout) v;
		               container.addView(view);
		               view.setVisibility(View.VISIBLE);
		        	   break;

		           case DragEvent.ACTION_DRAG_ENDED:
		        	   break;

		           default:
		              Log.e("DragDrop Example","Unknown action type received by OnDragListener.");
		              break;
		        }
				return false;
			}
		};
		
		OnTouchListener touchListener = new View.OnTouchListener() {
			@Override
			    public boolean onTouch(View view, MotionEvent motionEvent) {
			      if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
			        ClipData data = ClipData.newPlainText("", "");
			        DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
			        view.startDrag(data, shadowBuilder, view, 0);
			        view.setVisibility(View.INVISIBLE);
			        return true;
			      } else {
			        return false;
			      }
			    }
			  };
		
			  
		i1.setOnTouchListener(touchListener);
		i2.setOnTouchListener(touchListener);
		i3.setOnTouchListener(touchListener);
		i4.setOnTouchListener(touchListener);
		i5.setOnTouchListener(touchListener);
		i6.setOnTouchListener(touchListener);
		i7.setOnTouchListener(touchListener);
		i8.setOnTouchListener(touchListener);
	}
	
	
}
