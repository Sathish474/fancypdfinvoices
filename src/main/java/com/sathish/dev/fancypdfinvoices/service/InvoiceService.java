package com.sathish.dev.fancypdfinvoices.service;


import com.sathish.dev.fancypdfinvoices.model.Invoice;
import com.sathish.dev.fancypdfinvoices.model.User;
import com.sathish.dev.fancypdfinvoices.repository.InvoiceRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    private final UserService userService;
    private final String cdnUrl;

    public InvoiceService(InvoiceRepository invoiceRepository, UserService userService, @Value("${cdn.url}") String cdnUrl) {
        this.userService = userService;
        this.cdnUrl = cdnUrl;
        this.invoiceRepository = invoiceRepository;
    }

    @PostConstruct
    public void init() {
        System.out.println("Fetching PDF Template from S3...");
        // TODO download from s3 and save locally
    }

    @PreDestroy
    public void shutdown() {
        System.out.println("Deleting downloaded templates...");
        // TODO actual deletion of PDFs
    }

    @Transactional
    public Iterable<Invoice> findAll() {
        System.out.println("Is a database transaction open? = " + TransactionSynchronizationManager.isActualTransactionActive());

        return invoiceRepository.findAll();
    }

    @Transactional
    public Invoice create(String userId, Integer amount) {
        System.out.println("Is a database transaction open? = " + TransactionSynchronizationManager.isActualTransactionActive());
        String generatedPdfUrl = cdnUrl + "/images/default/sample.pdf";

        Invoice invoice = new Invoice();
        invoice.setPdfUrl(generatedPdfUrl);
        invoice.setAmount(amount);
        invoice.setUserId(userId);
        return invoiceRepository.save(invoice);
    }

    public Iterable<Invoice> findUserByID(String userId){
        System.out.println("Is a database transaction open? = " + TransactionSynchronizationManager.isActualTransactionActive());
        return invoiceRepository.findByUserID(userId);
    }
}
