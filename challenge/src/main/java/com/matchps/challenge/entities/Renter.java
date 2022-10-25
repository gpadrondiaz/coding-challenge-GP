package com.matchps.challenge.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The persistent class for the "VEHICLE" database table.
 *
 */
@Entity
@Table(name="\"renter\"", schema ="\"uber_car_rental\"")
public class Renter implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="\"renter_id\"")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="\"document_id\"")
    private String documentId;

    @Column(name="\"first_name\"")
    private String firstName;

    @Column(name="\"last_name\"")
    private String lastName;

    @Column(name="\"telephone_number\"")
    private String telephoneNumber;

    @Column(name="\"credit_card_number\"")
    private String creditCardNumber;

    @Column()
    private String email;

    public Renter() {
    }

    public Renter(String documentId, String firstName, String lastName, String telephoneNumber, String email, String creditCardNumber) {
        this.documentId = documentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.creditCardNumber = creditCardNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }
}
