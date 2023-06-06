package de.neuefische.backend.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@With
@Builder
@Document("Cars")
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    @Id
    private String objectId;

    @Indexed(unique = true)
    private String brand;
    private String model;

}
