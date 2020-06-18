package factory;

import DAO.UserDAO;
import java.io.IOException;
import java.util.Properties;

public class UserDaoFactory {
    public UserDAO getDao() {
        try {
            Properties properties = new Properties();
            properties.load(UserDaoFactory.class.getClassLoader().getResourceAsStream("selectImplementation.properties"));
            String daotype = properties.getProperty("daotype");
            if (daotype.equals("hibernate")) {
                return new HibernateDaoFactory().getDao();
            } else {
                return new JdbcDaoFactory().getDao();   // "jdbc"
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
