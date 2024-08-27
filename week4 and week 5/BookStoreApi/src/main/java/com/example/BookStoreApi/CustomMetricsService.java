package com.example.BookStoreApi;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Gauge;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
public class CustomMetricsService {

    private final MeterRegistry meterRegistry;


    private double gaugeValue = 0.0;

    public CustomMetricsService(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;

        // Registering a default gauge
        Gauge.builder("custom_gauge", this, CustomMetricsService::getGaugeValue)
                .description("A custom gauge metric")
                .register(meterRegistry);
    }

    public double getGaugeValue() {
        return gaugeValue;
    }

    public void setGaugeValue(double gaugeValue) {
        this.gaugeValue = gaugeValue;
    }
}
