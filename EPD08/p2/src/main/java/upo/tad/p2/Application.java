/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upo.tad.p2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ridao
 */
public class Application {

    DB db;
    ObjectMapper mapper; // Turns DB results into good looking JSONs

    Application(DB db) {
        this.db = db;
        this.mapper = new ObjectMapper();
        // Inject passengers
        BasicDBObject pas1 = new BasicDBObject();
        BasicDBObject pas2 = new BasicDBObject();
        BasicDBObject pas3 = new BasicDBObject();
        BasicDBObject pas4 = new BasicDBObject();
        pas1.append("name", "Manuel Ridao");
        pas2.append("name", "Juan Antonio Rosriguez");
        pas3.append("name", "Antonio Jose Herrera");
        pas4.append("name", "Miguel Angel Montero");
        pas1.append("nationality", "Spain");
        pas2.append("nationality", "UK");
        pas3.append("nationality", "France");
        pas4.append("nationality", "US");
        pas1.append("passport", "123456789");
        pas2.append("passport", "987654321");
        pas3.append("passport", "246808642");
        pas4.append("passport", "135797531");
        db.getCollection("passengers").drop();
        db.getCollection("passengers").insert(pas1);
        db.getCollection("passengers").insert(pas2);
        db.getCollection("passengers").insert(pas3);
        db.getCollection("passengers").insert(pas4);

        // Inject planes
        BasicDBObject plan1 = new BasicDBObject();
        BasicDBObject plan2 = new BasicDBObject();
        plan1.append("model", "Airbus 320");
        plan2.append("model", "Airbus 330");
        plan1.append("year", "2015");
        plan2.append("year", "2016");
        ArrayList rev1 = new ArrayList();
        for (int i = 1; i < 5; i++) {
            BasicDBObject rev = new BasicDBObject();
            rev.append("date", "2017-" + i * 3 + "-01");
            rev.append("result", "FAVOURABLE");
            rev1.add(rev);
        }
        plan1.append("revisions", rev1);
        plan2.append("revisions", rev1);
        db.getCollection("planes").drop();
        db.getCollection("planes").insert(plan1);
        db.getCollection("planes").insert(plan2);

        // Inject flights
        BasicDBObject flight1 = new BasicDBObject();
        BasicDBObject flight2 = new BasicDBObject();
        BasicDBObject flight3 = new BasicDBObject();
        flight1.append("plane", plan1);
        flight2.append("plane", plan2);
        flight3.append("plane", plan1);
        flight1.append("departure", "MAD");
        flight2.append("departure", "NYC");
        flight3.append("departure", "SVQ");
        flight1.append("destination", "SVQ");
        flight2.append("destination", "MAD");
        flight3.append("destination", "BCN");
        ArrayList pass1 = new ArrayList();
        pass1.add(pas1);
        pass1.add(pas2);
        pass1.add(pas3);
        pass1.add(pas4);
        ArrayList pass2 = new ArrayList();
        pass2.add(pas1);
        pass2.add(pas4);
        ArrayList pass3 = new ArrayList();
        pass3.add(pas2);
        pass3.add(pas3);
        pass3.add(pas4);
        flight1.append("passengers", pass1);
        flight2.append("passengers", pass2);
        flight3.append("passengers", pass3);
        db.getCollection("flights").drop();
        db.getCollection("flights").insert(flight1);
        db.getCollection("flights").insert(flight2);
        db.getCollection("flights").insert(flight3);
    }

    void run() {
        String action = "-1";
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Choose action: \n"
                    + "\t1. Find Passengers\n"
                    + "\t2. Find Planes\n"
                    + "\t3. Find Flights\n"
                    + "\t0. Exit\n");
            action = sc.next();
            switch (action) {
                case "1":
                    System.out.println(findPassengers());
                    break;
                case "2":
                    System.out.println(findPlanes());
                    break;
                case "3":
                    System.out.println(findFlights());
                default:
                    break;
            }
        } while (!"0".equals(action));
    }

    private String findPassengers() {
        String res = "";
        DBCursor c = db.getCollection("passengers").find();
        while (c.hasNext()) {
            Object json;
            try {
                // Format DBObject string into JSON
                json = mapper.readValue(c.next().toString(), Object.class);
                res += mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
                res += "\n";
            } catch (IOException ex) {
                Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ("".equals(res)) ? "Collection empty" : res;
    }

    private String findPlanes() {
        String res = "";
        DBCursor c = db.getCollection("planes").find();
        while (c.hasNext()) {
            Object json;
            try {
                // Format DBObject string into JSON
                json = mapper.readValue(c.next().toString(), Object.class);
                res += mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
                res += "\n";
            } catch (IOException ex) {
                Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ("".equals(res)) ? "Collection empty" : res;
    }

    private String findFlights() {
        String res = "";
        DBCursor c = db.getCollection("flights").find();
        while (c.hasNext()) {
            Object json;
            try {
                // Format DBObject string into JSON
                json = mapper.readValue(c.next().toString(), Object.class);
                res += mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
                res += "\n";
            } catch (IOException ex) {
                Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ("".equals(res)) ? "Collection empty" : res;
    }

}
