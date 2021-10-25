import com.pharmacy.traning.exception.DaoException;
import com.pharmacy.traning.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class m {

    private static final Logger logger = LogManager.getLogger();
    public static void main(String[] args) {
        try {
            try {
                throw new RuntimeException("111111vgh");
            } catch (RuntimeException e) {
                throw new DaoException("sdfvgb", e);
            }
        } catch (DaoException e) {
            logger.error("qqqqqqq", e);
        }
    }
}
