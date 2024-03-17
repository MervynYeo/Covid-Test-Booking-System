package com.fit3077.covidtesting.testsite.search;

import com.fit3077.covidtesting.testsite.TestSite;
import com.fit3077.covidtesting.testsite.TestSiteSystem;

import java.util.ArrayList;
import java.util.List;

public interface Search {

    ArrayList<TestSite> execute();
    default List<TestSite> getTestSites(){
        TestSiteSystem testSiteSystem=new TestSiteSystem();
        return testSiteSystem.getTestSites();
    };
}
