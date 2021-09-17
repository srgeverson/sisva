package setup;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory FABRICA = construtorSessionFactory();

    private static SessionFactory construtorSessionFactory() {
        //criando objeto  configuração hibernate 
        Configuration configuracao = new Configuration();
        configuracao.configure("/setup/hibernate.cfg.xml");
        configuracao.addAnnotatedClass(model.Acesso.class);
        configuracao.addAnnotatedClass(model.Ajuda.class);
        configuracao.addAnnotatedClass(model.Authority.class);
        configuracao.addAnnotatedClass(model.Erro.class);
        configuracao.addAnnotatedClass(model.LogAcesso.class);
        configuracao.addAnnotatedClass(model.LogAjuda.class);
        configuracao.addAnnotatedClass(model.LogAuthority.class);
        configuracao.addAnnotatedClass(model.LogErro.class);
        configuracao.addAnnotatedClass(model.LogTeste.class);
        configuracao.addAnnotatedClass(model.LogUsers.class);
        configuracao.addAnnotatedClass(model.Teste.class);
        configuracao.addAnnotatedClass(model.Users.class);
        //criando um construtor de sessoes,
        StandardServiceRegistryBuilder construtor = new StandardServiceRegistryBuilder();
        //aplica as configurações do hibernate ao construtor de sessoes
        construtor.applySettings(configuracao.getProperties());
        //construir sessao e passa o contrutor de sessoes como parametro
        return configuracao.buildSessionFactory(construtor.build());
    }

    public static SessionFactory getFabrica() {
        return FABRICA;
    }

}
