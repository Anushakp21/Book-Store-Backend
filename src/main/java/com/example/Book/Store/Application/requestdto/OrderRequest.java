package com.example.Book.Store.Application.requestdto;

import com.example.Book.Store.Application.entity.Address;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;


public class OrderRequest {

    @NotNull(message = "Address Cannot be null")
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress (Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "OrderRequest{" +
                "address=" + address +
                '}';
    }
}
