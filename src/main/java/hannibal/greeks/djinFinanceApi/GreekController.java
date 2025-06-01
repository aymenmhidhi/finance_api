package hannibal.greeks.djinFinanceApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/greeks")
public class GreekController {

    @Autowired
    private BlackScholesService service;

    @PostMapping
    public List<GreekResult> computeGreeks(@RequestBody List<OptionRequest> options) {
        return options.stream().map(service::calculate).collect(Collectors.toList());
    }
}