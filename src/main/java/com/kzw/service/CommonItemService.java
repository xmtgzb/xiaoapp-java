package com.kzw.service;

import com.kzw.entity.CommonItemEO;

public interface CommonItemService {
    public CommonItemEO getByCode(String parentCode);
}
