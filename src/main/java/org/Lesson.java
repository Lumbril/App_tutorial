package org;

import java.io.File;

public class Lesson {
    private String testUrl;
    private String inputCMD;
    private String outputCMD;
    private String text;

    public Lesson(String url, String text) {
        this.testUrl = url;
        this.text = text;

        File root = new File("src/main/resources");

        this.inputCMD = "java Main < " + root.getAbsolutePath() + url.substring(18);
        this.outputCMD = "> " + root.getAbsolutePath() + "/user_answer/ans.txt";
    }

    public String getInputCMD() {
        return inputCMD;
    }

    public void setInputCMD(String inputCMD) {
        this.inputCMD = inputCMD;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getOutputCMD() {
        return outputCMD;
    }

    public String getTestUrl() {
        return testUrl;
    }

    public String getUrlAns() {
        String urlAns = testUrl.substring(0, testUrl.length() - 6);

        return urlAns + "answers/";
    }
}
