import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;


/**
 * 


 * @author Shilpa Deshpande
 * 


 */
public class StringConcatenationPerformanceMeasurer {

    private final int iteration;
    private final String fileName;

    class Measurement{
        private long concat, plus, wrongBuilder, rightBuilder, stringBuffer;
    
        
        /**
         * @return The concat
         */
        public long getConcat() {
        
            return concat;
        }
    
        
        /**
         * @param concat The concat to set
         */
        public void setConcat(long concat) {
        
            this.concat = concat;
        }
    
        
        /**
         * @return The plus
         */
        public long getPlus() {
        
            return plus;
        }
    
        
        /**
         * @param plus The plus to set
         */
        public void setPlus(long plus) {
        
            this.plus = plus;
        }
    
        
        /**
         * @return The wrongBuilder
         */
        public long getWrongBuilder() {
        
            return wrongBuilder;
        }
    
        
        /**
         * @param wrongBuilder The wrongBuilder to set
         */
        public void setWrongBuilder(long wrongBuilder) {
        
            this.wrongBuilder = wrongBuilder;
        }
    
        
        /**
         * @return The rightBuilder
         */
        public long getRightBuilder() {
        
            return rightBuilder;
        }
    
        
        /**
         * @param rightBuilder The rightBuilder to set
         */
        public void setRightBuilder(long rightBuilder) {
        
            this.rightBuilder = rightBuilder;
        }
    
        
        /**
         * @return The stringBuffer
         */
        public long getStringBuffer() {
        
            return stringBuffer;
        }
    
        
        /**
         * @param stringBuffer The stringBuffer to set
         */
        public void setStringBuffer(long stringBuffer) {
        
            this.stringBuffer = stringBuffer;
        }
    }

    /**
     * @param fileName 
     * @param iteration 
     * 
     */
    public StringConcatenationPerformanceMeasurer(int iteration, String fileName) {
        this.iteration = iteration;
        this.fileName = fileName;

    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        
        int iteration = 10;
        String fileName = "StringConcatenationMeasurement.xls";
        try {
            System.out.println("Please enter number of iterations.");
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String readLine = in.readLine();
            iteration = Integer.valueOf(readLine);          
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        StringConcatenationPerformanceMeasurer instance = new StringConcatenationPerformanceMeasurer(
                iteration, fileName); 
        try {
            instance.checkStringConcatenationPerformance();
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @throws Exception 
     * 
     */
    public void checkStringConcatenationPerformance() throws Exception {
            
        ArrayList measurementList = new ArrayList();
        for (int k = 0; k < iteration; k++) {
            int limit = 5000;
            String[] array = new String[limit];
            for (int i = 0; i < limit; i++) {
                array[i] = "e" + i + " ";
            }
            // Native String Concatenation using concat method
            long t0 = System.currentTimeMillis();
            String sbS = "";
            for (int i = 0; i < limit; i++) {
                sbS = sbS.concat(array[i]);
            }
            long t1 = System.currentTimeMillis();
            //System.out.println("Final String using concat " + sbS);
            sbS = null;
            
            
            // Wrong String concatenation using StringBuilder
            StringBuilder sb = new StringBuilder();
            sbS = "";
            for (int i = 0; i < limit; i++) {
                sbS = (sb.append(sbS).append(array[i])).toString();
                sb = new StringBuilder();
            }
            long t2 = System.currentTimeMillis();
            //System.out.println("Final String - wrong usage of StringBuilder " + sbS);
            sbS = null;
            
            
            // Right String concatenation using StringBuilder
            sb = new StringBuilder();
            for (int i = 0; i < limit; i++) {
                sb.append(array[i]);
            }
            sbS = sb.toString();
            //System.out.println("Final String - right usage of StringBuilder " + sbS);
            long t3 = System.currentTimeMillis();
            sbS = null;
            
            
            // String concatenation using +
            sbS = "";
            for (int i = 0; i < limit; i++) {
                sbS = sbS + array[i];
            }
            long t4 = System.currentTimeMillis();
            //System.out.println("Final String using + " + sbS);
            sbS = null;
            
            
            // String concatenation using StringBuffer
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < limit; i++) {
                buffer.append(array[i]);
            }
            sbS = buffer.toString();
            //System.out.println("Final String using StringBuffer " + sbS);
            long t5 = System.currentTimeMillis();
            sbS = null;
            
            
            System.out.println("=================================");
            System.out.println("Native String Concatenation using concat method (Time in Milliseconds) " + (t1 - t0));
            System.out.println("Wrong String concatenation using StringBuilder (Time in Milliseconds) " + (t2 - t1));
            System.out.println("Right String concatenation using StringBuilder (Time in Milliseconds) " + (t3 - t2));
            System.out.println("String concatenation using + (Time in Milliseconds) " + (t4 - t3));
            System.out.println("String concatenation using StringBuffer (Time in Milliseconds) " + (t5 - t4));
            System.out.println("=================================");
            
            Measurement mm = new Measurement();
            mm.setConcat((t1-t0));
            mm.setWrongBuilder((t2-t1));
            mm.setRightBuilder((t3-t2));
            mm.setPlus((t4-t3));
            mm.setStringBuffer((t5-t4));
            measurementList.add(mm);
            
        }      
    }    
}
