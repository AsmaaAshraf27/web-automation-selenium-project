package data;

import com.github.javafaker.Faker;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelReader {
    private static final String FILE_PATH = System.getProperty("user.dir") + "/src/test/java/data/TestData.xlsx";
    private static final Faker faker = new Faker();

    public static List<String[]> getRegisterData() throws IOException {
        return readSheetWithFaker("RegisterUsersData");
    }

    public static List<String[]> getLoginData() throws IOException {
        return readSheet("LoginUsersData");
    }

    public static List<String[]> getSearchData() throws IOException {
        return readSheet("SearchData");
    }

    public static List<String[]> getContactUsData() throws IOException {
        return readSheetWithFaker("ContactUsData");
    }

    public static List<String[]> getCheckoutData() throws IOException {
        return readSheetWithFaker("CheckoutData");
    }
    public static List<String[]> getGuestCheckoutData() throws IOException {
        return readSheetWithFaker("GustCheckOutData");
    }

    public static List<String[]> getSearchWithAutoCompleteData() throws IOException {
        return readSheet("SearchWithAutoCompleteData");
    }
    public static List<String[]> getCompareProductData() throws IOException {
        return readSheet("CompareProductData");
    }
    public static List<String[]> getChangePasswordData() throws IOException {
        return readSheetWithFaker("ChangePasswordData");
    }
    public static List<String[]> getEmailFriendForRegisteredData() throws IOException {
        return readSheetWithFaker("EmailFriendForRegisteredData");
    }
    public static List<String[]> getEmailFriendForNonRegisteredData() throws IOException {
        return readSheetWithFaker("EmailFriendForNonRegisteredData");
    }
    public static List<String[]> getAddProductToShoppingCartData() throws IOException {
        return readSheet("AddProductToShoppingCartData");
    }
    public static List<String[]> getAddProductToWishingListData() throws IOException {
        return readSheet("AddProductToWishingListData");
    }
    public static List<String[]> getReviewData() throws IOException {
        return readSheetWithFaker("ReviewData");
    }




    private static List<String[]> readSheet(String sheetName) throws IOException {
        List<String[]> data = new ArrayList<>();
        FileInputStream file = new FileInputStream(new File(FILE_PATH));
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheet(sheetName);

        Iterator<Row> rows = sheet.iterator();
        if (!rows.hasNext()) return data; // Empty sheet

        rows.next(); // Skip header

        while (rows.hasNext()) {
            Row row = rows.next();
            int columns = row.getLastCellNum();
            String[] rowData = new String[columns];

            for (int i = 0; i < columns; i++) {
                Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                cell.setCellType(CellType.STRING);
                rowData[i] = cell.getStringCellValue().trim();
            }

            data.add(rowData);
        }

        workbook.close();
        file.close();
        return data;
    }

    private static List<String[]> readSheetWithFaker(String sheetName) throws IOException {
        List<String[]> data = new ArrayList<>();
        FileInputStream file = new FileInputStream(new File(FILE_PATH));
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheet(sheetName);
        // التحقق من وجود الورقة
        if (sheet == null) {
            workbook.close();
            file.close();
            throw new IllegalArgumentException("Sheet '" + sheetName + "' not found in Excel file");
        }

        Iterator<Row> rows = sheet.iterator();
        if (!rows.hasNext()) return data; // Empty sheet

        rows.next(); // Skip header

        while (rows.hasNext()) {
            Row row = rows.next();
            int columns = row.getLastCellNum();
            String[] rowData = new String[columns];

            for (int i = 0; i < columns; i++) {
                Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                cell.setCellType(CellType.STRING);
                String cellValue = cell.getStringCellValue().trim();

                if (cellValue.equalsIgnoreCase("faker")) {
                    rowData[i] = generateFakeData(sheetName,i);
                } else {
                    rowData[i] = cellValue;
                }
            }

            data.add(rowData);
        }

        workbook.close();
        file.close();
        return data;
    }

    private static String generateFakeData(String sheetName, int columnIndex) {
        return switch (sheetName) {

            case "RegisterUsersData" -> switch (columnIndex) {
                case 0 -> faker.name().firstName();
                case 1 -> faker.name().lastName();
                case 2 -> faker.internet().emailAddress();
                case 3 -> faker.number().digits(8); // password
                default -> "N/A";
            };

            case "ReviewData" -> switch (columnIndex) {
                case 0 -> faker.name().firstName();
                case 1 -> faker.name().lastName();
                case 2 -> faker.internet().emailAddress();
                case 3 -> faker.number().digits(8);
                case 4 -> "Black & White Diamond Heart";
                case 5 -> "Awesome Product!";
                case 6 -> faker.lorem().sentence(10); // review content
                default -> "N/A";
            };

            case "ContactUsData" -> switch (columnIndex) {
                case 0 -> faker.name().fullName();  // Name
                case 1 -> faker.internet().emailAddress(); // Email
                case 2 -> faker.lorem().paragraph(); // Message
                default -> "N/A";
            };

            case "CheckoutData" -> switch (columnIndex) {
                case 0 -> faker.name().firstName();
                case 1 -> faker.name().lastName();
                case 2 -> faker.internet().emailAddress();
                case 3 -> "Egypt";
                case 4 -> faker.address().city();
                case 5 -> faker.address().fullAddress();
                case 6 -> faker.address().zipCode();
                case 7 -> faker.phoneNumber().phoneNumber();
                case 8 -> "Black & White Diamond Heart";
                case 9 -> faker.number().digits(8);

                default -> "N/A";
            };
            case "GustCheckOutData" -> switch (columnIndex) {
                case 0 -> faker.name().firstName();
                case 1 -> faker.name().lastName();
                case 2 -> faker.internet().emailAddress();
                case 3 -> faker.address().city();
                case 4 -> faker.address().fullAddress();
                case 5 -> faker.address().zipCode();
                case 6 -> faker.phoneNumber().phoneNumber();

                default -> "N/A";
            };
            case "ChangePasswordData" -> switch (columnIndex) {
                case 0 -> faker.name().firstName();
                case 1 -> faker.name().lastName();
                case 2 -> faker.internet().emailAddress();
                case 3 -> faker.number().digits(8);
                case 4 -> faker.number().digits(8);


                default -> "N/A";
            };
            case "EmailFriendForRegisteredData" -> switch (columnIndex) {

                case 0 -> faker.name().firstName();
                case 1 -> faker.name().lastName();
                case 2 -> faker.internet().emailAddress();
                case 3 -> faker.number().digits(8);
                case 4 -> faker.internet().emailAddress();
                case 5 -> faker.lorem().sentence(10);
                default -> "N/A";

            };
            case "EmailFriendForNonRegisteredData" -> switch (columnIndex) {
                case 0 -> faker.internet().emailAddress();
                case 1 -> faker.internet().emailAddress();
                case 2 -> faker.lorem().sentence(10);
                default -> "N/A";


            };
            default -> switch (columnIndex) {
                default -> "N/A";
            };


        };

}}
