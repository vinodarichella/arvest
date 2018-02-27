package com.arvest.app.domain;


import com.arvest.app.util.Constants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

@Document(collection = "robinhood_user")
public class RobinhoodUser {

    @Id
    private String id;

    @NotNull
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    @Indexed
    private String userId;

    @JsonIgnore
    @NotNull
    @Size(min = 60, max = 60)
    private String password;

    @Email
    @Size(min = 5, max = 100)
    @Indexed
    private String email;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RobinhoodUser that = (RobinhoodUser) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
