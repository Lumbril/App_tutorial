package org;

import java.io.File;
import java.io.IOException;

public class Checker {
    private int maxPoint;
    private int points;

    public Checker(int maxPoint) {
        this.maxPoint = maxPoint;
    }

    public void check(String command, String urlAns, int step) throws IOException {
        String url = "src/main/resources/";

        //Runtime.getRuntime().exec("cmd.exe /c " + command, null, new File(url));

        File file = new File(url + "Test.class");
        file.delete();
    }

    public int getMaxPoint() {
        return maxPoint;
    }

    public int getPoints() {
        return points;
    }
}
