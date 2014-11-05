package com.crowdtogo.crowdie.crowdtogo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;
import com.astuetz.PagerSlidingTabStrip;

public class HomeFragment extends Fragment {

    public static final String TAG = HomeFragment.class
            .getSimpleName();

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) view
                .findViewById(R.id.tabs);
        ViewPager pager = (ViewPager) view.findViewById(R.id.pager);
        MyPagerAdapter adapter = new MyPagerAdapter(getChildFragmentManager());
        pager.setAdapter(adapter);
        tabs.setViewPager(pager);
        tabs.setTextColor(getResources().getColor(R.color.white));
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        private final String[] TITLES = { "DELIVERY REQUESTS", "ONGOING DELIVERY"};

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public SherlockFragment getItem(int position) {

            switch (position) {
                case 0:
                    // tab 1 delivery requests fragment
                    return new DeliveryRequestFragment();
                case 1:
                    // tab2 ongoing delivery fragment
                    return new OngoingDeliveryFragment();
            }

            return null;

        }

    }

}
