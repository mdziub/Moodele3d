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

    }
    @Test
    public void createTable() throws Exception {
        assertTrue(serv.createTable());
    }
    public void testConn() throws Exception {
        Exception ex = null;
        try {
            new Service();
        } catch (Exception e) {
            ex = e;
        }
        assertEquals(null,ex);
    }
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

}