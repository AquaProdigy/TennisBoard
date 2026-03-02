package org.roadmap.tennisboard.dto.match;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMatchRequest {

    @NotBlank(message = "Player one not be blank")
    @Size(min = 3, message = "Player one is short")
    @Size(max = 30, message = "Player one too short")
    private String player1;

    @NotBlank(message = "Player two not be blank")
    @Size(min = 3, message = "Player two is short")
    @Size(max = 30, message = "Player two too short")
    private String player2;

}
