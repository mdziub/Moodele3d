package Modele.Service;

import Modele.Entity.CategoryEntity;
import Modele.Entity.ModelEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class ServiceTest {



    Service serv;

    @Before
    public void setUp() throws Exception {
        serv =new Service();
    }
    @After
    public void tearDown() throws Exception {
        serv.dropTable();
        serv.closeCon();
    }
    @Test
    public void createTable() throws Exception {
        assertTrue(serv.createTable()); //jesli createTable jest true
    }

    @Test
    public void testConn() throws Exception {
        Exception ex = null;
        try {
            new Service();
        } catch (Exception e) {
            ex = e;
        }
        assertEquals(null,ex);
    }

    @Test
    public void insertInCategory() throws Exception {
        ModelEntity m1=new ModelEntity(); //tworzy nowy obiekt klasy
        m1.setNazwaMod("248-31");  //dzieki set i get mozemy odniesc sie do prywatnego pola w innej klasie poprzez uzycie metody publicznej
        m1.setProducent("Klose");
        m1.setProgram("3ds max");
        serv.insertInModel(m1); //dodaje rekordy do tabeli
        List<ModelEntity> me=serv.selectModel(); //przypisuje do kolekcji (listy obiektow) me obiekty z selectModel

        CategoryEntity c1=new CategoryEntity();//tworzy nowy obiekt klasy category
        c1.setNazwaKat("Milo");
        c1.setNumer(123213);
        c1.setOpis("Biurka male z wysuwka");
        c1.setIdmod(me.get(0).getId_modele()); //pobiera id 1wszego obiektu z kolekcji
        assertTrue(serv.insertInCategory(c1)); //sprawdza czy insert zwrocil wartosc true
        List<CategoryEntity> ce=serv.selectCategory();  //przypisuje do kolekcji obiekty z selectCategory
        assertEquals("Milo",ce.get(0).getNazwaKat()); //sprawdza czy 1wszy obiekt z katregorii to podana nazwa katgorii


    }
    @Test
    public void insertInModel() throws Exception {
        ModelEntity m1=new ModelEntity(); //nowy obiekt klasy ModelEntity
        m1.setNazwaMod("246-30");
        m1.setProducent("Klose");
        m1.setProgram("3ds max");
        assertTrue(serv.insertInModel(m1)); //sprawdza, czy reordy zostaly dodane
        List<ModelEntity> me=serv.selectModel(); //przypisujemy kolekcje z selectModel do me
        assertEquals("246-30",me.get(0).getNazwaMod()); //sprawdza, czy 1wszy obiekt kolekcji to podana nazwa

    }

    @Test
    public void updateCategory() throws Exception {
        ModelEntity m1=new ModelEntity();
        m1.setNazwaMod("100323");
        m1.setProducent("Klose");
        m1.setProgram("2020 Fusion");
        serv.insertInModel(m1);
        List<ModelEntity> me=serv.selectModel();

        CategoryEntity c1=new CategoryEntity();
        c1.setNazwaKat("Milo");
        c1.setNumer(123213);
        c1.setOpis("Biurka bez kontenerow");
        c1.setIdmod(me.get(0).getId_modele());
        assertTrue(serv.insertInCategory(c1));

        List<CategoryEntity> ce=serv.selectCategory();
        CategoryEntity categoryEntity=ce.get(0);
        categoryEntity.setNazwaKat("Nowa");

        assertTrue(serv.updateCategory(categoryEntity));


    }

    @Test
    public void updateModel() throws Exception {
        ModelEntity m1=new ModelEntity();
        m1.setNazwaMod("100323");
        m1.setProducent("Klose");
        m1.setProgram("2020 Fusion");
        serv.insertInModel(m1);
        List<ModelEntity> me=serv.selectModel();
        ModelEntity modelEntity=me.get(0);
        modelEntity.setNazwaMod("nowa");
        assertTrue(serv.updateModel(modelEntity));

    }
    @Test
    public void selectCategory() throws Exception {
        ModelEntity m1=new ModelEntity();
        m1.setNazwaMod("100323");
        m1.setProducent("Klose");
        m1.setProgram("2020 Fusion");
        serv.insertInModel(m1);
        List<ModelEntity> me=serv.selectModel();

        CategoryEntity c1=new CategoryEntity();
        c1.setNazwaKat("Milo");
        c1.setNumer(123213);
        c1.setOpis("Biurka bez kontenerow");
        c1.setIdmod(me.get(0).getId_modele());
        CategoryEntity c2=new CategoryEntity();
        c2.setNazwaKat("Venice");
        c2.setNumer(123213);
        c2.setOpis("Komody Venice ");
        c2.setIdmod(me.get(0).getId_modele()); //pobiera i przypisuje id 1wszego elementu
        CategoryEntity c3=new CategoryEntity();
        c3.setNazwaKat("Venice");
        c3.setNumer(123213);
        c3.setOpis("Lozka Venice");
        c3.setIdmod(me.get(0).getId_modele());
        assertTrue(serv.insertInCategory(c1));
        assertTrue(serv.insertInCategory(c2));
        assertTrue(serv.insertInCategory(c3));

        List<CategoryEntity> cl=serv.selectCategory();

        assertEquals(3,cl.size());

    }
    @Test
    public void selectModel() throws Exception {
        ModelEntity m1=new ModelEntity();
        m1.setNazwaMod("1002-10");
        m1.setProducent("Klose");
        m1.setProgram("3ds max");
        ModelEntity m2=new ModelEntity();
        m2.setNazwaMod("1002-15");
        m2.setProducent("Klose");
        m2.setProgram("3ds max");
        ModelEntity m3=new ModelEntity();
        m3.setNazwaMod("1002-20");
        m3.setProducent("Klose");
        m3.setProgram("3ds max");
        assertTrue(serv.insertInModel(m1));
        assertTrue(serv.insertInModel(m2));
        assertTrue(serv.insertInModel(m3));
        List<ModelEntity> me=serv.selectModel();
        assertEquals(3,me.size());
    }
    @Test
    public void deleteFromCategoryById() throws Exception {
        ModelEntity m1=new ModelEntity();
        m1.setNazwaMod("1002-18");
        m1.setProducent("Klose");
        m1.setProgram("3ds max");
        serv.insertInModel(m1);
        List<ModelEntity> me=serv.selectModel();
        CategoryEntity c3=new CategoryEntity();
        c3.setNazwaKat("Venice");
        c3.setNumer(123213);
        c3.setOpis("Szafy Venice");
        c3.setIdmod(me.get(0).getId_modele());
        assertTrue(serv.insertInCategory(c3));

        List<CategoryEntity> listCat=serv.selectCategory();
        assertTrue(serv.deleteFromCategoryById(listCat.get(0)));
        List<CategoryEntity> cl=serv.selectCategory();
        assertEquals(0,cl.size());
    }
    @Test
    public void deleteFromModelById() throws Exception {
        ModelEntity m1=new ModelEntity();
        m1.setNazwaMod("1002-21");
        m1.setProducent("Klose");
        m1.setProgram("3ds max");
        serv.insertInModel(m1);
        List<ModelEntity> listMod=serv.selectModel();
        assertTrue(serv.deleteFromModelById(listMod.get(0)));
        listMod=serv.selectModel();
        assertEquals(0,listMod.size());
    }

    @Test
    public void dropTable() throws Exception {
        assertTrue(serv.dropTable());
        ModelEntity m1=new ModelEntity();
        m1.setNazwaMod("1002-16");
        m1.setProducent("Klose");
        m1.setProgram("2020 Fusion");
        assertFalse(serv.insertInModel(m1));
    }
    @Test
    public void closeCon() throws Exception {
        assertTrue(serv.closeCon());
        assertFalse(serv.createTable());
    }

}