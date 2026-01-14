import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

public class JSONPathTester {

    private static final StringBuffer _JSON = new StringBuffer();
    static {
        _JSON.append( "{" );
        _JSON.append( "     \"firstName\": \"John\"," );
        _JSON.append( "     \"lastName\": \"doe\"," );
        _JSON.append( "     \"age\": 26," );
        _JSON.append( "     \"address\": {" );
        _JSON.append( "          \"streetAddress\": \"naist street\"," );
        _JSON.append( "          \"city\": \"Nara\"," );
        _JSON.append( "          \"postalCode\": \"630-0192\"" );
        _JSON.append( "     }," );
        _JSON.append( "     \"phoneNumbers\": [" );
        _JSON.append( "          {" );
        _JSON.append( "               \"type\": \"iPhone\"," );
        _JSON.append( "               \"number\": \"0123-4567-8888\"" );
        _JSON.append( "          }," );
        _JSON.append( "          {" );
        _JSON.append( "               \"type\": \"home\"," );
        _JSON.append( "               \"number\": \"0123-4567-8910\"" );
        _JSON.append( "          }" );
        _JSON.append( "     ]" );
        _JSON.append( "}" );
    }

    public static void main( String[] args ) {
        
        long startTime = System.currentTimeMillis();
        final DocumentContext json = JsonPath.parse( _JSON.toString() );
        System.out.println( "PARSE in " + (System.currentTimeMillis() - startTime) + " ms" );

        startTime = System.currentTimeMillis();
        System.out.println( "firstName = " + json.read( "firstName", String.class )  + " in " + (System.currentTimeMillis() - startTime) + " ms" );
        
        startTime = System.currentTimeMillis();
        System.out.println( "address.city = " + json.read( "address.city", String.class )  + " in " + (System.currentTimeMillis() - startTime) + " ms" );

        startTime = System.currentTimeMillis();
        Object home = json.read( "phoneNumbers[?(@.type==\"home\")]" );
        System.out.println( "phoneNumbers['home'] = " + home + " - Class: " + home.getClass().getName()  + " in " + (System.currentTimeMillis() - startTime) + " ms" );
        
        startTime = System.currentTimeMillis();
        System.out.println( "phoneNumbers[1].number = " + json.read( "phoneNumbers[1].number", String.class )  + " in " + (System.currentTimeMillis() - startTime) + " ms" );
        
        startTime = System.currentTimeMillis();
        System.out.println( "phoneNumbers['iPhone'].number = " + json.read( "phoneNumbers[?(@.type==\"iPhone\")].number" )  + " in " + (System.currentTimeMillis() - startTime) + " ms" );
    }
}
