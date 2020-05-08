package com.example.dell.jd.net;

public interface INetCallBack<T> {
    void onSuccess(T t);
    void onError(Throwable throwable);
}
