/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mah
 */
@Entity
@Table(name = "ARRANGEMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Arrangement.findAll", query = "SELECT a FROM Arrangement a")
    , @NamedQuery(name = "Arrangement.findByArrId", query = "SELECT a FROM Arrangement a WHERE a.arrId = :arrId")
    , @NamedQuery(name = "Arrangement.findByArrStyle", query = "SELECT a FROM Arrangement a WHERE a.arrStyle = :arrStyle")
    , @NamedQuery(name = "Arrangement.findByArrSize", query = "SELECT a FROM Arrangement a WHERE a.arrSize = :arrSize")
    , @NamedQuery(name = "Arrangement.findByArrQuantity", query = "SELECT a FROM Arrangement a WHERE a.arrQuantity = :arrQuantity")
    , @NamedQuery(name = "Arrangement.findByCustid", query = "SELECT a FROM Arrangement a WHERE a.custid = :custid")
    , @NamedQuery(name = "Arrangement.findByOrderid", query = "SELECT a FROM Arrangement a WHERE a.orderid = :orderid")})
public class Arrangement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "ARR_ID")
    private String arrId;
    @Size(max = 50)
    @Column(name = "ARR_STYLE")
    private String arrStyle;
    @Column(name = "ARR_SIZE")
    private Integer arrSize;
    @Column(name = "ARR_QUANTITY")
    private Integer arrQuantity;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "CUSTID")
    private String custid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "ORDERID")
    private String orderid;

    public Arrangement() {
    }

    public Arrangement(String arrId) {
        this.arrId = arrId;
    }

    public Arrangement(String arrId, String custid, String orderid) {
        this.arrId = arrId;
        this.custid = custid;
        this.orderid = orderid;
    }

    public String getArrId() {
        return arrId;
    }

    public void setArrId(String arrId) {
        this.arrId = arrId;
    }

    public String getArrStyle() {
        return arrStyle;
    }

    public void setArrStyle(String arrStyle) {
        this.arrStyle = arrStyle;
    }

    public Integer getArrSize() {
        return arrSize;
    }

    public void setArrSize(Integer arrSize) {
        this.arrSize = arrSize;
    }

    public Integer getArrQuantity() {
        return arrQuantity;
    }

    public void setArrQuantity(Integer arrQuantity) {
        this.arrQuantity = arrQuantity;
    }

    public String getCustid() {
        return custid;
    }

    public void setCustid(String custid) {
        this.custid = custid;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (arrId != null ? arrId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Arrangement)) {
            return false;
        }
        Arrangement other = (Arrangement) object;
        if ((this.arrId == null && other.arrId != null) || (this.arrId != null && !this.arrId.equals(other.arrId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Arrangement[ arrId=" + arrId + " ]";
    }
    
}
