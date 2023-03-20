package com.pritimahajan.crudfirestore.domain;

import com.google.cloud.firestore.annotation.DocumentId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gcp.data.firestore.Document;

@Setter
@Getter
@AllArgsConstructor
@Document(collectionName = "Users")
public class User
{

    @DocumentId
    private String email;

    private String name;

    public User()
    {
    }
}
