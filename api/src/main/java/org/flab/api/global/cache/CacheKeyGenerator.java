package org.flab.api.global.cache;

import static org.flab.api.global.cache.CacheConstant.PREPARED_SEAT;
import static org.flab.api.global.cache.CacheConstant.SHOW;
import static org.flab.api.global.cache.CacheConstant.ZONE;
import static org.flab.api.global.cache.CacheConstant.ZONE_SEAT_LIST;

public class CacheKeyGenerator {

    public static String preparedSeatsForShowKeyGenerate(long showId) {
        return showId + ":" + PREPARED_SEAT;
    }

    public static String getZoneSeatListKeyGenerate(long showId, long zoneId) {
        return  SHOW + ":" + showId + ":" + ZONE + ":" + zoneId+ ":" + ZONE_SEAT_LIST;
    }
}
