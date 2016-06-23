package com.dev;

import java.util.ArrayList;
import java.util.List;

public class Command {
    public static String XML_TAG_COMMAND = "command";
    public static String XML_TAG_COMMAND_STRING = "command_string";
    public static String XML_TAG_DESCRIPTION = "description";
    public static String XML_TAG_DIRECTORY = "directory";
    public static String XML_TAG_FILTER = "filter";
    public static String XML_TAG_SUCCESSFUL_STRING = "successful_string";
    public static String XML_TAG_SELECTED = "selected";

    public static final String SUCCESSFUL_STRING = "SUCCESSFUL";
    public static final String FAILURE_STRING = "FAILURE";

    private String[][] cmds;
    private String cmdResult;
    private String dir;
    private String filter;
    private String successStr;
    private String description;
    private String status;
    private boolean selected;

    private CommandPanel commandPane;

    /**
     * @param cmd
     * @param dir
     * @param filter      set to null, if don't use it
     * @param successStr  set to null, if don't use it
     * @param description
     */
    public Command(String[][] cmd, String dir, String filter, String successStr, String description, boolean selected) {
        this.cmds = cmd;
        this.dir = dir;
        this.filter = filter;
        this.successStr = successStr;
        this.description = description;
        this.selected = selected;
    }

    public String[][] getCmds() {
        return cmds;
    }

    public List<String> getCmdsAsList() {
        List<String> cmdList = new ArrayList<String>();

        for (String[] cArray : cmds) {
            StringBuilder stringBuilder = new StringBuilder();
            for (String c : cArray) {
                stringBuilder.append(c + " ");
            }
            cmdList.add(stringBuilder.toString());
        }
        return cmdList;
    }

    public void setCmds(String[][] cmd) {
        this.cmds = cmd;
    }

    public String getCmdResult() {
        return cmdResult;
    }

    public void setCmdResult(String cmdResult) {
        this.cmdResult = cmdResult;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getSuccessStr() {
        return successStr;
    }

    public void setSuccessStr(String successStr) {
        this.successStr = successStr;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CommandPanel getCommandPane() {
        return commandPane;
    }

    public void setCommandPane(CommandPanel commandPane) {
        this.commandPane = commandPane;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String toXml() {
        StringBuilder res = new StringBuilder();
        res.append(Utils.packXmlNode(XML_TAG_DESCRIPTION, this.description));
        for (String s : this.getCmdsAsList()) {
            res.append(Utils.packXmlNode(XML_TAG_COMMAND_STRING, s));
        }
        res.append(Utils.packXmlNode(XML_TAG_DIRECTORY, this.dir));
        res.append(Utils.packXmlNode(XML_TAG_SUCCESSFUL_STRING, this.successStr));
        return Utils.packXmlNode(XML_TAG_COMMAND, res.toString());
    }

    public String toHtml() {
        StringBuilder res = new StringBuilder();
        res.append("<html>");
        res.append("<h3>Directory: ");
        res.append(this.dir);
        res.append("</h3>");
        for (String s : this.getCmdsAsList()) {
            res.append("<p>");
            res.append(s);
            res.append("</p>");
        }
        res.append("</html>");
        return res.toString();
    }

}
