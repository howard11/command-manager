/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProjectManagerTest {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getCurrentProject method, of class ProjectManager.
     */
    @Test
    public void testLoadProjectsFromXml() {
        ProjectManager projectManager = new ProjectManager();

        for (Project p : projectManager.getProjects()) {
            p.toXml();
        }
    }
}
