package com.intervale.statistics.util;

import com.intervale.statistics.dto.NationalRateDto;
import com.intervale.statistics.dto.RateDto;
import com.intervale.statistics.model.entity.RateEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class Calculations {

    public Map<String, BigDecimal> getStringBigDecimalMapForNR(BigDecimal priceByTitle, List<NationalRateDto> saleRate) {
        return saleRate.stream()
                .collect(Collectors.toMap(NationalRateDto::getIso, i -> priceByTitle
                        .divide(i.getRate()
                                .divide(BigDecimal.valueOf(i.getQuantity()), 4, RoundingMode.HALF_UP), 4, RoundingMode.HALF_UP)));
    }

    public Map<String, BigDecimal> getStringBigDecimalMapForR(BigDecimal priceByTitle, List<RateEntity> saleRate) {

        return saleRate.stream()
                .collect(Collectors.toMap(RateEntity::getSellIso, price -> priceByTitle
                        .divide(price.getBuyRate()
                                .divide(BigDecimal.valueOf(price.getQuantity()), 4, RoundingMode.HALF_UP), 4, RoundingMode.HALF_UP)));
    }
}
