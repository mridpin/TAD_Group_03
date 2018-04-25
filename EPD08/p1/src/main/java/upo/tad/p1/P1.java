/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upo.tad.p1;

import com.mongodb.*;



/**
 *
 * @author ridao
 */
public class P1 {

    public static void main(String[] args) {
        try {
            // DB Setup
            Mongo client = new Mongo("localhost", 27017);
            DB db = client.getDB("lfpdb");
            System.out.println("Connecting to dabase ...");
            // Collection setup: Collections are lazily created when something is inserted
            DBCollection players = db.getCollection("players");
            DBCollection teams = db.getCollection("teams");
            
            Application app = new Application(db);
            app.run();
            
            
        } catch (Exception e) {

        }

    }
}
