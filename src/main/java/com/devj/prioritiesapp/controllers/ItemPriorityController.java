package com.devj.prioritiesapp.controllers;

import com.devj.prioritiesapp.exceptions.NumberOrderOutOfBoundsException;
import com.devj.prioritiesapp.models.ItemPriority;
import com.devj.prioritiesapp.repository.ItemPriorityRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ItemPriorityController {

    private final ItemPriorityRepository repository;

    public ItemPriorityController(ItemPriorityRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/")
    public String index(){ return "redirect:/listAll";}

    @GetMapping(value = "/listAll")
    public ModelAndView listAll(){
        return new ModelAndView("listAll").
                addObject("items", repository.
                        findAllByIdAfterOrderByPriorityOrderNumberDesc(0)).
                addObject("mostra", repository.countAllByIdAfter(0).longValue() > 0);
    }

    @RequestMapping(value = "/novo-item", method = RequestMethod.GET)
    public ModelAndView newItemPriority(){
        return new ModelAndView("item/newItemPriority").
                addObject("itemPriority", new ItemPriority());
    }

    @SuppressWarnings(value = "SpringMVCViewInspection")
    @RequestMapping(value = "/novo-item", method = RequestMethod.POST)
    public String newItemPriority(ItemPriority itemPriority){

        repository.save(itemPriority);

        return "redirect:/listAll";
    }

    @GetMapping(value = "/listAll/up/{id}")
    public String upOrder(@PathVariable long id){

        ItemPriority priorityList = repository.findById(id);

        try {
            priorityList.upOrder();
        } catch (NumberOrderOutOfBoundsException e) {
            return "erro";
        }

        repository.save(priorityList);

        return "redirect:/listAll";
    }

    @GetMapping(value = "/listAll/down/{id}")
    public String downOrder(@PathVariable long id){

        ItemPriority priorityList = repository.findById(id);

        try {
            priorityList.downOrder();
        } catch (NumberOrderOutOfBoundsException e) {
            return "erro";
        }

        repository.save(priorityList);

        return "redirect:/listAll";
    }

    @GetMapping(value = "/listAll/delete/{id}")
    public String deleteItem(@RequestParam("id") long id){

        repository.deleteById(id);

        return "redirect:/listAll";
    }
}
