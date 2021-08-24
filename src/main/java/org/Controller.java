package org;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Controller {
    FileChooser fileChooser = new FileChooser();

    @FXML
    private VBox lessons;

    @FXML
    private Text rating;

    @FXML
    public void initialize() throws IOException {
        ObservableList elements = FXCollections.observableArrayList();

        File dir = new File("src/main/resources/lessons");
        File[] files = dir.listFiles();

        for (int i = 0; i < files.length; i++) {
            Parent root = FXMLLoader.load(getClass().getResource("/lesson.fxml"));
            Text text = (Text) root.lookup("#lesson");
            String number = (i + 1) + ". ";
            text.setText(number + files[i].getName().replaceFirst("[.][^.]+$", ""));
            elements.add(root);
        }

        lessons.getChildren().addAll(elements);
    }

    @FXML
    public void clickCheckButton() throws InterruptedException {
        int step = Helper.currStep;
        Lesson lesson = Helper.lessons[step];

        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JAVA", "*.java"));
        File file = fileChooser.showOpenDialog(new Stage());

        if (file != null) {
            String cmd = "cmd.exe /c javac " + file.getName();
            String dir = file.getPath().replaceFirst("[\\\\][^\\\\]+$", "");

            CompileFile compileFile = new CompileFile(cmd, dir);
            compileFile.start();
            compileFile.join();

            File tests = new File(lesson.getTestUrl());
            File[] testFiles = tests.listFiles();

            Checker checker = new Checker(testFiles.length, dir, testFiles, lesson);

            checker.start();
            checker.join();

            rating.setText(checker.getRating());

            File javaClass = new File(file.getAbsolutePath().replaceFirst("[.][^.]+$", ".class"));
            javaClass.delete();
        }
    }
}
