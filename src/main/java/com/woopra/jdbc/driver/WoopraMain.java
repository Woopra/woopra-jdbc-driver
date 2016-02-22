package com.woopra.jdbc.driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class WoopraMain {

    public static void main(String[] argv) throws Exception {

        Class.forName("com.woopra.jdbc.driver.WoopraDriver");

        Connection connection = DriverManager.getConnection("jdbc:woopra://staging.woopra.com",
                "BLLISHYY1HBDUGS89CBT4BU4R288V1FL",
                "494aS6kMQDSKWPLBQ4Asu5FCdam5bShCBMX0pPI912dTaoT5VdbEWkgG4inFiYmY");

//        String query = "select visitors() as People, visits() as Visits, actions() as Actions from actions group by actions.url where actions.~n=web-view order by People";
//        String query = "select visitors() as People, visits() as Visits, actions() as Actions from visits group by substring(substring(date_format(visits.time, yyyy-DDDD), 4),  1)";
//        String query = "select "
//                + "visits() as Chrome having visits.browser='chrome', "
//                + "visits() as Safari having visits.browser='safari', "
//                + "actions() as Actions from visits group by substring(substring(date_format(visits.time, 'yyyy-MM-dd') , 2),3) "
//                + "order by People limit 10 offset 0";
        String query = "select "
                + "visitors.company as Company, visitors() as Count from visitors group by visitors.company order by Count";

        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println(">>" + rs.getString("~") + " :::: " + rs.getString(1));
        }

        rs.close();
        ps.close();
    }
}
