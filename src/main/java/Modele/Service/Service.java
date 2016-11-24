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
    private PreparedStatement deleteFromModelStat;
    private PreparedStatement updateModelsStat;

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
            deleteFromModelStat=conn.prepareStatement(
                    "DELETE FROM modele WHERE id_modele=?");
            dropTableCategoryStat=conn.prepareStatement(
                    "DROP table kategoria");
            dropTableModeleStat= conn.prepareStatement(
                    "DROP TABLE modele");
            updateModelsStat = conn.prepareStatement(
                    "Update modele set nazwaMod=? WHERE id_modele=?");


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

    public boolean updateModel (ModelEntity mod){
        try {
            updateModelsStat.setString(1,mod.getNazwaMod());
            updateModelsStat.setLong(2,mod.getId_modele());
            updateModelsStat.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
        }


    public List<CategoryEntity> selectCategory(){
        List<CategoryEntity> allCategories =new ArrayList<>();
        try {
            ResultSet resultSet = selectCategoryStat.executeQuery();
            while (resultSet.next()) {
                CategoryEntity c=new CategoryEntity();
                c.setId_kategoria(resultSet.getLong("id_kategoria"));
                c.setOpis(resultSet.getString("opis"));
                c.setNazwaKat(resultSet.getString("nazwaKat"));
                c.setNumer(resultSet.getInt("numer"));
                c.setIdmod(resultSet.getLong("idMod"));
                allCategories.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allCategories;
    }
    public List<ModelEntity> selectModel(){
        List<ModelEntity> allModels =new ArrayList<>();
        try {
            ResultSet resultSet = selectModeleStat.executeQuery();
            while (resultSet.next()) {
                ModelEntity m=new ModelEntity();
                m.setId_modele(resultSet.getLong("id_modele"));
                m.setNazwaMod(resultSet.getString("nazwaMod"));
                m.setProducent(resultSet.getString("producent"));
                m.setProgram(resultSet.getString("program"));
                allModels.add(m);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allModels;
    }
    public boolean deleteFromCategoryById(CategoryEntity idCat){
        try {
            deleteFromCategoryStat.setLong(1,idCat.getId_kategoria());
            deleteFromCategoryStat.execute();

        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean deleteFromModelById(ModelEntity idMod){
        try {
            deleteFromModelStat.setLong(1,idMod.getId_modele());
            deleteFromModelStat.execute();

        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean dropTable(){
        try {
            dropTableCategoryStat.execute();
            dropTableModeleStat.execute();
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean closeCon(){
        try {
            conn.close();

        } catch(SQLException e) {
            System.err.println("Problem z zamknieciem polaczenia");
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
