package hello.itemservice.domain.item;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ItemRepositoryTest {
    private final ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    @DisplayName("Item 객체를 저장소에 저장한다.")
    public void saveItem() {
        //given
        Item item = new Item("itemA", 10000, 10);

        //when
        Item savedItem = itemRepository.save(item);

        //then
        assertThat(item).isEqualTo(savedItem);
    }

    @Test
    @DisplayName("저장소에 저장된 모든 아이템들을 리스트 형태로 반환한다.")
    public void findAll() {
        //given
        Item itemA = new Item("itemA", 10000, 10);
        Item itemB = new Item("itemB", 20000, 5);
        itemRepository.save(itemA);
        itemRepository.save(itemB);

        //when
        List<Item> actualItems = itemRepository.findAll();

        //then
        assertThat(actualItems).contains(itemA, itemB);
        assertThat(actualItems).hasSize(2);
    }


    @Test
    @DisplayName("")
    void updateItem() {
        //given
        Item item = new Item("item", 10000, 10);
        Item savedItem = itemRepository.save(item);
        Long itemId = savedItem.getId();

        //when
        Item updateParam = new Item("anotherItem", 20000, 30);
        itemRepository.update(itemId, updateParam);

        //then
        Item findItem = itemRepository.findById(itemId);

        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }
}