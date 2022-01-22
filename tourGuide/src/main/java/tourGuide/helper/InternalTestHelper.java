package tourGuide.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tourGuide.service.UserService;

public class InternalTestHelper {
    private Logger logger = LoggerFactory.getLogger(UserService.class);
    // Set this default up to 100,000 for testing
    private static int internalUserNumber = 100;

    public static void setInternalUserNumber(int internalUserNumber) {
        InternalTestHelper.internalUserNumber = internalUserNumber;
    }

    public static int getInternalUserNumber() {
        return internalUserNumber;
    }
    

}

