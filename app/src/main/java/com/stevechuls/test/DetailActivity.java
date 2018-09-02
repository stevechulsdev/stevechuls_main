package com.stevechuls.test;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private Button mBackBtn;
    private Button mOneBtn;
    private Button mTwoBtn;
    private Button mThreeBtn;
    private Button mFourBtn;

    private boolean mBtn_Flag1 = false;
    private boolean mBtn_Flag2 = false;
    private boolean mBtn_Flag3 = false;
    private boolean mBtn_Flag4 = false;

    private Drawable drawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String imageurl = intent.getStringExtra("imageurl");
//        byte[] bytes = intent.getByteArrayExtra("bitmap");
//        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), imageurl);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mBackBtn = (Button)findViewById(R.id.back_btn);
        mBackBtn.setOnClickListener(listener);
        mOneBtn = (Button)findViewById(R.id.one_btn);
        mOneBtn.setOnClickListener(listener);
        mTwoBtn = (Button)findViewById(R.id.two_btn);
        mTwoBtn.setOnClickListener(listener);
        mThreeBtn = (Button)findViewById(R.id.three_btn);
        mThreeBtn.setOnClickListener(listener);
        mFourBtn = (Button)findViewById(R.id.four_btn);
        mFourBtn.setOnClickListener(listener);

    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId())
            {
                case R.id.back_btn:
                    finish();
                    break;

                case R.id.one_btn:
                    if(mBtn_Flag1)
                    {
                        mOneBtn.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.white));
                        mBtn_Flag1 = false;
                    }
                    else
                    {
                        mOneBtn.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.yellow));
                        mBtn_Flag1 = true;
                    }
                    break;

                case R.id.two_btn:
                    if(mBtn_Flag2)
                    {
                        mTwoBtn.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.white));
                        mBtn_Flag2 = false;
                    }
                    else
                    {
                        mTwoBtn.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.yellow));
                        mBtn_Flag2 = true;
                    }
                    break;

                case R.id.three_btn:
                    if(mBtn_Flag3)
                    {
                        mThreeBtn.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.white));
                        mBtn_Flag3 = false;
                    }
                    else
                    {
                        mThreeBtn.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.yellow));
                        mBtn_Flag3 = true;
                    }
                    break;

                case R.id.four_btn:
                    if(mBtn_Flag4)
                    {
                        mFourBtn.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.white));
                        mBtn_Flag4 = false;
                    }
                    else
                    {
                        mFourBtn.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.yellow));
                        mBtn_Flag4 = true;
                    }
                    break;

            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

//            LinearLayout linearLayout = (LinearLayout)getActivity().findViewById(R.id.bottom_bar);
//            linearLayout.setBackgroundColor(Color.parseColor("#0000CC"));

            return rootView;
        }
    }

    public static class PlaceholderFragment2 extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment2() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment2 newInstance(int sectionNumber, String imageurl) {
            PlaceholderFragment2 fragment = new PlaceholderFragment2();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
//            args.putParcelable("bitmap", bitmap);
            args.putString("imageurl", imageurl);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_detail2, container, false);
            Bundle bundle = getArguments();
//            Bitmap bitmap = bundle.getParcelable("bitmap");

            ImageView imageView = (ImageView)rootView.findViewById(R.id.detail_imageview);

            String url = "http://sangchul.ipdisk.co.kr:7364/image/cartoon/";

            Picasso.with(getContext())
                    .load(url + bundle.getString("imageurl"))
                    .into(imageView);

            return rootView;
        }
    }

    public static class PlaceholderFragment3 extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment3() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment3 newInstance(int sectionNumber) {
            PlaceholderFragment3 fragment = new PlaceholderFragment3();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_detail3, container, false);

//            LinearLayout linearLayout = (LinearLayout)getActivity().findViewById(R.id.bottom_bar);
//            linearLayout.setBackgroundColor(Color.TRANSPARENT);

            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private String imageurl;

        public SectionsPagerAdapter(FragmentManager fm, String imageurl) {
            super(fm);
            this.imageurl = imageurl;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position)
            {
                case 0:
                    return PlaceholderFragment.newInstance(position);

                case 1:
                    return PlaceholderFragment2.newInstance(position, imageurl);

                case 2:
                    return PlaceholderFragment3.newInstance(position);
            }
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
//            return PlaceholderFragment.newInstance(position + 1);
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }
}
