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

		if (!itemName.isEmpty()) {
			Item i;
			if (itemPrice.isEmpty()) {
				i = new Item(itemName, "0.00");
			} else i = new Item(itemName, itemPrice);
			itemRepo.save(i);
			i = null;
		}

		Iterable list = itemRepo.findAll();
		model.addAttribute("items", list);

		return "MyMarket";
	}

}
