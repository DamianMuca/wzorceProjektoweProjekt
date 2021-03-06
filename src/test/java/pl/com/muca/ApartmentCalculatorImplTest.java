package pl.com.muca;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.function.Function;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pl.com.muca.apartment.Apartment;
import pl.com.muca.calculator.ApartmentCalculator;
import pl.com.muca.calculator.ApartmentCalculatorImpl;

public class ApartmentCalculatorImplTest {
  private final static double DELTA_ACCURACY = 10E-3;
  private Function<Apartment, @Nullable ApartmentCalculator> calculator;

  @BeforeClass
  public void setUp(){
    calculator = ApartmentCalculatorImpl::from;
  }

  @Test
  public void calculateFloorArea_firstHouse_shouldReturnCorrectArea()
      throws FileNotFoundException, ParseException {
    File houseFile = new File("src/test/resources/house_1.txt");
    Apartment apartment = Apartment.from(houseFile);
    ApartmentCalculator apartmentCalculator = calculator.apply(apartment);

    double apartmentFloorArea = apartmentCalculator.calculateFloorArea();

    assertEquals(apartmentFloorArea, 79, DELTA_ACCURACY);
  }

  @Test
  public void calculateFloorArea_secondHouse_shouldReturnCorrectArea()
      throws FileNotFoundException, ParseException {
    File houseFile = new File("src/test/resources/house_2.txt");
    Apartment apartment = Apartment.from(houseFile);
    ApartmentCalculator apartmentCalculator = calculator.apply(apartment);

    double apartmentFloorArea = apartmentCalculator.calculateFloorArea();

    assertEquals(apartmentFloorArea, 137, DELTA_ACCURACY);
  }

  @Test
  public void calculateWallSurfaceArea_firstHouse_shouldReturnCorrectArea()
      throws FileNotFoundException, ParseException {
    File houseFile = new File("src/test/resources/house_1.txt");
    Apartment apartment = Apartment.from(houseFile);
    ApartmentCalculator apartmentCalculator = calculator.apply(apartment);

    double wallSurfaceArea = apartmentCalculator.calculateWallSurfaceArea();

    assertEquals(wallSurfaceArea, 170, DELTA_ACCURACY);
  }

  @Test
  public void calculateWallSurfaceArea_secondHouse_shouldReturnCorrectArea()
      throws FileNotFoundException, ParseException {
    File houseFile = new File("src/test/resources/house_2.txt");
    Apartment apartment = Apartment.from(houseFile);
    ApartmentCalculator apartmentCalculator = calculator.apply(apartment);

    double wallSurfaceArea = apartmentCalculator.calculateWallSurfaceArea();

    assertEquals(wallSurfaceArea, 275, DELTA_ACCURACY);
  }

  @Test(expectedExceptions = ParseException.class)
  public void createApartment_fromBrokenFormatFile_shouldThrowException()
      throws FileNotFoundException, ParseException {
    File houseFile = new File("src/test/resources/file_broken_format.txt");
    Apartment apartment = Apartment.from(houseFile);
  }

  @Test(expectedExceptions = FileNotFoundException.class)
  public void createApartment_fromNonExistingFile_shouldThrowException()
      throws FileNotFoundException, ParseException {
    File houseFile = new File("non_existing_file");

    Apartment.from(houseFile);
  }
}
