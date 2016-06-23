package com.dev.shelltools;

import com.dev.Command;
import com.dev.ProjectFrame;
import com.dev.Utils;

import java.io.*;
import java.util.List;

public class ShellProcess extends Thread {
    private static String LINE_SEPARATOR = "\n";
    private Process process = null;
    private String executeResult = null;

    private List<Command> commands = null;

    public ShellProcess(List<Command> commandList) {
        this.commands = commandList;
    }

    @Override
    public void destroy() {
        this.stopShell();
    }

    public void stopShell() {
        if (process != null) {
            process.destroy();
        }
    }

    @Override
    public void run() {

        for (Command c : commands) {
            if (c.getCommandPane() != null) c.getCommandPane().setInprogress();
            this.runCommand(c);
            if (c.getCommandPane() != null) c.getCommandPane().setRunComplete();
        }

        if (ProjectFrame.projectFrame != null) ProjectFrame.projectFrame.setRunComplete();
    }

    private void runCommand(Command c) {
        StringBuilder res = new StringBuilder();
        try {
            for (String[] cmd : c.getCmds()) {
                this.process = Runtime.getRuntime().exec(cmd, null, new File(c.getDir()));
                this.process.waitFor();

                BufferedReader buf = new BufferedReader(new InputStreamReader(this.process.getInputStream()));
                String line;
                while ((line = buf.readLine()) != null) {
                    if (!Utils.isEmptyString(c.getFilter()) && !line.contains(c.getFilter())) {
                        continue;
                    }
                    res.append(line + LINE_SEPARATOR);
                }
            }
            //Save execute result
            c.setCmdResult(res.toString());

            c.setStatus(Utils.isEmptyString(c.getCmdResult()) || (!Utils.isEmptyString(c.getSuccessStr()) && c.getCmdResult().contains(c.getSuccessStr())) ? Command.SUCCESSFUL_STRING : Command.FAILURE_STRING);
        } catch (Exception e) {
            Dumpable.error("Cannot execute shell command ", e);
            return;
        }
    }

    public int getStatus() {
        if (this.process == null) {
            return Integer.MAX_VALUE;
        }
        return this.process.exitValue();
    }

    public String getExecuteResult() {
        return executeResult;
    }

}
