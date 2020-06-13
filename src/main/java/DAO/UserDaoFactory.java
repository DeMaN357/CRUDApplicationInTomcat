package DAO;

import util.DBHelper;

import java.io.IOException;
import java.util.Properties;

public class UserDaoFactory {

    public static UserDAO getFactory(){
        try {
            Properties properties = new Properties();
            properties.load(UserDaoFactory.class.getClassLoader().getResourceAsStream("selectImplementation.properties"));
            String daotype = properties.getProperty("daotype");
            if(daotype.equals("jdbc")){
                return new UserJdbcDAO(DBHelper.getInstance().getMySqlConnection());
            }else if(daotype.equals("hibernate")){
                return new HibernateDAO(DBHelper.getInstance().getMySqlConfiguration().buildSessionFactory());
            }else {
                return new UserJdbcDAO(DBHelper.getInstance().getMySqlConnection());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
