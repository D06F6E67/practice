package com.lee.mybatisplus.service;

import com.lee.mybatisplus.domain.request.GenCodeRequest;

public interface GenCodeService {

    byte[] genCode(GenCodeRequest genCodeRequest);

}
