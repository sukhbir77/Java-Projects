
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import static javafx.geometry.Pos.CENTER;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Math_Tutor extends Application {

    TextField answer;
    Label question, correct, incorrect, result, show;
    Button new_ex, ans_ques;
    ArrayList<String> questions = new ArrayList<>();
    char sign[] = {'+', '-', 'X', '/'};
    int right = 0;
    int wrong = 0;
    int q = 0;
    int a = 0;
    int b = 0;
    int count = 0;

    @Override
    public void start(Stage ps) {

        questions.add("What is the addition of ");
        questions.add("What is subtraction of ");
        questions.add("What is multiplication of ");
        questions.add("What is closest division of ");

        randomValues();

        answer = new TextField();

        question = new Label(questions.get(q) + a + " " + sign[q] + " " + b + " ? ");
        question.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        question.setTextFill(Color.YELLOW);

        correct = new Label("Correct Answers : " + " " + right);
        correct.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
        correct.setTextFill(Color.GREEN);

        incorrect = new Label("Incorrect Answers : " + " " + wrong);
        incorrect.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
        incorrect.setTextFill(Color.RED);

        result = new Label("Result");
        result.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        result.setTextFill(Color.ORANGE);

        new_ex = new Button("New Exercise");
        ans_ques = new Button("Answer");
        show = new Label();

        ans_ques.setOnAction(e -> {
            run();

        });

        new_ex.setOnAction(e -> {
            askQuestion();
            count = 0;
            right = 0;
            wrong = 0;
            answer.setText("");
            correct.setText("Correct Answers:" + right);
            incorrect.setText("Incorrect Answers:" + wrong);
            show.setText("");
            ans_ques.setDisable(false);
        });

        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(10, 10, 10, 10));

        FlowPane buttonPane = new FlowPane();
        buttonPane.setAlignment(Pos.CENTER);

        FlowPane resultPane = new FlowPane();
        resultPane.setAlignment(Pos.CENTER);
        resultPane.setMargin(correct, new Insets(10, 40, 10, 10));

        buttonPane.getChildren().addAll(new_ex, ans_ques);
        resultPane.getChildren().addAll(correct, incorrect);

        vBox.getChildren().addAll(question, answer, buttonPane, result, resultPane, show);

        Scene s = new Scene(vBox);
        ps.setScene(s);
        ps.setTitle("Math Tutor");
        ps.show();

    }

    public void randomValues() {
        q = (int) (Math.random() * 4);
        a = (int) (Math.random() * 10) + 1;
        b = (int) (Math.random() * 10) + 1;
    }

    public void askQuestion() {
        randomValues();
        answer.setText("");
        question.setText(questions.get(q) + a + " " + sign[q] + " " + b + " ? ");
    }

    public boolean nonNumber(String check) {
        if (check.isEmpty()) {
            return false;
        } else {
            for (int i = 0; i < check.length(); i++) {
                if (Character.isDigit(check.charAt(i)) || check.charAt(i) == '.' || check.charAt(i) == '-');
                else {
                    return false;
                }
            }
        }
        return true;
    }

    public void wrongAlert() {

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText("Wrong Input");
        alert.setContentText("Please enter Only Numbers");
        alert.showAndWait();
    }

    public void check() {
        if (q == 0) {
            double res = a + b;
            if (Double.parseDouble(answer.getText()) == res) {
                right = right + 1;
                correct.setText("Correct Answers :" + right);
            } else {
                wrong = wrong + 1;
                incorrect.setText("InCorrect Answers :" + wrong);
            }

        } else if (q == 1) {
            double res = a - b;
            if (Double.parseDouble(answer.getText()) == res) {
                right = right + 1;
                correct.setText("Correct Answers :" + right);
            } else {
                wrong = wrong + 1;
                incorrect.setText("InCorrect Answers :" + wrong);
            }
        } else if (q == 2) {
            double res = a * b;
            if (Double.parseDouble(answer.getText()) == res) {
                right = right + 1;
                correct.setText("Correct Answers :" + right);
            } else {
                wrong = wrong + 1;
                incorrect.setText("InCorrect Answers :" + wrong);
            }
        } else if (q == 3) {
            double res = a / b;
            if (res < Math.floor(res) + 0.5) {
                res = Math.floor(res);
            } else {
                res = Math.ceil(res);
            }
            if (Double.parseDouble(answer.getText()) == res) {
                right = right + 1;
                correct.setText("Correct Answers :" + right);
            } else {
                wrong = wrong + 1;
                incorrect.setText("InCorrect Answers :" + wrong);
            }
        }
    }

    public void ratio() {
        if (wrong == 0) {
            show.setText("Absolutely perfect!!");
            show.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
            show.setTextFill(Color.GREEN);
        } else if ((right / wrong) * 100 > 70) {
            show.setText("Good Work");
            show.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
            show.setTextFill(Color.GREEN);
        } else {
            show.setText("Better Luck Next Time");
            show.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
            show.setTextFill(Color.RED);
        }
    }

    public void run() {
        if (nonNumber(answer.getText())) {
            if (count < 9) {

                check();
                askQuestion();
                count++;
            } else {
                check();
                answer.setText("");
                ans_ques.setDisable(true);
                ratio();
            }
        } else {
            wrongAlert();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
