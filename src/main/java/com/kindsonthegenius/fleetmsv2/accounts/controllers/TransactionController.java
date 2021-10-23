package com.kindsonthegenius.fleetmsv2.accounts.controllers;

import com.kindsonthegenius.fleetapp_v2.accounts.models.Transaction;
import com.kindsonthegenius.fleetapp_v2.accounts.services.TransactionService;
import com.kindsonthegenius.fleetapp_v2.accounts.services.TransactionStatusService;
import com.kindsonthegenius.fleetapp_v2.accounts.services.TransactionTypeService;
import com.kindsonthegenius.fleetapp_v2.hr.services.EmployeeService;
import com.kindsonthegenius.fleetapp_v2.parameters.services.ClientService;
import com.kindsonthegenius.fleetapp_v2.parameters.services.ContactService;
import com.kindsonthegenius.fleetapp_v2.parameters.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private TransactionStatusService transactionStatusService;
    @Autowired
    private TransactionTypeService transactionTypeService;
    @Autowired
    private ContactService contactService;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private EmployeeService employeeService;

    public Model addModelAttributes(Model model){
        model.addAttribute("transactionStatuses", transactionStatusService.findAll());
        model.addAttribute("transactionTypes", transactionTypeService.findAll());
        model.addAttribute("contacts", contactService.findAll());
        model.addAttribute("suppliers", supplierService.findAll());
        model.addAttribute("clients", clientService.findAll());
        model.addAttribute("employees", employeeService.findAll());
        return model;
    }

    @GetMapping("/accounts/transactions")
    public String  getAll(Model model){
        List<Transaction> transactions =   transactionService.findAll();
        model.addAttribute("transactions", transactions);
        addModelAttributes(model);
        return "/accounts/transactions";
    }

    @GetMapping("/accounts/transactionAdd")
    public String addTransaction(Model model){
        addModelAttributes(model);
        return "accounts/transactionAdd";
    }

    //The op parameter is either Edit or Details
    @GetMapping("/accounts/transaction/{op}/{id}")
    public String editTransaction(@PathVariable Integer id, @PathVariable String op, Model model){
        Transaction transaction = transactionService.findById(id);
        model.addAttribute("transaction", transaction);
        addModelAttributes(model);
        return "/accounts/transaction"+ op;
    }

    @PostMapping("/accounts/transactions")
    public String save(Transaction transaction){
        transactionService.save(transaction);
        return "redirect:/accounts/transactions";
    }

    @RequestMapping(value = "/accounts/transactions/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public  String delete(@PathVariable Integer id){
        transactionService.delete(id);
        return "redirect:/accounts/transactions";
    }
}
