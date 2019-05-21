package ijh.dgsw.hs.kr.paintexam;

import android.Manifest;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class DrawingView extends AppCompatImageView implements View.OnTouchListener {

    private Paint paint;
    // private float x = 0, y = 0;
    private Path path;
    private ArrayList<Path> paths;
    private int color;
    private ArrayList<Integer> colors;

    private void init(){
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND); // 모서리 둥글게
        paint.setStrokeWidth(15); // 선 굵기
        paint.setAntiAlias(true);

        path = new Path();
        paths = new ArrayList<>();
        color = Color.BLACK;
        colors = new ArrayList<>();

        setOnTouchListener(this);
    }

    public void setColor(int color){
        path = new Path();
        this.color = color;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // canvas.drawColor(Color.WHITE);

        for(int i = 0; i < paths.size(); i++){
            Log.d("DV", "stored path " + i);
            paint.setColor(colors.get(i));
            canvas.drawPath(paths.get(i), paint);
        }
        paint.setColor(color);
        // canvas.drawCircle(x, y, 50, paint);
        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();

        switch (action){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                // x = event.getX();
                // y = event.getY();
                path.lineTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_UP:
                paths.add(path);
                colors.add(color);
                break;
        }

        invalidate();
        return false;
    }

    public void setPathBack(){
        if(paths.size() == 0)
            return;

        for(int i = paths.size(); i >= 0; i--){
            paths.remove(i);
            invalidate();
        }
    }

    public DrawingView(Context context) {
        super(context);
        init();
    }

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
}
