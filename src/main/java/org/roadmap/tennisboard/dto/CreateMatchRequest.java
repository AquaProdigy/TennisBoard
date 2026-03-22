package org.roadmap.tennisboard.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.roadmap.tennisboard.api.ApiValidateMessages;

@Getter
@Setter
public class CreateMatchRequest {

    @NotBlank(message = "Player one - " + ApiValidateMessages.IS_BLANK)
    @Size(min = 3, message = "Player one - " + ApiValidateMessages.TOO_SHORT)
    @Size(max = 20, message = "Player one - " + ApiValidateMessages.TOO_LONG)
    @Pattern(regexp = "^[a-zA-Z\\s-]+$", message = "Player one - " + ApiValidateMessages.ONLY_LETTERS)
    private String firstPlayer;

    @NotBlank(message = "Player two - " + ApiValidateMessages.IS_BLANK)
    @Size(min = 3, message = "Player two - " + ApiValidateMessages.TOO_SHORT)
    @Size(max = 20, message = "Player two - " + ApiValidateMessages.TOO_LONG)
    @Pattern(regexp = "^[a-zA-Z\\s-]+$", message = "Player two - " + ApiValidateMessages.ONLY_LETTERS)
    private String secondPlayer;

}
