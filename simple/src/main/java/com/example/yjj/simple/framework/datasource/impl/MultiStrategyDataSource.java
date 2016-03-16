package com.example.yjj.simple.framework.datasource.impl;

import com.example.yjj.simple.framework.IParameter;
import com.example.yjj.simple.framework.datasource.DataFetcher;
import com.example.yjj.simple.framework.datasource.DataSource;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 组合多个DataFetcher,实现从缓存，DB,文件系统或网络中获取数据
 *
 * @author:YJJ
 * @date:2016/3/16
 * @email:yangjianjun@117go.com
 */
public abstract class MultiStrategyDataSource<R, S> implements DataSource<S> {
    private Set<DataFetcher<R>> dataFetchers;
    private volatile boolean isClose;

    public MultiStrategyDataSource() {
    }

    public MultiStrategyDataSource(DataFetcher<R>... dataFetchers) {
        this.dataFetchers = new HashSet<>(Arrays.asList(dataFetchers));
    }

    //// FIXME: 2016/3/16 UI thread
    @Override
    public S getData(IParameter extra, String... values) {
        if (dataFetchers == null)
            return null;
        for (DataFetcher<R> dataFetcher : dataFetchers) {
            if (dataFetcher.isClose())
                break;
            S s = null;
            try {
                s = convert(dataFetcher.fetchData(extra, values));
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (s != null)
                return s;
        }
        return null;
    }

    @Override
    public void close() {
        if (!isClose) {
            Collection<DataFetcher<R>> closeFetchers = null;
            synchronized (this) {
                if (isClose) {
                    return;
                }
                isClose = true;
                closeFetchers = dataFetchers;
                dataFetchers = null;
            }
            if (closeFetchers == null)
                return;
            for (DataFetcher<R> dataFetcher : closeFetchers) {
                dataFetcher.close();
            }
        }
    }

    @Override
    public boolean isClose() {
        return isClose;
    }

    public void add(DataFetcher<R> dataFetcher) {
        if (dataFetcher != null || dataFetcher.isClose()) {
            return;
        }
        if (!isClose) {
            synchronized (this) {
                if (!isClose) {
                    if (dataFetchers == null) {
                        dataFetchers = new HashSet<>(4);
                    }
                    dataFetchers.add(dataFetcher);
                    return;
                }
            }
        }
    }

    public void remove(DataFetcher<R> dataFetcher) {
        if (!isClose) {
            boolean removed = false;
            synchronized (this) {
                if (isClose)
                    return;
                removed = dataFetchers.remove(dataFetcher);
            }
            if (removed) {
                dataFetcher.close();
            }
        }
    }

    public abstract S convert(R input);


}
