package com.example.server.service;

import com.example.server.model.Server;

import java.io.IOException;
import java.util.Collection;

public interface ServerService {

    Server create(Server server);
    Collection<Server>List(int Limit);
    Server get(Long id);
    Server update(Server server);
    Boolean delete(Long id);
    Server ping(String ipAddress) throws IOException;

}
