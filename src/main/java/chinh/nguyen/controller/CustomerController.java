package chinh.nguyen.controller;

import chinh.nguyen.model.Customer;
import chinh.nguyen.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.PublicKey;

@Controller
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    @GetMapping("/")
    public String list(Model model){
        model.addAttribute("listCustomer",customerService.findAll());
        return "list";
    }
    @GetMapping("show/create")
    public String showFormCreate(Model model){
        model.addAttribute("formCreate", new Customer());
        return "create";

    }
    @PostMapping("action/create")
    public String create(@ModelAttribute("formCreate") Customer customer ,Model model){
        customerService.save(customer);
        model.addAttribute("message","Create customer success!!");
        return "create";
    }
    @GetMapping("detail/{id}")
    public String showFormForAdd(@PathVariable("id") Long id, Model model){
        Customer customer = customerService.findById(id);
        model.addAttribute("customer",customer);
        return "detail";
    }
    @GetMapping("/edit/{id}")
    public ModelAndView showFormEdit(@PathVariable Long id){
        Customer customer = customerService.findById(id);
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("editForm",customer);
        return modelAndView;
    }
    @PostMapping("/edit/customer")
    public String editCustomer(@ModelAttribute("editForm") Customer customer){
        customerService.save(customer);
        return "redirect:/";
    }
    @GetMapping("/delete/{id}")
    public ModelAndView showFormDelete(@PathVariable Long id){
        Customer customer = customerService.findById(id);
        ModelAndView modelAndView = new ModelAndView("delete");
        modelAndView.addObject("deleteForm",customer);
        return modelAndView;
    }
    @PostMapping("/delete")
    public String deleteCustomer(@ModelAttribute("deleteForm") Customer customer){
        customerService.delete(customer.getId());
        return "redirect:/";
    }


}
