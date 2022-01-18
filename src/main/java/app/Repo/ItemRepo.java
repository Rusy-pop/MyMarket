package app.Repo;

import app.Model.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepo extends CrudRepository<Item, Long> {
    public Item findByName(String itemName);
}
