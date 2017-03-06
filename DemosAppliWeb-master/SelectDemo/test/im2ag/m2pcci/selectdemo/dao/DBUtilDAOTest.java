/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package im2ag.m2pcci.selectdemo.dao;

import javax.sql.DataSource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Philippe Genoud - UGA Université Grenoble Alpes - Lab. LIG Steamer
 */
public class DBUtilDAOTest {

    private static DataSource dataSource;

    public DBUtilDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        System.out.println("Before Class");
        dataSource = new TestDataSource("VOTRE_LOGIN", "VOTRE_MOT_DE_PASSE");
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of createOptionList method, of class DBUtilDAO.
     */
    @Test
    public void testCreatePaysList() throws Exception {
        System.out.println("createOptionList");
        PaysDAO.listePays(dataSource);
    }

}
