package cn.lingox.android;

import android.test.InstrumentationTestCase;

import cn.lingox.android.bin.traveler.TravelerManager;

/**
 * Created by lipeng21 on 2016/3/25.
 */
public class TestLocal extends InstrumentationTestCase {

    public void testLocalList(){
//        LocalManager.getInstatnce().loadLocalList(0);
        TravelerManager.getInstatnce().loadTravelerList(0);
    }
}
