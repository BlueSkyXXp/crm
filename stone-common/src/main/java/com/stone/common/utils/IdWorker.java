package com.stone.common.utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class IdWorker {
    private static final SequenceUtils sequence = new SequenceUtils();

    public static String nextId(){
        return String.valueOf(sequence.nextId());
    }

}
