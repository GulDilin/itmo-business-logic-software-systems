package guldilin.controller;

import guldilin.config.jwt.JwtProvider;
import guldilin.dto.AuthRequestDTO;
import guldilin.dto.WorkerAuthResponseDTO;
import guldilin.dto.WorkerDTO;
import guldilin.dto.WorkerRegistrationRequestDTO;
import guldilin.service.WorkerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController implements ValidationExceptionHandler {

    private final WorkerService workerService;
    private final JwtProvider jwtProvider;

    @Autowired
    AuthController(WorkerService workerService, JwtProvider jwtProvider){
        this.workerService = workerService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/register")
    public WorkerDTO registerUser(@RequestBody @Valid WorkerRegistrationRequestDTO workerDTO) {
        return workerService.create(workerDTO);
    }

    @PostMapping("/auth")
    public WorkerAuthResponseDTO auth(@RequestBody AuthRequestDTO request) {
        WorkerDTO workerDTO = workerService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        String token = jwtProvider.generateToken(workerDTO.getLogin());
        WorkerAuthResponseDTO workerAuthResponseDTO = new WorkerAuthResponseDTO(workerDTO);
        workerAuthResponseDTO.setToken(token);
        return workerAuthResponseDTO;
    }

}