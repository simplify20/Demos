package com.example.yjj.simple.presentation.bindingadapter;

import android.databinding.BindingAdapter;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.example.yjj.simple.SimleApplication;

/**
 * @author:YJJ
 * @date:2016/3/14
 * @email:yangjianjun@117go.com
 */
public class ImageAdapter {

    @BindingAdapter(value = {"imgUrl", "placeHolder"})
    public void setImageUrl(ImageView imageView, String url, @DrawableRes int placeHolder) {
        SimleApplication.getPicasso()
                .load(url)
                .placeholder(placeHolder)
                .into(imageView);
    }
    @BindingAdapter(value = {"imgUrl"})
    public void setImageUrl(ImageView imageView, String url) {
        SimleApplication.getPicasso()
                .load(url)
                .into(imageView);
    }
}
