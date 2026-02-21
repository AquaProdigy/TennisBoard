package org.roadmap.tennisboard.dto.match;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateMatchRequest {

    @NotBlank(message = "Name not be blank")
    @Size(min = 3, message = "Name is short")
    @Size(max = 255, message = "Name too short")
    private String player1;

    @NotBlank(message = "Name not be blank")
    @Size(min = 3, message = "Name is short")
    @Size(max = 255, message = "Name too short")
    private String player2;

    @Override
    public String toString() {
        return "CreateMatchRequest{" +
                "player1='" + player1 + '\'' +
                ", player2='" + player2 + '\'' +
                '}';
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }
}
