/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upo.tad.p2;

import com.mongodb.*;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ridao
 */
public class Main {
    public static void main (String[] args) throws IOException {
        try {
            // Setup: database
            Mongo mongo = new Mongo("localhost", 27017);
            DB db = mongo.getDB("iberia"); // Creates the db after an insert if it doesnt exists
            System.out.println("Connected to dabatase iberia");
            
            Application app = new Application (db);
            app.run();
        } catch (UnknownHostException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MongoException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
