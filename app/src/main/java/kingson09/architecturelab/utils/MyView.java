package kingson09.architecturelab.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * TODO: document your custom view class.
 */
@SuppressLint("AppCompatCustomView")
public class MyView extends TextView {
  public MyView(Context context) {
    super(context);
  }

  public MyView(Context context, AttributeSet attrs) {
    super(context, attrs);

  }

  public MyView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);

  }

  public void dispatchWindowFocusChanged(boolean hasFocus) {
    onWindowFocusChanged(hasFocus);
    System.out.println("View  dispatchWindowFocusChanged");
  }

  @Override
  public boolean dispatchTouchEvent(MotionEvent event) {
    System.out.println("View dispatchTouchEvent");
    return super.dispatchTouchEvent(event);
  }

  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
  }

  protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    super.onLayout(changed, left, top, right, bottom);
  }

  public void draw(Canvas canvas) {
    super.draw(canvas);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
  }

}
