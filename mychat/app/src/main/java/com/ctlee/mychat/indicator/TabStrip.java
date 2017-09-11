package com.ctlee.mychat.indicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ctlee.mychat.R;

/**
 * Created by ctLee on 2017/9/8.
 */

public class TabStrip extends HorizontalScrollView {
    /**
     * 指示器容器
     */
    private LinearLayout container;
    /**
     * 指示器个数
     */
    private int tabCount;
    /**
     * 当前tab的位置，默认为0
     */
    private int currentPosition = 0;
    /**
     * 选中的tab位置
     */
    private int selectedPosition;
    private float currentPositionOffset = 0f;
    private int lastScrollX = 0;
    /**
     * LayoutParams用于添加指示器到指示器容器中使用，按等权重分配指示器宽度
     */
    private LinearLayout.LayoutParams expandedTabLayoutParams;
    /**
     * 指示器颜色
     */
    private int indicatorColor;
    /**
     * 文字颜色
     */
    private int textColor;
    /**
     * 文字大小
     */
    private int textSize;
    /**
     * 选中位置的文字大小
     */
    private int selectedTextSize;
    /**
     * 指示器高度
     */
    private int indicatorHeight;
    /**
     * 指示器左右间距
     */
    private int indicatorMargin;
    /**
     * ViewPager
     */
    private ViewPager viewPager;
    /**
     * ViewPager适配器
     */
    private PagerAdapter pagerAdapter;
    /**
     * pager改变监听器
     */
    private final PagerStateChangeListener pagerStateChangeListener =new PagerStateChangeListener();
    /**
     * 画笔
     */
    private Paint paint;
    /**
     * context
     */
    private Context context;

    public TabStrip(Context context) {
        super(context);
    }

    public TabStrip(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TabStrip(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TabStrip(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * 初始化
     * @param context
     * @param attrs
     * @param defStyleAttr
     * @param defStyleRes
     */
    private void init(Context context,AttributeSet attrs,int defStyleAttr,int defStyleRes){
        this.context = context;
        //取消横向滚动条
        setHorizontalScrollBarEnabled(false);
        //指示器容器初始化
        container = new LinearLayout(context);
        container.setOrientation(LinearLayout.HORIZONTAL);
        container.setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        //添加指示器到容器scrollview
        addView(container);
        //获取屏幕相关信息
        DisplayMetrics dm = getResources().getDisplayMetrics();
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TabStrip,defStyleAttr,defStyleRes);
        int n = typedArray.getIndexCount();
        for(int i = 0; i < n; i++){
            int attr = typedArray.getIndex(i);
            switch(attr){
                //指示器颜色，默认为黄色
                case R.styleable.TabStrip_indicatorColor:
                    indicatorColor = typedArray.getColor(attr, Color.YELLOW);
                    break;
                //指示器高度，默认为2
                case R.styleable.TabStrip_indicatorHeight:
                    indicatorHeight = typedArray.getDimensionPixelSize(attr,2);
                    break;
                //指示器默认左右间距，默认为20
                case R.styleable.TabStrip_indicatorMargin:
                    indicatorMargin = typedArray.getDimensionPixelSize(attr,20);
                    break;
                //文字颜色，默认为黑色
                case R.styleable.TabStrip_indicatorTextColor:
                    textColor = typedArray.getColor(attr,Color.BLACK);
                    break;
                //文字大小，默认为15
                case R.styleable.TabStrip_indicatorTextSize:
                    textSize = typedArray.getDimensionPixelSize(attr,(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,15,dm));
                    break;
                //选中项的文字大小，默认为18
                case R.styleable.TabStrip_indicatorSelectedTextSize:
                    selectedTextSize = typedArray.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,18,dm));
                    break;
                default :

                    break;

            }
            //typedArray回收
            typedArray.recycle();
            expandedTabLayoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1.0f);
            //初始化画笔
            paint = new Paint();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //如果指示器个数为0，直接结束绘画
        if(tabCount == 0){
            return;
        }
        //获取onMeasure后的高
        final int height = getHeight();
        /**
         * 画指示器下的线
         */
        //设置颜色
        paint.setColor(indicatorColor);
        //当前指示tab的位置
        View currentTab = container.getChildAt(currentPosition);
        //当前tab的左边相对父容器的左边距
        float leftPadding = currentTab.getPaddingLeft();
        //当前tab的左边相对父容器的右边距
        float rightPadding = currentTab.getPaddingRight();
        //如果出现位移
        if(currentPosition > 0f && currentPosition < tabCount -1){
            View nextTab = container.getChildAt(currentPosition+1);
            final float nextTabLeft = nextTab.getLeft();
            final float nextTabRight = nextTab.getRight();
            leftPadding = (currentPositionOffset * nextTabLeft) + ((1f - currentPositionOffset) * leftPadding);
            rightPadding = (currentPositionOffset * nextTabRight) + ((1f - currentPositionOffset) * rightPadding);
        }
        //绘制
        canvas.drawRect(leftPadding,height - indicatorHeight,rightPadding,height,paint);

    }

