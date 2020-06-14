package com.example.a302;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class view_pager_adapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private int[] images; //= {R.drawable.test_view_1,R.drawable.test_view_2,R.drawable.test_view_3}

    public view_pager_adapter(Context context, int[] images) { //this is the function that is called where it will load the photos to be shown in the detail activity
        this.context = context;
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view ==o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) { //this adds the images to the image view
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.view_pager_layout, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.myImageView);
        imageView.setImageResource(images[position]);

        ViewPager viewPager = (ViewPager)container;
        viewPager.addView(view);
        return  view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) { // this destroys image when off screen
        ViewPager viewPager = (ViewPager)container;
        View view = (View)object;
        viewPager.removeView(view);
    }
}
