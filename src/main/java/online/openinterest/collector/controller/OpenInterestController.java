package online.openinterest.collector.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import online.openinterest.collector.service.MarketsService;

@Controller
public class OpenInterestController {

    @Autowired
    private MarketsService service;

    /**
     * @param model
     * @return
     */
    @GetMapping("/openinterestpage")
    public String getOpenInterestPage(Model model) {
        model.addAttribute("openinterest", service.getOpenInterest());
        return "openinterest";
    }

    /**
     * @return
     */
    @GetMapping("/openinterest")
    public ResponseEntity<Integer> getOpenInterest() {
        return ResponseEntity.ok().body(service.getOpenInterest());
    }

}