    /**
     * 设置viewpager
     * @param viewPager
     */
    private void setViewPager(ViewPager viewPager){
        this.viewPager = viewPager;
        if(pagerAdapter == null){
            throw new IllegalStateException("ViewPager does not has a adapter instance.");
        }else{
            pagerAdapter = viewPager.getAdapter();
        }
        viewPager.addOnAdapterChangeListener((ViewPager.OnAdapterChangeListener) pagerStateChangeListener);
        update();
    }

    /**
     * 更新界面
     */
    private void update(){
        //指示器容器移除所有子view
        container.removeAllViews();
        //获取指示器个数
        tabCount = pagerAdapter.getCount();
        //逐个添加指示器
        for (int i = 0;i < tabCount;i++){
            addTab(i,pagerAdapter.getPageTitle(i));
        }
        //更新tab样式
        updateTabStyle();
        //在oncreate中View.getWidth和View.getHeight无法获得一个view的高度和宽度，这是因为View组件布局要在onResume回调后完成。
        // 所以现在需要使用getViewTreeObserver().addOnGlobalLayoutListener()来获得宽度或者高度。
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                getViewTreeObserver().removeOnGlobalLayoutListener(this);
                currentPosition = viewPager.getCurrentItem();
                scrollToChild(currentPosition,0);
            }
        });
    }

    /**
     * 滑动ScrollView
     * @param position
     * @param offset
     */
    private void scrollToChild(int position,int offset){
        if(tabCount == 0){
            return;
        }
        int newScrollX = container.getChildAt(position).getScrollX()+offset;
        if(newScrollX != lastScrollX){
            lastScrollX = newScrollX;
            scrollTo(newScrollX,0);
        }
    }

    private void addTab(final int position,CharSequence title){
        TextView tvTab = new TextView(context);
        tvTab.setText(title);
        tvTab.setTextColor(textColor);
        tvTab.setTextSize(textSize);
        tvTab.setGravity(Gravity.CENTER);
        tvTab.setSingleLine();
        tvTab.setFocusable(true);
        tvTab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(position);
            }
        });
        tvTab.setPadding(indicatorMargin,0,indicatorMargin,0);
        container.addView(tvTab,position,expandedTabLayoutParams);
    }

    /**
     * 更新指示器样式
     */
    private void updateTabStyle(){
        for(int i = 0;i < tabCount;i++){
            TextView tab = (TextView) container.getChildAt(i);
            if(i == selectedPosition){
                //设置选中的指示器颜色和文字大小
                tab.setTextColor(indicatorColor);
                tab.setTextSize(selectedTextSize);
            }else{
                tab.setTextColor(textColor);
                tab.setTextSize(textSize);
            }
        }
    }

    /**
     * viewPager状态改变监听
     *
     */
    private class PagerStateChangeListener implements ViewPager.OnPageChangeListener {
        /**
         * 滚动时，只要处理指示器下方横线的滚动
         * @param position
         * @param positionOffset
         * @param positionOffsetPixels
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            currentPosition = position;
            currentPositionOffset = positionOffset;
            //处理指示器下方横线滚动
            scrollToChild(position, (int) (positionOffset*(container.getChildAt(position).getWidth())));
            invalidate();//invalidate()是用来刷新view的，必须在UI线程中进行工作。
        }

        /**
         * page滚动结束
         * @param position
         */
        @Override
        public void onPageSelected(int position) {
            //滚动结束后的位置
            selectedPosition = position;
            //更新指示器状态
            updateTabStyle();
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            //滑动状态为停止时
            if(state == ViewPager.SCROLL_STATE_IDLE){
                scrollToChild(viewPager.getCurrentItem(),0);
            }
        }
    }
}
