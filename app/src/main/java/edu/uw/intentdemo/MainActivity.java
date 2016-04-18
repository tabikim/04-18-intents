package edu.uw.intentdemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "**Main**";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View launchButton = findViewById(R.id.btnLaunch);
        launchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v(TAG, "Launch button pressed");

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                // careful of what class you are in! (Wanted to refer to MainActivity not onClickListener)
                // MainActivity to SecondActivity class

                // Extras a like bundles to add more data to intent
                intent.putExtra("edu.uw.intentdemo.message", "Hello from MainActivity");

                startActivity(intent); // starts intent

                //sendBroadcast(intent);
            }
        });

    }

    // Note: intents - message object to communicate between one activity to another (or another context)
    // once received, something can be done with it (response, change, send back, etc)


    public void callNumber(View v) {
        Log.v(TAG, "Call button pressed");

        // implicit intent - specify action and data
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:206-685-1622"));

        if(intent.resolveActivity(getPackageManager()) != null) { // check to see if there is an activity to deliver to
            startActivity(intent);
        }
    }

    private static int REQUEST_PICTURE_CODE = 1;

    public void takePicture(View v) {
        Log.v(TAG, "Camera button pressed");

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); // "anybody who can capture an image, please respond"

        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_PICTURE_CODE); // when start activity, give me a result/intent back
        }
    }

    public void sendMessage(View v) {
        Log.v(TAG, "Message button pressed");

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("5554", null, "This a message", null, null);

    }

    @Override // what will happen when you get a result
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_PICTURE_CODE && resultCode == RESULT_OK) {
            // i got picture!
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            ImageView imageView = (ImageView)findViewById(R.id.imgThumbnail);
            imageView.setImageBitmap(imageBitmap);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
