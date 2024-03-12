package com.test.request;

import static java.lang.Thread.sleep;

import java.util.HashMap;
import java.util.Map;

public interface RequestClient {
    void receive(int message);
}

class Request {
    static Map<String, RequestClient> map = new HashMap<>();

    public static void request(String id) {
        doRequest(id);
    }

    private static void doRequest(String id) {
        Thread thread = new Thread(() -> {
            try {
                sleep(1000);
                map.get(id).receive(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });
        thread.start();
    }

    public static String getId(RequestClient client) {
        String id = getId(client);
        map.put(id, client);
        return client.toString();
    }
}


