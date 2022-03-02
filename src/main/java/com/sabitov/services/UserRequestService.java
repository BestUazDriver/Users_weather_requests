package com.sabitov.services;

import com.sabitov.models.UserRequest;
import com.sabitov.weather_rep.UserRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRequestService {

    private final UserRequestRepository userRequestRepository;

    public List<UserRequest> findAll(){
        return userRequestRepository.findAll();
    }

    public void save(UserRequest userRequest){
        userRequestRepository.save(userRequest);
    }

}
