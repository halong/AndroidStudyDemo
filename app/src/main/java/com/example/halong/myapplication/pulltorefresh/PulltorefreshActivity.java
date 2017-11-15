package com.example.halong.myapplication.pulltorefresh;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.halong.myapplication.R;

import java.util.List;

public class PulltorefreshActivity extends AppCompatActivity {
    private LinearLayout header;
    private ListView listView;
    private ViewGroup.MarginLayoutParams marginLayoutParams;
    private static int MyTopMargin;
    private boolean ablePull = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulltorefresh);

        DataInterface dataInterface = new DataProvider(new DataProvider.DataCallback() {
            @Override
            public void updateData(List<String> list) {
                updateView(list);
            }
        });

        initView(dataInterface);
    }


    private void updateView(List<String> list) {

    }

    private void initView(DataInterface dataInterface) {
        header = (LinearLayout) findViewById(R.id.header);
        listView = (ListView) findViewById(R.id.listview);

        marginLayoutParams = (ViewGroup.MarginLayoutParams) header.getLayoutParams();
        MyTopMargin = marginLayoutParams.topMargin;

        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataInterface.getInitListData()));

        listView.setOnTouchListener(new View.OnTouchListener() {
            float startY;
            float endY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // 如果ListView不到顶 就不触发下拉
                if (!isTopOfListView(listView) || !ablePull) {
                    return false;
                }
                // 根据MotionEven的变化改变TextView的topMargin实现下拉
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startY = event.getRawY();
                        break;

                    case MotionEvent.ACTION_MOVE:
                        endY = event.getRawY();
                        //通过改变marginLayoutParams.topMargin实现布局移动
                        marginLayoutParams.topMargin += (int) ((endY - startY) * 1 / 2);
                        startY = endY;

                        if (marginLayoutParams.topMargin <= MyTopMargin) {// 未下拉状态
                            marginLayoutParams.topMargin = MyTopMargin;
                            return false;
                        } else {
                            header.setLayoutParams(marginLayoutParams);
                        }
                        break;

                    case MotionEvent.ACTION_UP:
                        // 未下拉状态
                        if (marginLayoutParams.topMargin <= MyTopMargin) {
                            return false;
                        } else {
                            new RefreshComeback(marginLayoutParams.topMargin).execute();
                        }

                        break;
                }
                return true;
            }
        });
    }

    /**
     * 判断ListView是否下拉到顶部
     */
    public boolean isTopOfListView(ListView l) {
        View v = l.getChildAt(0);
        if (v == null) {
            return true;
        } else if (v.getTop() == 0 && l.getFirstVisiblePosition() == 0) {
            return true;
        }
        return false;
    }


    // 回滚线程
    class RefreshComeback extends AsyncTask<Void, Integer, Void> {
        int topMargin;

        public RefreshComeback(int topMargin) {
            this.topMargin = topMargin;
        }

        @Override
        protected Void doInBackground(Void... params) {
            while (topMargin > MyTopMargin) {
                //刷新条件
                if (topMargin == 0 || topMargin == 1 || topMargin == -1) {
                    ablePull = false;
                    topMargin = -2;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    ablePull = true;
                    topMargin -= 2;
                    publishProgress(topMargin);
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }


            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // TODO Auto-generated method stub
            super.onProgressUpdate(values);
            marginLayoutParams.topMargin = values[0];
            header.setLayoutParams(marginLayoutParams);
        }
    }

    interface DataInterface {
        List<String> getInitListData();
    }
}
