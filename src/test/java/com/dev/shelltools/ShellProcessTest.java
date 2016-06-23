package com.dev.shelltools;

import com.dev.Command;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Test <code>ShellProcess</code>.
 *
 * @see ShellProcess
 */
public class ShellProcessTest {
    @Test
    public void testExec() throws Exception {
        String dirStr = "/data/home/mywork/trunk/iha/-iha-client";

        List<Command> lc = new ArrayList<Command>();
        lc.add(new Command(new String[][]{{"/bin/ls"}}, dirStr, "iha", null, "Show Dir",true));

        ShellProcess process = new ShellProcess(lc);
        process.start();
    }
}
