package com.fit3077.covidtesting.testsite;

import com.fit3077.covidtesting.common.Action;
import com.fit3077.covidtesting.testsite.search.SearchType;
import com.fit3077.covidtesting.testsite.search.Search;
import com.fit3077.covidtesting.testsite.search.SearchByFacility;
import com.fit3077.covidtesting.testsite.search.SearchBySuburb;
import com.fit3077.covidtesting.user.User;

import java.util.ArrayList;
import java.util.Scanner;

public class ViewTestSite extends Action {
    public void execute(User user)throws Exception{
        Scanner scanner=new Scanner(System.in);
        ArrayList<SearchType> searchTypes=new ArrayList<SearchType>();
        for (SearchType st:SearchType.values()){
            searchTypes.add(st);
        }
        for(int i=0;i<searchTypes.size();i++){
            System.out.println("press "+i+" to search by "+searchTypes.get(i));
        }
        System.out.println("Please select a search type");
        int index=scanner.nextInt();
        ArrayList<TestSite> testSites=search(searchTypes.get(index));
        for (TestSite testSite:testSites){
            System.out.println(testSite);
        }
    }
    private ArrayList<TestSite> search(SearchType searchType){
        Search search=null;
        if (searchType==SearchType.SUBURB){
            search=new SearchBySuburb();
        } else if (searchType==SearchType.FACILITYTYPE) {
            search=new SearchByFacility();
        }
        return search.execute();

    }
    public String displayChar(){
        return "v";
    }
    public String toString(){
        return "View test site";
    }
}
