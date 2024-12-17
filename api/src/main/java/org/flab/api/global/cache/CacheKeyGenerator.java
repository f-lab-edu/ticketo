package org.flab.api.global.cache;

import static org.flab.api.global.cache.CacheConstant.PREPARED_SEAT;

public class CacheKeyGenerator {

    public static String preparedSeatsForShowKeyGenerate(Long showId) {
        return PREPARED_SEAT + ":" + showId;
    }
}
