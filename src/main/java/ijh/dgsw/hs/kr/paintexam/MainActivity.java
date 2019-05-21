package ijh.dgsw.hs.kr.paintexam;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    private DrawingView drawingView;
    private RadioGroup colorGr;
    private RadioButton color1;
    private RadioButton color2;
    private RadioButton color3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawingView = findViewById(R.id.drawingView);

        color1 = findViewById(R.id.radioButton);
        color2 = findViewById(R.id.radioButton2);
        color3 = findViewById(R.id.radioButton3);

        colorGr = findViewById(R.id.selColorGroup);
        colorGr.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int color = 0;

                if(checkedId == color1.getId())
                    color = R.color.color1;
                else if(checkedId == color2.getId())
                    color = R.color.color2;
                else if(checkedId == color3.getId())
                    color = R.color.color3;

                drawingView.setColor(color);
            }
        });
    }

    @Override
    public void onBackPressed() {
        drawingView.setPathBack();
    }

    // 파일 저장
    /*
    public void onSave(){
        try{
            drawingView.setDrawingCacheEnabled(true);
            File file = new File("/");
            FileOutputStream outputStream = new FileOutputStream(file);
            Bitmap bitmap = drawingView.getDrawingCache();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);

            Toast.makeText(this, "파일 저장 성공", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException fnfe){
            fnfe.printStackTrace();
            Toast.makeText(this, "파일 저장 실패", Toast.LENGTH_SHORT).show();
        }
    }
    */
}
