package mx.edu.utez.gmudeployment.modules.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import mx.edu.utez.gmudeployment.modules.students.StudentEntity;

@Entity
@Table(name = "users")
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String username;
    private String password;
    private String email;
    @Column(name = "role")
    private String role;

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private StudentEntity student;
}
