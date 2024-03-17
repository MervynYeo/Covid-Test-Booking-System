package com.fit3077.covidtesting.testsite;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fit3077.covidtesting.common.FIT3077Api;
import com.fit3077.covidtesting.common.JsonUtils;
import com.fit3077.covidtesting.common.SubSystem;

import java.util.Collections;
import java.util.List;

public class TestSiteSystem extends SubSystem {
    private final String URL_PATH = "/testing-site";

    public TestSiteSystem(){super.setUrlPath(URL_PATH);}

    public List<TestSite> getTestSites(){
        try {
            String response = FIT3077Api.get(this.urlPath);
            List<TestSite> testSites = JsonUtils.toObjectList(response, new TypeReference<List<TestSite>>() { });
            return testSites;
        } catch (Exception e) {
            System.out.println("Error in TestSiteSystem.getTestSites: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public TestSite getTestSite(String id) {
        try {
            String response = FIT3077Api.get(this.urlPath + "/" + id);
            TestSite testSite = JsonUtils.toObject(response, TestSite.class);
            return testSite;
        } catch (Exception e) {
            System.out.println("Error in TestSiteSystem.getTestSites: " + e.getMessage());
            return null;
        }
    }
}
