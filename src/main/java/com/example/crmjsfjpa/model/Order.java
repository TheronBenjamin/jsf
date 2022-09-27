package com.example.crmjsfjpa.model;

//import jakarta.persistence.*;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "orders")
@NamedQuery(name = "Order.findByLabel", query = "SELECT o FROM Order o WHERE o.label=:label")
public class Order implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 100, nullable = false)
	private String label;
	
	/** average daily rate excluding tax adr_et */
	@Column(nullable = false)
	private Double adrEt;

	@Column(nullable = false)
	private Double numberOfDays;

	@Column(nullable = false)
	private Double tva;

	@Column(length = 30, nullable = false)
	private String status;

	@Column(length = 100, nullable = false)
	private String type;

	@Column(name = "notes")
	private String orderNotes;
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	public Order() {
		super();
	}

	public Order(Integer id, String label, Double adrEt, Double numberOfDays, Double tva, String status,
				 String type, String orderNotes, Customer customer) {
		this.id = id;
		this.label = label;
		this.adrEt = adrEt;
		this.numberOfDays = numberOfDays;
		this.tva = tva;
		this.status = status;
		this.type = type;
		this.orderNotes = orderNotes;
		this.customer = customer;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Double getAdrEt() {
		return adrEt;
	}

	public void setAdrEt(Double adrEt) {
		this.adrEt = adrEt;
	}

	public Double getNumberOfDays() {
		return numberOfDays;
	}

	public void setNumberOfDays(Double numberOfDays) {
		this.numberOfDays = numberOfDays;
	}

	public Double getTva() {
		return tva;
	}

	public void setTva(Double tva) {
		this.tva = tva;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOrderNotes() {
		return orderNotes;
	}

	public void setOrderNotes(String orderNotes) {
		this.orderNotes = orderNotes;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Order{" +
				"id=" + id +
				", label='" + label + '\'' +
				", adrEt=" + adrEt +
				", numberOfDays=" + numberOfDays +
				", tva=" + tva +
				", status='" + status + '\'' +
				", type='" + type + '\'' +
				", orderNotes='" + orderNotes + '\'' +
				", customer=" + customer +
				'}';
	}
}
