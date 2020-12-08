package edu.miu.cs.auctionproject.service;


import edu.miu.cs.auctionproject.domain.Credential;

import java.util.List;

public interface CredentialService {
    List<Credential> findAll();
    Credential save(Credential credential);
    Credential findById(Long cId);
    void delete(Long cId);
}
