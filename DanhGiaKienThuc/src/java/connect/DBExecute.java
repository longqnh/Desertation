/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import tools.ScriptRunner;


/**
 *
 * @author NTL
 */
public class DBExecute {
    public static void main(String[] args) throws FileNotFoundException, IOException, SQLException {
        Connection connection = DBConnect.getConnecttion();
        
        ScriptRunner scriptRunner = new ScriptRunner(connection, true, true);
        scriptRunner.runScript(new BufferedReader(new FileReader("DB/DBSQL.sql")));
        scriptRunner.runScript(new BufferedReader(new FileReader("DB/NHCH10.sql")));
        scriptRunner.runScript(new BufferedReader(new FileReader("DB/NHCH11.sql")));
        scriptRunner.runScript(new BufferedReader(new FileReader("DB/NHCH12.sql")));
    }
}
