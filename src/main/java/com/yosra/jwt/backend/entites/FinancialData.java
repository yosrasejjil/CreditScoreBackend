package com.yosra.jwt.backend.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Financial_Data")

public class FinancialData implements Serializable {


    @Id
    @Column(name = "accession_no", nullable = false, unique = true)
    private String accessionNo;

    private Integer fy;
    private String fp;
    private String form;
    private LocalDate filed;

    private Double assets;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", nullable = false)
    private User user;
}