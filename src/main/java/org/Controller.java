package org;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;

public class Controller {
    @FXML
    private VBox lessons;

    @FXML
    private static Text text;

    @FXML
    public void initialize() throws IOException {
        ObservableList elements = FXCollections.observableArrayList();

        File dir = new File("src/main/resources/lessons");
        File[] files = dir.listFiles();

        for (int i = 0; i < files.length; i++) {
            Parent root = FXMLLoader.load(getClass().getResource("/lesson.fxml"));
            Text text = (Text) root.lookup("#lesson");
            String number = Integer.toString(i + 1) + ". ";
            text.setText(number + files[i].getName().replaceFirst("[.][^.]+$", ""));
            elements.add(root);
        }

        lessons.getChildren().addAll(elements);
    }

    @FXML
    public void clickCheckButton() throws IOException, InterruptedException {
        int step = Helper.currStep;
        Lesson lesson = Helper.lessons[step];
        String url = "src/main/resources/";

        Runtime.getRuntime().exec("cmd.exe /c javac Test.java", null, new File(url));

        File tests = new File(lesson.getUrl());
        File[] testFiles = tests.listFiles();

        Checker checker = new Checker(testFiles.length);

        for (int i = 0; i < testFiles.length; i++) {
            String cmd = lesson.getInputCMD() + testFiles[i].getName() + " " + lesson.getOutputCMD();
            String urlAns = lesson.getUrlAns();

            checker.check(cmd, urlAns, i);
        }
    }
}
