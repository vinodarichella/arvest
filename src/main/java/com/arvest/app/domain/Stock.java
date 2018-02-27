package com.arvest.app.domain;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Document
@Data
public class Stock implements Serializable {

    @Id
    private String id;

    @NotNull
    @Pattern(regexp = "[A-Z]+")
    @Indexed
    private String symbol;

    private String quote;

    private String description;

    private String name;
}
