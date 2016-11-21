package Modele.Service;

import Modele.Entity.CategoryEntity;
import Modele.Entity.ModelEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Service{

    public static final String DRIVER = "org.sqlite.JDBC";
    public static final String DB_URL = "jdbc:sqlite:ModeleDB.db";
    private Connection conn;
    private Statement stat;
    private PreparedStatement insertCategoryStat;
    private PreparedStatement insertModeleStat;
    private PreparedStatement selectCategoryStat;
    private PreparedStatement selectModeleStat;
    private PreparedStatement dropTableCategoryStat;
    private PreparedStatement dropTableModeleStat;
    private PreparedStatement deleteFromCategoryStat;
    private PreparedStatement deleteFromModeleStat;

    public Servie(){
        try {
            Class.forName(DRIVER);
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        try{
            conn = DriverManager.getConnection(DB_URL);
            stat = conn.createStatement();
            createTable();
            insertModeleStat = conn.prepareStatement(
                    "insert into modele values (NULL, ?, ?, ?);");
            insertCategoryStat = conn.prepareStatement(
                    "insert into kategoria values (NULL, ?, ?, ?, ?);");
            selectCategoryStat = conn.prepareStatement(
                    "select * FROM kategoria");
            selectModeleStat=conn.prepareStatement
                    ("Select * from modele");
            deleteFromCategoryStat=conn.prepareStatement(
                    "DELETE FROM kategoria where id_kategoria=?");
            deleteFromModeleStat=conn.prepareStatement(
                    "DELETE FROM modele WHERE id_modele=?");
            dropTableCategoryStat=conn.prepareStatement(
                    "DROP table kategoria");
            dropTableModeleStat= conn.prepareStatement(
                    "DROP TABLE modele");


        } catch (SQLException e) {

            e.printStackTrace();
        }

        }


    }
}