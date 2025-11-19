package mx.edu.utez.gmudeployment.modules.user;

import lombok.RequiredArgsConstructor;
import mx.edu.utez.gmudeployment.utils.ApiResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public ResponseEntity<ApiResponseDto> findAll(){
        return new ResponseEntity<>(new ApiResponseDto(userRepository.findAll(),false,null), HttpStatus.OK);
    }

    public ResponseEntity<ApiResponseDto> findById(Long id){

        Optional<UserEntity> foundUser = userRepository.findById(id);
        if (foundUser.isEmpty()){
            return new ResponseEntity<>(new ApiResponseDto(null,true,"usuario no encontrado"),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ApiResponseDto(foundUser.get(),false,null),HttpStatus.OK);
    }

    public ResponseEntity<ApiResponseDto> save (UserDto dto){

        return null;
    }
}
