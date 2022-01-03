package com.example.mo_v;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class AppExecutors {

    private static AppExecutors instance;

    public static AppExecutors getInstance(){
        if(instance==null){
            instance=new AppExecutors();

        }
        return instance;

    }

    private ScheduledExecutorService mNetworkIO= Executors.newScheduledThreadPool(1);

    public ScheduledExecutorService getmNetworkIO(){
        return mNetworkIO;

    }

}
