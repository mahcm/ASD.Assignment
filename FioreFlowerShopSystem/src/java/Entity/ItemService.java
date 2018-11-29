package Entity;

import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class ItemService {

    @PersistenceContext
    EntityManager mgr;
    @Resource
    Query query;

    public ItemService(EntityManager mgr) {
        this.mgr = mgr;
    }

    public boolean addItem(Item item) {
        mgr.persist(item);
        return true;
    }

    public Item findItemByItemCode(String itemCode) {
        Item item = mgr.find(Item.class, itemCode);
        return item;
    }

    public boolean deleteItem(String itemCode) {
        Item item = findItemByItemCode(itemCode);
        if (item != null) {
            mgr.remove(item);
            return true;
        }
        return false;
    }

    public List<Item> findAll() {
        List itemList = mgr.createNamedQuery("Item.findAll").getResultList();
        return itemList;
    }

    public boolean updateItem(Item item) {
        Item tempItem = findItemByItemCode(item.getItemId());
        if (tempItem != null) {
            tempItem.setItemName(item.getItemName());
            tempItem.setItemType(item.getItemType());
            tempItem.setItemDesc(item.getItemDesc());
            tempItem.setQuantity(item.getQuantity());
            tempItem.setDefaultPrice(item.getDefaultPrice());
            return true;
        }
        return false;
    }
}
