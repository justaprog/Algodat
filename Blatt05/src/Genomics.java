

public class Genomics {

    //TODO
    
    /**
     * @param strang the String that is being sequenced (e.g. CAGTCCAGTCAGTC)
     * @param dictionary a dictionary of words (e.g. {AGT, CA, CAG, GTC, TC, TCA, TCC})
     * @return number of possible ways to compose strang using words from the dictionary
     */
    public static long optBottomUp(String strang, String[] dictionary)
    {
        // TODO
        long[] array = new long[strang.length()];
        int s_len = strang.length();
        if(s_len == 0){
            return 1;
        }
        for (int i = s_len-1; i>=0; i--){
            for (int j= 0; j<dictionary.length; j++){
                if(strang.startsWith(dictionary[j],i) && dictionary[j].length() == s_len - i){
                    array[i] = 1 +array[i];
                }else if(strang.startsWith(dictionary[j],i)){
                    array[i] += array[i+ dictionary[j].length()];
                }
            }
        }
        return array[0];
    }

    


    public static void main(String[] args)
    {
        String strang = "CAGTCCAGTCAGAGT";
        String[] wbuch = {"AGT", "CA", "CAG", "GTC", "TC", "TCA", "TCC"};
        long result = optBottomUp(strang, wbuch);
        System.out.println(result);
    }
}

