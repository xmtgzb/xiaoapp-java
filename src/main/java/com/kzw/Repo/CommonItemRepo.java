package com.kzw.Repo;

import com.kzw.entity.CommonItemEO;

public interface CommonItemRepo {

    public CommonItemEO getByCode(String parentCode);

}
