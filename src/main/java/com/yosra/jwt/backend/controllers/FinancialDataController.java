package com.yosra.jwt.backend.controllers;

import com.yosra.jwt.backend.dtos.FinancialDataDto;
import com.yosra.jwt.backend.entites.FinancialData;
import com.yosra.jwt.backend.services.FinancialDataService;
//import com.yosra.jwt.backend.services.ModelArtService;
import com.yosra.jwt.backend.services.FlaskPredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/financial-data")
@CrossOrigin(origins = "http://localhost:4200") // Adjust the origin as per your Angular app's URL
public class FinancialDataController {


    @Autowired
    private FinancialDataService financialDataService;
    @Autowired
    private FlaskPredictionService flaskPredictionService;
//    @Autowired
//    private ModelArtService modelArtService;

//    @PostMapping("/create")
//    public ResponseEntity<String> createFinancialData(@RequestBody FinancialDataDto financialDataDto) {
//        // Save the financial data first
//        FinancialData savedFinancialData = financialDataService.createFinancialData(financialDataDto);
//
//        // Call the ModelArtService to get the model score using the financial data
//        String score;
//        try {
//            score = modelArtService.getModelScore(financialDataDto);  // Pass the DTO directly
//        } catch (Exception e) {
//            return ResponseEntity.status(500)
//                    .body("Financial Data Created, but an error occurred while fetching the score: " + e.getMessage());
//        }
//
//        // Return the response with both the accession number and the model score
//        return ResponseEntity.ok("Financial Data Created Successfully with Accession Number: "
//                + savedFinancialData.getAccessionNo()
//                + " and Model Score: " + score);
//    }
@PostMapping("/create")
public ResponseEntity<String> createFinancialData(@RequestBody FinancialDataDto financialDataDto) {
    // Save the financial data (including prediction)
    FinancialData savedFinancialData = financialDataService.createFinancialData(financialDataDto);
    savedFinancialData.setPrediction(flaskPredictionService.getPrediction(financialDataDto));


    // Return the response with the prediction already saved in the entity
    return ResponseEntity.ok("Financial Data Created Successfully with Accession Number: "
            + savedFinancialData.getAccessionNo() + ". Prediction: " + savedFinancialData.getPrediction());
}


    // Get financial data by accession number (GET /api/financial-data/{accessionNo})
    @GetMapping("/{accessionNo}")
    public ResponseEntity<FinancialDataDto> getFinancialData(@PathVariable String accessionNo) {
        return financialDataService.getFinancialData(accessionNo)
                .map(financialData -> ResponseEntity.ok(convertToDTO(financialData)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Get all financial data (GET /api/financial-data)
    @GetMapping("/all")
    public ResponseEntity<List<FinancialDataDto>> getAllFinancialData() {
        List<FinancialData> financialDataList = financialDataService.getAllFinancialData();
        List<FinancialDataDto> financialDataDtos = financialDataList.stream()
                .map(this::convertToDTO)
                .toList();
        return ResponseEntity.ok(financialDataDtos);
    }

    // Update financial data by accession number (PUT /api/financial-data/{accessionNo})
    @PutMapping("/update/{accessionNo}")
    public ResponseEntity<String> updateFinancialData(@PathVariable String accessionNo, @RequestBody FinancialDataDto financialDataDto) {
        FinancialData updatedFinancialData = financialDataService.updateFinancialData(accessionNo, financialDataDto);
        return ResponseEntity.ok("Financial Data Updated Successfully with Accession Number: " + updatedFinancialData.getAccessionNo());
    }

    // Delete financial data by accession number (DELETE /api/financial-data/{accessionNo})
    @DeleteMapping("/delete/{accessionNo}")
    public ResponseEntity<String> deleteFinancialData(@PathVariable String accessionNo) {
        financialDataService.deleteFinancialData(accessionNo);
        return ResponseEntity.ok("Financial Data Deleted Successfully with Accession Number: " + accessionNo);
    }

    // Utility to convert FinancialData entity to FinancialDataDto
    private FinancialDataDto convertToDTO(FinancialData financialData) {
        FinancialDataDto dto = new FinancialDataDto();
        dto.setAccessionNo(financialData.getAccessionNo());
        dto.setId(financialData.getUser().getId()); // Assuming you want to include CIK from User
        dto.setAssets(financialData.getAssets());
        dto.setFy(financialData.getFy());
        dto.setFp(financialData.getFp());
        dto.setForm(financialData.getForm());
        dto.setFiled(financialData.getFiled());
        dto.setCurrentAssets(financialData.getCurrentAssets());
        dto.setCurrentLiabilities(financialData.getCurrentLiabilities());
        dto.setStockholdersEquity(financialData.getStockholdersEquity());
        dto.setLiabilitiesAndStockholdersEquity(financialData.getLiabilitiesAndStockholdersEquity());
        dto.setEarningBeforeInterestAndTaxes(financialData.getEarningBeforeInterestAndTaxes());
        dto.setRetainedEarnings(financialData.getRetainedEarnings());
        dto.setRevenues(financialData.getRevenues());
        dto.setWorkingCapital(financialData.getWorkingCapital());
        dto.setLiabilities(financialData.getLiabilities());
        dto.setNetCashOperatingActivities(financialData.getNetCashOperatingActivities());
        dto.setNetCashInvestingActivities(financialData.getNetCashInvestingActivities());
        dto.setNetCashFinancingActivities(financialData.getNetCashFinancingActivities());
        dto.setCash(financialData.getCash());
        dto.setAccountsReceivable(financialData.getAccountsReceivable());
        dto.setInventory(financialData.getInventory());
        dto.setCurrentOtherAssets(financialData.getCurrentOtherAssets());
        dto.setNoncurrentAssets(financialData.getNoncurrentAssets());
        dto.setIntangibleAssets(financialData.getIntangibleAssets());
        dto.setAccountsPayable(financialData.getAccountsPayable());
        dto.setNetIncome(financialData.getNetIncome());
        dto.setGrossProfit(financialData.getGrossProfit());
        dto.setOperatingExpenses(financialData.getOperatingExpenses());
        dto.setNonoperatingIncome(financialData.getNonoperatingIncome());
        dto.setInterestExpense(financialData.getInterestExpense());
        dto.setShortTermDebt(financialData.getShortTermDebt());
        dto.setLongTermDebt(financialData.getLongTermDebt());
        dto.setNoncurrentLiabilities(financialData.getNoncurrentLiabilities());
        return dto;
    }
}
