package edu.miu.cs.auctionproject.service;

import edu.miu.cs.auctionproject.domain.Address;

public interface AddressService {
    Address findAddressByUserId(Long userId);

    void saveAddress(Address address);
}
