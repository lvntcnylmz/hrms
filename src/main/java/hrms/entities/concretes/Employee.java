package hrms.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "user_id")
@Table(name = "Employees")
public class Employee extends User{

    @NotNull(message = "First name cannot be empty.")
    @Column
    private String firstName;

    @NotNull(message = "Last name cannot be empty.")
    @Column
    private String lastName;

}
