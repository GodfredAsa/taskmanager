package io.taskmanager.util;

import java.util.UUID;

public class RandomUUIDGenerator {

    public String generateRandomUUID(){
        return UUID.randomUUID().toString().substring(0, 13);
    }
}
