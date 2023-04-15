package com.example.tictactoe;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class TicTacToe extends Application {
    private Button buttons[][] = new Button[3][3];
    private Label player1ScoreLabel, player2ScoreLabel;
    private int person1Score =0, person2Score=0;
    private boolean person1=true;
    private BorderPane createContent(){
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));
        root.setBackground(new Background(new BackgroundFill(Color.valueOf("#FDE2F3"), CornerRadii.EMPTY, Insets.EMPTY)));
        // Title
        Label titletable = new Label("Criss-Cross");
        titletable.setTextFill(Color.valueOf("#2A2F4F"));
        titletable.setStyle("-fx-font-size : 30pt; -fx-font-weight : bold;");
        BorderPane.setAlignment(titletable, Pos.CENTER);
        root.setTop(titletable);

        //game Board
        GridPane board = new GridPane();
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                Button button = new Button();
                button.setStyle("-fx-font-size : 24pt; -fx-font-weight : bold;");
                button.setPrefSize(70, 70);
                button.setOnAction(actionEvent -> buttonClicked(button));
                buttons[i][j] =  button;
                board.add(button, j,i);
            }
        }
        board.setAlignment(Pos.CENTER);
        board.setPadding(new Insets(10, 0, 0, 0));
//        BorderPane.setAlignment(board, Pos.CENTER);
        root.setCenter(board);
        //Score
        HBox score = new HBox(20);
        score.setAlignment(Pos.CENTER);
        score.setPadding(new Insets( 10, 0, 0, 0));
        player1ScoreLabel = new Label("Person 1 : 0");
        player2ScoreLabel = new Label("Person 2 : 0");
        score.getChildren().addAll(player1ScoreLabel, player2ScoreLabel);
        player1ScoreLabel.setTextFill(Color.valueOf("#2A2F4F"));
        player2ScoreLabel.setTextFill(Color.valueOf("#2A2F4F"));
        player1ScoreLabel.setStyle("-fx-font-size: 14pt; -fx-font-weight: 500");
        player2ScoreLabel.setStyle("-fx-font-size: 14pt; -fx-font-weight: 600");
        root.setBottom(score);
        return root;
    }

    private void Winner(){
        // row
        for(int i=0;i<3;i++){
            if(buttons[i][0].getText().equals(buttons[i][1].getText())
                    && buttons[i][0].getText().equals(buttons[i][2].getText())
                    && !buttons[i][0].getText().isEmpty()){
                if(buttons[i][0].getText().equals("X")){
                    showWinner("Person 1");
                }else{
                    showWinner("Person 2");
                }
                return;
            }
        }
        //column
        for(int i=0;i<3;i++){
            if(buttons[0][i].getText().equals(buttons[1][i].getText())
                    && buttons[0][i].getText().equals(buttons[2][i].getText())
                    && !buttons[0][i].getText().isEmpty()){
                if(buttons[0][i].getText().equals("X")){
                    showWinner("Person 1");
                }else{
                    showWinner("Person 2");
                }
                return;
            }
        }
        //diagonal
        if(buttons[0][0].getText().equals(buttons[1][1].getText())
                && buttons[0][0].getText().equals(buttons[2][2].getText())
                && !buttons[0][0].getText().isEmpty()){
            if(buttons[0][0].getText().equals("X")){
                showWinner("Person 1");
            }else{
                showWinner("Person 2");
            }
        } else if (buttons[2][0].getText().equals(buttons[1][1].getText())
                && buttons[2][0].getText().equals(buttons[0][2].getText())
                && !buttons[2][0].getText().isEmpty()) {
            if(buttons[2][0].getText().equals("X")){
                showWinner("Person 1");
            }else{
                showWinner("Person 2");
            }
        }

        // tie
        full();
    }

    private void full(){
        for (Button row[] : buttons) {
            for (Button button : row) {
                if (button.getText().equals("")) {
                    return;
                }
            }
        }
        showWinner("tie");
        resetBoard();
    }
    private void showWinner(String Winner){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("");
        if(Winner.equals("tie")){
            alert.setTitle("Tie");
            alert.setContentText("Draw");
            alert.showAndWait();
            return;
        }
        alert.setTitle("Winner");
        alert.setContentText(Winner + " is the Winner");
        updateScore(Winner);
        alert.showAndWait();
        resetBoard();
    }

    private void resetBoard(){
        for (Button row[] : buttons){
            for (Button button : row){
                button.setText("");
            }
        }
        person1 = true;
    }

    private void buttonClicked(Button button){
        if(button.getText().equals("")){
            if (person1) {
                button.setText("X");
            } else {
                button.setText("O");
            }
            person1 =  !person1;

            Winner();
        }
    }

    private void updateScore(String winner){
        if(winner.equals("Person 1")){
            person1Score++;
            player1ScoreLabel.setText("Person 1 : "+ person1Score);
        }else{
            person2Score++;
            player2ScoreLabel.setText("Person 2 : "+ person2Score);
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent(), 300, 350);
        stage.setTitle("Criss-Cross");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}