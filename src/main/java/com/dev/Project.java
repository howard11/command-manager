package com.dev;

import java.util.List;

public class Project {
    public static String XML_TAG_PROJECT = "project";
    public static String XML_TAG_PROJECT_NAME = "name";
    public static String XML_TAG_COMMANDS = "commands";

    private String projectName;
    private List<Command> cmdList;
    private boolean selected;

    public Project(String projectName, List<Command> cmdList, boolean selected) {
        this.projectName = projectName;
        this.cmdList = cmdList;
        this.selected = selected;
    }

    public List<Command> getCmdList() {
        return cmdList;
    }

    public void setCmdList(List<Command> cmdList) {
        this.cmdList = cmdList;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String toXml() {
        StringBuilder res = new StringBuilder();
        res.append(Utils.packXmlNode(XML_TAG_PROJECT_NAME, this.projectName));

        StringBuilder commandsXml = new StringBuilder();
        for (Command c : this.cmdList) {
            commandsXml.append(c.toXml());
        }
        res.append(Utils.packXmlNode(XML_TAG_COMMANDS, commandsXml.toString()));
        return Utils.packXmlNode(XML_TAG_PROJECT, res.toString());
    }
}
