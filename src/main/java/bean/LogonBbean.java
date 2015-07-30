package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;

import security.SessionContext;
import util.FacesUtils;
import dao.UsuarioDAO;
import entity.Usuario;

@ManagedBean
@SessionScoped
public class LogonBbean {
	

	   
    private static final long serialVersionUID = 1L;
 
    private static Logger logger = Logger.getLogger(LogonBbean.class);
 
    private String email;
    private String login;
    private String senha;
    
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
 
    /**
     * Retorna usuario logado
     * */
    public Usuario getUser() {
       return (Usuario) SessionContext.getInstance().getUsuarioLogado();
    }
 
    public String doLogin() {
       try {
           logger.info("Tentando logar com usuário " + login);
           System.out.println("etntando logar");
           Usuario usuario = usuarioDAO.isUsuarioValido(login, senha);
           
           if (usuario == null) {
        	   System.out.println("nao achou");
             FacesUtils.addErrorMessage("Login ou Senha errado, tente novamente !");
             return "";
           }
           System.out.println("achou usuarior");
           
           logger.info("Login efetuado com sucesso");
           FacesUtils.addInfoMessage("Login efetuado com sucesso!");
           SessionContext.getInstance().setAttribute("usuarioLogado", usuario);
           FacesUtils.sucesso();
           return "index";
       } catch (Exception e) {
    	   System.out.println("erro: " + e.getMessage());
    	   FacesUtils.erro();
    	   FacesUtils.addErrorMessage("Erro ao tentar logar: " + e.getMessage());
           return "";
       }
 
    }
 
    public String doLogout() {
       logger.info("Fazendo logout com usuário "
               + SessionContext.getInstance().getUsuarioLogado().getNome());
       SessionContext.getInstance().encerrarSessao();
       return "login";
    }
 
    public void solicitarNovaSenha() {
//       try {
//           getUserBO().gerarNovaSenha(login, email);
//           addInfoMessage("Nova Senha enviada para o email " + email);
//       } catch (BOException e) {
//           addErrorMessage(e.getMessage());
//           FacesContext.getCurrentInstance().validationFailed();
//       }
    }
 
    public String getLogin() {
       return login;
    }
 
    public void setLogin(String login) {
       this.login = login;
    }
 
    public String getSenha() {
       return senha;
    }
 
    public void setSenha(String senha) {
       this.senha = senha;
    }
 
    public String getEmail() {
       return email;
    }
 
    public void setEmail(String email) {
       this.email = email;
    }
 
	
}
