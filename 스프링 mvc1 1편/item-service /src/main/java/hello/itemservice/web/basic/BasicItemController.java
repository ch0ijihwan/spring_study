package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("basic/items")
public class BasicItemController {
    private final ItemRepository itemRepository;

    @Autowired
    public BasicItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute(item);
        return "basic/item";
    }

    //테스트용 데이터 추가
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 30));
    }

    @GetMapping("/add")
    public String addForm() {

        return "basic/addForm";
    }

    //    @PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                            @RequestParam Integer price,
                            @RequestParam Integer quantity,
                            Model model) {
        Item savedItem = itemRepository.save(new Item(itemName, price, quantity));
        model.addAttribute(savedItem);
        return "basic/item";
    }

    //    @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item, Model model) {
        itemRepository.save(item);
//        model.addAttribute(item); @ModelAttribute 에 의해 자동 추가
        return "basic/item";
    }

    //    @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item, Model model) {
        //("item") 생략시 첫글자를 소문자로 바꾼 것이 디폴트 => Item 을 item 으로
        itemRepository.save(item);
//        model.addAttribute(item); @ModelAttribute 에 의해 자동 추가
        return "basic/item";
    }

    //    @PostMapping("/add")
    public String addItemV4(Item item) {
        //("item") 생략시 첫글자를 소문자로 바꾼 것이 디폴트 => Item 을 item 으로
        itemRepository.save(item);
//        model.addAttribute(item);
        return "basic/item";
    }

    //        @PostMapping("/add") //리다이렉트 방식 사용
//    public String addItemV5(Item item) {
//        //("item") 생략시 첫글자를 소문자로 바꾼 것이 디폴트 => Item 을 item 으로
//        itemRepository.save(item);
////        model.addAttribute(item);
//        return "redirect:/basic/items"+ item.getId();
//    }

    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes) {
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());//하단 리턴 처럼 치환해주면 치환해준 값으로 넘어감
        redirectAttributes.addAttribute("status", true);// 그렇지 않은경우 쿼리 파라미터로 넘어간다

        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item foundItem = itemRepository.findById(itemId);
        model.addAttribute("item", foundItem);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }
}
