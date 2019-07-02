package com.example.task1.ui.main.Tab1.model;

public class ContactList {

  private String name;
  private String phone_number;
  private String address;
  private Long personId;
  private Long thumnailld;
  private String contactid;

  public ContactList(String name, String phone_number, String address, Long personId,
      Long thumnailld, String contactid) {

    this.name = name;
    this.phone_number = phone_number;
    this.address = address;
    this.personId = personId;
    this.thumnailld = thumnailld;
    this.contactid = contactid;

  }

  public String getName() {
    return name;
  }

  public String getPhone_number() {
    return phone_number;
  }

  public String getAddress() {
    return address;
  }

  public Long getPersonId() {
    return personId;
  }

  public Long getThumnailld() {
    return thumnailld;
  }

  public String getContactid() {
    return contactid;
  }
}
