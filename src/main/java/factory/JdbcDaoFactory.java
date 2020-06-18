package factory;

import DAO.UserDAO;
import DAO.UserJdbcDAO;

public class JdbcDaoFactory implements DaoFactory {
    @Override
    public UserDAO getDao() {
        return UserJdbcDAO.getInstance();
    }
}
