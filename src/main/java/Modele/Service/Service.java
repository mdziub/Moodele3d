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

    public Service(){
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
    public boolean createTable(){

        String createModelString="CREATE TABLE IF NOT EXISTS modele ( id_modele INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, nazwaMod TEXT, producent TEXT, program TEXT)";
        String createCategoryString="CREATE TABLE IF NOT EXISTS kategoria ( id_kategoria INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, nazwaKat TEXT, opis TEXT, numer INTEGER ,idMod INTEGER,FOREIGN KEY(idMod) REFERENCES modele(id_modele))";

        try {
            stat.execute(createModelString);
            stat.execute(createCategoryString);
        } catch (SQLException e) {
            System.err.println("Blad przy tworzeniu tabeli");
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean insertInCategory(CategoryEntity cat){
        try {
            insertCategoryStat.setString(1, cat.getNazwaKat());
            insertCategoryStat.setString(2, cat.getOpis());
            insertCategoryStat.setInt(3, cat.getNumer());
            insertCategoryStat.setLong(4,cat.getIdmod());
            insertCategoryStat.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean insertInModel(ModelEntity mod){
        try {
            insertModeleStat.setString(1, mod.getNazwaMod());
            insertModeleStat.setString(2, mod.getProducent());
            insertModeleStat.setString(3,mod.getProgram());
            insertModeleStat.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
