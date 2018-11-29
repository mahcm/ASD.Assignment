/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mah
 */
@Entity
@Table(name = "ORDER1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Order1.findAll", query = "SELECT o FROM Order1 o")
    , @NamedQuery(name = "Order1.findByOrderid", query = "SELECT o FROM Order1 o WHERE o.orderid = :orderid")
    , @NamedQuery(name = "Order1.findByOrderdate", query = "SELECT o FROM Order1 o WHERE o.orderdate = :orderdate")
    , @NamedQuery(name = "Order1.findByFinaldate", query = "SELECT o FROM Order1 o WHERE o.finaldate = :finaldate")
    , @NamedQuery(name = "Order1.findByOrdertype", query = "SELECT o FROM Order1 o WHERE o.ordertype = :ordertype")
    , @NamedQuery(name = "Order1.findByLocation", query = "SELECT o FROM Order1 o WHERE o.location = :location")
    , @NamedQuery(name = "Order1.findByCompletedate", query = "SELECT o FROM Order1 o WHERE o.completedate = :completedate")
    , @NamedQuery(name = "Order1.findByPrice", query = "SELECT o FROM Order1 o WHERE o.price = :price")})
public class Order1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "ORDERID")
    private String orderid;
    @Size(max = 10)
    @Column(name = "ORDERDATE")
    private String orderdate;
    @Size(max = 10)
    @Column(name = "FINALDATE")
    private String finaldate;
    @Column(name = "ORDERTYPE")
    private Character ordertype;
    @Size(max = 50)
    @Column(name = "LOCATION")
    private String location;
    @Size(max = 10)
    @Column(name = "COMPLETEDATE")
    private String completedate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRICE")
    private Double price;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order1")
    private Collection<ItemOrder> itemOrderCollection;
    @JoinColumn(name = "CUSTID", referencedColumnName = "CUSTID")
    @ManyToOne(optional = false)
    private Customer custid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderid")
    private Collection<Arrangement> arrangementCollection;

    public Order1() {
    }

    public Order1(String orderid) {
        this.orderid = orderid;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    public String getFinaldate() {
        return finaldate;
    }

    public void setFinaldate(String finaldate) {
        this.finaldate = finaldate;
    }

    public Character getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(Character ordertype) {
        this.ordertype = ordertype;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCompletedate() {
        return completedate;
    }

    public void setCompletedate(String completedate) {
        this.completedate = completedate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @XmlTransient
    public Collection<ItemOrder> getItemOrderCollection() {
        return itemOrderCollection;
    }

    public void setItemOrderCollection(Collection<ItemOrder> itemOrderCollection) {
        this.itemOrderCollection = itemOrderCollection;
    }

    public Customer getCustid() {
        return custid;
    }

    public void setCustid(Customer custid) {
        this.custid = custid;
    }

    @XmlTransient
    public Collection<Arrangement> getArrangementCollection() {
        return arrangementCollection;
    }

    public void setArrangementCollection(Collection<Arrangement> arrangementCollection) {
        this.arrangementCollection = arrangementCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderid != null ? orderid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Order1)) {
            return false;
        }
        Order1 other = (Order1) object;
        if ((this.orderid == null && other.orderid != null) || (this.orderid != null && !this.orderid.equals(other.orderid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Order1[ orderid=" + orderid + " ]";
    }
    
}
