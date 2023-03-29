package com.sathish.dev.fancypdfinvoices.web;

import com.sathish.dev.fancypdfinvoices.dto.InvoiceDto;
import com.sathish.dev.fancypdfinvoices.model.Invoice;
import com.sathish.dev.fancypdfinvoices.service.InvoiceService;
import jakarta.validation.Valid;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InvoicesController {
    private final InvoiceService invoiceService;

    public InvoicesController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/invoices")
    // @RequestMapping(value = "/invoices", method = RequestMethod.GET)
    public Iterable<Invoice> invoices() {
        return invoiceService.findAll();
    }

    @PostMapping("/invoices")
    public Invoice createInvoice(@Valid @RequestBody InvoiceDto invoiceDto) {
        return invoiceService.create(invoiceDto.getUserId(), invoiceDto.getAmount());
    }

    @GetMapping("/invoicebyuser")
    public Iterable<Invoice> invoiceByUserID(@Param("userId") String userId) {
        return invoiceService.findUserByID(userId);
    }
}
