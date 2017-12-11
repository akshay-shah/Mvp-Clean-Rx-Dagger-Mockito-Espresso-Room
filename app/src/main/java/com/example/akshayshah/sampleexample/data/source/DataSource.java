package com.example.akshayshah.sampleexample.data.source;

import com.example.akshayshah.sampleexample.data.User;

/**
 * Created by akshay.shah on 08/12/17.
 */

public interface DataSource {
    interface UserPutCallback{
        void onInsertSuccess();
        void onInsertFail();
    }
    interface UserRemoveCallback{
        void onRemoveSuccess();
        void onRemoveFail();
    }
    void putUser(User user,UserPutCallback callback);
    void removeUser(User user,UserRemoveCallback callback);
}
