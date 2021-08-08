package org;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class ControllerLesson {
    @FXML
    private Text lesson;

    @FXML
    public void click() {
        StringBuilder buf = new StringBuilder();

        for (int i = 0; i < lesson.getText().length(); i++) {
            if (lesson.getText().charAt(i) != '.') {
                buf.append(lesson.getText().charAt(i));
            } else {
                break;
            }
        }

        int num = Integer.valueOf(buf.toString()) - 1;
        Helper.currStep = num;
        String str = Helper.lessons[num].getText();

        Scene scene = lesson.getScene();
        Text text = (Text) scene.lookup("#text");
        text.setText(str);
        Text rating = (Text) scene.lookup("#rating");
        rating.setVisible(true);
        Button check = (Button) scene.lookup("#check");
        check.setVisible(true);
    }
}
