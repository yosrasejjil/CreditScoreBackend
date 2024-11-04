package com.yosra.jwt.backend.services;

import com.yosra.jwt.backend.dtos.FinancialDataDto;
import com.yosra.jwt.backend.entites.FinancialData;
import com.yosra.jwt.backend.entites.User;
import com.yosra.jwt.backend.repositories.FinancialDataRepository;
import com.yosra.jwt.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FinancialDataService {

    @Autowired
    private FinancialDataRepository financialDataRepository;

    @Autowired
    private UserRepository userRepository;


    // Create financial data with associated User and prediction
    public FinancialData createFinancialData(FinancialDataDto financialDataDto) {
        FinancialData financialData = new FinancialData();

        // Ensure that accessionNo is set from the DTO
        if (financialDataDto.getAccessionNo() == null || financialDataDto.getAccessionNo().isEmpty()) {
            throw new IllegalArgumentException("Accession number must be provided.");
        }
        financialData.setAccessionNo(financialDataDto.getAccessionNo());

        // Update other fields of FinancialData from the DTO
        updateEntityFromDTO(financialData, financialDataDto);

        // Retrieve the User entity by id
        User user = userRepository.findById(financialDataDto.getId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + financialDataDto.getId()));

        // Associate the User with FinancialData
        financialData.setUser(user);


        // Save and return the FinancialData entity with prediction
        return financialDataRepository.save(financialData);
    }
    // Retrieve financial data by accession number
    public Optional<FinancialData> getFinancialData(String accessionNo) {
        return financialDataRepository.findById(accessionNo);
    }

    // Retrieve all financial data entries
    public List<FinancialData> getAllFinancialData() {
        return financialDataRepository.findAll();
    }

    // Update financial data with associated User
    public FinancialData updateFinancialData(String accessionNo, FinancialDataDto financialDataDto) {
        return financialDataRepository.findById(accessionNo).map(financialData -> {
            updateEntityFromDTO(financialData, financialDataDto);

            // Retrieve the associated User by id
            User user = userRepository.findById(financialDataDto.getId())
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + financialDataDto.getId()));

            financialData.setUser(user);

            return financialDataRepository.save(financialData);
        }).orElseThrow(() -> new RuntimeException("FinancialData not found with accessionNo: " + accessionNo));
    }

    // Delete financial data by accession number
    public void deleteFinancialData(String accessionNo) {
        financialDataRepository.deleteById(accessionNo);
    }

    // Helper method to update a FinancialData entity from its DTO
    private void updateEntityFromDTO(FinancialData financialData, FinancialDataDto financialDataDto) {
        financialData.setAssets(financialDataDto.getAssets());
        financialData.setFy(financialDataDto.getFy());
        financialData.setFp(financialDataDto.getFp());
        financialData.setForm(financialDataDto.getForm());
        financialData.setFiled(financialDataDto.getFiled());
        financialData.setCurrentAssets(financialDataDto.getCurrentAssets());
        financialData.setCurrentLiabilities(financialDataDto.getCurrentLiabilities());
        financialData.setStockholdersEquity(financialDataDto.getStockholdersEquity());
        financialData.setLiabilitiesAndStockholdersEquity(financialDataDto.getLiabilitiesAndStockholdersEquity());
        financialData.setEarningBeforeInterestAndTaxes(financialDataDto.getEarningBeforeInterestAndTaxes());
        financialData.setRetainedEarnings(financialDataDto.getRetainedEarnings());
        financialData.setRevenues(financialDataDto.getRevenues());
        financialData.setWorkingCapital(financialDataDto.getWorkingCapital());
        financialData.setLiabilities(financialDataDto.getLiabilities());
        financialData.setNetCashOperatingActivities(financialDataDto.getNetCashOperatingActivities());
        financialData.setNetCashInvestingActivities(financialDataDto.getNetCashInvestingActivities());
        financialData.setNetCashFinancingActivities(financialDataDto.getNetCashFinancingActivities());
        financialData.setCash(financialDataDto.getCash());
        financialData.setAccountsReceivable(financialDataDto.getAccountsReceivable());
        financialData.setInventory(financialDataDto.getInventory());
        financialData.setCurrentOtherAssets(financialDataDto.getCurrentOtherAssets());
        financialData.setNoncurrentAssets(financialDataDto.getNoncurrentAssets());
        financialData.setIntangibleAssets(financialDataDto.getIntangibleAssets());
        financialData.setAccountsPayable(financialDataDto.getAccountsPayable());
        financialData.setNetIncome(financialDataDto.getNetIncome());
        financialData.setGrossProfit(financialDataDto.getGrossProfit());
        financialData.setOperatingExpenses(financialDataDto.getOperatingExpenses());
        financialData.setNonoperatingIncome(financialDataDto.getNonoperatingIncome());
        financialData.setInterestExpense(financialDataDto.getInterestExpense());
        financialData.setShortTermDebt(financialDataDto.getShortTermDebt());
        financialData.setLongTermDebt(financialDataDto.getLongTermDebt());
        financialData.setNoncurrentLiabilities(financialDataDto.getNoncurrentLiabilities());
        financialData.setPrediction(financialDataDto.getPrediction()); // Set the prediction

    }
}
