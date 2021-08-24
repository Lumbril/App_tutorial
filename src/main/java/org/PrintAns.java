package org;

import java.io.File;
import java.io.IOException;

class PrintAns extends Thread {
    private String dir;
    private String command;

    public PrintAns(String command, String dir) {
        this.dir = dir;
        this.command = command;
    }

    @Override
    public void run() {
        try {
            Runtime.getRuntime().exec("cmd.exe /c" + command, null, new File(dir));
            sleep(500);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
