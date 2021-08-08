package org;

public class Lesson {
    private String inputCMD;
    private String outputCMD = "src/main/resources/userFiles > ans.txt";
    private String text;

    public Lesson(String cmd, String text) {
        this.inputCMD = cmd;
        this.text = text;
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
}
