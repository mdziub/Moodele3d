package Modele.Service;

import Modele.Entity.CategoryEntity;
import Modele.Entity.ModelEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by macko_000 on 2016-11-21.
 */
public class ServiceTest {


    private Service serwus;
    @Before
    public void setUp() throws Exception {
        serwus=new Service();
    }

    @After
    public void tearDown() throws Exception {

    }
    @Test
    public void createTable() throws Exception {
    //prze konstruktor
    }

    @Test
    public void insertInCategory() throws Exception {
//        CategoryEntity cos=new CategoryEntity();
//        cos.setNazwaKat("ja pierdole");
//        cos.
//        assertTrue(serwus.insertInCategory(cos));

    }

    @Test
    public void insertInModel() throws Exception {
        ModelEntity model=new ModelEntity();
        model.setNazwaMod("cscacacxas");
        model.setProducent("sadadad");
        model.setProgram("wibro");
        serwus.insertInModel(model);

    }
}