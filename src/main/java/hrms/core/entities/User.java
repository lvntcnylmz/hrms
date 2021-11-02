package hrms.core.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class User {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Email(message = "Please enter a valid e-mail address.")
    @NotEmpty(message = "Email field cannot be empty.")
    @Column(name = "email", unique = true)
    private String email;

    @NotEmpty(message = "Password cannot be empty.")
    @Column(name = "password")
    private String password;

}
