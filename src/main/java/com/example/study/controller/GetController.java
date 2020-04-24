package com.example.study.controller;

import org.springframework.web.bind.annotation.*;

@RestController // 컨트롤러로 사용한다는 지시자
@RequestMapping("/api") // 이곳으로 들어 올 API를 매핑하기 위함, http://localhost:8080/api
public class GetController {

    @RequestMapping(method= RequestMethod.GET, path="/getMethod") // method는 어떤 타입으로 받을 것인지, path는 어떤 주소로 받겠다라는 설정
    public String getRequest() { // 사용자의 요청에 대해서 한가지 메소드로 받아들일 수 있음. localhost:8080/api/getMethod
        return "Hi getMethod";
    } // 작성한 메소드 검증하기 위해서 : Junit, web browser 통해서 직접 확인, Rest tool로 확인

    @GetMapping("/getParameter") // localhost:8080/api/getParameter?id=1234&password=abcd
    public String getParameter(@RequestParam String id, @RequestParam(name="password") String pwd) {
        System.out.println("id : " + id);
        System.out.println("pwd : " + pwd);

        return id+pwd;
    }
}
