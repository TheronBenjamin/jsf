package com.example.crmjsfjpa.model;

//import jakarta.persistence.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 100, nullable = false)
	private String lastname;

	@Column(length = 100, nullable = false)
	private String firstname;

	@Column(length = 200, nullable = false)
	private String company;

	@Column(nullable = false)
	private String mail;

	@Column(length = 15, nullable = false)
	private String phone;

	@Column(length = 15, nullable = false)
	private String mobile;
	
	private String notes;
	
	private Boolean active;
	@OneToMany(mappedBy = "customer")
	private List<Order> orders;

	public Customer() {
		super();
	}

	public Customer(Integer id, String lastname, String firstname, String company, String mail, String phone, String mobile,
					String notes, Boolean active, List<Order> orders) {
		super();
		this.id = id;
		this.lastname = lastname;
		this.firstname = firstname;
		this.company = company;
		this.mail = mail;
		this.phone = phone;
		this.mobile = mobile;
		this.notes = notes;
		this.active = active;
		this.orders = orders;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public Boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getActive() {
		return active;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "Customer{" +
				"id=" + id +
				", lastname='" + lastname + '\'' +
				", firstname='" + firstname + '\'' +
				", company='" + company + '\'' +
				", mail='" + mail + '\'' +
				", phone='" + phone + '\'' +
				", mobile='" + mobile + '\'' +
				", notes='" + notes + '\'' +
				", active=" + active +
				'}';
	}
}
