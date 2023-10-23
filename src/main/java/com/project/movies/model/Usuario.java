package com.project.movies.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotNull(message = "O apelido é obrigatório.")
    @Size(min = 3, message = "O apelido deve ter pelo menos 3 caracteres.")
    private String apelido;

    @Column(unique = true, nullable = false)
    @NotNull(message = "O email é obrigatório.")
    @Email(message = "Formato de email inválido.")
    private String email;

    @Column(nullable = false)
    @NotNull(message = "A senha é obrigatório.")
    @Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres.")
    private String senha;
}
