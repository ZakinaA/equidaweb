package database;

import model.Cheval;
import model.Race;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DaoCheval {

    Connection cnx;
    static PreparedStatement requeteSql = null;
    static ResultSet resultatRequete = null;

    public static ArrayList<Cheval> getLesChevaux(Connection cnx){

        ArrayList<Cheval> lesChevaux= new ArrayList<Cheval>();
        try{
            requeteSql = cnx.prepareStatement("select c.id as c_id, c.nom as c_nom,  r.id as r_id, r.nom as r_nom " +
                    " from cheval c inner join race r " +
                    " on c.race_id = r.id ");
            //System.out.println("REQ="+ requeteSql);
            resultatRequete = requeteSql.executeQuery();

            while (resultatRequete.next()){

                Cheval c = new Cheval();
                c.setId(resultatRequete.getInt("c_id"));
                c.setNom(resultatRequete.getString("c_nom"));

                Race r = new Race();
                r.setId(resultatRequete.getInt("r_id"));
                r.setNom(resultatRequete.getString("r_nom"));

                c.setRace(r);

                lesChevaux.add(c);
            }

        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println("La requête de getLesChevaux a généré une exception SQL");
        }
        return lesChevaux;
    }

    public static Cheval getLeCheval(Connection cnx, int id){
        Cheval cheval = null;
        try{
            requeteSql = cnx.prepareStatement("select c.id as c_id, c.nom as c_nom, r.id as r_id, r.nom as r_nom " +
                    "from cheval c inner join race r " +
                    "on c.race_id = r.id " +
                    "where c.id = ?");
            requeteSql.setInt(1, id);
            resultatRequete = requeteSql.executeQuery();

            if (resultatRequete.next()){
                cheval = new Cheval();
                cheval.setId(resultatRequete.getInt("c_id"));
                cheval.setNom(resultatRequete.getString("c_nom"));

                Race race = new Race();
                race.setId(resultatRequete.getInt("r_id"));
                race.setNom(resultatRequete.getString("r_nom"));

                cheval.setRace(race);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println("La requête de getLeCheval a généré une exception SQL");
        }
        return cheval;
    }
}