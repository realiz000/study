package com.example.study.service;


import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.User;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserRepository userRepository;

    /*repository도 다른 서비스로 분류를 시켜야함.
    * 엔티티 repository에 대한 서비스를 따로둠으로서 나중에 추상화 할 때 다시 할 예정*/

    // 1. request data
    // 2. user 생성
    // 3. 생성된 데이터 -> UserApiResponse return
    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {

        // 1. request data
        UserApiRequest userApiRequest = request.getData();

        // 2. user 생성
        User user = User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status("REGISTERED") // 실수할 수도 있음 이렇게 박아놓으면, 나중에 enum으로 관리할 것
                .phoneNumber(userApiRequest.getPhoneNumber())
                .email(userApiRequest.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();

        User newUser = userRepository.save(user);

        // 3. 생성된 데이터 -> UserApiResponse return
        /*userApiResponse는 User객체를 가지고 만들 것이기 때문에
        * read나 update에서 다른 부분에서도 사용할 수 있기 때문에 따로 메소드를 뺄것임.
        * 아래 response 메소드*/


        return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {

//        // id -> repository getOne, getById
//        Optional<User> optional = userRepository.findById(id);
//        //user -> userApiResponse return
//
//        return optional
//                .map(user -> response(user))
//                //user가 존재하면, map : 다른 리턴 형태로 변환,
//                // user를 받아서 response method 통해서 Header<UserApiRespose>로 리턴
//                .orElseGet(
//                        // user가 없으면 method 호출해서 Header에 error를 넘기면서 데이터 없음을 리턴
//                        () -> Header.ERROR("데이터없음")
//                );

        /* 이렇게 한번에 할 수도 있음. * */
        return userRepository.findById(id)
                .map(user -> response(user))
                .orElseGet(
                    () -> Header.ERROR("데이터 없음")
                );


    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {
        // data
        UserApiRequest userApiRequest = request.getData();

        // id -> user 데이터 찾기
        Optional<User> optional = userRepository.findById(userApiRequest.getId());

        return optional.map(user -> {
            // data->update
            user.setAccount(userApiRequest.getAccount())
                    .setPassword(userApiRequest.getPassword())
                    .setStatus(userApiRequest.getStatus())
                    .setPhoneNumber(userApiRequest.getPhoneNumber())
                    .setEmail(userApiRequest.getEmail())
                    .setRegisteredAt(userApiRequest.getRegisteredAt())
                    .setUnregisteredAt(userApiRequest.getUnregisteredAt())
                    ;
            return user;
        })
        .map(user -> userRepository.save(user)) //update
        .map(updateUser->response(updateUser)) //userApiResponse
        .orElseGet(()->Header.ERROR("데이터 없음"));


        // uaserApiRespose
    }

    @Override
    public Header delete(Long id) {
        // id -> repository -> user
        Optional<User> optional = userRepository.findById(id);

        // delete
       return optional.map(user -> {
            userRepository.delete(user);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));

        //return
    }

    private Header<UserApiResponse> response(User user) {
        // user 객체 가지고 -> userApiResponse 리턴

        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword()) // TODO : 암호화, 길이
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();

        // Header + data return
        return Header.OK(userApiResponse);

    }
}
