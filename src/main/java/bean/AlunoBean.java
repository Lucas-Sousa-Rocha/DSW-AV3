package bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dao.AlunoDAO;
import entidades.Aluno;

@ManagedBean
@SessionScoped
public class AlunoBean  {
    private Aluno aluno = new Aluno();
    private List<Aluno> alunos;
    private AlunoDAO alunoDAO = new AlunoDAO();

    public Aluno getAluno() {
        return aluno;
    }

    public List<Aluno> getAlunos() {
        if (alunos == null) {
            alunos = alunoDAO.listar();
        }
        return alunos;
    }

    public void salvar() {
        alunoDAO.salvar(aluno);
        aluno = new Aluno();
        alunos = alunoDAO.listar();
    }

    public void excluir(Integer id) {
        alunoDAO.excluir(id);
        alunos = alunoDAO.listar();
    }

    public void mostrarDetalhes(Aluno alunoSelecionado) {
        this.aluno = alunoSelecionado;
    }
}
