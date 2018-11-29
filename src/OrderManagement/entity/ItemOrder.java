/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mah
 */
@Entity
@Table(name = "ITEM_ORDER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemOrder.findAll", query = "SELECT i FROM ItemOrder i")
    , @NamedQuery(name = "ItemOrder.findByItemid", query = "SELECT i FROM ItemOrder i WHERE i.itemOrderPK.itemid = :itemid")
    , @NamedQuery(name = "ItemOrder.findByOrderid", query = "SELECT i FROM ItemOrder i WHERE i.itemOrderPK.orderid = :orderid")
    , @NamedQuery(name = "ItemOrder.findByQuantity", query = "SELECT i FROM ItemOrder i WHERE i.quantity = :quantity")})
public class ItemOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ItemOrderPK itemOrderPK;
    @Column(name = "QUANTITY")
    private Integer quantity;
    @JoinColumn(name = "ITEMID", referencedColumnName = "ITEMID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Item item;
    @JoinColumn(name = "ORDERID", referencedColumnName = "ORDERID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Order1 order1;

    public ItemOrder() {
    }

    public ItemOrder(ItemOrderPK itemOrderPK) {
        this.itemOrderPK = itemOrderPK;
    }

    public ItemOrder(String itemid, String orderid) {
        this.itemOrderPK = new ItemOrderPK(itemid, orderid);
    }

    public ItemOrderPK getItemOrderPK() {
        return itemOrderPK;
    }

    public void setItemOrderPK(ItemOrderPK itemOrderPK) {
        this.itemOrderPK = itemOrderPK;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Order1 getOrder1() {
        return order1;
    }

    public void setOrder1(Order1 order1) {
        this.order1 = order1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemOrderPK != null ? itemOrderPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemOrder)) {
            return false;
        }
        ItemOrder other = (ItemOrder) object;
        if ((this.itemOrderPK == null && other.itemOrderPK != null) || (this.itemOrderPK != null && !this.itemOrderPK.equals(other.itemOrderPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ItemOrder[ itemOrderPK=" + itemOrderPK + " ]";
    }
    
}
