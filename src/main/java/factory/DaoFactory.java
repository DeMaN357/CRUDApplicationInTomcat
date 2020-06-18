package factory;

import DAO.UserDAO;

public interface DaoFactory {
    UserDAO getDao();
}
