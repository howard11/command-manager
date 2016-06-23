package com.dev;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProjectManager {

    public static String BASE_DIR = "./";
    public static String CONFIG_FILE = "xml/config.xml";

    public static final String XML_TAG_APPLICATION_NAME = "application_name";

    public static String applicationName = null;

    private List<Project> projectList = null;

    private Project currentProject = null;

    public ProjectManager() {
        this.getResourceLocation();
        this.projectList = this.loadProjectsFromXml();
        this.currentProject = projectList.get(0);
    }

    private void getResourceLocation() {
        if (!new File(BASE_DIR + CONFIG_FILE).exists()) {
            //FOR test only
            BASE_DIR = "./src/main/resources/";
        }
    }

    public Project getCurrentProject() {
        return currentProject;
    }

    public void setCurrentProject(Project currentProject) {
        this.currentProject = currentProject;
    }

    public List<Project> getProjects() {
        if (projectList == null) {
            this.projectList = this.loadProjectsFromXml();
        }

        return projectList;
    }

    public Project getByName(String queryName) {
        if (Utils.isEmptyString(queryName)) return null;

        for (Project p : this.projectList) {
            if (p.getProjectName().equals(queryName)) return p;
        }
        return null;
    }

    public Project getBySeqNo(int n) {
        return this.projectList.get(n);
    }

    private List<Project> loadProjectsFromXml() {
        List<Project> projects = new ArrayList<Project>();
        try {
            SAXReader reader = new SAXReader();
            //reader.setValidation(true);
            Document doc = reader.read(new File(BASE_DIR + CONFIG_FILE));
            Element application = doc.getRootElement();
            //Set application window title
            applicationName = getNodeText(application.element(XML_TAG_APPLICATION_NAME));
            //Get projects
            for (Element pNode : (List<Element>) (application.elements(Project.XML_TAG_PROJECT))) {
                List<Command> commandList = new ArrayList<Command>();
                for (Element cNode : (List<Element>) (((List<Element>) (pNode.elements(Project.XML_TAG_COMMANDS))).get(0).elements(Command.XML_TAG_COMMAND))) {
                    //Get commands
                    List<Element> cmdStrNodes=cNode.elements(Command.XML_TAG_COMMAND_STRING);
                    String[][] cmdArray=new String[cmdStrNodes.size()][];
                    for(int i=0;i<cmdArray.length;++i){
                        cmdArray[i] = Utils.splitCommand(getNodeText(cmdStrNodes.get(i)));
                    }
                    commandList.add(new Command(cmdArray,
                            getNodeText(cNode.element(Command.XML_TAG_DIRECTORY)),
                            getNodeText(cNode.element(Command.XML_TAG_FILTER)),
                            getNodeText(cNode.element(Command.XML_TAG_SUCCESSFUL_STRING)),
                            getNodeText(cNode.element(Command.XML_TAG_DESCRIPTION)),
                            "true".equals(getNodeText(cNode.element(Command.XML_TAG_SELECTED)))));
                }

                boolean projectSelected="true".equals(getNodeText(pNode.element(Command.XML_TAG_SELECTED)));
                projects.add(new Project(getNodeText(pNode.element(Project.XML_TAG_PROJECT_NAME)), commandList,projectSelected));
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return projects;
    }

    /**
     * test
     *
     * @param e
     * @return
     */
    public String getNodeText(Element e) {
        return e == null ? "" : e.getTextTrim();
    }
}
