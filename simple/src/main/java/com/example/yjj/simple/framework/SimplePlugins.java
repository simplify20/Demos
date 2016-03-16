package com.example.yjj.simple.framework;

import com.example.yjj.simple.framework.util.RepositoryHook;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author:YJJ
 * @date:2016/3/16
 * @email:yangjianjun@117go.com
 */
public class SimplePlugins {

    private static SimplePlugins INSTANCE = new SimplePlugins();
    private static AtomicReference<RepositoryHook> repositoryHookAtomicReference = new AtomicReference<>();
    static final RepositoryHook DEFAULT_REPOSITORY_HOOK = new RepositoryHook() {
    };

    SimplePlugins() {
    }

    public static SimplePlugins getInstance() {
        return INSTANCE;
    }

    public static void registerRepositoryHook(RepositoryHook repositoryHook) {
        if (!repositoryHookAtomicReference.compareAndSet(null, repositoryHook)) {
            throw new IllegalStateException("Another strategy was already registered: " + repositoryHookAtomicReference.get());
        }
    }

    public static RepositoryHook getRepositoryHook() {
        if (repositoryHookAtomicReference.get() == null) {
            repositoryHookAtomicReference.compareAndSet(null, DEFAULT_REPOSITORY_HOOK);
        }
        return repositoryHookAtomicReference.get();
    }


}
