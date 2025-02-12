package com.roni2024.ronisfirstappplication;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class NoamActivity2 extends AppCompatActivity {

    Button selectImageButton;
    ImageView imageView;

    // הגדרת ה-ActivityResultLauncher
    ActivityResultLauncher<Intent> activityResultLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if (result.getResultCode() == RESULT_OK) {
                                Intent data = result.getData();
                                if (data != null) {
                                    // הצגת התמונה ב-ImageView
                                    imageView.setImageURI(data.getData());
                                    Toast.makeText(NoamActivity2.this, "תמונה נבחרה בהצלחה!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
            );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noam2);

        // בדוק אם יש הרשאת מצלמה
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // אם אין הרשאה, בקש אותה
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
        }

        // חיבור כפתור לבחור תמונה
        selectImageButton = findViewById(R.id.selectImageButton); // מחבר את הכפתור שנמצא ב-XML
        imageView = findViewById(R.id.imageView); // מחבר את ה-ImageView להצגת התמונה

        selectImageButton.setOnClickListener(v -> {
            // יצירת אינטנט לפתיחת הגלריה
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            // הפעלת ה-Launcher שיפתח את הגלריה
            activityResultLauncher.launch(intent);
        });
    }
}
