package bean;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;
import dao.AlunoDAO;
import entidades.Aluno;
import util.MessageUtil;

@ManagedBean
@ViewScoped
public class AlunoBean {
	private Aluno aluno = new Aluno();
	private List<Aluno> alunos;
	
	
	

	/*public List<Aluno> getAlunos() {
		alunos = AlunoDAO.listar();
		return alunos;
	}*/
	
	/*public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}*/
	
	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	@PostConstruct
    public void init() {
        alunos = AlunoDAO.listar();
    }

	public String salvar() {
        try {
            // Salva o usuário no banco de dados
            AlunoDAO.salvar(aluno);
            FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Aluno cadastrado com sucesso!", null));
            aluno = new Aluno(); // Limpa o formulário após salvar
            return  null;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aluno já cadastrado !", null));
            e.printStackTrace();
            return null;  // Permanece na página de cadastro
        }
    }
       
	
        
    
    /*EXCLUIR*/

	public String excluir(Aluno aluno) {
		try {
			AlunoDAO.excluir(aluno);
			alunos.remove(aluno);
			MessageUtil.addInfoMsg("", "Aluno excluido com sucesso");
			alunos = AlunoDAO.listar();
		} catch (Exception e) {
			MessageUtil.addErrorMsg("Erro", "Erro ao excluir o Usuário");
		}
		return null;
	}

	

	public void mostrarDetalhes(Aluno aluno) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String dataNascimentoFormatada = sdf.format(aluno.getDataNascimento());
            // Exibir os detalhes do aluno selecionado
            String detalhes = String.format("ID: %d, Nome: %s, Data de Nascimento: %s, Matrícula: %s",aluno.getId(),aluno.getNome(),dataNascimentoFormatada,aluno.getMatricula());
            // Adicionar as informações na tela com p:messages
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Detalhes do Aluno", detalhes));
    }
	
	/*EDITAR*/
	public void onRowEdit(RowEditEvent<Aluno> event) {
	    try {
	        // Verifica se o aluno é null
	        Aluno aluno = event.getObject();
	        if (aluno == null) {
	            // Se o aluno for null, exibe uma mensagem de erro
	            MessageUtil.addErrorMsg("Erro", "Aluno não encontrado para edição.");
	            return; // Interrompe a execução caso o aluno seja null
	        }

	        // Chama o DAO para editar o aluno
	        AlunoDAO.editar(aluno);

	        // Exibe mensagem de sucesso
	        MessageUtil.addInfoMsg("Sucesso", "Aluno editado com sucesso!");

	        // Exibe o nome do aluno para verificação
	        System.out.println(aluno.getNome());
	    } catch (Exception e) {
	        // Caso ocorra algum erro, exibe uma mensagem de erro
	        MessageUtil.addErrorMsg("Erro", "Erro ao editar o aluno.");
	    }
	}




	/*----------------------------------------------*/
	
	/* EVENTOS DA PAGINA LISTAGEM */ 
	public void onRowCancel(RowEditEvent<Aluno> event) {
    	MessageUtil.addInfoMsg("Cancelado", "Edição cancelada");
    }



	
	

	
	
}
