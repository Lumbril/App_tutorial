package org;

public class Lesson {
    private String url;
    private String inputCMD;
    private String outputCMD = "> ans.txt";
    private String text;

    public Lesson(String url, String text) {
        this.url = url;
        this.text = text;

        this.inputCMD = "java Main < " + url.substring(18);
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

    public String getUrl() {
        return url;
    }

    public String getUrlAns() {
        String urlAns = url.substring(18, url.length() - 6);

        return urlAns + "answers/";
    }
}
