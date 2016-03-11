package com.example.yjj.simple.data.entity.github;

import com.google.gson.annotations.SerializedName;

/**
 * @author:YJJ
 * @date:2016/3/9
 * @email:yangjianjun@117go.com
 */
public class Repo {

    public long id;
    public String name;
    public String html_url;
    @SerializedName("stargazers_count")
    public long stars;
    public long forks;
    public String language;

}
