package org;

import java.io.File;
import java.io.IOException;

public class CompileFile extends Thread {
    private String cmd;
    private String dir;

    public CompileFile(String cmd, String dir) {
        this.cmd = cmd;
        this.dir = dir;
    }

    @Override
    public void run() {
        try {
            Runtime.getRuntime().exec(cmd, null, new File(dir));
            sleep(1000);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
