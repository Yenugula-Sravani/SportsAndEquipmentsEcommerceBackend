package com.restapi.service;

import com.restapi.dao.AddressDAO;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.Address;
import com.restapi.model.AppUser;
import com.restapi.repository.AddressRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.AddressRequest;
import com.restapi.response.AddressResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressDAO addressDAO;

    @Autowired
    private UserRepository userRepository;

    public AddressResponse findAll() {
        List<Address> addressList = addressRepository.findAll();
        return addressDAO.mapToAddressResponse(addressList);
    }

    @Transactional
    public AddressResponse create(AddressRequest addressRequest) {
        Address address = addressDAO.mapToAddress(addressRequest);
        AppUser appUser = userRepository.findById(addressRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("userId", "userId",
                        addressRequest.getUserId()));
        address.setAppUser(appUser);
        addressRepository.save(address);
        return findAll();
    }


    public AddressResponse update(AddressRequest addressRequest) {
        Address address = addressDAO.mapToAddress(addressRequest);
        AppUser appUser = userRepository.findById(addressRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("userId", "userId",
                        addressRequest.getUserId()));
        address.setAppUser(appUser);
        addressRepository.save(address);
        return findAll();
    }


    public AddressResponse deleteById(Long id) {
        addressRepository.deleteById(id);
        return findAll();
    }
}
