package com.example.study.controller.api;

import com.example.study.controller.CrudController;
import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.OrderGroup;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.OrderGroupApiRequest;
import com.example.study.model.network.response.OrderGroupApiResponse;
import com.example.study.service.OrderGroupApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@Slf4j
@RestController
@RequestMapping("/api/orderGroup")
public class OrderGroupApiController extends CrudController<OrderGroupApiRequest, OrderGroupApiResponse, OrderGroup> {

//    @Autowired
//    private OrderGroupApiService orderGroupApiService;
//
//    @PostConstruct
//    public void init() {
//        this.baseService = orderGroupApiService;
//    }

//    @Override
//    @PostMapping("")
//    public Header<OrderGroupApiResponse> create(@RequestBody Header<OrderGroupApiRequest> request) {
//        return orderGroupApiService.create(request);
//    }
//
//    @Override
//    @GetMapping("{id}")
//    public Header<OrderGroupApiResponse> read(@PathVariable Long id) {
//        log.info("id : {}", id);
//        return orderGroupApiService.read(id);
//    }
//
//    @Override
//    @PutMapping("")
//    public Header<OrderGroupApiResponse> update(@RequestBody Header<OrderGroupApiRequest> request) {
//        return orderGroupApiService.update(request);
//    }
//
//    @Override
//    @DeleteMapping("{id}")
//    public Header delete(@PathVariable Long id) {
//        return orderGroupApiService.delete(id);
//    }
}
