package factory;

import DAO.HibernateDAO;
import DAO.UserDAO;

public class HibernateDaoFactory implements DaoFactory {
    @Override
    public UserDAO getDao() {
        return HibernateDAO.getInstance();
    }
}
