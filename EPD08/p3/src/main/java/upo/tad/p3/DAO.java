/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upo.tad.p3;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.*;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ridao
 */
public class DAO {

    Mongo mongo;
    DB db;
    DBCollection players;
    DBCollection teams;
    ObjectMapper mapper;

    public DAO() {
        try {
            // Problema 1
            mongo = new Mongo("localhost", 27017);
            db = mongo.getDB("lfpdb");
            players = db.getCollection("players");
            teams = db.getCollection("teams");
            mapper = new ObjectMapper();
            cargarDatos();
        } catch (MongoException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cargarDatos() {
        BasicDBObject player1 = new BasicDBObject();
        BasicDBObject player2 = new BasicDBObject();
        BasicDBObject player3 = new BasicDBObject();
        BasicDBObject player4 = new BasicDBObject();
        player1.append("name", "Manuel Ridao");
        player2.append("name", "Juan Antonio Rodriguez");
        player3.append("name", "Antonio Jose Herrera");
        player4.append("name", "Roboute Guilliman");
        player1.append("number", "1");
        player2.append("number", "2");
        player3.append("number", "3");
        player4.append("number", "4");
        player1.append("birthdate", "1992-09-09");
        player2.append("birthdate", "1932-01-01");
        player3.append("birthdate", "1994-02-02");
        player4.append("birthdate", "1991-03-03");

        BasicDBObject team1 = new BasicDBObject();
        BasicDBObject team2 = new BasicDBObject();
        team1.append("name", "UPO FC");
        team2.append("name", "US FC");
        team1.append("arena", "UPO Staduim");
        team2.append("arena", "Seville Arena");
        ArrayList players1 = new ArrayList();
        players1.add(player1);
        players1.add(player2);
        ArrayList players2 = new ArrayList();
        players2.add(player3);
        players2.add(player4);
        team1.append("players", players1);
        team2.append("players", players2);
        
        players = db.getCollection("players");
        players.drop();
        players.insert(player1);
        players.insert(player2);
        players.insert(player3);
        players.insert(player4);
        
        teams = db.getCollection("teams");
        teams.drop();
        teams.insert(team1);
        teams.insert(team2);
    }
    
    public List<String> findTeams() {
        DBCursor c = db.getCollection("teams").find();
        List<String> res = new ArrayList<>();
        while(c.hasNext()) {
            Object json;
            try {
                // Format DBObject string into JSON
                json = mapper.readValue(c.next().toString(), Object.class);
                res.add(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json));
            } catch (IOException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return res;
    }
    
    public List<String> findPlayers() {
        DBCursor c = db.getCollection("players").find();
        List<String> res = new ArrayList<>();
        while(c.hasNext()) {
            Object json;
            try {
                // Format DBObject string into JSON
                json = mapper.readValue(c.next().toString(), Object.class);
                res.add(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json));
            } catch (IOException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return res;
    }
}
