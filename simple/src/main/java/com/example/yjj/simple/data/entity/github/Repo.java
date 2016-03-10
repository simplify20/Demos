package com.example.yjj.simple.data.entity.github;

/**
 * @author:YJJ
 * @date:2016/3/9
 * @email:yangjianjun@117go.com
 */
public class Repo {

    public final long id;
    public final String name;
    public final String html_url;

    public Repo(long id, String name, String html_url) {
        this.id = id;
        this.name = name;
        this.html_url = html_url;
    }
}
