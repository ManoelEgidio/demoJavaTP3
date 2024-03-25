package ManoelEgidio;

import ManoelEgidio.Escola;
import ManoelEgidio.EscolaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/escolas")
public class EscolaController {

    @Autowired
    private EscolaRepositorio escolaRepository;

    @GetMapping
    public List<Escola> getEscolas() {
        return escolaRepository.findAll();
    }

    @PostMapping
    public Escola createEscola(@RequestBody Escola escola) {
        return escolaRepository.save(escola);
    }

    @DeleteMapping("/{id}")
    public void deleteEscola(@PathVariable Long id) {
        escolaRepository.deleteById(id);
    }
}