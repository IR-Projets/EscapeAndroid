package editor;

import java.net.URISyntaxException;

import com.example.escapeandroid.R;
import com.example.escapeandroid.R.layout;
import com.example.escapeandroid.R.menu;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Files;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class SelectFile extends Activity {

	private Spinner spinner;
	private String level;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_file);
		level = getIntent().getStringExtra("level");
		initSpinner();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.select_file, menu);
		return true;
	}
	
	
	private void initSpinner(){
		spinner = (Spinner) findViewById(R.id.spinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.planets_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
	}
	
	public void mapSelected(View view){
		String selectedItem = (String)spinner.getSelectedItem();
		Intent intent = new Intent(this, EditorActivity.class);
		intent.putExtra("map", selectedItem);
		intent.putExtra("level", level);
		startActivity(intent);
	}
	
	
	
	/*
	 * 	LES FILE CHOOSER (pas implémenté mais quand même la)
	 */	
	public void selectFile(View view){
		showFileChooser();
	}
	
	
	private static final int FILE_SELECT_CODE = 0;

	private void showFileChooser() {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		//intent.setType("*/*");
		intent.setType("file/*");
		//intent.addCategory(Intent.CATEGORY_BROWSABLE);

		try {
			startActivityForResult(Intent.createChooser(intent, "Select a File to Upload"), FILE_SELECT_CODE);
		} catch (android.content.ActivityNotFoundException ex) {
			// Potentially direct the user to the Market with a Dialog
			Toast.makeText(this, "Please install a File Manager.", Toast.LENGTH_SHORT).show();
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case FILE_SELECT_CODE:
			if (resultCode == RESULT_OK) {
				Uri uri = data.getData();
				String path = uri.toString();
			}
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	public static String getPath(Context context, Uri uri) throws URISyntaxException {
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = { "_data" };
            Cursor cursor = null;

            try {
                cursor = context.getContentResolver().query(uri, projection, null, null, null);
                int column_index = cursor
                .getColumnIndexOrThrow("_data");
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
                // Eat it
            }
        }

        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

}
