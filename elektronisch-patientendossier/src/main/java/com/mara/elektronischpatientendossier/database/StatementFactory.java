package com.mara.elektronischpatientendossier.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//factory class om statement objecten te maken
//maakt automatisch verbinding met de database wanneer nodig
public class StatementFactory {
    //database connectie string
    private final String connectionString = "jdbc:mysql://localhost:8889/fysiowave_patiëntendossier?user=root&password=root";

    //singleton instance
    private static StatementFactory instance = null;

    //database connection
    private Connection connection = null;

    //singleton
    public static StatementFactory getInstance() {
        if(null == instance) {
            instance = new StatementFactory();
        }

        return instance;
    }


    //verbind met de database in de constructor, zo gebeurt het altijd voor er functies aangeroepen kunnen worden
    private StatementFactory() {
        try {
            connection = DriverManager.getConnection(connectionString);
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    //maak een statement en return die
    public Statement createStatement() {
        assert null != connection : "createStatement: connection is null";

        try {
            //probeer aan te maken
            return connection.createStatement();
        } catch (SQLException se) {
            //zo niet, toon een fout
            return null; //laat de caller het maar uitzoeken
        }
    }

}
