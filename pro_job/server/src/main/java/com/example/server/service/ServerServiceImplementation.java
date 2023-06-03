package com.example.server.service;

import com.example.server.model.Server;
import com.example.server.repo.ServerRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.w3c.dom.html.HTMLImageElement;

import javax.transaction.Status;
import javax.transaction.Transactional;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;

import static java.lang.Boolean.TRUE;
import static java.util.List.of;

@RequiredArgsConstructor //lombok is going to create all the constructors
@Service
@Transactional
@Slf4j
public class ServerServiceImplementation implements ServerService{

    private static final Status SERVER_UP = null;
    private static final Status SERVER_DOWN = null;
    private final ServerRepo serverRepo;
    @Override
    public Server create(Server server) {
        log.info("Saving new server:{}",server.getName());
        server.setImageUrl(setServerImageUrl());
        return serverRepo.save(server);
    }


    @Override
    public Collection<Server> List(int limit) {
        log.info("Fetching all Servers");
        return serverRepo.findAll(of(0,limit)).toList();
        return null;
    }

    @Override
    public Server get(Long id) {
        log.info("Saving new server by id:{}", id);

        return serverRepo.findById(id).get();
    }

    @Override
    public Server update(Server server) {
        log.info("Updating server:{}",server.getName());
        return serverRepo.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("deleting server by id:{}",id);
        serverRepo.deleteById(id);
        return TRUE;
    }

    @Override
    public Server ping(String ipAddress) throws IOException {
            log.info("pinging server IP:{}",ipAddress);
            Server server=serverRepo.findByIPAddress(ipAddress);
        InetAddress address= InetAddress.getByName(ipAddress);//TRY TO PING THE SERVER
        server.setStatus(address.isReachable(10000) ? SERVER_UP: SERVER_DOWN);
        serverRepo.save(server);
        return server;
    }

    private String setServerImageUrl() {
        return null;
    }

}
