package com.example.BookStoreApi;
import com.example.BookStoreApi.CustomMetricsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/metrics")
public class MetricsController {

    private final CustomMetricsService metricsService;

    public MetricsController(CustomMetricsService metricsService) {
        this.metricsService = metricsService;
    }

    @PostMapping("/set-gauge")
    public void setGaugeValue(@RequestParam double value) {
        metricsService.setGaugeValue(value);
    }

    @GetMapping("/gauge")
    public double getGaugeValue() {
        return metricsService.getGaugeValue();
    }
}
