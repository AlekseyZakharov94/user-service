package com.alzakhar.user_service.model;

import com.alzakhar.user_service.custom_annotations.UniqueEmail;
import com.alzakhar.user_service.custom_annotations.UniquePhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Pattern(regexp = "^[A-Za-z]*$",
            message = "Name is not valid, must contain only latin characters")
    private String name;

    @Column(unique = true)
    @NotBlank(message = "Email is mandatory")
    @Email
    @UniqueEmail
    private String email;

    @Column(unique = true)
    @NotBlank(message = "Phone number is mandatory")
    @Pattern(regexp = "^\\+?[(]?\\d{3}[)]?[-\\s.]?\\d{3}[-\\s.]?\\d{4,6}$",
            message = "Phone number is not valid." + "Write number without additional symbols and spaces")
    @UniquePhoneNumber
    private String phoneNumber;

    //Наверное лучше этот атрибут вынести в отдельную таблицу и создать связь @ManyToOne,
    // но для упрощения задачи не стал этого делать
    @NotNull
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    private Timestamp statusChangedTimestamp;
}
