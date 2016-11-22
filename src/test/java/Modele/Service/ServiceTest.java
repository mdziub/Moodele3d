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
        assertTrue(serv.createTable());
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
        ModelEntity m1=new ModelEntity();
        m1.setNazwaMod("sdadasdasdasd");
        m1.setProducent("sdasdsad");
        m1.setProgram("sdsadsad");
        serv.insertInModel(m1);
        List<ModelEntity> me=serv.selectModel();

        CategoryEntity c1=new CategoryEntity();
        c1.setNazwaKat("sfsf");
        c1.setNumer(123213);
        c1.setOpis("dupa");
        c1.setIdmod(me.get(0).getId_modele());
        assertTrue(serv.insertInCategory(c1));
        List<CategoryEntity> ce=serv.selectCategory();
        assertEquals("sfsf",ce.get(0).getNazwaKat());


    }
    @Test
    public void insertInModel() throws Exception {
        ModelEntity m1=new ModelEntity();
        m1.setNazwaMod("sdadasdasdasd");
        m1.setProducent("sdasdsad");
        m1.setProgram("sdsadsad");
        assertTrue(serv.insertInModel(m1));
        List<ModelEntity> me=serv.selectModel();
        assertEquals("sdadasdasdasd",me.get(0).getNazwaMod());

    }
    @Test
    public void selectCategory() throws Exception {
        ModelEntity m1=new ModelEntity();
        m1.setNazwaMod("sdadasdasdasd");
        m1.setProducent("sdasdsad");
        m1.setProgram("sdsadsad");
        serv.insertInModel(m1);
        List<ModelEntity> me=serv.selectModel();

        CategoryEntity c1=new CategoryEntity();
        c1.setNazwaKat("sfsf");
        c1.setNumer(123213);
        c1.setOpis("dupa");
        c1.setIdmod(me.get(0).getId_modele());
        CategoryEntity c2=new CategoryEntity();
        c2.setNazwaKat("sfsf");
        c2.setNumer(123213);
        c2.setOpis("dupa");
        c2.setIdmod(me.get(0).getId_modele());
        CategoryEntity c3=new CategoryEntity();
        c3.setNazwaKat("sfsf");
        c3.setNumer(123213);
        c3.setOpis("dupa");
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
        m1.setNazwaMod("sdadasdasdasd");
        m1.setProducent("sdasdsad");
        m1.setProgram("sdsadsad");
        ModelEntity m2=new ModelEntity();
        m2.setNazwaMod("sdadasdasdasd");
        m2.setProducent("sdasdsad");
        m2.setProgram("sdsadsad");
        ModelEntity m3=new ModelEntity();
        m3.setNazwaMod("sdadasdasdasd");
        m3.setProducent("sdasdsad");
        m3.setProgram("sdsadsad");
        assertTrue(serv.insertInModel(m1));
        assertTrue(serv.insertInModel(m2));
        assertTrue(serv.insertInModel(m3));
        List<ModelEntity> me=serv.selectModel();
        assertEquals(3,me.size());
    }
    @Test
    public void deleteFromCategoryById() throws Exception {
        ModelEntity m1=new ModelEntity();
        m1.setNazwaMod("sdadasdasdasd");
        m1.setProducent("sdasdsad");
        m1.setProgram("sdsadsad");
        serv.insertInModel(m1);
        List<ModelEntity> me=serv.selectModel();
        CategoryEntity c3=new CategoryEntity();
        c3.setNazwaKat("sfsf");
        c3.setNumer(123213);
        c3.setOpis("dupa");
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
        m1.setNazwaMod("sdadasdasdasd");
        m1.setProducent("sdasdsad");
        m1.setProgram("sdsadsad");
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
        m1.setNazwaMod("sdadasdasdasd");
        m1.setProducent("sdasdsad");
        m1.setProgram("sdsadsad");
        assertFalse(serv.insertInModel(m1));
    }
    @Test
    public void closeCon() throws Exception {
        assertTrue(serv.closeCon());
        assertFalse(serv.createTable());
    }

}