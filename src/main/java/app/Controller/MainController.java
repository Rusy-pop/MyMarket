package app.Controller;

import app.Model.Item;
import app.Repo.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

	@Autowired
	ItemRepo itemRepo;

	@GetMapping("/")
	public String main(Model model, String message){
		return "main";
	}

	@PostMapping("/")
	public String reg(Model model, String message) {
		model.addAttribute("message", message);
		return "main";
	}

	@GetMapping("/MyMarket")
	public String showMarket(Model model) {
		Iterable list = itemRepo.findAll();
		model.addAttribute("items", list);
		return "MyMarket";
	}

	@PostMapping("/MyMarket")
	public String addItem(@RequestParam String itemName,
						  @RequestParam String itemPrice,
						  Model model){
		if (itemRepo.findByName(itemName)==null){
			Item it = new Item(itemName, itemPrice);
			itemRepo.save(it);
			model.addAttribute("message", "Товар добавлен");
		} else model.addAttribute("message", "Товар с таким именем уже существует");

		Iterable list = itemRepo.findAll();
		model.addAttribute("items", list);

		return "MyMarket";
	}
}
