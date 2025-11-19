package mx.edu.utez.gmudeployment.modules.user;

import lombok.RequiredArgsConstructor;
import mx.edu.utez.gmudeployment.modules.role.Roles;
import mx.edu.utez.gmudeployment.modules.students.StudentEntity;
import mx.edu.utez.gmudeployment.modules.students.StudentRepository;
import mx.edu.utez.gmudeployment.utils.ApiResponseDto;
import mx.edu.utez.gmudeployment.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentRepository studentRepository;


    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponseDto> findAll(){
        return new ResponseEntity<>(new ApiResponseDto(userRepository.findAll(),false,null, HttpStatus.OK), HttpStatus.OK);
    }

    public ResponseEntity<ApiResponseDto> findById(Long id){

        Optional<UserEntity> foundUser = userRepository.findById(id);
        if (foundUser.isEmpty()){
            return new ResponseEntity<>(new ApiResponseDto(null,true,"usuario no encontrado", HttpStatus.NOT_FOUND),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ApiResponseDto(foundUser.get(),false,null, HttpStatus.OK),HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseEntity<ApiResponseDto> save (UserDto dto){
        ApiResponseDto body;
        try {
            UserEntity u = new UserEntity();
            u.setRole(Roles.STUDENT.name());
            u.setUsername(dto.getUsername());
            u.setEmail(dto.getEmail());
            u.setPassword(PasswordUtils.generateEncodedPassword(dto.getUsername(),dto.getFullName()));

            u = userRepository.save(u);

            StudentEntity s = new StudentEntity();
            s.setFullName(dto.getFullName());
            s.setUser(u);
            s.setMatricula(generateMatricula());

            s = studentRepository.saveAndFlush(s);

            //guardar estudiante

             body = new ApiResponseDto(null, false, "operación realizada con éxito", HttpStatus.CREATED);
            //2025gmu0000
        } catch (Exception e) {
            body = new ApiResponseDto(null, true, "no se pudo registrar al usuario", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(body,body.getStatus());
    }



    private String generateMatricula(){
        LocalDateTime now = LocalDateTime.now();
        return now.getYear() + "gmu" + now.getMonthValue()+ now.getDayOfMonth() + "" + now.getHour() + "" + now.getMinute() + ""+ now.getSecond();
    }
}
