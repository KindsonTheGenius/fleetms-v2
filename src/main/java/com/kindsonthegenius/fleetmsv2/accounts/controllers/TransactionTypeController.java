package com.kindsonthegenius.fleetmsv2.accounts.controllers;

import com.kindsonthegenius.fleetapp_v2.accounts.models.TransactionType;
import com.kindsonthegenius.fleetapp_v2.accounts.services.TransactionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TransactionTypeController {

    @Autowired
    private TransactionTypeService transactionTypeService;

    @GetMapping("/accounts/transactionTypes")
    public String parameters(Model model){
        List<TransactionType> transactionTypes = transactionTypeService.findAll();
        model.addAttribute("transactionTypes", transactionTypes);
        return "accounts/transactionTypes";
    }

    //Get Job Title by id
    @GetMapping("/accounts/transactionType/{id}")
    @ResponseBody
    public TransactionType getById(@PathVariable Integer id){
        return transactionTypeService.findById(id).orElse(null);
    }

    //Add TransactionType
    @PostMapping("/accounts/transactionTypes")
    public String addNew(TransactionType transactionType) {
        transactionTypeService.save(transactionType);
        return "redirect:/accounts/transactionTypes";
    }

    @RequestMapping(value="/accounts/transactionType/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable Integer id) {
        transactionTypeService.delete(id);
        return "redirect:/accounts/transactionTypes";
    }
}
