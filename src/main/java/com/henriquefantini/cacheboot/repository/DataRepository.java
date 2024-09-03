package com.henriquefantini.cacheboot.repository;

import com.henriquefantini.cacheboot.model.Data;
import com.henriquefantini.cacheboot.util.RandomUtil;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DataRepository {

    public Data getRandonData() {
        Data data = new Data();

        data.setFirstName(RandomUtil.getRandomString(RandomUtil.getRandomNumberBetween(3, 15)));
        data.setLastname(RandomUtil.getRandomString(RandomUtil.getRandomNumberBetween(3, 15)));
        data.setAge(RandomUtil.getRandomNumberBetween(13, 99));
        data.setAuthorized(RandomUtil.getRandomBoolean());

        return data;
    }

    public List<Data> getData(int maxLength)
    {
        List<Data> retValue = new ArrayList<>();

        for(int count = 0 ; count < maxLength ; count++)
        {
            retValue.add( this.getRandonData() );
        }

        return retValue;
    }
}
