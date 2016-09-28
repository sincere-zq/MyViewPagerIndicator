package sincere.com.myviewpagerindicator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sincere.com.myviewpagerindicator.fragment.ViewPagerIndicatorFragment;
import sincere.com.myviewpagerindicator.view.MyViewPagerIndicator;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{
    private MyViewPagerIndicator indicator;
    private ViewPager mViewPager;
    private List<String> mTitle = Arrays.asList("短信", "电话", "邮件","短信1", "电话1", "邮件1");
    private List<ViewPagerIndicatorFragment> fragments = new ArrayList<>();
    private FragmentPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initData();
        indicator.setVisibleTabCount(3);
        indicator.setTabItemTitles(mTitle);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(this);
    }

    private void initData() {
        for (int i = 0; i < mTitle.size(); i++) {
            ViewPagerIndicatorFragment fragment = ViewPagerIndicatorFragment.newInstance(mTitle.get(i));
            fragments.add(fragment);
        }

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments == null ? 0 : fragments.size();
            }
        };
    }

    private void initViews() {
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        indicator = (MyViewPagerIndicator) findViewById(R.id.indicator);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //偏移量tabWidth*positionOffset+positon*tabWidth
        indicator.scroll(position,positionOffset);
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
