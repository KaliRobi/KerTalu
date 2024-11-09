package com.kertalu.kertalu.finance;

import com.kertalu.kertalu.inventory.InventoryAsset;
import com.kertalu.kertalu.repositories.ClientRepository;
import com.kertalu.kertalu.repositories.InventoryAssetRepository;
import com.kertalu.kertalu.repositories.TransactionRepository;
import com.kertalu.kertalu.users.clients.ktclients.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FinancialService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private InventoryAssetRepository inventoryAssetRepository; // For asset management

    // Add a transaction (income or expense)
    public void addTransaction(Client client, String type, double amount, String category, String description) {

            Transaction transaction = new Transaction(type, amount, category, client, description);
            transactionRepository.save(transaction);

    }

    // Calculate net worth (income - expenses)
    public double calculateNetWorth(Long userId) {
        List<Transaction> transactions = transactionRepository.findByUserId(userId);
        double income = 0;
        double expense = 0;

        for (Transaction transaction : transactions) {
            if ("income".equalsIgnoreCase(transaction.getType())) {
                income += transaction.getAmount();
            } else if ("expense".equalsIgnoreCase(transaction.getType())) {
                expense += transaction.getAmount();
            }
        }

        // Include asset depreciation if necessary
        double assetDepreciation = calculateAssetDepreciation(userId);
        return income - expense - assetDepreciation;
    }

    // Retrieve all transactions for a user
    public List<Transaction> getTransactions(Long userId) {
        return transactionRepository.findByUserId(userId);
    }

    // Calculate total assets, including depreciation
    public double calculateTotalAssets(Long userId) {
        List<InventoryAsset> assets = inventoryAssetRepository.findByUserId(userId);
        double totalAssetsValue = 0;

        for (InventoryAsset asset : assets) {
            totalAssetsValue += asset.getValue();
        }

        return totalAssetsValue;
    }

    // Calculate asset depreciation (assuming straight-line depreciation for simplicity)
    private double calculateAssetDepreciation(Long userId) {
        List<InventoryAsset> assets = inventoryAssetRepository.findByUserId(userId);
        double depreciation = 0;

        for (InventoryAsset asset : assets) {
            // For now, assume 10% depreciation
            depreciation += asset.getValue() * 0.1;
        }

        return depreciation;
    }


    // Generate profit and loss statement for a specific time period
    public Map<String, Double> generateProfitAndLoss(Long userId, Instant startDate, Instant endDate) {
        List<Transaction> transactions = transactionRepository.findByUserId(userId);
        Map<String, Double> pAndL = new HashMap<>();
        double income = 0;
        double expenses = 0;

        for (Transaction transaction : transactions) {
            Instant transactionDate = transaction.getDate(); // Assume the date in the transaction is of type Instant
            // Check if transaction date is within the specified range
            if (transactionDate.isAfter(startDate) && transactionDate.isBefore(endDate)) {
                if ("income".equalsIgnoreCase(transaction.getType())) {
                    income += transaction.getAmount();
                } else if ("expense".equalsIgnoreCase(transaction.getType())) {
                    expenses += transaction.getAmount();
                }
            }
        }

        // Return Profit and Loss as a map
        pAndL.put("Income", income);
        pAndL.put("Expenses", expenses);
        pAndL.put("Net Profit/Loss", income - expenses);
        return pAndL;
    }


    // Forecast future income and expenses based on historical data (simplified)
    public double forecastIncome(Long userId, int monthsAhead) {
        List<Transaction> transactions = transactionRepository.findByUserId(userId);
        double totalIncome = 0;
        int transactionCount = 0;

        for (Transaction transaction : transactions) {
            if ("income".equalsIgnoreCase(transaction.getType())) {
                totalIncome += transaction.getAmount();
                transactionCount++;
            }
        }

        double averageIncome = transactionCount > 0 ? totalIncome / transactionCount : 0;
        return averageIncome * monthsAhead; // Predict future income based on average income
    }

    // Cash Flow Analysis (OPEX, CAPEX)
    public Map<String, Double> calculateCashFlow(Long userId, Instant startDate, Instant endDate) {
        List<Transaction> transactions = transactionRepository.findByUserId(userId);
        Map<String, Double> cashFlow = new HashMap<>();
        double opex = 0;  // Operational Expenses
        double capex = 0;  // Capital Expenses

        for (Transaction transaction : transactions) {
            if (transaction.getDate().isAfter(startDate) && transaction.getDate().isBefore(endDate)) {
                if (transaction.getCategory().equalsIgnoreCase("equipment") || transaction.getCategory().equalsIgnoreCase("land")) {
                    capex += transaction.getAmount();
                } else {
                    opex += transaction.getAmount();
                }
            }
        }

        cashFlow.put("OPEX", opex);
        cashFlow.put("CAPEX", capex);
        cashFlow.put("Net Cash Flow", opex - capex);
        return cashFlow;
    }

    // Example of tax calculation (basic example for agricultural income)
    public double calculateTax(Long userId) {
        double totalIncome = 0;
        List<Transaction> transactions = transactionRepository.findByUserId(userId);

        for (Transaction transaction : transactions) {
            if ("income".equalsIgnoreCase(transaction.getType())) {
                totalIncome += transaction.getAmount();
            }
        }

        // Simplified tax rate, for instance, 10% tax on income
        return totalIncome * 0.1;
    }
}
