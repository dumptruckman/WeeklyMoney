package org.drjk.money;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class MySql {
    
    static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://192.168.1.42:3306/office");
    }
    
    static final String OPENING_COUNT_QUERY = "SELECT * FROM `office`.`OpeningCount` WHERE `Date` = ?";
    
    static final String OPENING_COUNT_UPDATE = "INSERT INTO `office`.`OpeningCount` "
    		+ "(`Date`, `Pennies`, `Nickles`, `Dimes`, `Quarters`, `Halfdollars`, `Ones`, `Fives`, `Tens`, `Twenties`, `Fifties`, `Hundreds`, `Receipt Totals`, `Initials`)"
    		+ " VALUES "
    	    + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
    		+ " ON DUPLICATE KEY UPDATE "
    	    + "`Date` = ?, `Pennies` = ?, `Nickles` = ?, `Dimes` = ?, `Quarters` = ?, `Halfdollars` = ?, `Ones` = ?, `Fives` = ?, `Tens` = ?, `Twenties` = ?, `Fifties` = ?, `Hundreds` = ?, `Receipt Totals` = ?, `Initials` = ?";
}
