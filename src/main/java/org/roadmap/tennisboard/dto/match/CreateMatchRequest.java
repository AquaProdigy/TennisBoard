package org.roadmap.tennisboard.dto.match;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMatchRequest {

    @NotBlank(message = "Name not be blank")
    @Size(min = 3, message = "Name is short")
    @Size(max = 255, message = "Name too short")
    private String player1;

    @NotBlank(message = "Name not be blank")
    @Size(min = 3, message = "Name is short")
    @Size(max = 255, message = "Name too short")
    private String player2;

}
