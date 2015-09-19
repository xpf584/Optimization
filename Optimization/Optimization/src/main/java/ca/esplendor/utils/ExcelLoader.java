package ca.esplendor.utils;

import ca.esplendor.optimization.Player;
import ca.esplendor.optimization.PlayerData;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

/**
 * Created by chenzheng on 15-09-11.
 */
public class ExcelLoader {
    public static String inputFile = "DFS Analysis Week2.xlsx";
    public static void  main (String[] args) throws Exception {
        ExcelLoader loader = new ExcelLoader();

        // load the csv into a map
        List<Map<String, String>> mapList = loader.readCsvIntoHashMap(inputFile,0);
        /*PlayerData.loadPlayerList(mapList);*/

    }

    public static List<Map<String, String>> readCsvIntoHashMap(String inputFileName, int workSheetId)  {
        List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
        List<String> titleList = new ArrayList<String>();

        try
        {
            InputStream file = ExcelLoader.class.getClassLoader().getResourceAsStream(inputFileName);
            //FileInputStream file = new FileInputStream(new File(inputFileName));

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(workSheetId);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            boolean bFirstLine = true;
            while (rowIterator.hasNext())
            {
                if(bFirstLine) { // get title
                    //For each row, iterate through all the columns
                    Row row = rowIterator.next();
                    titleList = parseRowToMap(row, rowIterator);
                    bFirstLine = false;
                    continue;
                }

                Row row = rowIterator.next();
                List<String> rowItemList = parseRowToMap(row, rowIterator);
                Map<String, String> map = new HashMap<String, String>();
                for(int j = 0; j < titleList.size(); j++) {
                    map.put(titleList.get(j), rowItemList.get(j));
                }
                if (map.get("Player") == null || ((String) map.get("Player")).isEmpty()) {
                    break;
                }
                resultList.add(map);


            }
            file.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return resultList;
    }

    private static List<String> parseRowToMap(Row row, Iterator<Row> rowIterator) {
        List<String> returnMap = new ArrayList<String>();

        //For each row, iterate through all the columns
        for(int cn=0; cn<row.getLastCellNum(); cn++) {
            // If the cell is missing from the file, generate a blank one
            // (Works by specifying a MissingCellPolicy)
            Cell cell = row.getCell(cn, Row.CREATE_NULL_AS_BLANK);
            // Print the cell for debugging
//            System.out.println("CELL: " + cn + " --> " + cell.toString());
            returnMap.add(cell.toString());
        }

        return returnMap;
    }

}
