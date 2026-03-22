package org.roadmap.tennisboard.api;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiValidateMessages {
    public static final String NAME_IS_SAME = "name can't be the same";
    public static final String TOO_SHORT = "length must not be less than 3 characters";
    public static final String TOO_LONG = "length must not exceed 20 characters";
    public static final String IS_BLANK = "Player one not be blank";
    public static final String ONLY_LETTERS = "name must contain only letters";
}
