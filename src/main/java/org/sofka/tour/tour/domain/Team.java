package org.sofka.tour.tour.domain;

import lombok.*;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Team")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Team {

    @Id
    @NonNull
    private String codeTeam;
    private String name;
    private String idCountry;


}
