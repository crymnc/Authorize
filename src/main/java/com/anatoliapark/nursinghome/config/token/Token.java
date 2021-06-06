package com.anatoliapark.nursinghome.config.token;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Token {
    private String name;
    private String lastName;
    private String username;
    private String token;
}
