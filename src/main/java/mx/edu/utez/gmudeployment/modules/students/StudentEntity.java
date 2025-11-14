package mx.edu.utez.gmudeployment.modules.students;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import mx.edu.utez.gmudeployment.modules.user.UserEntity;

@Entity
@Table(name = "students")
@Data
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "student_id", nullable = false, unique = true)
    private String studentId;

    private String fullName;

    @OneToOne
    @JoinColumn(name="id_user",unique = true)
    private UserEntity user;
}
