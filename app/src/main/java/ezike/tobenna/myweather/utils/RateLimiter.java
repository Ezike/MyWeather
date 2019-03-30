package ezike.tobenna.myweather.utils;

import android.util.ArrayMap;

import java.util.concurrent.TimeUnit;

/**
 * Utility class that decides whether we should fetch some data or not.
 */
public class RateLimiter<KEY> {

    private final long timeOut;
    private ArrayMap<KEY, Long> timeStamps = new ArrayMap<>();

    public RateLimiter(int timeOut, TimeUnit timeUnit) {
        this.timeOut = timeUnit.toMillis(timeOut);
    }

    public synchronized boolean shouldFetch(KEY key) {
        Long lastFetched = timeStamps.get(key);
        long now = now();
        if (lastFetched == null) {
            timeStamps.put(key, now);
            return true;
        }

        if (now - lastFetched > timeOut) {
            timeStamps.put(key, now);
            return true;
        }
        return false;
    }

    private long now() {
        return System.currentTimeMillis();
    }

    public synchronized void reset(KEY key) {
        timeStamps.remove(key);
    }
}
