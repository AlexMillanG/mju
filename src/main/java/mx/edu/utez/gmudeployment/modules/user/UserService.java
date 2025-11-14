package mx.edu.utez.gmudeployment.modules.user;

import lombok.RequiredArgsConstructor;
import mx.edu.utez.gmudeployment.utils.ApiResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public ResponseEntity<ApiResponseDto> findAll(){
        return new ResponseEntity<>(new ApiResponseDto(userRepository.findAll(),false,null), HttpStatus.OK);
    }
}
