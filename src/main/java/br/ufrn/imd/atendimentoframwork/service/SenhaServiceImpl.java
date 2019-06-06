package br.ufrn.imd.atendimentoframwork.service;

import br.ufrn.imd.atendimentoframwork.model.Pessoa;
import br.ufrn.imd.atendimentoframwork.model.Senha;
import br.ufrn.imd.atendimentoframwork.repository.SenhaRepository;
import br.ufrn.imd.atendimentoframwork.service.interfaces.GuicheService;
import br.ufrn.imd.atendimentoframwork.service.interfaces.PessoaService;
import br.ufrn.imd.atendimentoframwork.service.interfaces.SenhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SenhaServiceImpl implements SenhaService {
    private final SenhaRepository senhaRepository;
    private final PessoaService pessoaService;
    private final GuicheService guicheService;

    @Autowired
    public SenhaServiceImpl(SenhaRepository senhaRepository, PessoaService pessoaService, @Qualifier("cartorioGuicheService") GuicheService guicheService) {
        this.senhaRepository = senhaRepository;
        this.pessoaService = pessoaService;
        this.guicheService = guicheService;
    }

    @Override
    public Senha save(Senha senha) {
        Pessoa pessoa = pessoaService.findByCpf(senha.getPessoa().getCpf());
        if(pessoa != null) {
            senha.setPessoa(pessoa);
        } else {
            pessoaService.save(senha.getPessoa());
        }
        senha.setGuiche(guicheService.getMelhorGuiche(senha.getTipoServico()));
        senha.setHorarioChegada(LocalDateTime.now());
        return senhaRepository.save(senha);
    }

    @Override
    public void updateAll(List<Senha> senhas) {
        senhaRepository.saveAll(senhas);
    }
}
