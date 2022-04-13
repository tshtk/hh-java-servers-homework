package counter;

import java.util.concurrent.atomic.AtomicLong;

public class Counter {
    public static final AtomicLong value = new AtomicLong(0);
}
