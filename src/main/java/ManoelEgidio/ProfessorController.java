package ManoelEgidio;

import ManoelEgidio.Professor;
import ManoelEgidio.Escola;
import ManoelEgidio.ProfessorRepository;
import ManoelEgidio.EscolaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private EscolaRepositorio escolaRepository;

    @GetMapping("/{escolaId}")
    public List<Professor> getProfessoresByEscola(@PathVariable Long escolaId) {
        return professorRepository.findByEscolaId(escolaId);
    }

    @PostMapping("/{escolaId}")
    public Professor createProfessor(@PathVariable Long escolaId, @RequestBody Professor professor) {
        Optional<Escola> escolaOptional = escolaRepository.findById(escolaId);
        if (escolaOptional.isPresent()) {
            Escola escola = escolaOptional.get();
            professor.setEscola(escola);
            return professorRepository.save(professor);
        } else {
            throw new IllegalArgumentException("Escola com ID " + escolaId + " não encontrada.");
        }
    }

    @DeleteMapping("/{escolaId}/{id}")
    public void deleteProfessor(@PathVariable Long escolaId, @PathVariable Long id) {
        Optional<Professor> professorOptional = professorRepository.findById(id);
        if (professorOptional.isPresent()) {
            Professor professor = professorOptional.get();
            if (professor.getEscola() != null && professor.getEscola().getId().equals(escolaId)) {
                professorRepository.deleteById(id);
            } else {
                throw new IllegalArgumentException("Professor com ID " + id + " não pertence à escola com ID " + escolaId);
            }
        } else {
            throw new IllegalArgumentException("Professor com ID " + id + " não encontrado.");
        }
    }
}