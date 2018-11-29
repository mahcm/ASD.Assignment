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
@Table(name = "ITEM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Item.findAll", query = "SELECT i FROM Item i")
    , @NamedQuery(name = "Item.findByItemid", query = "SELECT i FROM Item i WHERE i.itemid = :itemid")
    , @NamedQuery(name = "Item.findByItemname", query = "SELECT i FROM Item i WHERE i.itemname = :itemname")
    , @NamedQuery(name = "Item.findByDefaultprice", query = "SELECT i FROM Item i WHERE i.defaultprice = :defaultprice")})
public class Item implements Serializable {

    @Size(max = 30)
    @Column(name = "ITEMNAME")
    private String itemname;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "ITEMID")
    private String itemid;
    @Column(name = "DEFAULTPRICE")
    private Integer defaultprice;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private Collection<ItemOrder> itemOrderCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private Collection<ItemArrangement> itemArrangementCollection;

    public Item() {
    }

    public Item(String itemid) {
        this.itemid = itemid;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }


    public Integer getDefaultprice() {
        return defaultprice;
    }

    public void setDefaultprice(Integer defaultprice) {
        this.defaultprice = defaultprice;
    }

    @XmlTransient
    public Collection<ItemOrder> getItemOrderCollection() {
        return itemOrderCollection;
    }

    public void setItemOrderCollection(Collection<ItemOrder> itemOrderCollection) {
        this.itemOrderCollection = itemOrderCollection;
    }

    @XmlTransient
    public Collection<ItemArrangement> getItemArrangementCollection() {
        return itemArrangementCollection;
    }

    public void setItemArrangementCollection(Collection<ItemArrangement> itemArrangementCollection) {
        this.itemArrangementCollection = itemArrangementCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemid != null ? itemid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.itemid == null && other.itemid != null) || (this.itemid != null && !this.itemid.equals(other.itemid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Item[ itemid=" + itemid + " ]";
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }
    
}
