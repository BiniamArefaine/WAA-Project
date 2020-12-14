package edu.miu.cs.auctionproject.service.impl;

import edu.miu.cs.auctionproject.domain.Address;
import edu.miu.cs.auctionproject.repository.AddressRepository;
import edu.miu.cs.auctionproject.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImp implements AddressService {
    @Autowired
    AddressRepository addressRepository;

    @Override
    public Address findAddressByUserId(Long userId) {
        return addressRepository.findByUserId(userId);
    }

    @Override
    public void saveAddress(Address address) {
        addressRepository.save(address);
    }
}
