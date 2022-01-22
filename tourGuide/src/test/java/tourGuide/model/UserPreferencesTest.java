package tourGuide.model;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.money.CurrencyUnit;
import javax.money.Monetary;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserPreferencesTest {

    @Test
    public void  shouldReturnToString(){
//       GIVEN
        int attractionProximity = Integer.MAX_VALUE;
        CurrencyUnit currency = Monetary.getCurrency("USD");
        Money lowerPricePoint = Money.of(0, currency);
        Money highPricePoint = Money.of(Integer.MAX_VALUE, currency);
        int tripDuration = 1;
        int ticketQuantity = 1;
        int numberOfAdults = 1;
        int numberOfChildren = 0;
//        WHEN
        UserPreferences up = new UserPreferences();
//        THEN

      assertEquals("UserPreferences{" +
                "attractionProximity=" + attractionProximity +
                ", currency=" + currency +
                ", lowerPricePoint=" + lowerPricePoint +
                ", highPricePoint=" + highPricePoint +
                ", tripDuration=" + tripDuration +
                ", ticketQuantity=" + ticketQuantity +
                ", numberOfAdults=" + numberOfAdults +
                ", numberOfChildren=" + numberOfChildren +
                '}', up.toString());
    }

}