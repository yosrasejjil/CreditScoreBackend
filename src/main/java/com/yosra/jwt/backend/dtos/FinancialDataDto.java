package com.yosra.jwt.backend.dtos;

import com.yosra.jwt.backend.entites.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public class FinancialDataDto {

        private String accessionNo;
        private Long id; // Reference to the User entity

        private Double assets;
        private Integer fy;
        private String fp;
        private String form;
        private LocalDate filed;

        private Double currentAssets;
        private Double currentLiabilities;
        private Double stockholdersEquity;
        private Double liabilitiesAndStockholdersEquity;
        private Double earningBeforeInterestAndTaxes;
        private Double retainedEarnings;
        private Double revenues;
        private Double workingCapital;
        private Double liabilities;
        private Double netCashOperatingActivities;
        private Double netCashInvestingActivities;
        private Double netCashFinancingActivities;
        private Double cash;
        private Double accountsReceivable;
        private Double inventory;
        private Double currentOtherAssets;
        private Double noncurrentAssets;
        private Double intangibleAssets;
        private Double accountsPayable;
        private Double netIncome;
        private Double grossProfit;
        private Double operatingExpenses;
        private Double nonoperatingIncome;
        private Double interestExpense;
        private Double shortTermDebt;
        private Double longTermDebt;
        private Double noncurrentLiabilities;


    }
